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

    /**
     * Display the main menu of the application.
     */
    void displayMainMenu();

    /**
     * Display the levels menu.
     */
    void displayLevelsMenu();

    /**
     * Display the instructions of the game.
     */
    void displayInstructions();

    /**
     * Start a specific level based on the number passed in input.
     * 
     * @param levelNumber the level's number
     */
    void startLevel(int levelNumber);

    /**
     * Quit the game.
     */
    void quit();
}
