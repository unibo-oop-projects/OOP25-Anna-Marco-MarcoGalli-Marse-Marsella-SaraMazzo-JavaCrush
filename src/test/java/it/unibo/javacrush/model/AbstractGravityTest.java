package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;

public abstract class AbstractGravityTest {

    protected Board board;
    protected GravityEngine gravity;

     private static final int ROWS = 5;
     private static final int COLS = 5;

     @BeforeEach
        void setUp() {
            board = new BoardImpl(ROWS, COLS);
            gravity = createGravity();
        }
    
    protected abstract GravityEngine createGravity();

    @Test
    void testSingleMovement() {
        Position start = new Position(2, 2);
        Direction dir = gravity.getDirection();

        Position expected = new Position(start.x() + dir.getDx(), start.y() + dir.getDy());
        board.setCell(start,Optional.of(new CellImpl(CellType.MOKA)));
        boolean moved = gravity.applyGravity(board);

        assertTrue(moved, "Expected movement in direction " + dir);
        assertTrue(board.getCellAt(start).isEmpty());
        assertTrue(board.getCellAt(expected).isPresent());
    }

    @Test
    void testBlockedByAnotherCell() {
        Direction dir = gravity.getDirection();
        int edgeX = dir.getDx() > 0 ? COLS - 1 : (dir.getDx() < 0 ? 0 : 2);
        int edgeY = dir.getDy() > 0 ? ROWS - 1 : (dir.getDy() < 0 ? 0 : 2);

        Position posEdge = new Position(edgeX, edgeY);
        Position posBeforeEdge = new Position(edgeX - dir.getDx(), edgeY - dir.getDy());
        
        board.setCell(posEdge, Optional.of(new CellImpl(CellType.MOKA)));
        board.setCell(posBeforeEdge, Optional.of(new CellImpl(CellType.CUP)));

        boolean moved = gravity.applyGravity(board);
        assertFalse(moved, "Expected no movement due to blockage");
    }

    @Test
    void testReachingTheEdge() {
        Direction dir = gravity.getDirection();
        
        int startX = dir.getDx() > 0 ? 0 : (dir.getDx() < 0 ? COLS - 1 : 2);
        int startY = dir.getDy() > 0 ? 0 : (dir.getDy() < 0 ? ROWS - 1 : 2);
        Position startPos = new Position(startX, startY);

        board.setCell(startPos, Optional.of(new CellImpl(CellType.MOKA)));

        for (int i = 0; i < Math.max(ROWS, COLS); i++) {
            gravity.applyGravity(board);
        }

        int endX = dir.getDx() > 0 ? COLS - 1 : (dir.getDx() < 0 ? 0 : startX);
        int endY = dir.getDy() > 0 ? ROWS - 1 : (dir.getDy() < 0 ? 0 : startY);
        Position endPos = new Position(endX, endY);

        assertTrue(board.getCellAt(endPos).isPresent(), "Expected cell to reach the edge");
        assertDoesNotThrow(() -> gravity.applyGravity(board));
    }
}
