package it.unibo.javacrush.controller.impl;

import java.util.Map;
import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.GameMatchContext;
import it.unibo.javacrush.model.api.Match;
import it.unibo.javacrush.model.api.MatchManager;
import it.unibo.javacrush.model.api.MoveEngine;
import it.unibo.javacrush.model.api.PhysicsHandler;
import it.unibo.javacrush.model.api.Session;
import it.unibo.javacrush.model.api.StallEngine;
import it.unibo.javacrush.powerup.api.PowerUpManager;
import it.unibo.javacrush.view.api.GameView;

/**
 * Implementation of the {@link GameController} interface.
 */
public class GameControllerImpl implements GameController {

    private final GameView view;
    private final Map<CellType, Integer> goals;
    private final Board board;
    private final PhysicsHandler physics;
    private final StallEngine stallEngine;
    private final Session session;
    private final MoveEngine moveEngine;
    private final MatchManager matchManager;
    private final PowerUpManager powerUpManager;

    private Position lastClickedPosition;
    private Set<Match> matches = Set.of();

    /**
     * Constructor for the GameControllerImpl class.
     * 
     * @param gameContext the context of the game, containing all the necessary 
     *      information for the controller
     * @param view the view for the game
     */
    public GameControllerImpl(final GameMatchContext gameContext, final GameView view) {
        this.view = view;
        this.board = gameContext.getBoard();
        this.goals = gameContext.getLevelConfig().goals();
        this.session = gameContext.getSession();
        this.physics = gameContext.getPhysicsHandler();
        this.stallEngine = gameContext.getStallEngine();
        this.moveEngine = gameContext.getMoveEngine();
        this.matchManager = gameContext.getMatchManager();
        this.powerUpManager = gameContext.getLevelConfig().powerUpManager();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hit(final Position pos) {
        if (this.powerUpManager.isPowerUpSelected()) {
            return this.handlePoweUpHit(pos);
        }
        return this.normalHit(pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleMatches() {
        this.matches.stream()
            .forEach(match -> {
                this.session.updateGoals(match.getType(), match.getSize());
                this.matchManager.removeMatch(this.board, match);
        });
        this.matches.clear();

        this.view.updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applyGravity() {
        final boolean boardChanged = this.physics.update(this.board);

        if (boardChanged) {
            this.view.updateView();
        } else {
            // The board is stable but there could be some matches
            this.matches = this.matchManager.findAllMatches(this.board);

            if (!this.matches.isEmpty()) {
                // Board will change due to removing matches
                this.handleMatches();
                return true;
            } else {
                // Resolve stall
                this.stallEngine.resolveStall(this.board);
                this.view.updateView();
            }
        }

        return boardChanged;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean selectPowerUp(final int id) {
        return this.powerUpManager.selectPowerUp(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState updateGameState() {
        return this.session.getGameStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellType getCellTypeAtPos(final Position pos) {
        final var cell = this.board.getCellAt(pos);
        if (cell.isPresent()) {
            return cell.get().getType();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMovesLeft() {
        return this.session.getMovesLeft();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<CellType, Integer> getGoals() {
        return Map.copyOf(this.goals);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<CellType, Integer> getGoalsProgress() {
        final Map<CellType, Integer> progress = new java.util.HashMap<>();
        
        for (var goal : this.session.getGoals()) {
            progress.put(goal.getTargetType(), goal.getCurrentAmount());
        }
        
        return progress;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBoardCols() {
        return this.board.getCols();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getBoardRows() {
        return this.board.getRows();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quitLevel() {
        this.view.quitLevel();
    }

    /**
     * Handle the hit in the normal way, it will select the cell and then deselect 
     * it if it's already selected.
     *
     * @param pos the position of the cell clicked
     * @return true if the cells are swapped, false otherwise
     */
    private boolean normalHit(final Position pos) {
        if (this.lastClickedPosition == null) {
            // If the cell is null it can't be swapped
            this.lastClickedPosition = pos;
            return false;
        } else {
            final boolean canSwap = this.swap(this.lastClickedPosition, pos);

            this.lastClickedPosition = null;
            if (canSwap) {
                this.session.decreaseMoves();
                this.matches = this.matchManager.findAllMatches(this.board);
            }

            return canSwap;
        }
    }

    /**
     * Handle the hit when a PowerUp is selected, it will apply the PowerUp on the clicked cell.
     * 
     * @param pos the position of the cell clicked
     * @return true if the PowerUp is applied, false otherwise
     */
    private boolean handlePoweUpHit(final Position pos) {
        final boolean applyPowerUp = this.powerUpManager.applyPowerUp(this.board, pos);

        if (applyPowerUp) {
            this.matches = this.powerUpManager.getMatches();
            this.powerUpManager.resetPowerUpSelection();
            this.view.updateView();
        }

        return applyPowerUp;
    }

    /**
     * Swap two cells.
     *
     * @param p1 the first selected cell
     * @param p2 the second selected cell
     * @return true if the cells are swapped, false otherwise
     */
    private boolean swap(final Position p1, final Position p2) {
        if (moveEngine.canSwap(this.board, p1, p2)) {
            this.board.swapCells(p1, p2);
            return true;
        }
        return false;
    }

}
