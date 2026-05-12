package it.unibo.javacrush.model.impl.gravity;

import it.unibo.javacrush.common.Direction;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;

public class CrazyGravity implements GravityEngine{

    private GravityEngine currentStrategy;

    public CrazyGravity() {
        this.currentStrategy = GravityEngine.getRandom();
    }
    @Override
    public Boolean applyGravity(Board board) {
        boolean moved = currentStrategy.applyGravity(board);
        this.currentStrategy = GravityEngine.getRandom();
        return moved;
    }

    @Override
    public Direction getDirection() {
        return currentStrategy.getDirection();
    }
}
