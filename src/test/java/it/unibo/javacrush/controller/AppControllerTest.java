package it.unibo.javacrush.controller;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.unibo.javacrush.common.AppEventType;
import it.unibo.javacrush.common.GameEvent;
import it.unibo.javacrush.controller.impl.AppControllerImpl;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.view.api.SceneManager;

/**
 * Test class for the {@link AppControllerImpl} class.
 */
@ExtendWith(MockitoExtension.class)
public class AppControllerTest {

    @Mock
    private SceneManager sceneManager;

    @Mock
    private LevelManager levelManager;

    @InjectMocks
    private AppControllerImpl appController;

    /**
     * Test the execution of the ExitGameCommand when the EXIT_GAME event is notified.
     */
    @Test
    void testExitGameCommand() {
        var exitEvent = new GameEvent(AppEventType.EXIT_GAME,  Optional.empty());

        appController.notifyEvent(exitEvent);

        verify(sceneManager).quit();
    }

    /**
     * Test the execution of the GoToMenuCommand 
     * when the GO_TO_MENU event is notified.
     */
    @Test
    void testGoToMenuCommand() {
        var menuEvent = new GameEvent(AppEventType.GO_TO_MENU, Optional.empty());

        appController.notifyEvent(menuEvent);

        verify(sceneManager).showMenu();
    }

    /**
     * Test the execution of the GoToLevelsCommand 
     * when the GO_TO_LEVELS event is notified.
     */
    @Test
    void testGoToLevelsCommand() {
        var levelsEvent = new GameEvent(AppEventType.GO_TO_LEVELS, Optional.empty());

        appController.notifyEvent(levelsEvent);

        verify(sceneManager).showLevels();
    }

    /**
     * Test the execution of the ShowInstructionsCommand 
     * when the SHOW_INSTRUCTIONS event is notified.
     */
    @Test
    void testShowInstructionsCommand() {
        var instructionsEvent = new GameEvent(AppEventType.SHOW_INSTRUCTIONS,  Optional.empty());

        appController.notifyEvent(instructionsEvent);

        verify(sceneManager).showInstructions();
    }

}
