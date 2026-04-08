package it.unibo.javacrush.model.api;

import java.util.Set;

import it.unibo.javacrush.common.Match;
import it.unibo.javacrush.common.Position;

public interface MatchDetector {

    /**
     * Find a match at the given position on the board.
     * @param board the board to check for matches
     * @param pos the position to check for matches
     * @return a set of matches found at the given position,or an empty set if no matches are found
    */
    Set<Match> findMatchesAt(Board board, Position pos);

    /**
     * Find all matches on the board.
     * @param board the board to check for matches
     * @return a set of all matches found on the board
     */
    Set<Match> findAllMatches(Board board);

}

