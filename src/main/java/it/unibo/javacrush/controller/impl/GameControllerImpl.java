package it.unibo.javacrush.controller.impl;

import java.util.Map;
import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GameMatchContext;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.api.MoveEngine;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.api.Session;
import it.unibo.javacrush.model.api.StallEngine;

/**
 * Implementation of the {@link GameController} interface.
 */
public class GameControllerImpl implements GameController{

    // TO BE CHANGED BASED ON GAMEMATCHCONTEXT
    //private final int rows;
    //private final int cols;
    //private final int moves;
    private final Map<CellType, Integer> goals;
    private final GravityEngine gravity;
    private final Board board;
    private final PhysicsHandler physics;
    private final StallEngine stallEngine;
    private final Session session;
    private final MoveEngine moveEngine;
    private final MatchManager matchManager;

    private Position lastClickedPosition = null;
    private Set<Match> matches = Set.of();
    

    public GameControllerImpl(final GameMatchContext gameContext) {
        //this.rows = gameContext.getLevelConfig().rows();
        //this.cols = gameContext.getLevelConfig().cols();
        this.board = gameContext.getBoard();
        //this.moves = gameContext.getLevelConfig().moves();
        this.goals = gameContext.getLevelConfig().goals();
        this.session = gameContext.getSession();
        this.gravity = gameContext.getLevelConfig().gravity();
        this.physics = gameContext.getPhysicsHandler();
        this.stallEngine = gameContext.getStallEngine();
        this.moveEngine = gameContext.getMoveEngine();
        this.matchManager = gameContext.getMatchManager();
    }


    @Override
    public boolean hit(final Position pos) {
        if (this.lastClickedPosition.equals(null)) {
            // If the cell is null it can't be swapped
            this.lastClickedPosition = pos;
            return false;
        } else {
            boolean canSwap = this.swap(this.lastClickedPosition, pos);
            this.lastClickedPosition = null;
            if (canSwap) {
                this.session.decreaseMoves();
                this.matches = this.matchManager.findAllMatches(this.board);
            }
            return canSwap;
        }
    }


    @Override
    public void gameLoop() {
        // Update and remove matches from the board
        this.matches.stream()
            .forEach(match -> {
                this.session.updateGoals(match.getType(), match.getSize());
                this.matchManager.removeMatch(this.board, match);
        });
        this.matches = null;

        // Update view

        // VA STACCATO
        // Apply gravity -> ciclata e per ogni ciclo update view

        // Check if the game is stalled, if so apply stall engine

        // Update view
    }


    @Override
    public GameState updateGameState() {
        return this.session.getGameStatus();
    }


    @Override
    public CellType getCellTypeAtPos(final Position pos) {
        return this.board.getCellAt(pos).get().getType();
    }

    @Override
    public int getMovesLeft() {
        return this.session.getMovesLeft();
    }

    @Override
    public void quitLevel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quitLevel'");
    }

    /**
     * Swap two cells.
     *
     * @param p1 the first selected cell
     * @param p2 the second selected cell
     * @return true if the cells are swapped, false otherwise
     */
    private boolean swap(final Position p1, final Position p2) {
        if (moveEngine.canSwap(this.board, p1, p2)) {
            this.board.swapCells(p1, p2);
            this.session.decreaseMoves();
            return true;
        }
        return false;
    }

}
