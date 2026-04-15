package it.unibo.javacrush.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;

public class BoardImpl implements Board {

    private final Map<Position, Optional<Cell>> cells = new HashMap<>();
    private final int rows;
    private final int cols;
    
    /**
     * BoardImpl constructor.
     * 
     * @param size the size of the board.
     */
    public BoardImpl(int rows, int cols) {

        this.rows = rows;
        this.cols = cols;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //creiamo la griglia delle posizioni senza celle dentro
                this.cells.put(new Position(i, j), Optional.empty());
            }
        }
    }

    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public int getCols() {
        return this.cols;
    }

    @Override
    public Optional<Cell> getCellAt(Position pos) {
        return cells.get(pos);
    }

    @Override
    public void swapCells(Position pos1, Position pos2) {
        var tmp = cells.get(pos1);
        cells.replace(pos1, cells.get(pos2));
        cells.replace(pos2, tmp);
    }

    @Override
    public void setCell(Position pos, Optional<Cell> cell) {
        cells.replace(pos,cell);
    }

}
