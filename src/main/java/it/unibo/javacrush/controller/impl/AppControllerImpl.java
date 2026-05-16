package it.unibo.javacrush.controller.impl;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import it.unibo.javacrush.common.AppEventType;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.controller.api.Event;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.controller.impl.commands.ExitGameCommand;
import it.unibo.javacrush.controller.impl.commands.GoToLevelsCommand;
import it.unibo.javacrush.controller.impl.commands.GoToMenuCommand;
import it.unibo.javacrush.controller.impl.commands.ShowInstructiosCommand;
import it.unibo.javacrush.model.api.LevelManager;

import it.unibo.javacrush.view.api.SceneManager;

/**
 * Implementation of the {@link AppController} interface.
 */
public class AppControllerImpl implements AppController {

    private final SceneManager sceneManager;
    private final Map<AppEventType, Function<Event, Command>> commands = new EnumMap<>(AppEventType.class);
    private final LevelManager levelManager;
    private Optional<GameController> currentGameController = Optional.empty();


    public AppControllerImpl(final SceneManager sceneManager, final LevelManager levelManager) {
        this.sceneManager = sceneManager;
        this.levelManager = levelManager;

        this.setUpCommands();
    }

    @Override
    public void notifyEvent(final Event event) {
        var commandFactory = commands.get(event.type());

        if (commandFactory != null) {
            commandFactory.apply(event).execute();
        }
    }

    public Optional<GameController> getCurrentGameController() {
        return currentGameController;
    }

    /**
     * Set up the mapping between events and commands.
     */
    private void setUpCommands() {
        this.commands.put(AppEventType.EXIT_GAME, event -> new ExitGameCommand(this.sceneManager));
        this.commands.put(AppEventType.GO_TO_LEVELS, event -> new GoToLevelsCommand(this.sceneManager));
        this.commands.put(AppEventType.GO_TO_MENU, event -> new GoToMenuCommand(this.sceneManager));
        this.commands.put(AppEventType.SHOW_INSTRUCTIONS, event -> new ShowInstructiosCommand(this.sceneManager));
    }

    /**
     * Cancel the GameController used for the game.
     */
    private void terminateGame() {
        this.currentGameController = Optional.empty();
    }
}