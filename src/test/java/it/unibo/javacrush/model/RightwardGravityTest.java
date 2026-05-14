package it.unibo.javacrush.model;

import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.gravity.RightwardGravity;

public class RightwardGravityTest extends AbstractGravityTest {

    @Override
    protected GravityEngine createGravity() {
        return new RightwardGravity();
    }

}
