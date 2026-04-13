package it.unibo.javacrush.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;

public class BoardImpl implements Board {

    private Map<Position, Optional<Cell>> cells = new HashMap<>();
    
    /**
     * BoardImpl constructor.
     * 
     * @param size the size of the board.
     */
    public BoardImpl(int size) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                //creiamo la griglia delle posizioni senza celle dentro
                this.cells.put(new Position(i, j), Optional.empty());
            }
        }
    }

    @Override
    public int getRows() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRows'");
    }

    @Override
    public int getCols() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCols'");
    }

    @Override
    public Optional<Cell> getCellAt(Position pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCellAt'");
    }

    @Override
    public void swapCells(Position pos1, Position pos2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'swapCells'");
    }

}
