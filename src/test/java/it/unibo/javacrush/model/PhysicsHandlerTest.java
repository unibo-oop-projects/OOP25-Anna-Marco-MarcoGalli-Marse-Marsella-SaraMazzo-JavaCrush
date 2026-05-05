package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.DownwardGravity;
import it.unibo.javacrush.model.impl.PhysicsHandlerImpl;
import it.unibo.javacrush.model.impl.StallEngineImpl;

public class PhysicsHandlerTest {

    private Board board;
    private PhysicsHandler physics;
    private static final int ROWS = 3;
    private static final int COLS = 3;

    @BeforeEach
    void setUp() {
        this.board = new BoardImpl(ROWS, COLS);
        this.physics = new PhysicsHandlerImpl(new DownwardGravity(), new StallEngineImpl());
    }

    @Test
    void testInitializeBoard() {
        physics.initializeBoard(board);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                assertTrue(board.getCellAt(new Position(col, row)).isPresent(), "the cell at (" + col + ", " + row + ") should be filled");
            }
        }
        assertFalse(physics.update(board), "The board should be stable and not change after initialization");
    }

    /**
     * Tests the "Continuous Flow" logic: when a cell falls, 
     * a new one should immediately enter from the top in the same update step.
     */
    @Test
    void testContinuousFlow() {

        physics.update(board); 
        boolean secondUpdate = physics.update(board);
        
        assertTrue(secondUpdate, "The board should have changed");

        for (int i = 0; i < COLS; i++) {
            // Check if the piece actually moved down
            assertTrue(board.getCellAt(new Position(i, 1)).isPresent(), "Cell at col " + i + " should have fallen from row 0 to row 1");
            
            // Check if a new piece replaced it
            assertTrue(board.getCellAt(new Position(i, 0)).isPresent(), "A new cell should have appeared at col " + i + " row 0");
        }
    }

    /**
     * Tests if the board reaches a stable state (completely full and no more moves possible).
     */
    @Test
    void testStability() {
        /* The safetyCounter prevents infinite loops during testing if the 
         * physics logic fails to stabilize.
         */
        int safetyCounter = 0;
        while (physics.update(board) && safetyCounter < 10) {
            safetyCounter++;
        }
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                assertTrue(board.getCellAt(new Position(col, row)).isPresent(), "the cell at (" + col + ", " + row + ") should be filled");
            }
        }
        assertFalse(physics.update(board), "The board should be stable and not change after the last update");
    }
}
