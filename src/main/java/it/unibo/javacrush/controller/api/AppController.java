package it.unibo.javacrush.controller.api;

/**
 * Interface representing the main controller of the application, 
 * responsible for managing the interactions between the menu and the levels.
 */
public interface AppController {

    /**
     * Handles a specific event and executes the corresponding command.
     * 
     * @param event the event to handle
     */
    void notifyEvent(Event event);

}
