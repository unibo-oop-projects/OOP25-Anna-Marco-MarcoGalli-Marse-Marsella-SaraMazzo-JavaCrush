package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;
import it.unibo.javacrush.model.impl.StallEngineImpl;

/**
 * Test for {@link it.unibo.javacrush.model.api.StallEngine}.
 */
public class StallEngineTest {

    private Board board;
    private Board initial;
    private StallEngine st;
    private static final int DIMENSION = 4;

    /**
     * Initialises and fill board and initial with equal-typed cells.
     */
    @BeforeEach
    void init() {

        board = new BoardImpl(DIMENSION, DIMENSION);
        initial = new BoardImpl(DIMENSION, DIMENSION);
        st = new StallEngineImpl();

        for (int y = 0; y < board.getCols(); y++) {
            for (int x = 0; x < board.getRows(); x++) {
                initial.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.CUP)));
                board.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.CUP)));
            }
        }
    }

    /**
     * Test that StallEngine throws ad Exception if the given board contains any null cell.
     */
    @Test
    void testNoStallEmptyBoard() {
        for (int y = 0; y < DIMENSION; y++) {
            for (int x = 0; x < DIMENSION; x++) {
                initial.setCell(new Position(x, y), Optional.empty());
                board.setCell(new Position(x, y), Optional.empty());
            }
        }
        assertThrows(NoSuchElementException.class, () -> {
            st.resolveStall(board);
        }, "Constructor should throw NoSuchElementException if any cell of the board is empty");
    }

    /**
     * Test that StallEngine doesn't modify the given board if there are possible moves.
     */
    @Test
    void testNoStallWithMoves() {

        //set board and init with the same distribution of cellTypes that forms possible moves
        board.setCell(new Position(1, 0), Optional.of(new CellImpl(CellType.MILK)));
        board.setCell(new Position(0, 1), Optional.of(new CellImpl(CellType.MILK)));
        board.setCell(new Position(1, 2), Optional.of(new CellImpl(CellType.MILK)));
        board.setCell(new Position(2, 1), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(3, 1), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(0, 3), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(1, 3), Optional.of(new CellImpl(CellType.SPOON)));
        board.setCell(new Position(2, 2), Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(new Position(3, 2), Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(new Position(2, 3), Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(new Position(3, 3), Optional.of(new CellImpl(CellType.MOKA)));

        initial.setCell(new Position(1, 0), Optional.of(new CellImpl(CellType.MILK)));
        initial.setCell(new Position(0, 1), Optional.of(new CellImpl(CellType.MILK)));
        initial.setCell(new Position(1, 2), Optional.of(new CellImpl(CellType.MILK)));
        initial.setCell(new Position(2, 1), Optional.of(new CellImpl(CellType.SPOON)));
        initial.setCell(new Position(3, 1), Optional.of(new CellImpl(CellType.SPOON)));
        initial.setCell(new Position(0, 3), Optional.of(new CellImpl(CellType.SPOON)));
        initial.setCell(new Position(1, 3), Optional.of(new CellImpl(CellType.SPOON)));
        initial.setCell(new Position(2, 2), Optional.of(new CellImpl(CellType.MOKA)));
        initial.setCell(new Position(3, 2), Optional.of(new CellImpl(CellType.MOKA)));
        initial.setCell(new Position(2, 3), Optional.of(new CellImpl(CellType.MOKA)));
        initial.setCell(new Position(3, 3), Optional.of(new CellImpl(CellType.MOKA)));

        assertFalse(st.isStall(board));

        st.resolveStall(board);

        assertFalse(st.isStall(board));
        assertEquals(initial, board);

    }

    /**
     * Test that, if there's stall, StallEngine shuffles the cells in a manner that possible moves appear.
     */
    @Test
    void testStall() {

        int index = 0;
        for (int y = 0; y < DIMENSION; y++) {
            for (int x = 0; x < DIMENSION; x++) {
                initial.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.values()[index % CellType.values().length])));
                board.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.values()[index % CellType.values().length])));
                index++;
            }
        }

        assertTrue(st.isStall(board));

        st.resolveStall(board);

        assertFalse(st.isStall(board));
        assertNotEquals(initial, board);

    }
}
