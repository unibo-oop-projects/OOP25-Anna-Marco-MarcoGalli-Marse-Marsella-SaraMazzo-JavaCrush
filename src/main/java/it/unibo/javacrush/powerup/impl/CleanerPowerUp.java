package it.unibo.javacrush.powerup.impl;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.powerup.api.PowerUp;

/**
 * This PowerUp removes a specified cell from the board.
 */
public class CleanerPowerUp implements PowerUp{

    @Override
    public Boolean applyPowerUp(Board board, Position pos) {

        if(!board.getGrid().containsKey(pos) || board.getCellAt(pos).isEmpty()) {
            return false;
        }

        board.removeCell(pos);
        return true;
    }
    
}
