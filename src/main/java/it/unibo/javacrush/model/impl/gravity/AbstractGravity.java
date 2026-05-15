package it.unibo.javacrush.model.impl.gravity;

import java.util.Optional;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.api.GravityEngine;

public abstract class AbstractGravity implements GravityEngine {

    private final Direction direction;

    protected AbstractGravity(Direction direction) {
        this.direction = direction;
    }

    @Override
    public abstract Boolean applyGravity(Board board);

    @Override
    public Direction getDirection() {
        return this.direction;
    }

    protected boolean isInBound(Board board, final Position pos) {
        return pos.x() >= 0 && pos.x() < board.getCols() 
        && pos.y() >= 0 && pos.y() < board.getRows();
    }

    protected boolean tryMove(final Board board, final Position current, final Position target) {
        if (!isInBound(board, current) || !isInBound(board, target)) {
            return false;
        }

        final Optional<Cell> currentCell = board.getCellAt(current);
        final Optional<Cell> targetCell = board.getCellAt(target);

        if (currentCell.isPresent() && targetCell.isEmpty()) {
            board.setCell(target, currentCell);
            board.setCell(current, Optional.empty());
            return true;
        }
        return false;
    }

}
