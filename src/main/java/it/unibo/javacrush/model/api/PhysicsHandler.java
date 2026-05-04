package it.unibo.javacrush.model.api;

public interface PhysicsHandler {

    /**
     * Applies the physics to the board, such as gravity and refill at the same step.
     * @param board the game board game to update
     * @return true if the board was changed, false otherwise
     */
    boolean update(Board board);

    /**
     * changes the current gravity strategy at runtime.
     * @param gravity the new gravity strategy to be applied
     */
    void setGravity(GravityEngine gravity);
}
