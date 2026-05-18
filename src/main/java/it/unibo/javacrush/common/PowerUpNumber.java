package it.unibo.javacrush.common;

import it.unibo.javacrush.powerup.impl.AbstractPowerUp;
import it.unibo.javacrush.powerup.impl.RemoveCell;
import it.unibo.javacrush.powerup.impl.RemoveRow;
import it.unibo.javacrush.powerup.impl.RemoveType;

/**
 * Represents all the PowerUps of the game.
 */
public enum PowerUpNumber {

    SINGLECELL(new RemoveCell()),
    ROW(new RemoveRow()),
    TYPE(new RemoveType());

    private final AbstractPowerUp power;

    PowerUpNumber(final AbstractPowerUp pow) {
        this.power = pow;
    }

    /**
     * Get the PowerUp Object stored in the enum members.
     * 
     * @return the PowerUp Object of the selected powerup of the enum.
     */
    public AbstractPowerUp getPowerUp() {
        return this.power;
    }
}
