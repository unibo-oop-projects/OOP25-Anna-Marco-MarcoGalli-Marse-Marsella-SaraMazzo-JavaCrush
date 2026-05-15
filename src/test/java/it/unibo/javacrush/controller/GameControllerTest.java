package it.unibo.javacrush.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.powerup.api.PowerUpManager;

/**
 * Test class for the {@link GameControllerImpl} class.
 */
@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    @Mock
    private AppController appController;
    @Mock
    private Board board;
    @Mock
    private PowerUpManager powerUpManager;
    @Mock
    private GameState gameState;

    
}
