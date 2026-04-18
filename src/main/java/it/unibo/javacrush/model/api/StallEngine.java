package it.unibo.javacrush.model.api;

/**
 * Interface representing the engine that detects a deadlock (stall) with no possible moves
 * and resolve the situation by shuffling the cells on the board.
 */
public interface StallEngine {

    /**
     * Check if there's a stall situation and refresh all the cells on the board
     * to create admitted moves, if there isn't any stall this method won't change anything.
     * 
     * @param board the board on where check the stall.
     */
    void computeStall(Board board);

}
