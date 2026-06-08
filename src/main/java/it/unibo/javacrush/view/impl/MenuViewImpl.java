package it.unibo.javacrush.view.impl;

import java.util.Optional;

import it.unibo.javacrush.common.AppEventType;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.Event;
import it.unibo.javacrush.view.api.MenuView;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Implementation of the {@link MenuView} interface.
 */
public class MenuViewImpl implements MenuView {

    private final VBox root;

    /**
     * Constructor of {@link MenuViewImpl}.
     * 
     * @param controller TODO
     */
    public MenuViewImpl(final AppController controller) {

        this.root = new VBox(30);
        this.root.setAlignment(Pos.CENTER); // Centra tutto!

        final Label title = new Label("JAVACRUSH");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #2800ef;");

        final Button playButton = new Button("Play");
        final Button guideButton = new Button("How to play");

        playButton.setPrefWidth(200);
        guideButton.setPrefWidth(200);

        this.root.getChildren().addAll(title, playButton, guideButton);

        playButton.setOnAction(e -> {
            // Creiamo l'evento per andare alla selezione livelli
            final Event goToLevelsEvent = new Event() {
                @Override
                public AppEventType type() {
                    return AppEventType.GO_TO_LEVELS;
                }

                @Override
                public Optional<Integer> id() {
                    return Optional.empty(); // Nel menu non serve l'id del livello
                }
            };
            // Notifichiamo il controller ufficiale
            controller.notifyEvent(goToLevelsEvent);
        });
        guideButton.setOnAction(e -> {
            final Event goToInstructionsEvent = new Event() {

                @Override
                public AppEventType type() {
                    return AppEventType.SHOW_INSTRUCTIONS;
                }

                @Override
                public Optional<Integer> id() {
                    return Optional.empty();
                }

            };
            controller.notifyEvent(goToInstructionsEvent);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Parent getView() {
        return this.root;
    }

}
