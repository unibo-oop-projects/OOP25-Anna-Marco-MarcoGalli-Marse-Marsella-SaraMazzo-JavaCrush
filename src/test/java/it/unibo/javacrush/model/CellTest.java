package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.impl.CellImpl;

public class CellTest {

    @Test
    void testCellCreation() {
        CellType expectedType = CellType.COFFEE_BEAN;    
        Cell cell = new CellImpl(expectedType);

        // verify that the cell returns the correct type
        assertEquals(expectedType, cell.getType(), "Cell type should match the expected type");
    }

    @Test
    void testNullType() {
        assertThrows(NullPointerException.class, () -> {
            new CellImpl(null);
        }, "Constructor should throw NullPointerException if type is null");
    }

    @Test
    void testToString() {
        Cell randomTypeCell = new CellImpl(CellType.getRandomType());
        var expectedString = "Cell[" + randomTypeCell.getType() + "]";
        assertEquals(expectedString, randomTypeCell.toString(), "toString should return the correct format");

        Cell cell = new CellImpl(CellType.SUGAR);
        assertEquals("Cell[SUGAR]", cell.toString(), "toString should return the correct format");
    }

}
