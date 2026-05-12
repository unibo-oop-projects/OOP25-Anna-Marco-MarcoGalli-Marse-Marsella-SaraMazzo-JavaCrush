package it.unibo.javacrush.powerup.impl;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.powerup.api.PowerUp;

/**
 * This PowerUp removes a specified cell from the board.
 */
public class CleanerPowerUp extends PowerUp{

    @Override
    public Boolean applyPowerUp(Board board, Position pos) {
        if (this.isAppliable(board, pos)) {
            board.removeCell(pos);
            return true;
        }

        return false;
    }
    
}
