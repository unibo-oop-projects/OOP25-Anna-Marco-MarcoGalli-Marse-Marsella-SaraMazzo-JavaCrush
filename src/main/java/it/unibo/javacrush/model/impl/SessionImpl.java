package it.unibo.javacrush.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.api.Goal;
import it.unibo.javacrush.model.api.GoalFactory;
import it.unibo.javacrush.model.api.Session;

/** 
 * Implementation of the {@link Session} interface.
*/
public class SessionImpl implements Session{

    private static final int GOAL_TARGET = 10;
    private static final int NUMBER_GOALS = 2;

    private int movesLeft;
    private final List<Goal> goals = new ArrayList<>();

    public SessionImpl(final int moves, final Map<Position, Optional<Cell>> cells, final GoalFactory factory) {
        this.movesLeft = moves;

        // Find the available types in the board
        List<CellType> availableType = cells.values().stream()
            .filter(Optional::isPresent)
            .map(Optional::get)
            .map(elem -> elem.getType())
            .distinct()
            .collect(Collectors.toList());

        Collections.shuffle(availableType);

        availableType.stream().limit(NUMBER_GOALS).forEach(type -> {
            this.goals.add(factory.createGoal(type, GOAL_TARGET));
        });
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
        if (this.goals.stream().allMatch(Goal::isReached)) {
            return GameState.WON;
        }
        return this.movesLeft == 0 ? GameState.LOST : GameState.PLAYING;
    }

}
