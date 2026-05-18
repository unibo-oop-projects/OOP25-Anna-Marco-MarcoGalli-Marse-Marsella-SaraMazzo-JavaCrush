package it.unibo.javacrush.view.api;

import it.unibo.javacrush.controller.api.GameController;

public interface SceneManager {
    void showMenu();
    void showLevels();
    void showGame(GameController gameController); // Puoi passare parametri!

    /**
     * Get the GameView.
     * 
     * @return the gameView
     */
    GameView getGameView();

    void showInstructions(); // UNDERSTAND IF WE WANT IT
    void quit(); // UNDERSTAND IF WE WANT IT
}