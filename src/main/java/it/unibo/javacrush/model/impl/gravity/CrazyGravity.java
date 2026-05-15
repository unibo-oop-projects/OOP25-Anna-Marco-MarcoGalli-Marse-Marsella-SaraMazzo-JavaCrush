package it.unibo.javacrush.model.impl.gravity;

import java.util.List;
import java.util.Random;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;

/**
 * A gravity engine that randomly changes its direction after each application.
 */
public class CrazyGravity implements GravityEngine {

    private GravityEngine currentStrategy;
    private final Random random = new Random();
    private final List<GravityEngine> strategies;

    public CrazyGravity(final List<GravityEngine> strategies) {
        if(strategies == null || strategies.isEmpty()) {
            throw new IllegalArgumentException("Strategies list cannot be null or empty");
        }
        this.strategies = List.copyOf(strategies);
        this.currentStrategy = getRandomStrategy();
    }
    private GravityEngine getRandomStrategy() {
        return strategies.get(random.nextInt(strategies.size()));
    }

    @Override
    public Boolean applyGravity(final Board board) {
        final boolean moved = currentStrategy.applyGravity(board);
        if(!moved) {
            this.currentStrategy = getRandomStrategy();
        }
        return moved;
    }

    @Override
    public Direction getDirection() {
        return currentStrategy.getDirection();
    }
}
