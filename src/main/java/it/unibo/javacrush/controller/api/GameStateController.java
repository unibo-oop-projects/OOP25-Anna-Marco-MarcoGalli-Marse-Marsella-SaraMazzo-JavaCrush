package it.unibo.javacrush.controller.api;

import it.unibo.javacrush.common.Position;

/**
 * Interface representing a game controller.
*/
public interface GameStateController {
    
    /**
     * The method responsible of the move made by swapping two cells.
     * 
     * @param p1 the first selected cell.
     * @param p2 the second selected cell.
     */
    void makeMove(Position p1, Position p2);

    /**
     * The method responsible for updating the board after a move, before the next one.
     */
    void gameLoop();

    /**
     * Returns if the player had loose the game.
     * 
     * @return true if the player run out of moves, false otherwise.
     */
    boolean isGameOver();

    /**
     * Return if the player had won.
     * 
     * @return true if the player had achieved all the objectives, false otherwise.
     */
    boolean isWin();
}
