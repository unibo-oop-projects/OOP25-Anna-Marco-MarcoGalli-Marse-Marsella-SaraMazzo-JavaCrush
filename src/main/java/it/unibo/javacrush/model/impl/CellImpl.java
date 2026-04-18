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

}
