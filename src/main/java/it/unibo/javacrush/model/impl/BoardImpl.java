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
                this.cells.put(new Position(j, i), Optional.empty());
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
    public Map<Position, Optional<Cell>> getGrid() {
        return Map.copyOf(this.cells);
    }

    @Override
    public void swapCells(Position pos1, Position pos2) {
        var tmp = cells.get(pos1);
        cells.put(pos1, cells.get(pos2));
        cells.put(pos2, tmp);
    }

    @Override
    public void setCell(Position pos, Optional<Cell> cell) {
        cells.put(pos,cell);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cells == null) ? 0 : cells.hashCode());
        result = prime * result + rows;
        result = prime * result + cols;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BoardImpl other = (BoardImpl) obj;
        if (cells == null) {
            if (other.cells != null)
                return false;
        } else if (!cells.equals(other.cells))
            return false;
        if (rows != other.rows)
            return false;
        if (cols != other.cols)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BoardImpl [cells=" + cells + "]";
    }

}
