package it.unibo.javacrush.view.impl;

import it.unibo.javacrush.controller.api.MockMenuController;
import it.unibo.javacrush.view.api.MenuView;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuViewImpl implements MenuView{
    
    private final VBox root;

    public MenuViewImpl(MockMenuController controller) {
        // 1. Creiamo il contenitore principale (VBox con spazio di 30px tra elementi)
        this.root = new VBox(30);
        this.root.setAlignment(Pos.CENTER); // Centra tutto!

        // 2. Creiamo il Titolo (come nel tuo disegno)
        Label title = new Label("JAVACRUSH");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #2800ef;"); // Un po' di stile per il titolo

        // 3. Creiamo i Bottoni
        Button playButton = new Button("Play");
        Button guideButton = new Button("How to play");

        // Diamo un po' di stile ai bottoni per farli somigliare al disegno
        playButton.setPrefWidth(200);
        guideButton.setPrefWidth(200);

        // 4. Aggiungiamo tutto al VBox
        this.root.getChildren().addAll(title, playButton, guideButton);
        
        // Per ora facciamo solo un print per vedere se funzionano
        playButton.setOnAction(e -> controller.playButton());
        guideButton.setOnAction(e -> controller.howToPlayButton());
    }

    @Override
    public Parent getView() {
        return this.root;
    }

}
