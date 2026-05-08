package it.unibo.javacrush.powerup.impl;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.powerup.api.PowerUp;

/**
 * This PowerUp removes all the cells on the board with the same type of the given cell.
 */
public class VaporizerPowerUp implements PowerUp{

    @Override
    public Boolean applyPowerUp(Board board, Position pos) {

        if(!board.getGrid().containsKey(pos) || board.getCellAt(pos).isEmpty()) {
            return false;
        }

        Cell type = board.getCellAt(pos).get();

        for (int y = 0; y < board.getRows(); y++) {
            for (int x = 0; x < board.getCols(); x++) {
                Position current = new Position(x, y);

                if (board.getCellAt(current).isPresent() && board.getCellAt(current).get().equals(type)){
                    board.removeCell(current);
                }
            }
        }

        return true;
    }
    
}
