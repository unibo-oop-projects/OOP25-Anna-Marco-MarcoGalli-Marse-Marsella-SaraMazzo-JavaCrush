package it.unibo.javacrush.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import it.unibo.javacrush.common.*;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.model.api.MatchDetector;

public class StallEngineImpl implements StallEngine{

    private MatchDetector detector = new MatchDetectorImpl();

    @Override
    public boolean isStall(Board board) {

        Board tmp = new BoardImpl(board.getRows(), board.getCols());

        for (int y = 0; y < board.getCols(); y++) {
            for (int x = 0; x < board.getRows(); x++) {
                var p = new Position(x, y);
                if (board.getCellAt(p).isEmpty()) {
                    throw new NoSuchElementException("The current board is not well initialized");
                }
                tmp.setCell(p, board.getCellAt(p));
            }
        }

        for (int y = 0; y < tmp.getCols() - 2; y++) {
            for (int x = 0; x < tmp.getRows() - 2; x++) {

                var current = new Position(x, y);
                if (detector.findMatchesAt(this.swapRight(tmp, current), current) != null) {
                    return false;
                }
                this.swapRight(tmp, current);

                if  (detector.findMatchesAt(this.swapDown(tmp, current), current) != null) {
                    return false;
                }
                this.swapDown(tmp, current);
            }
        }

        return true;
    }

    @Override
    public void resolveStall(Board board) {

        List<Cell> tmp = new ArrayList<>();
        int index;

        while (this.isStall(board) || !detector.findAllMatches(board).isEmpty()) {

            tmp.clear();
            for (int y = 0; y < board.getCols(); y++) {
                for (int x = 0; x < board.getRows(); x++) {
                    tmp.add(board.getCellAt(new Position(x, y)).get());
                }
            }
            Collections.shuffle(tmp);
            index = 0;

            for (int j = 0; j < board.getCols(); j++) {
                for (int i = 0; i < board.getRows(); i++) {
                    board.setCell(new Position(i, j), Optional.of(tmp.get(index)));
                    index++;
                }
            }
        }

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
