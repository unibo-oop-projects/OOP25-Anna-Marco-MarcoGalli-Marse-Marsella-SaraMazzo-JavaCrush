package it.unibo.javacrush.model;

import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.gravity.UpwardGravity;

public class UpwardGravityTest extends AbstractGravityTest {

    @Override
    protected GravityEngine createGravity() {
        return new UpwardGravity();
    }

}
