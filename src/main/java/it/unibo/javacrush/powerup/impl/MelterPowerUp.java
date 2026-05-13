package it.unibo.javacrush.powerup.impl;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.powerup.api.AbstractPowerUp;

/**
 * This PowerUp removes the entire row of cells of the given cell from the board.
 */
public class MelterPowerUp extends AbstractPowerUp {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean applyPowerUp(final Board board, final Position pos) {

        if (this.isAppliable(board, pos)) {

            for (int x = 0; x < board.getCols(); x++) {
                board.removeCell(new Position(x, pos.y()));
            }

            return true;
        }

        return false;
    }

}
