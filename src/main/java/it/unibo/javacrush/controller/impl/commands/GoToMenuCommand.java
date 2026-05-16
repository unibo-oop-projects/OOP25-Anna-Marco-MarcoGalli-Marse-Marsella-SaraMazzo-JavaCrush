package it.unibo.javacrush.controller.impl.commands;

import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Command to navigate to the main menu screen.
 */
public class GoToMenuCommand implements Command {

    private final SceneManager sceneManager;

    public GoToMenuCommand(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void execute() {
        this.sceneManager.showMenu();
    }

}
