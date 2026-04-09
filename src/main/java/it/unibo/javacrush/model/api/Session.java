package it.unibo.javacrush.model.api;

import java.util.Map;

import it.unibo.javacrush.common.CellType;

/**
 * Interface representing a game session.
 */
public interface Session {

    /**
     * The number of remaining moves.
     * 
     * @return the number of moves
     */
    int getMovesLeft();

    /**
     * Decrease the number of moves.
     */
    void decreaseMoves();

    /**
     * The updated goals of the game.
     * 
     * @return a map with the updated goals of the game 
     */
    Map<CellType, Integer> getGoals();

    /**
     * Update that cells of a specific type have been collected.
     * 
     * @param type  the type of cells collected
     * @param count the number of cells collected
     */
    void updateCellsCollected(CellType type, int count);

    /**
     * The state of the game, whether it is won or not.
     * 
     * @return true if the game is won, false otherwise
     */
    boolean isWon();

}
