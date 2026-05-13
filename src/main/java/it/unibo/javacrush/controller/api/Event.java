package it.unibo.javacrush.controller.api;

import java.util.Optional;

import it.unibo.javacrush.common.EventType;
import it.unibo.javacrush.common.Position;

/**
 * Interface representing an event in the game.
 */
public interface Event {

    /**
     * Get the type of the event.
     * 
     * @return the type of the event
     */
    EventType getType();

    /**
     * Get the position associated with the event, if any.
     * 
     * @return the position associated
     */
    Optional<Position> getPosition();

    /**
     * Get the ID associated with the event, if any.
     * 
     * @return the ID associated
     */
    Optional<Integer> getId();
}
