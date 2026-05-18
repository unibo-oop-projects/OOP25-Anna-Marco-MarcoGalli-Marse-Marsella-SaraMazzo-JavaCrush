package it.unibo.javacrush.powerup.impl;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;

/**
 * This PowerUp removes all the cells on the board with the same type of the given cell.
 */
public class RemoveType extends AbstractPowerUp {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean applyPowerUp(final Board board, final Position pos) {

        if (this.isAppliable(board, pos)) {

            final Cell type = board.getCellAt(pos).get();
            Position current;

            for (int y = 0; y < board.getRows(); y++) {
                for (int x = 0; x < board.getCols(); x++) {
                    current = new Position(x, y);

                    if (board.getCellAt(current).isPresent() && board.getCellAt(current).get().equals(type)) {
                        board.removeCell(current);
                    }
                }
            }

            return true;

        }

        return false;
    }

}
