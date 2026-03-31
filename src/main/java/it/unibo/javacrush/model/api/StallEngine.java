package it.unibo.javacrush.model.api;

/**
 * Interface representing the engine that detects a deadlock (stall) with no possible moves
 * and resolve the situation by shuffling the cells on the board.
 */
public interface StallEngine {

    /**
     * Detects if there is a situation of stall.
     * 
     * @return true if there is stall.
     */
    boolean isStall();

}
