package it.unibo.javacrush.controller.impl;

import java.util.Map;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.api.MoveEngine;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.MatchManagerImpl;
import it.unibo.javacrush.model.impl.MoveEngineImpl;
import it.unibo.javacrush.model.impl.PhysicsHandlerImpl;
import it.unibo.javacrush.model.impl.StallEngineImpl;

/**
 * Implementation of the {@link GameController} interface.
 */
public class GameControllerImpl implements GameController{

    private final int rows;
    private final int cols;
    private final int moves;
    private final Map<CellType, Integer> goals;
    private final GravityEngine gravity;
    private final Board board;
    private final PhysicsHandler physics;
    private final StallEngine stallEngine;
    //private final Session session;
    private final MoveEngine moveEngine;
    private final MatchManager matchManager;
    

    public GameControllerImpl(final LevelConfig config) {
        this.rows = config.rows();
        this.cols = config.cols();
        this.moves = config.moves();
        this.goals = config.goals();
        this.gravity = config.gravity();

        // Create Board
        this.board = new BoardImpl(rows, cols);

        //this.session = new SessionImpl

        this.matchManager = new MatchManagerImpl();

        this.moveEngine = new MoveEngineImpl();

        this.stallEngine = new StallEngineImpl();

        this.physics = new PhysicsHandlerImpl(this.gravity, this.stallEngine);
    }

    /**
     * Handle the click on the board, it will select the cell and then deselect it if it's already selected.
     *
     * @param p the position of the cell clicked
     *
     * @return true if we can select the cell, false otherwise
     */
    public boolean hit(final Position pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'hit'");
    }


    /**
     * Swap two cells.
     *
     * @param p1 the first selected cell
     * @param p2 the second selected cell
     */
    public void swap(final Position p1, final Position p2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swap'");
    }


    /**
     * Select a power-up and activate it.
     *
     * @param powerUpNumber the number of the power-up selected
     */
    void selectPowerUp(final int powerUpNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectPowerUp'");
    }


    /**
     * Start the game loop, it will be called when the level is loaded and will
     * handle the game logic until the level is completed or the player quits.
     */
    public void gameLoop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gameLoop'");
    }


    /**
     * Quit the level and go back to the main menu.
     */
    public void quitLevel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quitLevel'");
    }

}
