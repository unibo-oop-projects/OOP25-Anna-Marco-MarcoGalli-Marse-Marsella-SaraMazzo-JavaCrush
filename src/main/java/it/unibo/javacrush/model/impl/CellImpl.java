package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.model.api.Cell;
import java.util.Objects;

public class CellImpl implements Cell{

    private final CellType type;

    public CellImpl(final CellType type) {
        this.type = Objects.requireNonNull(type, "Cell type cannot be null");
    }

    @Override
    public CellType getType() {
        return this.type;
    }

    public String toString() {
           return "Cell[" + this.type + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        CellImpl other = (CellImpl) obj;
        if (type != other.type)
            return false;
        return true;
    }
}
