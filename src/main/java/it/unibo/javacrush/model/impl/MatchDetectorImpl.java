package it.unibo.javacrush.model.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Match;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.MatchDetector;

public class MatchDetectorImpl implements MatchDetector{

    @Override
    public Set<Match> findMatchesAt(Board board, Position pos) {

        Set<Match> matches = new HashSet<>();
        Set<Position> horizontal = checkHorizontal(board, pos);
        Set<Position> vertical = checkVertical(board, pos);

        if(!horizontal.isEmpty()) {
            matches.add(new Match(horizontal, board.getCellAt(pos).getType()));
        }
        if(!vertical.isEmpty()) {
            matches.add(new Match(vertical, board.getCellAt(pos).getType()));
        }

        return matches;
    }

    @Override
    public Set<Match> findAllMatches(Board board) {

        Set<Match> matches = new HashSet<>();

        for(int i = 0; i < board.getCols(); i++) {
            for(int j = 0; j < board.getRows(); j++) {
                matches.addAll(findMatchesAt(board,new Position(i,j)));
            }
        }

        return matches;
    }

    private boolean isInBounds(Board board, int col, int row) {
        return (col >= 0 && col < board.getCols()) && 
                (row >= 0 && row < board.getRows());
    }

    private Set<Position> checkHorizontal(Board board, Position pos) {
        
        Set<Position> match = new HashSet<>();
        match.add(pos);
        CellType matchType = board.getCellAt(pos).getType();
        
        int y = pos.y();
        int x = pos.x() - 1;
        while(isInBounds(board,x,y) && board.getCellAt(new Position(x,y)).getType() == matchType) {
            match.add(new Position(x,y));
            x--;
        }

        x = pos.x() + 1;
        while(isInBounds(board,x,y) && board.getCellAt(new Position(x,y)).getType() == matchType) {
            match.add(new Position(x,y));
            x++;
        }

        return match.size()>=3 ? match : Collections.emptySet();
    }

    private Set<Position> checkVertical(Board board, Position pos) {
        
        Set<Position> match = new HashSet<>();
        match.add(pos);
        CellType matchType = board.getCellAt(pos).getType();
        
        int x = pos.x();
        int y = pos.y() - 1;
        while(isInBounds(board,x,y) && board.getCellAt(new Position(x,y)).getType() == matchType) {
            match.add(new Position(x,y));
            y--;
        }

        y = pos.y() + 1;
        while(isInBounds(board,x,y) && board.getCellAt(new Position(x,y)).getType() == matchType) {
            match.add(new Position(x,y));
            y++;
        }

        return match.size()>=3 ? match : Collections.emptySet();
    }

}
