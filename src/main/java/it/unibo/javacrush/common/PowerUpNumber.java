package it.unibo.javacrush.common;

import it.unibo.javacrush.powerup.api.AbstractPowerUp;
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

    PowerUpNumber(AbstractPowerUp pow) {
        this.power = pow;
    }

    public AbstractPowerUp getPowerUp() {
        return this.power;
    }
}
