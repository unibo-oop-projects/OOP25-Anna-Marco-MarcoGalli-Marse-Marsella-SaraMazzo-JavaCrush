package it.unibo.javacrush.view.api;

import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.GameController;
import javafx.scene.Parent;

/**
 * Temporary interface, TO CHANGE
 */
public interface GameView {

    /**
     * Update the view.
     */
    void updateView();

    /**
     * Quit the current level.
     */
    void quitLevel();

    Parent getView();

    void setController(GameController controller, AppController appController);

}
