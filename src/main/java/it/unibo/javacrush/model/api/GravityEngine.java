package it.unibo.javacrush.model.api;

/**
 * Interface representing the gravity engine in the game.
 */
public interface GravityEngine {

    /**
     * Apply the gravity to the board, making the cells fall down if there are empty cells below them.
     * @param board the board to apply the gravity to
     */
    void applyGravity(Board board);
}
