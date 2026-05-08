package it.unibo.javacrush.model.api;

import java.util.Set;

/**
 * Interface representing the engine that detects a deadlock (stall) with no possible moves
 * and resolve the situation by shuffling the cells on the board.
 */
public interface StallEngine {

    /**
     * Check if there is stall.
     * 
     * @param board the board where to check.
     * @return true if there is stall, false otherwise.
     */
    boolean isStall(Board board);

    /**
     * Refresh all the cells on the board to create admitted moves,
     * if there isn't any stall this method won't change anything.
     * 
     * @param board the board where to resolve the stall.
     */
    void resolveStall(Board board);

    /**
     * Finds all possible matches on the given board.
     * 
     * @param board the board where to find the possible matches.
     * @return a Set with all the possible matches or an empty Set.
     */
    Set<Match> possibleMatches(Board board);

}
