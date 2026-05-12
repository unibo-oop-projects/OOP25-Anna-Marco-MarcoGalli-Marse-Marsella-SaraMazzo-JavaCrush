package it.unibo.javacrush.model.api;

import java.util.Map;

import it.unibo.javacrush.common.CellType;

/**
 * Represents the configuration for a game level.
 */
public record LevelConfig(
    int rows,
    int cols,
    int moves,
    Map<CellType, Integer> targetCells,
    GravityEngine gravity,
    boolean isDynamic
) {}
