package it.unibo.javacrush.model;

import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.gravity.DownwardGravity;

public class DownwardGravityTest extends AbstractGravityTest {

    @Override
    protected GravityEngine createGravity() {
        return new DownwardGravity();
    }

    

}
