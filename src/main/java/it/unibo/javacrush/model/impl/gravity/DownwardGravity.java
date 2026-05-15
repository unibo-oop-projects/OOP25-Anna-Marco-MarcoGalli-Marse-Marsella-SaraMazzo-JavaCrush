package it.unibo.javacrush.model.impl.gravity;

import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;

public class DownwardGravity extends AbstractGravity{

    public DownwardGravity() {
        super(Direction.DOWN);
    }

    @Override
    public Boolean applyGravity(final Board board) {
        boolean moved = false;
        for (int row = board.getRows() - 2; row >= 0; row--) {
            for (int col = 0; col < board.getCols(); col++) {
                final Position current = new Position(col, row);
                final Position target = new Position(col, row + 1);

                if (tryMove(board, current, target)) {
                    moved = true;
                }
            }
        }
        return moved;
    }

}
