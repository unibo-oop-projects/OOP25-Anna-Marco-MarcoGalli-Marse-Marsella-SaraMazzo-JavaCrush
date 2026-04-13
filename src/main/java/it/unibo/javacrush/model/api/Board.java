package it.unibo.javacrush.model.api;

import java.util.Optional;

import it.unibo.javacrush.common.Position;

/**
 * Interface representing a game board.
 * This interface defines the contract for a game board,
 * which is composed of cells and is responsible for managing the game state.
 */
public interface Board {

    /**
     * @return the number of rows in the board.
     */
    int getRows();

    /**
     * @return the number of columns in the board.
     */
    int getCols();

    /**
     * @param pos the position of the cell to retrieve.
     * @return the cell at the specified position.
     */
    Optional<Cell> getCellAt(Position pos);

    /**
     * Swaps the cells at the specified positions.
     * 
     * @param pos1 the first position to swap.
     * @param pos2 the second position to swap.
     */
    void swapCells(Position pos1, Position pos2);

}
