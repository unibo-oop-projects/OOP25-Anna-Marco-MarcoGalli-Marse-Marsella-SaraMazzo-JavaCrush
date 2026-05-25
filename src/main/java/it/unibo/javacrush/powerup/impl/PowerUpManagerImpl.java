package it.unibo.javacrush.powerup.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.common.PowerUpNumber;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.powerup.api.PowerUpManager;

/**
 * This class implements {@link it.unibo.javacrush.powerup.api.PowerUpManager}.
 */
public class PowerUpManagerImpl implements PowerUpManager {

    private static final int TOTPOWERUPS = PowerUpNumber.values().length;
    private int selectedInt;
    private AbstractPowerUp selected;
    private final List<Boolean> permission;

    /**
     * PowerUpManagerImpl constructor.
     */
    public PowerUpManagerImpl() {
        this.selectedInt = -1;
        this.selected = null;
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
        return this.selectedInt >= 0 && this.selectedInt < TOTPOWERUPS && this.selected != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean selectPowerUp(final int num) {
        if (this.permission.size() > num && num >= 0 && this.permission.get(num)) {
            this.selectedInt = num;
            this.selected = PowerUpNumber.values()[this.selectedInt].getPowerUp();
        }
        return this.isPowerUpSelected();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean resetPowerUpSelection() {
        this.selectedInt = -1;
        this.selected = null;
        return !this.isPowerUpSelected();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean applyPowerUp(final Board board, final Position pos) {
        if (this.isPowerUpSelected()
            && this.permission.get(this.selectedInt)
            && this.selected.applyPowerUp(board, pos)) {

                this.permission.remove(this.selectedInt);
                this.permission.add(this.selectedInt, false);
                return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Match> getMatches() {
        return this.selected.getMatches();
    }

}
