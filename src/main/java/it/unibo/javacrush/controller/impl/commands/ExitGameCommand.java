package it.unibo.javacrush.controller.impl.commands;

import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Command to exit the game.
 */
public class ExitGameCommand implements Command {

    private final SceneManager sceneManager;

    public ExitGameCommand(final SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void execute() {
        this.sceneManager.quit();
    }

}
