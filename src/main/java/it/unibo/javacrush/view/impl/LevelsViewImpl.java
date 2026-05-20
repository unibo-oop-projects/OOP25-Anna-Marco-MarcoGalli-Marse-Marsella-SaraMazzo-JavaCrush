package it.unibo.javacrush.view.impl;

import java.util.Optional;

import it.unibo.javacrush.common.AppEventType;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.Event;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.view.api.LevelsView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class LevelsViewImpl implements LevelsView{

    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;
    private static final int LEVEL_4 = 4;
    private static final int LEVEL_5 = 5;
    private final BorderPane root;

    public LevelsViewImpl(AppController appController, LevelManager levelManager) {
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
        Button expert = new Button("Expert");
        expert.setPrefWidth(200);
        Button crazyGravity = new Button("Crazy Gravity");
        crazyGravity.setPrefWidth(200);
        levels.getChildren().addAll(easy, medium, hard, expert, crazyGravity);

        Button menu = new Button("Menù");
        menu.setPrefWidth(100);
        back.getChildren().add(menu);

        this.root = new BorderPane();
        this.root.setPadding(new Insets(20)); // Aggiungiamo un po' di padding intorno
        this.root.setCenter(levels); // I livelli vanno al centro
        this.root.setBottom(back);

        menu.setOnAction(e -> {
            final Event goToMenuEvent = new Event() {

                @Override
                public AppEventType type() {
                    return AppEventType.GO_TO_MENU;
                }

                @Override
                public Optional<Integer> id() {
                    return Optional.empty();
                }
                
            };
            appController.notifyEvent(goToMenuEvent);
        });

        easy.setOnAction(e -> startLevelEvent(appController, LEVEL_1));
        medium.setOnAction(e -> startLevelEvent(appController, LEVEL_2));
        hard.setOnAction(e -> startLevelEvent(appController, LEVEL_3));
        expert.setOnAction(e -> startLevelEvent(appController, LEVEL_4));
        crazyGravity.setOnAction(e -> startLevelEvent(appController, LEVEL_5));

    }

    @Override
    public Parent getView() {
        return this.root;
    }

    private void startLevelEvent(AppController appController, int levelID) {
        final Event startLevelEvent = new Event() {

            @Override
            public AppEventType type() {
                return AppEventType.START_LEVEL;
            }

            @Override
            public Optional<Integer> id() {
                return Optional.of(levelID);
            }
            
        };
        appController.notifyEvent(startLevelEvent);
    }
}
