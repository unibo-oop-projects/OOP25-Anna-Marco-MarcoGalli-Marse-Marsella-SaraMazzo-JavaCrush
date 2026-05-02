package it.unibo.javacrush.model;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.model.api.Session;
import it.unibo.javacrush.model.impl.SessionImpl;
import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.model.api.Goal;
import it.unibo.javacrush.model.api.GoalFactory;

/**
 * Test for {@link it.unibo.javacrush.model.api.Session}.
 */
public class SessionTest {

    private static final int INITIAL_MOVES = 10;
    // We create an example of grid
    private static final Map<CellType, Integer> GOAL_CONFIGURATION = Map.of(
        CellType.COFFEE_BEAN, 10,
        CellType.MILK, 5,
        CellType.SUGAR, 15
    );
    //private static final GoalFactory FACTORY = new GoalFactoryImpl();
    private Session session;

    @BeforeEach
    void initialize() {
        //session = new SessionImpl(INITIAL_MOVES, GOAL_CONFIGURATION, FACTORY);
    }

    /**
     * Test that the session returns the correct number of remaining moves.
     */
    @Test
    void testGetMovesLeft() {
        assertEquals(INITIAL_MOVES, this.session.getMovesLeft());
    }

    /**
     * Test the decreaseMoves method after one and multiple usage.
     */
    @Test
    void testMovesDecrease() {
        this.session.decreaseMoves();

        // Control after a single decrease
        assertTrue(this.session.getMovesLeft() > 0);
        assertEquals((INITIAL_MOVES - 1), this.session.getMovesLeft());

        // Control after multiple decreases
        for (int i = 0; i < INITIAL_MOVES - 1; i++) {
            this.session.decreaseMoves();
        }
        assertEquals(0, this.session.getMovesLeft());
    }

    /**
     * Test that moves decrease when already at zero calls an IllegalStateException.
     */
    @Test
    void testMovesDecreaseWhenAlreadyZero() {
        for (int i = 0; i < INITIAL_MOVES; i++) {
            this.session.decreaseMoves();
        }

        assertThrows(
            IllegalStateException.class, 
            () -> this.session.decreaseMoves(),
            "The game cannot have a negative number of moves"
        );
    }

    /**
     * Test getGoals method.
     */
    @Test
    void testGetGoals() {
        List<Goal> goals = this.session.getGoals();

        assertNotNull(goals);
        assertThrows(
            UnsupportedOperationException.class,
            () -> goals.clear(),
            "The list should be unmodifiable"
        );
    }

    /**
     * Test getGameStatus method at the beginning of the session.
     */
    @Test
    void testGamePlaying() {
        assertEquals(GameState.PLAYING, this.session.getGameStatus());
    }

    /**
     * Test updateGoals method with existing CellType.
     */
    @Test
    void testUpdateGoalsWithExistingType() {
        // We create a template goal using stream
        Goal sugarGoal = this.session.getGoals().stream()
            .filter(elem -> elem.getTargetType() == CellType.SUGAR)
            .findAny()
            .orElseThrow();

        assertEquals(0, sugarGoal.getCurrentAmount());

        this.session.updateGoals(CellType.SUGAR, 3);

        assertEquals(3, sugarGoal.getCurrentAmount());
    }

    /**
     * Test updateGoals method without existing CellType.
     */
    @Test
    void testUpdateGoalsWithoutExistingType() {
        this.session.updateGoals(CellType.CUP, 3);

        // No goal should be updated
        this.session.getGoals().stream()
            .forEach(
                goal -> assertEquals(0, goal.getCurrentAmount())
            );
    }

    /**
     * Test getGameStatus method when the game is won.
     */
    @Test
    void testGameWon() {
        GOAL_CONFIGURATION.forEach((type, amount) -> {
            this.session.updateGoals(type, amount);
        });

        assertTrue(this.session.getMovesLeft() > 0);
        assertEquals(GameState.WON, this.session.getGameStatus());
    }

    /**
     * Test getGameStatus method when the game is lost.
     */
    @Test
    void testGameLost() {
        for (int i = 0; i < INITIAL_MOVES; i++) {
            this.session.decreaseMoves();
        }

        assertEquals(0, this.session.getMovesLeft());
        assertEquals(GameState.LOST, this.session.getGameStatus());
    }
}
