package it.unibo.javacrush.common;

import java.util.Optional;

import it.unibo.javacrush.controller.api.Event;

/**
 * Record representing a game event, implementing the {@link Event} interface.
 */
public record GameEvent(
    EventType type,
    Optional<Position> position,
    Optional<Integer> id) implements Event {

    @Override
    public EventType getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }

    @Override
    public Optional<Position> getPosition() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPosition'");
    }

    @Override
    public Optional<Integer> getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}
