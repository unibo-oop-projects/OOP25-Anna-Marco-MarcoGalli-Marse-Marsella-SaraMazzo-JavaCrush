package it.unibo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;
import it.unibo.javacrush.model.impl.StallEngineImpl;

public class StallEngineTest {
    
    @Test
    void testNoStall() {
        Board initial = new BoardImpl(5, 5);
        Board board = new BoardImpl(5, 5);
        
        StallEngine st = new StallEngineImpl();

        for (int y = 0; y < board.getCols(); y++) {
            for (int x = 0; x < board.getRows(); x++) {
                initial.setCell(new Position(x, y), Optional.of(new CellImpl(/*scegliere il tipo della cella*/)));
                board.setCell(new Position(x, y), Optional.of(new CellImpl(/*scegliere il tipo della cella*/)));
            }
        };

        assertEquals(initial, board);
    }
}
