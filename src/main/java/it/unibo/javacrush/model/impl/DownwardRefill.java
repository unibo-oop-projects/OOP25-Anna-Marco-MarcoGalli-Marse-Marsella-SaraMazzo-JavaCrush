package it.unibo.javacrush.model.impl;

import java.util.Optional;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.api.RefillEngine;

public class DownwardRefill implements RefillEngine {

    @Override
    public Boolean refill(Board board) {
        boolean changed = false;

        for (int col = 0; col < board.getCols(); col++) {
            Position pos = new Position(0, col);
            Optional<Cell> currentCell = board.getCellAt(pos);

            if (currentCell.isEmpty()) {
                Cell newCell = new CellImpl(CellType.getRandomType());
                board.setCell(pos, Optional.of(newCell));
                changed = true;
            }
        }
        return changed;
    }   

}
