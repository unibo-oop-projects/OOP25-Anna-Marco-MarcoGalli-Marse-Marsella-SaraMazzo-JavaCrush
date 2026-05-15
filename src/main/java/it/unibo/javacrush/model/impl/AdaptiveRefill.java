package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.RefillEngine;
import java.util.Optional;
import java.util.stream.IntStream;

public class AdaptiveRefill implements RefillEngine {

        private final GravityEngine gravity;

        public AdaptiveRefill(final GravityEngine gravity) {
            this.gravity = gravity;
        }

    @Override
    public Boolean refill(Board board) {
        final Direction dir = gravity.getDirection();
        final int rows = board.getRows();
        final int cols = board.getCols();

        final int range = dir.isVertical() ? cols : rows;
        final int fixedCoord = dir.getEntryPoint(rows, cols);

        long AddedCount = IntStream.range(0, range)
                .mapToObj(i -> createEntryPoint(i, fixedCoord, dir))
                .filter(pos -> board.getCellAt(pos).isEmpty())
                .peek(pos -> board.setCell(pos, Optional.of(new CellImpl(CellType.getRandomType()))))
                .count();

        return AddedCount > 0;
    } 
    
    private Position createEntryPoint(final int index, final int fixed, final Direction dir) {
        return dir.isVertical() ? new Position(index, fixed) : new Position(fixed, index);
    }

    @Override
    public void refillAll(Board board) {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                Position pos = new Position(c, r);
                if (board.getCellAt(pos).isEmpty()) {
                    board.setCell(pos, Optional.of(new CellImpl(CellType.getRandomType())));
                }
            }
        }
    }

}
