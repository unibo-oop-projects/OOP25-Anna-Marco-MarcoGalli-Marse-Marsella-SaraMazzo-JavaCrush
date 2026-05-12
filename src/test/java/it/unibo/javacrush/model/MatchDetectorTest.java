package it.unibo.javacrush.model;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.impl.BoardImpl;
import it.unibo.javacrush.model.impl.CellImpl;
import it.unibo.javacrush.model.impl.MatchManagerImpl;

class MatchDetectorTest {

    private Board board;
    private MatchManager detector;

    @BeforeEach
    void init() {
        board = new BoardImpl(5, 5);
        detector = new MatchManagerImpl();
    }

    @Test
    void testFindMatchesAtHorizontal() {
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,0);
        Position pos3 = new Position(2,0);
        CellType expectedType = CellType.CUP;

        board.setCell(pos1, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos2, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos3, Optional.of(new CellImpl(expectedType)));

        Match horizontalMatch = detector.findMatchesAt(board, new Position(1, 0));

        assertFalse(horizontalMatch.isEmpty());
        assertEquals(3, horizontalMatch.getSize());
        assertEquals(Set.of(pos1,pos2,pos3), horizontalMatch.getPositions());
        assertEquals(expectedType, horizontalMatch.getType());
    }

    @Test
    void testFindMatchesAtVertical() {
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(0,1);
        Position pos3 = new Position(0,2);
        Position pos4 = new Position(0,3);
        CellType expectedType = CellType.CUP;

        board.setCell(pos1, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos2, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos3, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos4, Optional.of(new CellImpl(expectedType)));

        Match verticalMatch = detector.findMatchesAt(board, new Position(0, 1));

        assertFalse(verticalMatch.isEmpty());
        assertEquals(4, verticalMatch.getSize());
        assertEquals(Set.of(pos1,pos2,pos3,pos4), verticalMatch.getPositions());
        assertEquals(expectedType, verticalMatch.getType());
    }

    @Test
    void testFindMatchesAtTshape() {
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,0);
        Position pos3 = new Position(2,0);
        Position pos4 = new Position(1,1);
        Position pos5 = new Position(1,2);
        CellType expectedType = CellType.MILK;

        board.setCell(pos1, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos2, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos3, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos4, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos5, Optional.of(new CellImpl(expectedType)));

        Match tShapeMatch = detector.findMatchesAt(board, new Position(1, 0));

        assertFalse(tShapeMatch.isEmpty());
        assertEquals(5, tShapeMatch.getSize());
        assertEquals(Set.of(pos1,pos2,pos3,pos4,pos5), tShapeMatch.getPositions());
        assertEquals(expectedType, tShapeMatch.getType());
    }

    @Test
    void testFindMatchesAtLshape() {
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(0,1);
        Position pos3 = new Position(0,2);
        Position pos4 = new Position(1,2);
        Position pos5 = new Position(2,2);
        Position pos6 = new Position(3,2);
        CellType expectedType = CellType.SUGAR;

        board.setCell(pos1, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos2, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos3, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos4, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos5, Optional.of(new CellImpl(expectedType)));
        board.setCell(pos6, Optional.of(new CellImpl(expectedType)));

        Match lShapeMatch = detector.findMatchesAt(board, new Position(0, 2));

        assertFalse(lShapeMatch.isEmpty());
        assertEquals(6, lShapeMatch.getSize());
        assertEquals(Set.of(pos1,pos2,pos3,pos4,pos5,pos6), lShapeMatch.getPositions());
        assertEquals(expectedType, lShapeMatch.getType());
    }

    @Test
    void testFindMatchesAtNoMatch() {
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,0);
        Position pos3 = new Position(2,0);
        CellType type1 = CellType.CUP;
        CellType type2 = CellType.MILK;

        board.setCell(pos1, Optional.of(new CellImpl(type1)));
        board.setCell(pos2, Optional.of(new CellImpl(type1)));
        board.setCell(pos3, Optional.of(new CellImpl(type2)));

        Match noMatch = detector.findMatchesAt(board, new Position(1, 0));

        assertNull(noMatch);
    }

    @Test
    void testFindAllMatches() {
        Position pos1 = new Position(0,0);
        Position pos2 = new Position(1,0);
        Position pos3 = new Position(2,0);

        Position pos4 = new Position(3,1);
        Position pos5 = new Position(3,2);
        Position pos6 = new Position(3,3);
        Position pos7 = new Position(3,4);

        CellType expectedType1 = CellType.COFFEE_BEAN;
        CellType expectedType2 = CellType.SUGAR;

        board.setCell(pos1, Optional.of(new CellImpl(expectedType1)));
        board.setCell(pos2, Optional.of(new CellImpl(expectedType1)));
        board.setCell(pos3, Optional.of(new CellImpl(expectedType1)));

        board.setCell(pos4, Optional.of(new CellImpl(expectedType2)));
        board.setCell(pos5, Optional.of(new CellImpl(expectedType2)));
        board.setCell(pos6, Optional.of(new CellImpl(expectedType2)));
        board.setCell(pos7, Optional.of(new CellImpl(expectedType2)));

        Set<Match> allMatches = detector.findAllMatches(board);

        assertEquals(2, allMatches.size());
    }
}
