package it.unibo.javacrush.model.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.model.api.MatchDetector;

public class MatchDetectorImpl implements MatchDetector{

    @Override
    public Match findMatchesAt(Board board, Position pos) {

        Set<Position> matches = new HashSet<>();
        Set<Position> horizontal = checkHorizontal(board, pos);
        Set<Position> vertical = checkVertical(board, pos);

        if(!horizontal.isEmpty()) {
            matches.addAll(horizontal);
        }
        if(!vertical.isEmpty()) {
            matches.addAll(vertical);
        }

        if (matches.isEmpty()) {
            return null; 
        }

        return new MatchImpl(matches, board.getCellAt(pos).get().getType());
    }

    @Override
    public Set<Match> findAllMatches(Board board) {

        Set<Match> matches = new HashSet<>();

        for(int i = 0; i < board.getRows(); i++) {
            for(int j = 0; j < board.getCols(); j++) {
                Position pos = new Position(j,i);
                if(!board.getCellAt(pos).isEmpty()) {

                    var match = findMatchesAt(board, pos);
                    if(match != null) {
                    matches.add(match);
                    }

                }
            }
        }

        return matches;
    }

    private boolean isInBounds(Board board, int col, int row) {
        return (col >= 0 && col < board.getCols()) && 
                (row >= 0 && row < board.getRows());
    }

    private Set<Position> checkHorizontal(Board board, Position pos) {
        
        Set<Position> matches = new HashSet<>();
        matches.add(pos);
        CellType matchType = board.getCellAt(pos).get().getType();
        
        int y = pos.y();
        int x = pos.x() - 1;
        while(isInBounds(board,x,y) && 
                board.getCellAt(new Position(x,y))
                    .map(Cell::getType)
                    .filter(type -> type == matchType)
                    .isPresent()) {

            matches.add(new Position(x,y));
            x--;
        }

        x = pos.x() + 1;
        while(isInBounds(board,x,y) && 
                board.getCellAt(new Position(x,y))
                    .map(Cell::getType)
                    .filter(type -> type == matchType)
                    .isPresent()) {

            matches.add(new Position(x,y));
            x++;
        }

        return matches.size()>=3 ? matches : Collections.emptySet();
    }

    private Set<Position> checkVertical(Board board, Position pos) {
        
        Set<Position> matches = new HashSet<>();
        matches.add(pos);
        CellType matchType = board.getCellAt(pos).get().getType();
        
        int x = pos.x();
        int y = pos.y() - 1;
        while(isInBounds(board,x,y) && 
                board.getCellAt(new Position(x,y))
                    .map(Cell::getType)
                    .filter(type -> type == matchType)
                    .isPresent()) {

            matches.add(new Position(x,y));
            y--;
        }

        y = pos.y() + 1;
        while(isInBounds(board,x,y) && 
                board.getCellAt(new Position(x,y))
                    .map(Cell::getType)
                    .filter(type -> type == matchType)
                    .isPresent()) {
    
            matches.add(new Position(x,y));
            y++;
        }

        return matches.size()>= 3 ? matches : Collections.emptySet();
    }

}
