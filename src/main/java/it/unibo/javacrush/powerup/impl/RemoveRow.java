package it.unibo.javacrush.powerup.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.impl.MatchImpl;

/**
 * This PowerUp removes the entire row of cells of the given cell from the board.
 */
public class RemoveRow extends AbstractPowerUp {

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean applyPowerUp(final Board board, final Position pos) {

        if (this.isAppliable(board, pos)) {
            final Set<Position> resultSet = new HashSet<>();
            Position current;

            for (int x = 0; x < board.getCols(); x++) {
                current = new Position(x, pos.y());
                resultSet.clear();
                resultSet.add(current);
                this.addMatches(new MatchImpl(resultSet, board.getCellAt(current).get().getType()));
            }

            return true;
        }

        return false;
    }

}
