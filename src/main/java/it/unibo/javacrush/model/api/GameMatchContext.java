package it.unibo.javacrush.model.api;

public interface GameMatchContext {
    Board getBoard();
    PhysicsHandler getPhysicsHandler();
    LevelConfig getLevelConfig();
    MoveEngine getMoveEngine();
    MatchManager getMatchManager();
    StallEngine getStallEngine();
    Session getSession();
}
