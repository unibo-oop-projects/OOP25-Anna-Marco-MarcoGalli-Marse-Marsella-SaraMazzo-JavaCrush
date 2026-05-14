package it.unibo.javacrush.model;

import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.impl.gravity.LeftwardGravity;

public class LeftwardGravityTest extends AbstractGravityTest {

    @Override
    protected GravityEngine createGravity() {
        return new LeftwardGravity();
    }

}
