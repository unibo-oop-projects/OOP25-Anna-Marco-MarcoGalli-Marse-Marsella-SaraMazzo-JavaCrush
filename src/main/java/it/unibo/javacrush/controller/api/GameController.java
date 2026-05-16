package it.unibo.javacrush.controller.api;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.common.Position;

/**
 * Interface representing the game controller, 
 * it's responsable for the single levels.
*/
public interface GameController {

    /**
     * Handle the click on the board, it will select the cell and then deselect 
     * it if it's already selected.
     *
     * @param p the position of the cell clicked
     * @return true if the cells are swapped, false otherwise
     */
    boolean hit(Position pos);

    /**
     * Start the game loop, it will be called when the level is loaded and will
     * handle the game logic until the level is completed or the player quits.
     */
    void gameLoop();

    /**
     * Update the game state.
     *
     * @return the updated game state
     */
    GameState updateGameState();

    /**
     * Gett the CellType for a specific cell in the board.
     * 
     * @param pos position of the cell
     * @return type of the cell
     */
    CellType getCellTypeAtPos(Position pos);

    /**
     * Get the remaining moves.
     * 
     * @return the numeber of remaining moves
     */
    int getMovesLeft();

    /**
     * Quit the level and go back to the main menu.
     */
    void quitLevel();

}
