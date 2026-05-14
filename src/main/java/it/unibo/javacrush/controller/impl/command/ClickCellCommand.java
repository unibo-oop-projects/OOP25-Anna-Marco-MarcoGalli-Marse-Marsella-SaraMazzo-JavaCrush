package it.unibo.javacrush.controller.impl.command;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.controller.api.GameController;

/**
 * Command to click a specific cell.
 */
public class ClickCellCommand implements Command {

    private final GameController gameController;
    private final Position position;

    public ClickCellCommand(final GameController gameController, final Position position) {
        this.gameController = gameController;
        this.position = position;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
