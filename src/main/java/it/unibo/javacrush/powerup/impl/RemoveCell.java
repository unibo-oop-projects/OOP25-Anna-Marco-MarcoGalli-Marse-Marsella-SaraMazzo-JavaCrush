package it.unibo.javacrush.powerup.impl;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;

/**
 * This PowerUp removes a specified cell from the board.
 */
public class RemoveCell extends AbstractPowerUp {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean applyPowerUp(final Board board, final Position pos) {
        if (this.isAppliable(board, pos)) {
            board.removeCell(pos);
            return true;
        }

        return false;
    }

}
