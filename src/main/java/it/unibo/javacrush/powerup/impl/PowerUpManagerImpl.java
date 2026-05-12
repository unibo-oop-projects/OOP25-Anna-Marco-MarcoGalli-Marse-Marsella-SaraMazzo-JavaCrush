package it.unibo.javacrush.powerup.impl;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.powerup.api.PowerUp;
import it.unibo.javacrush.powerup.api.PowerUpManager;

public class PowerUpManagerImpl implements PowerUpManager{

    private PowerUp power;

    @Override
    public Boolean selectPowerUp(int num) {
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
        if (this.power == null) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean applyPowerUp(Board board, Position pos) {
        
        if (this.power == null) {
            return false;
        }

        return this.power.applyPowerUp(board, pos);

    }

    
}
