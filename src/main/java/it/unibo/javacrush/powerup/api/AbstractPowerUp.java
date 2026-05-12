package it.unibo.javacrush.powerup.api;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;

/**
 * This abstract class is used as base by all the PowerUps classes of the game.
 */
public abstract class AbstractPowerUp {

    /**
     * Check if a PowerUp can be applied on the given position of the given board.
     * 
     * @param board the board that contains the position.
     * @param pos the position on where to apply the PowerUp.
     * @return false if pos isn't present in the board or if the cell in the board is empty.
     */
    public Boolean isAppliable(final Board board, final Position pos) {
        return board.getGrid().containsKey(pos) && board.getCellAt(pos).isPresent();
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
