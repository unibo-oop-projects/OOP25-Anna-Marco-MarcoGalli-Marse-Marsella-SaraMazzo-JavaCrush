package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.gravity.*;


public class CrazyGravityTest {

    @Test
    void testEmpryListThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new CrazyGravity(List.of()),
            "CrazyGravity should throw an exception if initialized with an empty strategy list");
    }
    @Test
    void testGravityChangesDirection() {

        final List<GravityEngine> strategies = List.of(new DownwardGravity(), new UpwardGravity());
        final CrazyGravity crazyGravity = new CrazyGravity(strategies);
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
