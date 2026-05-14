package it.unibo.javacrush.controller.impl;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

import it.unibo.javacrush.common.EventType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.controller.api.Event;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.powerup.api.PowerUpManager;

/**
 * Implementation of the {@link GameController} interface.
 */
public class GameControllerImpl implements GameController{

    // VARIABLE FOR THE VIEW (CURRENT MISSING)
    private final AppController appController;
    private final Board board;
    private final PowerUpManager powerUpManager;

    private final Map<EventType, Function<Event, Command>> commands = new EnumMap<>(EventType.class);

    private GameState gameState;

    public GameControllerImpl(
        final AppController appController,
        final Board board,
        final PowerUpManager powerUpManager,
        final GameState gameState) {

        this.appController = appController;
        this.board = board;
        this.powerUpManager = powerUpManager;
        this.gameState = gameState;
        this.setUpCommands();
    }

    /**
     * Set up the mapping between events and commands.
     */
    private void setUpCommands() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUpCommands'");
    }

    @Override
    public void notifyEvent(final Event event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyEvent'");
    }

    @Override
    public GameState updateGameState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGameState'");
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
    public void gameLoop(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gameLoop'");
    }

    /**
     * Quit the level and go back to the main menu.
     */
    public void quitLevel(){
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quitLevel'");
    }

}
