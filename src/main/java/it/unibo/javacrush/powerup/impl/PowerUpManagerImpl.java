package it.unibo.javacrush.powerup.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.powerup.api.AbstractPowerUp;
import it.unibo.javacrush.powerup.api.PowerUpManager;

/**
 * This class manages all the PowerUps of the game.
 */
public class PowerUpManagerImpl implements PowerUpManager {

    private static final int TOTPOWERUPS = 3;
    private int selected;
    private List<AbstractPowerUp> power;
    private List<Boolean> permission;

    /**
     * PowerUpManagerImpl constructor.
     */
    public PowerUpManagerImpl() {
        this.selected = -1;
        this.power = new ArrayList<>();
        this.permission = new ArrayList<>();

        this.power.addLast(new RemoveCell());
        this.power.addLast(new RemoveRow());
        this.power.addLast(new RemoveType());
        for (int i = 0; i < TOTPOWERUPS; i++) {
            this.permission.addLast(true);
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
            this.power.get(this.selected).applyPowerUp(board, pos)) {
            
                this.permission.remove(this.selected);
                this.permission.add(this.selected, false);
                return this.resetPowerUpSelection();
        }

        return false;
    }

}
