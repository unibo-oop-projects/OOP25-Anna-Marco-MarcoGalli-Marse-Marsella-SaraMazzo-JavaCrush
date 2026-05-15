package it.unibo.javacrush.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.model.api.Goal;
import it.unibo.javacrush.model.api.GoalFactory;
import it.unibo.javacrush.model.api.Session;

/** 
 * Implementation of the {@link Session} interface.
*/
public class SessionImpl implements Session{

    private int movesLeft;
    private final List<Goal> goals = new ArrayList<>();

    public SessionImpl(final int moves, final Map<CellType, Integer> goalsMap, final GoalFactory factory) {
        this.movesLeft = moves;

        goalsMap.entrySet().stream()
            .map(entry -> factory.createGoal(entry.getKey(), entry.getValue()))
            .forEach(goals::add);
    }

    @Override
    public int getMovesLeft() {
        return this.movesLeft;
    }

    @Override
    public void decreaseMoves() {
        if(this.movesLeft == 0) {
            throw new IllegalStateException("We cannot decrease moves when already at 0");
        }
        this.movesLeft--;
    }

    @Override
    public List<Goal> getGoals() {
        return Collections.unmodifiableList(goals);
    }

    @Override
    public void updateGoals(CellType type, int count) {
        this.goals.forEach(goal -> {
            if (goal.getTargetType() == type)
                goal.addProgress(count);
        });
    }

    @Override
    public GameState getGameStatus() {
        if (this.isGameWon()) {
            return GameState.WON;
        } else if (this.isGameLost()) {
            return GameState.LOST;
        }
        return GameState.PLAYING;
    }

    /**
     * Check if the game is won by verifying if all goals are reached.
     * 
     * @return true if all goals are reached, false otherwise
     */
    private boolean isGameWon() {
        return this.goals.stream().allMatch(Goal::isReached);
    }

    /**
     * Check if the game is lost by verifying if the player has no moves left.
     * 
     * @return true if the player has no moves left, false otherwise
     */
    private boolean isGameLost() {
        return this.movesLeft <= 0;
    }

}
