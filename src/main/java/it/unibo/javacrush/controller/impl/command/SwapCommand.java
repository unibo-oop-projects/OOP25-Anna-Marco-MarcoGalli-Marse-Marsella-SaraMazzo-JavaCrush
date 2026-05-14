package it.unibo.javacrush.controller.impl.command;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.controller.api.GameController;

/**
 * Command to swap two cells.
 */
public class SwapCommand implements Command {

    private final GameController controller;
    private final Position p1;
    private final Position p2;

    public SwapCommand(final GameController controller, final Position p1, final Position p2) {
        this.controller = controller;
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

}
