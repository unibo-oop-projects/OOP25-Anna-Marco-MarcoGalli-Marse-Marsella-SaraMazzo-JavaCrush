package it.unibo.javacrush.model.impl.gravity;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;

public class LeftwardGravity extends AbstractGravity {

    public LeftwardGravity() {
        super(Direction.LEFT);
    }

    @Override
    public Boolean applyGravity(final Board board) {
        boolean moved = false;
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 1; col < board.getCols(); col++) {
                final Position current = new Position(col, row);
                final Position target = new Position(col - 1, row);

                if (tryMove(board, current, target)) {
                    moved = true;
                }
            }
        }
        return moved;
    }
}
