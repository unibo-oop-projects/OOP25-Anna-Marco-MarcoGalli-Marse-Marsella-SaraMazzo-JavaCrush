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
    public boolean update(final Board board) {
        final boolean moved = gravity.applyGravity(board);
        final boolean refilled = refill.refill(board);
        return moved || refilled;
    }

    @Override
    public final void setGravity(final GravityEngine newGravity) {
        this.gravity = newGravity;
        this.refill = new AdaptiveRefill(newGravity);
    }

    @Override
    public void initializeBoard(final Board board) {
        this.refill.refillAll(board);
        this.stallEngine.resolveStall(board);
    }

    

}
