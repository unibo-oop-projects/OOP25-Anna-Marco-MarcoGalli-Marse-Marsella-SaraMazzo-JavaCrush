package it.unibo.javacrush.powerup.impl;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.powerup.api.AbstractPowerUp;
import it.unibo.javacrush.powerup.api.PowerUpManager;

/**
 * This class manages all the PowerUps of the game.
 */
public class PowerUpManagerImpl implements PowerUpManager {

    private AbstractPowerUp power;

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isPowerUpSelected() {
        return this.power != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean selectPowerUp(final int num) {
        switch (num) {
            case 0:
                this.power = new CleanerPowerUp();
                break;
            case 1:
                this.power = new MelterPowerUp();
                break;
            case 2:
                this.power = new VaporizerPowerUp();
                break;
            default:
                return false;
        }
        return this.isPowerUpSelected();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean resetPowerUpSelection() {
        this.power = null;
        return !this.isPowerUpSelected();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean applyPowerUp(final Board board, final Position pos) {

        return this.isPowerUpSelected() && this.power.applyPowerUp(board, pos);

    }

}
