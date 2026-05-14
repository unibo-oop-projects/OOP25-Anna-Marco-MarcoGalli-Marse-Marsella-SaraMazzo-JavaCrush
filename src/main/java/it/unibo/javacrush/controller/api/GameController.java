package it.unibo.javacrush.controller.api;

import it.unibo.javacrush.common.GameState;

/**
 * Interface representing the game controller, 
 * it's responsable for the single levels.
*/
public interface GameController {

    /**
     * Handles a specific event and executes the corresponding command.
     * 
     * @param event the event to handle
     */
    void notifyEvent(Event event);

    /**
     * Update the state of the game based on the current board and the player's actions.
     * 
     * @return the state of the game
     */
    GameState updateGameState();

}
