package it.unibo.javacrush.model.impl;

import java.util.List;
import java.util.Map;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.impl.gravity.*;

public class LevelFactory {

    private LevelFactory() {
        // Prevent instantiation
    }

    public static LevelConfig createLevel(int levelNumber) {
        return switch (levelNumber) {
            case 1 -> new LevelConfig(8, 8, 20,
                Map.of(CellType.MILK, 10),
                new DownwardGravity());

            case 2 -> new LevelConfig(6, 6, 25, 
                Map.of(CellType.MOKA, 15), 
                new DownwardGravity());

            case 3 -> new LevelConfig(7, 7, 30, 
                Map.of(CellType.CUP, 20), 
                new DownwardGravity());

            case 4 -> new LevelConfig(8, 8, 35, 
                Map.of(CellType.SPOON, 25), 
                new DownwardGravity());

            case 5 -> new LevelConfig(9, 9, 40, 
                Map.of(CellType.SUGAR, 30), 
                new CrazyGravity(allGravities()));

            default -> new LevelConfig(8, 8, 20, 
                Map.of(CellType.COFFEE_BEAN, 10), 
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
