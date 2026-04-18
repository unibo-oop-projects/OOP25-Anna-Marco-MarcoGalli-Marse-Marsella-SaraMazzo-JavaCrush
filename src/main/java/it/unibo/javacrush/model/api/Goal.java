package it.unibo.javacrush.model.api;

import it.unibo.javacrush.common.CellType;

/**
 * Interface representing a goal in the game.
 */
public interface Goal {
    
    /**
     *  The type of the goal (type of cells that need to be collected).
     * 
     * @return the type of the goal
     */
    CellType getTargetType();

    /**
     * The amount of cells that need to be collected to achieve the goal.
     * 
     * @return the target amount of the goal
     */
    int getTargetAmount();

    /**
     * The current amount of cells collected for this goal.
     * 
     * @return the current amount of the goal
     */
    int getCurrentAmount();

    /**
     * Add the collected cells to the current amount of the goal.
     * 
     * @param count the number of cells collected to be added to the current amount
     */
    void addProgress(int count);

    /**
     * Whether the goal is reached or not.
     * 
     * @return true if the goal is reached, false otherwise
     */
    boolean isReached();
}
