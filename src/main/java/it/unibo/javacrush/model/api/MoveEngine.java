package it.unibo.javacrush.model.api;

import it.unibo.javacrush.common.Position;

public interface MoveEngine {

    /**
     * Check if two positions are adjacent on the board.
     * 
     * @param pos1 the first position to check
     * @param pos2 the second position to check
     * @return true if the positions are adjacent, false otherwise
     */
    boolean isAdiacent(Position pos1, Position pos2);

    /**
     * Check if two positions can be swapped on the board, meaning that the swap would result in a match.
     * 
     * @param board the board to check for the swap
     * @param pos1 the first position to swap
     * @param pos2 the second position to swap
     * @return true if the positions can be swapped, false otherwise
     */
    boolean canSwap(Board board,Position pos1, Position pos2);

}
