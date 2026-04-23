package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;

import java.util.Optional;

import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.common.Position;

public class DownwardGravity implements GravityEngine {

    @Override
    public Boolean applyGravity(Board board) {
        boolean gravityApplied = false;

        for (int col = 0; col < board.getCols(); col++) {
            for (int row = board.getRows() - 2; row >= 0; row--) {

                Position currentPos = new Position(col, row);
                Position belowPos = new Position(col, row + 1);

                Optional<Cell> currentCell = board.getCellAt(currentPos);
                Optional<Cell> cellBelow = board.getCellAt(belowPos);

                if (currentCell.isPresent() && cellBelow.isEmpty()) {
                    board.setCell(belowPos, currentCell);
                    board.setCell(currentPos, Optional.empty());
                    gravityApplied = true;
                }
            }
        }
        return gravityApplied;
    }
}
