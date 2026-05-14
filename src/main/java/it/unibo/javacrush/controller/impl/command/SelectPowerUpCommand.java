package it.unibo.javacrush.controller.impl.command;

import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.powerup.api.PowerUpManager;

/**
 * Command to select a power up to use in the current level.
 */
public class SelectPowerUpCommand implements Command {

    private final PowerUpManager manager;
    private final int id;

    public SelectPowerUpCommand(final PowerUpManager manager, final int id) {
        this.manager = manager;
        this.id = id;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
