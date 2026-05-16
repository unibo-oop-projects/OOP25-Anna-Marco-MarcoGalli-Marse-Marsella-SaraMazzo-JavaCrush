package it.unibo.javacrush.controller.api;

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
    boolean hit(Position pos);

    /**
     * Swap two cells.
     *
     * @param p1 the first selected cell
     * @param p2 the second selected cell
     */
    void swap(Position p1, Position p2);

    /**
     * Start the game loop, it will be called when the level is loaded and will
     * handle the game logic until the level is completed or the player quits.
     */
    void gameLoop();

    /**
     * Quit the level and go back to the main menu.
     */
    void quitLevel();

}
