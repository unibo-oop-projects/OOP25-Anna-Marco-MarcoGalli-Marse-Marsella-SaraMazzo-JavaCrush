package it.unibo.javacrush.powerup.api;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;

public interface PowerUpManager {

    /**
     * Select one of the possible PowerUps.
     * 
     * @param num the index of the PowerUp to select.
     * @return false if the specified PowerUp doesn't exist.
     */
    Boolean selectPowerUp(int num);
    
    /**
     * Apply the selected PowerUp.
     * 
     * @param board the board where to work.
     * @param pos the position on where apply the PowerUp.
     * @return true if there wasn't any problem, false if something went wrong.
     */
    Boolean applyPowerUp(Board board, Position pos);

}
