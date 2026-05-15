package it.unibo.javacrush.model.impl;

import java.util.List;
import java.util.Map;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.impl.gravity.DownwardGravity;
import it.unibo.javacrush.model.impl.gravity.CrazyGravity;
import it.unibo.javacrush.model.impl.gravity.LeftwardGravity;
import it.unibo.javacrush.model.impl.gravity.RightwardGravity;
import it.unibo.javacrush.model.impl.gravity.UpwardGravity;

/**
 * Factory class for creating level configurations based on level number.
 */
public final class LevelFactory {

    private static final int ROWS_EASY = 12;
    private static final int ROWS_MEDIUM = 10;
    private static final int ROWS_HARD = 8;

    private static final int COLS_EASY = 12;
    private static final int COLS_MEDIUM = 10;
    private static final int COLS_HARD = 8;

    private static final int MOVES_EASY = 20;
    private static final int MOVES_MEDIUM = 15;
    private static final int MOVES_HARD = 10;

    private static final int GOAL_EASY = 10;
    private static final int GOAL_MEDIUM = 15;
    private static final int GOAL_HARD = 20;

    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;
    private static final int LEVEL_4 = 4;
    private static final int LEVEL_5 = 5;

    private LevelFactory() {
        // Prevent instantiation
    }

    /**
     * Creates a LevelConfiguration based on the provided level number.
     * 
     * @param levelNumber the number of the level
     * @return a LevelConfig object containing the configuration for the specified level
     */
    public static LevelConfig createLevel(final int levelNumber) {
        return switch (levelNumber) {
            case LEVEL_1 -> new LevelConfig(COLS_EASY, ROWS_EASY, MOVES_EASY,
                Map.of(CellType.MILK, GOAL_EASY),
                new DownwardGravity());

            case LEVEL_2 -> new LevelConfig(COLS_EASY, ROWS_EASY, MOVES_MEDIUM,
                Map.of(CellType.MOKA, GOAL_EASY),
                new DownwardGravity());

            case LEVEL_3 -> new LevelConfig(COLS_MEDIUM, ROWS_MEDIUM, MOVES_HARD,
                Map.of(CellType.CUP, GOAL_MEDIUM),
                new DownwardGravity());

            case LEVEL_4 -> new LevelConfig(COLS_HARD, ROWS_HARD, MOVES_MEDIUM,
                Map.of(CellType.SPOON, GOAL_HARD), 
                new DownwardGravity());

            case LEVEL_5 -> new LevelConfig(COLS_HARD, ROWS_HARD, MOVES_HARD,
                Map.of(CellType.SUGAR, GOAL_HARD),
                new CrazyGravity(allGravities()));

            default -> new LevelConfig(COLS_EASY, ROWS_EASY, MOVES_EASY,
                Map.of(CellType.COFFEE_BEAN, GOAL_EASY),
                new DownwardGravity());
        };

    }

    private static List<GravityEngine> allGravities() {
        return List.of(
            new DownwardGravity(),
            new UpwardGravity(),
            new LeftwardGravity(),
            new RightwardGravity()
        );
    }
}
