package it.unibo.javacrush.controller.impl.commands;

import java.util.function.Consumer;

import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.controller.impl.GameControllerImpl;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.view.api.GameView;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Command to start a level.
 */
public class StartLevelCommand implements Command {

    private final SceneManager sceneManager;
    private final LevelManager levelManager;
    private final int idLevel;
    private final Consumer<GameController> consumer;
    private final GameView gameView;

    public StartLevelCommand(
        final SceneManager sceneManager,
        final LevelManager levelManager,
        final int idLevel,
        final Consumer<GameController> consumer,
        final GameView gameView) {
        this.sceneManager = sceneManager;
        this.levelManager = levelManager;
        this.idLevel = idLevel;
        this.consumer = consumer;
        this.gameView = gameView;
    }

    @Override
    public void execute() {
        GameController gameController = new GameControllerImpl(
            this.levelManager.startMatch(idLevel),
            this.gameView
        );

        consumer.accept(gameController);

        this.sceneManager.showGame(gameController);
    }

}
