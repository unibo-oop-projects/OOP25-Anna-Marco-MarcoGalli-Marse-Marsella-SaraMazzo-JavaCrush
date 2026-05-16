package it.unibo.javacrush.controller.impl.commands;

import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Command to show the instructions screen.
 */
public class ShowInstructiosCommand implements Command {

    private final SceneManager sceneManager;

    public ShowInstructiosCommand(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void execute() {
        this.sceneManager.showInstructions();
    }

}
