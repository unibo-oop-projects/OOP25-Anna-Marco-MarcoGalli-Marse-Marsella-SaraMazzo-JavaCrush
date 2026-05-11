package it.unibo.javacrush.model.api;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.model.impl.gravity.DownwardGravity;
import it.unibo.javacrush.model.impl.gravity.LeftwardGravity;
import it.unibo.javacrush.model.impl.gravity.RightwardGravity;
import it.unibo.javacrush.model.impl.gravity.UpwardGravity;

/**
 * Interface representing the gravity engine in the game.
 */
public interface GravityEngine {

    /**
     * Apply the gravity to the board, making the cells fall down if there are empty cells below them.
     * 
     * @param board the board to apply the gravity to
     * 
     * @return true if any cell has moved, false otherwise
     */
    Boolean applyGravity(Board board);

    /**
     * Get the current gravity direction.
     * @return the current gravity direction
     */
    Direction getDirection();

    /**
     * get a random gravity engine instance.
     * @return a random gravity engine instance
     */
    static GravityEngine getRandom() {
        List<Supplier<GravityEngine>> strategies = List.of(
            DownwardGravity::new,
            UpwardGravity::new,
            LeftwardGravity::new,
            RightwardGravity::new
        );
        int randomIndex = new Random().nextInt(strategies.size());
        return strategies.get(randomIndex).get();
    }
}
