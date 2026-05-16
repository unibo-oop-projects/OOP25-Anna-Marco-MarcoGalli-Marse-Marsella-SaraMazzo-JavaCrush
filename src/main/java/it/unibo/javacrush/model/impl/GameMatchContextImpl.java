package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GameMatchContext;
import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.api.MoveEngine;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.api.Session;
import it.unibo.javacrush.model.api.StallEngine;

public class GameMatchContextImpl implements GameMatchContext{

    private final Board board;
    private final PhysicsHandler physicsHandler;
    private final LevelConfig levelConfig;
    private final MoveEngine moveEngine;
    private final MatchManager matchManager;
    private final StallEngine stallEngine;
    private final Session session;

    public GameMatchContextImpl(final Board board, final PhysicsHandler physicsHandler, final LevelConfig levelConfig, final MoveEngine moveEngine, final MatchManager matchManager, final StallEngine stallEngine, final Session session) {
        this.board = board;
        this.physicsHandler = physicsHandler;
        this.levelConfig = levelConfig;
        this.moveEngine = moveEngine;
        this.matchManager = matchManager;
        this.stallEngine = stallEngine;
        this.session = session;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public StallEngine getStallEngine() {
        return stallEngine;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public PhysicsHandler getPhysicsHandler() {
        return physicsHandler;
    }

    @Override
    public LevelConfig getLevelConfig() {
        return levelConfig;
    }

    @Override
    public MoveEngine getMoveEngine() {
        return moveEngine;
    }

    @Override
    public MatchManager getMatchManager() {
        return matchManager;
    }


}
