package it.unibo.javacrush.model.api;

import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;

public interface Match {

    /**
     * Return the type of the Match.
     * 
     * @return the type of this Match.
     */
    CellType getType();

    /**
     * Return the Set of the Position that forms the Match.
     * 
     * @return the Set of Position of the Match.
     */
    Set<Position> getPositions();

    /**
     * Return the size of the Match.
     * 
     * @return the size of the Set of Position.
     */
    int getSize();

    /**
     * Return wheter the Match is empy or not.
     * 
     * @return true if the Match is empy, false otherwise.
     */
    boolean isEmpty();

}