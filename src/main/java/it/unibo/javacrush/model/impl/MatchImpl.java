package it.unibo.javacrush.model.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Match;

public class MatchImpl implements Match {

    private final Set<Position> positions = new HashSet<>();
    private final CellType type;

    public MatchImpl(Set<Position> positions, CellType type) {
        this.positions.addAll(positions);
        this.type = type;
    }

    @Override
    public CellType getType() {
        return this.type;
    }

    @Override
    public Set<Position> getPositions() {
        return Set.copyOf(this.positions);
    }

    @Override
    public int getSize() {
        return positions.size();
    }

    @Override
    public boolean isEmpty() {
        return positions.isEmpty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((positions == null) ? 0 : positions.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        MatchImpl other = (MatchImpl) obj;
        return positions.equals(other.positions) && type == other.type;
    }
}
