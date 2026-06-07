package it.unibo.javacrush.view.impl;

import java.net.URL;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


import it.unibo.javacrush.common.AppEventType;
import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.common.PowerUpNumber;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.Event;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.view.api.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameViewImpl implements GameView{

    private final Map<CellType, Image> cellTypeImages = new EnumMap<>(CellType.class);
    private final Map<Button,Position> gridMap = new HashMap<>();
    private Set<Position> hint = new HashSet<>();
    private final BorderPane root;
    private final GridPane grid;
    private final VBox powerUpBox;
    private final VBox quitBox;
    private final HBox topBar;
    private GameController controller;
    private AppController appController;
    private Label movesLabel;
    private HBox goalsContainer;
    private Button selectedCell = null;
    private Button selectedPowerUp = null;
    private boolean isAnimating = false;
    private Service<Integer> ser;

    public GameViewImpl() {
        this.root = new BorderPane();
        this.root.setPadding(new Insets(20));

        this.grid = new GridPane();
        this.grid.setAlignment(Pos.BOTTOM_CENTER);

        this.root.setCenter(this.grid);

        this.powerUpBox = new VBox(10);
        this.powerUpBox.setAlignment(Pos.CENTER_RIGHT);
        this.powerUpBox.setPadding(new Insets(0, 10, 0, 10));


        this.quitBox = new VBox(40);
        this.quitBox.setAlignment(Pos.BOTTOM_LEFT);
        this.quitBox.setPadding(new Insets(0, 10, 0, 10));

        this.topBar = new HBox(10);
        this.topBar.setAlignment(Pos.CENTER);
        this.topBar.setPadding(new Insets(0, 0, 20, 0));
        this.root.setTop(this.topBar);

        for (CellType type : CellType.values()) {
            String path = "/" + type.toString().toLowerCase() + ".png";
            try {
                URL imageUrl = getClass().getResource(path);
                if (imageUrl != null) {
                    this.cellTypeImages.put(type, new Image(imageUrl.toExternalForm()));
                } else {
                    System.out.println("Attenzione, immagine non trovata: " + path);
                }
            } catch (Exception e) {
                System.out.println("Errore caricamento: " + path);
            }
        }

        this.ser = this.setService();
    }

    @Override
    public void updateView() {

        this.movesLabel.setText("Moves left: " + this.controller.getMovesLeft());
        this.movesLabel.setStyle("-fx-font-size: 16px; -fx-background-color: #f0f0f0; -fx-padding: 5 10 5 10; -fx-background-radius: 5;");
        
        this.goalsContainer.getChildren().clear();
        Map<CellType, Integer> goals = this.controller.getGoals();
        for (var goal : goals.entrySet()) {
            CellType type = goal.getKey();
            int amount = goal.getValue();
            int currentAmount = this.controller.getGoalsProgress().getOrDefault(type, 0);

            Label goalLabel = new Label(type.toString() + ": " + currentAmount + "/" + amount);
            goalLabel.setStyle("-fx-font-size: 16px; -fx-background-color: #f0f0f0; -fx-padding: 5 10 5 10; -fx-background-radius: 5;");
            this.goalsContainer.getChildren().add(goalLabel);
        }

        for (var e : gridMap.entrySet()) {
            Button bt = e.getKey();
            Position pos = e.getValue();

            CellType type = this.controller.getCellTypeAtPos(pos);
            if (type != null && this.cellTypeImages.containsKey(type)) {
                ImageView img = new ImageView(this.cellTypeImages.get(type));
                img.setFitWidth(30); 
                img.setFitHeight(30);
                img.setPreserveRatio(true);

                bt.setGraphic(img);
                bt.setStyle("-fx-background-color: transparent; -fx-border-color: #cccccc; -fx-border-width: 1px;");
            } else {
                bt.setGraphic(null);
                bt.setStyle("-fx-background-color: transparent; -fx-border-color: #cccccc; -fx-border-width: 1px;");
            }

            if (bt.equals(this.selectedCell)) {
                bt.setStyle("-fx-border-color: red; -fx-border-width: 3px; -fx-border-radius: 5;");
            }

            if (this.hint.contains(pos)) {
                bt.setStyle("-fx-background-color: green; -fx-border-width: 5px; -fx-border-radius: 5;");
            }
        }

    }

    @Override
    public void quitLevel() {
        final Event quitEvent = new Event() {

                @Override
                public AppEventType type() {
                    return AppEventType.EXIT_GAME;
                }

                @Override
                public Optional<Integer> id() {
                    return Optional.empty();
                }
                
            };
            this.appController.notifyEvent(quitEvent);
    }

    @Override
    public Parent getView() {
        return this.root;
    }

    @Override
    public void setController(GameController controller, AppController appController) {
        this.controller = controller;
        this.appController = appController;
        this.setUpGame();
    }

    private void setUpGame() {

        this.grid.getChildren().clear();
        this.gridMap.clear();
        this.powerUpBox.getChildren().clear();
        this.quitBox.getChildren().clear();
        this.topBar.getChildren().clear();

        this.movesLabel = new Label();

        this.goalsContainer = new HBox(15);
        this.goalsContainer.setAlignment(Pos.CENTER);

        this.topBar.getChildren().addAll(goalsContainer, movesLabel);

        for (int i = 0; i < controller.getBoardCols(); i++) {
            for (int j = 0; j < controller.getBoardRows(); j++) {
                Position pos = new Position(i, j);
                Button bt = new Button();
                bt.setPrefSize(40, 40);
                bt.setMinSize(40, 40);
                bt.setMaxSize(40, 40);
                this.grid.add(bt, j, i);
                this.gridMap.put(bt, pos);

                bt.setOnAction(e -> {
                    if (this.isAnimating) {
                        return;
                    }

                    boolean isActionValid = this.controller.hit(pos);

                    if (this.selectedPowerUp != null) {
                        this.selectedPowerUp.setStyle("");
                        this.selectedPowerUp = null;
                        this.controller.resetPowerUpSelection();
                    } else {
                        if (this.selectedCell == null) {
                            bt.setStyle("-fx-border-color: red; -fx-border-width: 3px; -fx-border-radius: 5;");
                            this.selectedCell = bt;
                        } else if (this.selectedCell == bt) {
                            bt.setStyle("");
                            this.selectedCell = null;
                            this.updateView();
                        } else {
                            this.selectedCell.setStyle("");
                            this.selectedCell = null;
                        }

                    }

                    if (isActionValid) {
                        this.isAnimating = true;

                        this.updateView();
                        this.controller.handleMatches();
                        this.updateView();
                        this.hint.clear();

                        Timeline timeline = new Timeline();

                        KeyFrame stallAlertFrame = new KeyFrame(Duration.seconds(0.5), ev -> {

                            if (this.controller.isStall()) {
                                Platform.runLater(() -> this.stallAlert());
                            }
                        });

                        KeyFrame frame = new KeyFrame(Duration.seconds(0.5), event -> {
                            
                            boolean isFalling = this.controller.applyGravity();

                            this.updateView();

                            if (!isFalling) {
                                timeline.stop();
                                this.isAnimating = false;
                                Platform.runLater(() -> this.timerTask());
                                Platform.runLater(() -> this.checkStateGame());
                                
                            }
                        });

                        timeline.getKeyFrames().add(stallAlertFrame);
                        timeline.getKeyFrames().add(frame);
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.play();

                        this.updateView();
                    }

                });
            }
        }

        Button powerUp1 = new Button("Hammer");
        powerUp1.setOnAction(e -> {
            boolean isAvailable = this.controller.selectPowerUp(PowerUpNumber.SINGLECELL.ordinal());

            if (isAvailable) {
                if (this.selectedCell != null) {
                    this.selectedCell.setStyle("");
                    this.selectedCell = null;
                }
                if (this.selectedPowerUp != null) {
                    this.selectedPowerUp.setStyle("");
                    powerUp1.setStyle("");
                    this.selectedPowerUp = null;
                    this.controller.resetPowerUpSelection();
                } else {
                    powerUp1.setStyle("-fx-border-color: red; -fx-border-width: 3px; -fx-border-radius: 5;");
                    this.selectedPowerUp = powerUp1;
                }
            }
        });

        Button powerUp2 = new Button("Rocket");
        powerUp2.setOnAction(e -> {
            boolean isAvailable = this.controller.selectPowerUp(PowerUpNumber.ROW.ordinal());

            if (isAvailable) {
                if (this.selectedCell != null) {
                    this.selectedCell.setStyle("");
                    this.selectedCell = null;
                }
                if (this.selectedPowerUp != null) {
                    this.selectedPowerUp.setStyle("");
                    powerUp2.setStyle("");
                    this.selectedPowerUp = null;
                    this.controller.resetPowerUpSelection();
                } else {
                    powerUp2.setStyle("-fx-border-color: red; -fx-border-width: 3px; -fx-border-radius: 5;");
                    this.selectedPowerUp = powerUp2;
                }
            }
        });
        Button powerUp3 = new Button("Magic Bomb");
        powerUp3.setOnAction(e -> {
            boolean isAvailable = this.controller.selectPowerUp(PowerUpNumber.TYPE.ordinal());

            if (isAvailable) {
                if (this.selectedCell != null) {
                    this.selectedCell.setStyle("");
                    this.selectedCell = null;
                }
                if (this.selectedPowerUp != null) {
                    this.selectedPowerUp.setStyle("");
                    powerUp3.setStyle("");
                    this.selectedPowerUp = null;
                    this.controller.resetPowerUpSelection();
                } else {
                    powerUp3.setStyle("-fx-border-color: red; -fx-border-width: 3px; -fx-border-radius: 5;");
                    this.selectedPowerUp = powerUp3;
                }
            }
        });
        powerUp1.setPrefWidth(100);
        powerUp2.setPrefWidth(100);
        powerUp3.setPrefWidth(100);

        this.powerUpBox.getChildren().addAll(powerUp1, powerUp2, powerUp3);
        this.root.setRight(this.powerUpBox);

        Button quit = new Button("Quit");
        quit.setPrefWidth(80);
        quit.setOnAction(e -> this.controller.quitLevel());

        this.quitBox.getChildren().add(quit);
        this.root.setLeft(quitBox);

        this.timerTask();
        this.updateView();
    }

    private void checkStateGame() {
        GameState state = this.controller.updateGameState();

        if (state == GameState.WON) {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("VICTORY!!");
            alert.setContentText("Congratulations! You have won the game.");
            alert.showAndWait();

            this.quitLevel();

        } else if (state == GameState.LOST) {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("GAME OVER");
            alert.setContentText("Sorry! You have lost the game.");
            alert.showAndWait();

            this.quitLevel();

        }
    }

    private void stallAlert() {

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("STALL");
        alert.setContentText("The board were in stall, the cells have been refreshed.");
        alert.showAndWait();
    }

    private Service<Integer> setService() {
        return new Service<Integer>() {
            @Override
            protected Task<Integer> createTask() {
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        int sec;
                        for (sec = 0; sec < 10; sec++) {
                            if (isCancelled()) {
                                updateMessage("Cancelled");
                                break;
                            }
                            updateMessage("Seconds " + sec);
                            updateProgress(sec, 10);

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException interrupted) {
                                if (isCancelled()) {
                                    updateMessage("Cancelled");
                                    break;
                                }
                            }
                        }
                        return sec;
                    }
                };
            }
        };
    }

    private void timerTask() {

        if (!this.isAnimating) {
            this.hint.clear();
            this.ser.restart();
        }

        this.ser.setOnSucceeded(event -> {
            if (!this.controller.isStall()) {
                this.hint = this.controller.getHint();
            }
            this.updateView();
        });

    }

}
