package it.unibo.javacrush.model.api;

/**
 * Interface representing the refill engine in the game.
 * This interface defines the contract for a refill engine,
 * which is responsible for refilling the board after the gravity has been applied.
 */
public interface RefillEngine {

    /**
     * Refill the board, filling the empty cells with new random cells.
     * @param board the board to refill
     */
    void refill(Board board);

}
