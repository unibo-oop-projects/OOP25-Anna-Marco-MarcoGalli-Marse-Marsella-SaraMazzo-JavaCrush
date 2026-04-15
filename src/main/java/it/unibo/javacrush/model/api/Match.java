package it.unibo.javacrush.model.api;

import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;

public interface Match {

    CellType getType();

    Set<Position> getPositions();

    int getSize();

    boolean isEmpty();

}