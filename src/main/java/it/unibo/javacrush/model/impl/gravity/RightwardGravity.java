package it.unibo.javacrush.model.impl.gravity;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;

public class RightwardGravity extends AbstractGravity {

    public RightwardGravity() {
        super(Direction.RIGHT);
    }

    @Override
    public Boolean applyGravity(Board board) {
        boolean moved = false;
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = board.getCols() - 2; col >= 0; col--) {
                Position current = new Position(col, row);
                Position target = new Position(col + 1, row);

                if (tryMove(board, current, target)) {
                    moved = true;
                }
            }
        }
        return moved;
        }

}
