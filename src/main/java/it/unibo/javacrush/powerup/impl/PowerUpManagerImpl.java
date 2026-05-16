package it.unibo.javacrush.powerup.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.common.PowerUpNumber;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.powerup.api.PowerUpManager;

/**
 * This class implements {@link it.unibo.javacrush.powerup.api.PowerUpManager}.
 */
public class PowerUpManagerImpl implements PowerUpManager {

    private static final int TOTPOWERUPS = PowerUpNumber.values().length;
    private int selected;
    private List<Boolean> permission;

    /**
     * PowerUpManagerImpl constructor.
     */
    public PowerUpManagerImpl() {
        this.selected = -1;
        this.permission = new ArrayList<>();

        for (int i = 0; i < TOTPOWERUPS; i++) {
            this.permission.add(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean isPowerUpSelected() {
        return this.selected >= 0 && this.selected < TOTPOWERUPS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean selectPowerUp(final int num) {
        if ((this.permission.size() > num) && (num >= 0) && (this.permission.get(num))) {
            this.selected = num;
        }
        return this.isPowerUpSelected();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean resetPowerUpSelection() {
        this.selected = -1;
        return !this.isPowerUpSelected();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean applyPowerUp(final Board board, final Position pos) {
        if (this.isPowerUpSelected() &&
            this.permission.get(this.selected) &&
            PowerUpNumber.values()[this.selected].getPowerUp().applyPowerUp(board, pos)) {

                this.permission.remove(this.selected);
                this.permission.add(this.selected, false);
                return this.resetPowerUpSelection();
        }

        return false;
    }

}
