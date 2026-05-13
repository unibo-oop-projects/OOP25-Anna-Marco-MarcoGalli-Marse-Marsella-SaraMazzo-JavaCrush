package it.unibo.javacrush.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.model.api.MatchManager;

/**
 * This class implements the interface StallEngine and handle the stall state of the board.
 */
public class StallEngineImpl implements StallEngine {

    private final MatchManager detector = new MatchManagerImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStall(final Board board) {

        return this.possibleMatches(board).isEmpty();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resolveStall(final Board board) {

        final List<Cell> tmp = new ArrayList<>();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Match> possibleMatches(final Board board) {

        final Board tmp = new BoardImpl(board.getRows(), board.getCols());
        final Set<Match> resultSet = new HashSet<>();

        Position p;
        for (int y = 0; y < board.getCols(); y++) {
            for (int x = 0; x < board.getRows(); x++) {
                p = new Position(x, y);
                if (board.getCellAt(p).isEmpty()) {
                    throw new NoSuchElementException("The current board is not well initialized");
                }
                tmp.setCell(p, board.getCellAt(p));
            }
        }

        for (int y = 0; y < tmp.getCols() - 2; y++) {
            for (int x = 0; x < tmp.getRows() - 2; x++) {

                p = new Position(x, y);
                if (detector.findMatchesAt(this.swapRight(tmp, p), p) != null) {
                    resultSet.add(detector.findMatchesAt(tmp, p));
                }
                this.swapRight(tmp, p);

                if (detector.findMatchesAt(this.swapDown(tmp, p), p) != null) {
                    resultSet.add(detector.findMatchesAt(tmp, p));
                }
                this.swapDown(tmp, p);
            }
        }

        return resultSet;
    }

    private Board swapRight(final Board tmp, final Position p) {

        if (p.x() + 1 < tmp.getCols()) {
            tmp.swapCells(p, new Position(p.x() + 1, p.y()));
        }
        return tmp;
    }

    private Board swapDown(final Board tmp, final Position p) {

        if (p.y() + 1 < tmp.getRows()) {
            tmp.swapCells(p, new Position(p.x(), p.y() + 1));
        }
        return tmp;
    }

}
