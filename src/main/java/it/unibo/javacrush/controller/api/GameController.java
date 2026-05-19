package it.unibo.javacrush.controller.api;

import java.util.Map;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.common.Position;

/**
 * Interface representing the controller of a game level, responsible for 
 * managing the interactions between the view and the model of the game.
 */
public interface GameController {

    /**
     * Handle the click on the board.
     *  - normally it will select the cell and then deselect it if it's already selected
     *  - if a powerUp was selected, it apply that on the clicked cell
     *
     * @param pos the position of the cell clicked
     * @return true if the cells are swapped, false otherwise
     */
    boolean hit(Position pos);

    /**
     * Update the goals and removes the matched from the board.
     */
    void handleMatches();

    /**
     * Apply the gravity to the board, updating eventual matches
     * and solve the eventual stall.
     * 
     * @return if the board was changed (previous time)
     */
    boolean applyGravity();

    /**
     * Select a powerUp based on its id.
     * 
     * @param id id of the powerUp
     * @return false if the specified PowerUp doesn't exist, true otherwise
     */
    boolean selectPowerUp(int id);

    /**
     * Update the game state.
     *
     * @return the updated game state
     */
    GameState updateGameState();

    /**
     * Get the CellType for a specific cell in the board.
     * 
     * @param pos position of the cell
     * @return type of the cell
     */
    CellType getCellTypeAtPos(Position pos);

    /**
     * Get the remaining moves.
     * 
     * @return the number of remaining moves
     */
    int getMovesLeft();

    /**
     * Get the goals of the level.
     * 
     * @return an unmodifiable map of goals
     */
    Map<CellType, Integer> getGoals();

    /**
     * Quit the level and go back to the main menu.
     */
    void quitLevel();

}
