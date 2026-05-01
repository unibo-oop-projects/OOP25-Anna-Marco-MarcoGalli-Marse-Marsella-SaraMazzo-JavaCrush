package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.RefillEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.DownwardRefill;

public class RefillEngineTest {
    private RefillEngine refillEngine;
    private Board board;
    private final int ROWS = 3;
    private final int COLS = 3;

    @BeforeEach
    public void setUp() {
        this.refillEngine = new DownwardRefill();
        this.board = new BoardImpl(ROWS, COLS);
    }

    @Test
    public void testRefill() {
        for (int i = 0; i < COLS; i++) {
            assertTrue(board.getCellAt(new Position(0, i)).isEmpty(), "The cell should be empty before refill");
        }

        boolean changed = refillEngine.refill(board);
        assertTrue(changed, "The refill method should return true if the board was changed");

        for (int i = 0; i < COLS; i++) {
            assertTrue(board.getCellAt(new Position(0, i)).isPresent(), "The cell should be filled after refill");
        }
    }

    @Test
    public void testRefillNoChange() {
        refillEngine.refill(board);
        boolean changed = refillEngine.refill(board);
        assertFalse(changed, "The refill method should return false if the board was not changed");
    }
}
