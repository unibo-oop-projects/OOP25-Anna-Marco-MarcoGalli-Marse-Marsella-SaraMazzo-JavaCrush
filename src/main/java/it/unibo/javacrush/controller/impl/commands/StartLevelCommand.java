package it.unibo.javacrush.controller.impl.commands;

import java.util.function.Consumer;

import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.controller.impl.GameControllerImpl;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Command to start a level.
 */
public class StartLevelCommand implements Command {

    // TO DO BASED ON LEVEL MANAGER
    private final SceneManager sceneManager;
    private final LevelManager levelManager;
    private final int idLevel;
    private final Consumer<GameController> consumer;

    public StartLevelCommand(
        final SceneManager sceneManager,
        final LevelManager levelManager,
        final int idLevel,
        final Consumer<GameController> consumer) {
        this.sceneManager = sceneManager;
        this.levelManager = levelManager;
        this.idLevel = idLevel;
        this.consumer = consumer;
    }

    @Override
    public void execute() {
        GameController gameController = new GameControllerImpl(
            this.levelManager.getLevelSetup(this.idLevel)
        );

        consumer.accept(gameController);
    }

}
