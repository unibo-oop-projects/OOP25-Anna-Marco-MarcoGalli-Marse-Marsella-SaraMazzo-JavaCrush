package it.unibo.javacrush.view.impl;

import it.unibo.javacrush.view.api.LevelsView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class LevelsViewImpl implements LevelsView{

    private final BorderPane root;

    public LevelsViewImpl() {
        // 1. Creiamo il contenitore principale (VBox con spazio di 30px tra elementi)
        VBox levels = new VBox(30);
        levels.setAlignment(Pos.CENTER); // Centra tutto!

        HBox back = new HBox(30);
        back.setAlignment(Pos.CENTER_LEFT);

        Button easy = new Button("Easy");
        easy.setPrefWidth(200);
        Button medium = new Button("Medium");
        medium.setPrefWidth(200);
        Button hard = new Button("Hard");
        hard.setPrefWidth(200);
        Button crazyGravity = new Button("Crazy Gravity");
        crazyGravity.setPrefWidth(200);
        levels.getChildren().addAll(easy, medium, hard, crazyGravity);

        Button menu = new Button("Menù");
        menu.setPrefWidth(100);
        back.getChildren().add(menu);

        this.root = new BorderPane();
        this.root.setPadding(new Insets(20)); // Aggiungiamo un po' di padding intorno
        this.root.setCenter(levels); // I livelli vanno al centro
        this.root.setBottom(back);
    }

    @Override
    public Parent getView() {
        return this.root;
    }

}
