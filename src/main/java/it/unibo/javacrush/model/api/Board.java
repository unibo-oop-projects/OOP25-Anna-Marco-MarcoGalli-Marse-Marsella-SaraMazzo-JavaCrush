package it.unibo.javacrush.model.api;

import it.unibo.javacrush.common.Position;

/**
 * Interface representing a game board.
 * This interface defines the contract for a game board,
 * which is composed of cells and is responsible for managing the game state.
 */
public interface Board {

    int getRows();

    int getCols();

    Cell getCellAt(Position pos);

}
