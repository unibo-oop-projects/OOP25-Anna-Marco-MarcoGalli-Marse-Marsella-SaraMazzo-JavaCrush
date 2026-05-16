package it.unibo.javacrush.common;

import java.util.Optional;

import it.unibo.javacrush.controller.api.Event;

/**
 * Record representing a game event, implementing the {@link Event} interface.
 */
public record GameEvent(
    AppEventType type,
    Optional<Integer> id) implements Event {
        
}
