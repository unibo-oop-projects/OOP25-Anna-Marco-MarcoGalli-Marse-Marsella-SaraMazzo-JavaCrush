package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;
import it.unibo.javacrush.powerup.api.PowerUpManager;
import it.unibo.javacrush.powerup.impl.PowerUpManagerImpl;

/**
 * Test for {@link it.unibo.javacrush.powerup.api.PowerUpManager}.
 */
class PowerUpManagerTest {

    private static final int DIM = 3;
    private static final int CLEANER = 0;
    private static final int MELTER = 1;
    private static final int VAPORIZER = 2;
    private PowerUpManager manager;
    private Board board;
    private Board initial;
    private Position pos;

    /**
     * Initialises the two Boards filling all their cells with MOKA and the PowerUpManager.
     */
    @BeforeEach
    void init() {
        this.board = new BoardImpl(DIM, DIM);
        this.initial = new BoardImpl(DIM, DIM);
        this.manager = new PowerUpManagerImpl();
        this.pos = new Position(0, 0);

        for (int y = 0; y < board.getCols(); y++) {
            for (int x = 0; x < board.getRows(); x++) {
                board.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.MOKA)));
                initial.setCell(new Position(x, y), Optional.of(new CellImpl(CellType.MOKA)));
            }
        }

    }

    @Test
    void testSelectPowerUp() {
        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.selectPowerUp(-1));
        assertFalse(manager.selectPowerUp(3));
        assertTrue(manager.selectPowerUp(0));
    }

    @Test
    void testApplyCleanerPowerUp() {

        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));
        assertEquals(board, initial);

        assertTrue(manager.selectPowerUp(CLEANER));
        assertTrue(manager.isPowerUpSelected());
        assertTrue(manager.applyPowerUp(board, pos));
        assertFalse(manager.applyPowerUp(board, pos));

        assertTrue(board.getCellAt(pos).isEmpty());
        assertNotEquals(board, initial);

        assertTrue(manager.resetPowerUpSelection());
        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));

    }

    @Test
    void testApplyMelterPowerUp() {

        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));
        assertEquals(board, initial);

        assertTrue(manager.selectPowerUp(MELTER));
        assertTrue(manager.isPowerUpSelected());
        assertTrue(manager.applyPowerUp(board, pos));
        assertFalse(manager.applyPowerUp(board, pos));

        for (int x = 0; x < board.getCols(); x++) {
            assertTrue(board.getCellAt(new Position(x, pos.y())).isEmpty());
        }

        assertNotEquals(board, initial);

        assertTrue(manager.resetPowerUpSelection());
        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));

    }

    @Test
    void testApplyVaporizerPowerUp() {

        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));
        assertEquals(board, initial);

        assertTrue(manager.selectPowerUp(VAPORIZER));
        assertTrue(manager.isPowerUpSelected());
        assertTrue(manager.applyPowerUp(board, pos));
        assertFalse(manager.applyPowerUp(board, pos));

        for (int y = 0; y < board.getRows(); y++) {
            for (int x = 0; x < board.getCols(); x++) {
                assertTrue(board.getCellAt(new Position(x, y)).isEmpty());
            }
        }

        assertNotEquals(board, initial);

        assertTrue(manager.resetPowerUpSelection());
        assertFalse(manager.isPowerUpSelected());
        assertFalse(manager.applyPowerUp(board, pos));

    }

}
