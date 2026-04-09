package it.unibo.javacrush.model.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.Match;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.MatchDetector;
import it.unibo.javacrush.model.api.MoveEngine;

public class MoveEngineImpl implements MoveEngine{

    private final MatchDetector detector = new MatchDetectorImpl();
    private final Set<Match> matches = new HashSet<>();

    @Override
    public boolean isAdjacent(Position pos1, Position pos2) {
        return (Math.abs(pos1.x()-pos2.x()) == 1 && pos1.y() == pos2.y()) ||
                (Math.abs(pos1.y()-pos2.y()) == 1 && pos1.x() == pos2.x());
    }

    @Override
    public boolean canSwap(Board board, Position pos1, Position pos2) {

        Set<Match> currentMatches = new HashSet<>();
        matches.clear();
        
        if(!isAdjacent(pos1, pos2)) {
            return false;
        }

        board.swapCells(pos1,pos2);
        currentMatches.addAll(detector.findMatchesAt(board, pos1));
        currentMatches.addAll(detector.findMatchesAt(board, pos2));
        board.swapCells(pos1,pos2);

        matches.addAll(currentMatches);
        currentMatches.clear();

        return !matches.isEmpty();
    }

}
