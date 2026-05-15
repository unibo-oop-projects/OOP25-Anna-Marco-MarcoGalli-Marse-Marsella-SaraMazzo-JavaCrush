package it.unibo.javacrush.controller.impl;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import it.unibo.javacrush.common.EventType;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.Command;
import it.unibo.javacrush.controller.api.Event;
import it.unibo.javacrush.controller.api.GameController;

/**
 * Implementation of the {@link AppController} interface.
 */
public class AppControllerImpl implements AppController {

    // VARIABLE FOR THE VIEW (CURRENT MISSING)
    private Optional<GameController> currentGameController = Optional.empty();
    private final Map<EventType, Function<Event, Command>> commands = new EnumMap<>(EventType.class);

    @Override
    public void notifyEvent(Event event) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyEvent'");
    }

    @Override
    public void displayMainMenu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayMainMenu'");
    }

    @Override
    public void displayLevelsMenu() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayLevelsMenu'");
    }

    @Override
    public void displayInstructions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'displayInstructions'");
    }

    @Override
    public void startLevel(final int levelNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'startLevel'");
    }

    @Override
    public void quit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quit'");
    }

}
