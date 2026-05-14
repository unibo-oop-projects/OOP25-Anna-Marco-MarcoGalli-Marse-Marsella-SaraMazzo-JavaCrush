package it.unibo.javacrush.model.impl.gravity;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;

public class UpwardGravity extends AbstractGravity {

    public UpwardGravity() {
        super(Direction.UP);
    }

    @Override
    public Boolean applyGravity(Board board) {
        boolean moved = false;
        for (int row = 1; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                Position current = new Position(col, row);
                Position target = new Position(col, row - 1);

                if (tryMove(board, current, target)) {
                    moved = true;
                }
            }
        }
        return moved;
    }

}
