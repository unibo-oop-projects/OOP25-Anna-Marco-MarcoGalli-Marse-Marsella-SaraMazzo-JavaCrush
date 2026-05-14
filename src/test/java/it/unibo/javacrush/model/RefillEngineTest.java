package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.RefillEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.gravity.*;
import it.unibo.javacrush.model.impl.AdaptiveRefill;

public class RefillEngineTest {
    private RefillEngine refillEngine;
    private Board board;
    private GravityEngine gravity;
    private final int ROWS = 3;
    private final int COLS = 3;

    @BeforeEach
    public void setUp() {
        this.gravity = new DownwardGravity();
        this.refillEngine = new AdaptiveRefill(gravity);
        this.board = new BoardImpl(ROWS, COLS);
    }

    @Test
    public void testRefill() {
        for (int i = 0; i < COLS; i++) {
            assertTrue(board.getCellAt(new Position(i, 0)).isEmpty(), "The cell should be empty before refill");
        }

        boolean changed = refillEngine.refill(board);
        assertTrue(changed, "The refill method should return true if the board was changed");

        for (int i = 0; i < COLS; i++) {
            assertTrue(board.getCellAt(new Position(i, 0)).isPresent(), "The cell should be filled after refill");
        }

        clearBoard();
        this.gravity = new UpwardGravity();
        this.refillEngine = new AdaptiveRefill(gravity);
        changed = refillEngine.refill(board);

        for (int col = 0; col < COLS; col++) {
            assertTrue(board.getCellAt(new Position(col, ROWS - 1)).isPresent(), "The cell at the bottom should be filled after refill with upward gravity");
        }

        clearBoard();
        this.gravity = new LeftwardGravity();
        this.refillEngine = new AdaptiveRefill(gravity);
        changed = refillEngine.refill(board);

        for (int row = 0; row < ROWS; row++) {
            assertTrue(board.getCellAt(new Position(COLS - 1, row)).isPresent(), "The cell at the rightmost column should be filled after refill with leftward gravity");
        }

        clearBoard();
        this.gravity = new RightwardGravity();
        this.refillEngine = new AdaptiveRefill(gravity);
        changed = refillEngine.refill(board);

        for (int row = 0; row < ROWS; row++) {
            assertTrue(board.getCellAt(new Position(0, row)).isPresent(), "The cell at the leftmost column should be filled after refill with rightward gravity");
        }
    }

    @Test
    public void testRefillNoChange() {
        refillEngine.refill(board);
        boolean changed = refillEngine.refill(board);
        assertFalse(changed, "The refill method should return false if the board was not changed");
    }

    @Test
    void refillAll() {
        refillEngine.refillAll(board);
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                assertTrue(board.getCellAt(new Position(col, row)).isPresent(), "the cell at (" + col + ", " + row + ") should be filled");
            }
        }
    }

    private void clearBoard() {
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                board.setCell(new Position(c, r), Optional.empty());
            }
        }
    }
}
