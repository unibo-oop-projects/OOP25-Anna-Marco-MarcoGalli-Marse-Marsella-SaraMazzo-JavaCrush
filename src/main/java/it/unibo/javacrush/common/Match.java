package it.unibo.javacrush.common;

import java.util.Set;

public record Match(Set<Position> positions, CellType type) {
    
    public Match {
        positions = Set.copyOf(positions); 
    }

    public int getSize() {
        return positions.size();
    }
}
