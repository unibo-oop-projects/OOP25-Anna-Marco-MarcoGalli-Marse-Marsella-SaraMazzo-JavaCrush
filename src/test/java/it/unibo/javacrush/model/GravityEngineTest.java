package it.unibo.javacrush.model;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;
import it.unibo.javacrush.model.impl.DownwardGravity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class GravityEngineTest {

    private Board board;
    private GravityEngine gravity;
    private static final int ROWS = 3;
    private static final int COLS = 3;

    @BeforeEach
    void setUp() {
        board = new BoardImpl(ROWS, COLS);
        gravity = new DownwardGravity();
    }

    @Test
    void testSingleStepFalling() {
        Position start = new Position(1,0);
        Position expected = new Position(1,1);
        Cell moka = new CellImpl(CellType.MOKA);
        board.setCell(start, Optional.of(moka));

        gravity.applyGravity(board);

        assertTrue(board.getCellAt(start).isEmpty(), "The original position should be empty after falling.");
        assertTrue(board.getCellAt(expected).isPresent(), "The new position should contain the cell after falling.");
        assertEquals(moka, board.getCellAt(expected).get(), "The cell in the new position should be the same as the original cell.");}

    @Test
    void testBlockedByAnotherCell() {
        Cell topCell = new CellImpl(CellType.MOKA);
        Cell bottomCell = new CellImpl(CellType.CUP);

        Position pos0 = new Position(0,0);
        Position pos1 = new Position(0,1);

        board.setCell(pos0, Optional.of(topCell));
        board.setCell(pos1, Optional.of(bottomCell));

        gravity.applyGravity(board);

        assertTrue(board.getCellAt(pos0).isPresent(), "The top cell should remain in place.");
        assertEquals(topCell, board.getCellAt(pos0).get(), "The top cell should be unchanged.");
        assertTrue(board.getCellAt(pos1).isPresent(), "The bottom cell should remain in place.");
        assertEquals(bottomCell, board.getCellAt(pos1).get(), "The bottom cell should be unchanged.");
    }

    @Test
    void testReachingTheBottom() {
        Position top = new Position(0,0);
        Cell moka = new CellImpl(CellType.MOKA);
        board.setCell(top, Optional.of(moka));

        gravity.applyGravity(board);
        gravity.applyGravity(board); // Apply gravity multiple times to ensure it reaches the bottom

        assertTrue(board.getCellAt(new Position(0, 2)).isPresent(), "The cell should have fallen to the bottom.");
        assertDoesNotThrow(() -> gravity.applyGravity(board), "Accessing out of bounds should not throw an exception.");
        assertTrue(board.getCellAt(new Position(0, 2)).isPresent());
    }

}
