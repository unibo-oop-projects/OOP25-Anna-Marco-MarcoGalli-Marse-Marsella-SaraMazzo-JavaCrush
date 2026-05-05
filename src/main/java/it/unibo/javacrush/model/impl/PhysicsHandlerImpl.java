package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.api.RefillEngine;
import it.unibo.javacrush.model.api.StallEngine;

public class PhysicsHandlerImpl implements PhysicsHandler{

    private GravityEngine gravity;
    private RefillEngine refill;
    private final StallEngine stallEngine;

    public PhysicsHandlerImpl(final GravityEngine startGravity, final StallEngine stallEngine) {
        this.stallEngine = stallEngine;
        setGravity(startGravity);
    }

    @Override
    public boolean update(Board board) {
        boolean moved = gravity.applyGravity(board);
        boolean refilled = refill.refill(board);
        return moved || refilled;
    }

    @Override
    public void setGravity(GravityEngine newGravity) {
        this.gravity = newGravity;
        this.refill = new AdaptiveRefill(newGravity);
    }

    @Override
    public void initializeBoard(Board board) {
        this.refill.RefillAll(board);
        this.stallEngine.computeStall(board);
    }

    

}
