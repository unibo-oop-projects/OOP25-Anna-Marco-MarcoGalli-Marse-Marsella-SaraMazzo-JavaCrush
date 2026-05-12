package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.gravity.CrazyGravity;


public class CrazyGravityTest {

    @Test
    void testGravityChangesDirection() {

        final CrazyGravity crazyGravity = new CrazyGravity();
        final Board board = new BoardImpl(8, 8);

        Direction firstDir = crazyGravity.getDirection();
        assertNotNull(firstDir, "CrazyGravity should return a non-null direction");
        
        crazyGravity.applyGravity(board);

        boolean changed = false;
        for (int i = 0; i < 20; i++) {
            crazyGravity.applyGravity(board);
                if (!crazyGravity.getDirection().equals(firstDir)) {
                    changed = true;
                    break;

                }  
        }
        assertTrue(changed, "CrazyGravity should change direction after applying gravity");
    }
}
