package it.unibo.javacrush.powerup.impl;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.powerup.api.PowerUp;

/**
 * This PowerUp removes the entire row of cells of the given cell from the board.
 */
public class MelterPowerUp extends PowerUp{

    @Override
    public Boolean applyPowerUp(Board board, Position pos) {

        if(this.isAppliable(board, pos)) {

            for (int x = 0; x < board.getCols(); x++) {
                board.removeCell(new Position(x, pos.y()));
            }

            return true;
        }

        return false;
    }

}
