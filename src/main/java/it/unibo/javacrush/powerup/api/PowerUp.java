package it.unibo.javacrush.powerup.api;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;

public abstract class PowerUp {
    
    public Boolean isAppliable(Board board, Position pos) {
        return (!board.getGrid().containsKey(pos) || board.getCellAt(pos).isEmpty()) ? false : true;
    }

    /**
     * Apply to the specified board the special power.
     * 
     * @param board the board where to apply the power.
     * @param pos the position of the cell where to apply the power.
     * @return false if the power wasn't applied to the board, true otherwise.
     */
    public abstract Boolean applyPowerUp(Board board, Position pos);
}
