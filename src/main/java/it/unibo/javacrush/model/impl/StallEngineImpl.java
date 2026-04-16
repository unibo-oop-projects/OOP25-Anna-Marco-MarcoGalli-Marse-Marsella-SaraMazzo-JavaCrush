package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.model.api.MatchDetector;

public class StallEngineImpl implements StallEngine{

    private MatchDetector detector = new MatchDetectorImpl();

    @Override
    public void computeStall(Board board) {

        if (this.isStall(board)) {
            
        }
    }

    private boolean isStall(Board board) {

        Board tmp = new BoardImpl(board.getRows(), board.getCols());

        for (int y = 0; y < tmp.getCols(); y++) {
            for (int x = 0; x < tmp.getRows(); x++) {

                var current = new Position(x, y);
                if (!detector.findMatchesAt(this.swapRight(tmp, current), current).isEmpty() ||
                    !detector.findMatchesAt(this.swapDown(tmp, current), current).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    private Board swapRight(Board tmp, Position p) {

        if (p.x() + 1 < tmp.getCols()) {
            tmp.swapCells(p, new Position(p.x() + 1, p.y()));
        }
        return tmp;
    }

    private Board swapDown(Board tmp, Position p) {

        if (p.y() + 1 < tmp.getRows()) {
            tmp.swapCells(p, new Position(p.x(), p.y() + 1));
        }
        return tmp;
    }

}
