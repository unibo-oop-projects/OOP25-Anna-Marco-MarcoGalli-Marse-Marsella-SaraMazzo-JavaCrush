package it.unibo.javacrush.view.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.view.api.GameView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameViewImpl implements GameView{

    private GameController controller;
    private final BorderPane root;
    private final GridPane grid;
    private final Map<Button,Position> gridMap = new HashMap<>();
    private final VBox powerUpBox;
    private final VBox quitBox;

    public GameViewImpl() {
        this.root = new BorderPane();
        this.root.setPadding(new Insets(20));

        this.grid = new GridPane();
        this.grid.setAlignment(Pos.BOTTOM_CENTER);

        this.root.setCenter(this.grid);

        this.powerUpBox = new VBox(10);
        this.powerUpBox.setAlignment(Pos.CENTER_RIGHT);
        this.powerUpBox.setPadding(new Insets(0, 10, 0, 10));


        this.quitBox = new VBox(10);
        this.quitBox.setAlignment(Pos.BOTTOM_LEFT);
        this.quitBox.setPadding(new Insets(0, 10, 0, 10));

    }

    @Override
    public void updateView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateView'");
    }

    @Override
    public void quitLevel() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quitLevel'");
    }

    @Override
    public Parent getView() {
        return this.root;
    }

    @Override
    public void setController(GameController controller) {
        this.controller = controller;
        this.setUpGame();
    }

    private void setUpGame() {

        for (int i = 0; i < controller.getBoardRows(); i++) {
            for (int j = 0; j < controller.getBoardCols(); j++) {
                Position pos = new Position(i, j);
                Button bt = new Button();
                bt.setPrefSize(50, 50);
                bt.setMinSize(50, 50);
                bt.setMaxSize(50, 50);
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

        this.quitBox.getChildren().add(quit);
        this.root.setLeft(quitBox);
    }

}
