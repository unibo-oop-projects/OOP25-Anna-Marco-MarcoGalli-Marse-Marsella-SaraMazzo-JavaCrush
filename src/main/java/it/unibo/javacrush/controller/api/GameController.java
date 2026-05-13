package it.unibo.javacrush.controller.api;

import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.common.Position;

/**
 * Interface representing the game controller, 
 * it's responsable for the single levels.
*/
public interface GameController {

    /**
     * Handle the click on the board, it will select the cell and then deselect it if it's already selected.
     * 
     * @param p the position of the cell clicked
     * 
     * @return true if we can select the cell, false otherwise
     */
    boolean hit(Position p);
    
    /**
     * Make a move by swapping two cells.
     * 
     * @param p1 the first selected cell
     * @param p2 the second selected cell
     */
    void makeMove(Position p1, Position p2);

    /**
     * Start the game loop, it will be called when the level is loaded and will 
     * handle the game logic until the level is completed or the player quits.
     */
    void gameLoop();

    /**
     * Select a power up to use in the current level.
     * 
     * @param powerUpNumber the number of the power up to select
     */
    void selectPowerUp(int powerUpNumber);

    /**
     * Update the state of the game based on the current board and the player's actions.
     * 
     * @return the state of the game
     */
    GameState updateGameState();

    /**
     * Quit the level and go back to the main menu.
     */
    void quitLevel();
}
