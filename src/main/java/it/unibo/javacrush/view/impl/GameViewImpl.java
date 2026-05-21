package it.unibo.javacrush.view.impl;

import java.net.URL;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.javacrush.common.AppEventType;
import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.Event;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.view.api.GameView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameViewImpl implements GameView{

    private GameController controller;
    private AppController appController;
    private final BorderPane root;
    private final GridPane grid;
    private final Map<Button,Position> gridMap = new HashMap<>();
    private final VBox powerUpBox;
    private final VBox quitBox;
    private final HBox topBar;
    private Label movesLabel;
    private HBox goalsContainer;
    private final Map<CellType, Image> cellTypeImages = new EnumMap<>(CellType.class);

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
    }

    @Override
    public void updateView() {
        this.movesLabel.setText("Moves left: " + this.controller.getMovesLeft());
        this.movesLabel.setStyle("-fx-font-size: 16px; -fx-background-color: #f0f0f0; -fx-padding: 5 10 5 10; -fx-background-radius: 5;");
        
        Map<CellType, Integer> goals = this.controller.getGoals();
        for (var goal : goals.entrySet()) {
            CellType type = goal.getKey();
            int amount = goal.getValue();

            Label goalLabel = new Label(type.toString() + ": " + amount);
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

        for (int i = 0; i < controller.getBoardRows(); i++) {
            for (int j = 0; j < controller.getBoardCols(); j++) {
                Position pos = new Position(i, j);
                Button bt = new Button();
                bt.setPrefSize(40, 40);
                bt.setMinSize(40, 40);
                bt.setMaxSize(40, 40);
                this.grid.add(bt, j, i);
                this.gridMap.put(bt, pos);
            }
        }

        Button powerUp1 = new Button("PowerUp 1");
        Button powerUp2 = new Button("PowerUp 2");
        Button powerUp3 = new Button("PowerUp 3");
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

        this.updateView();
    }

}
