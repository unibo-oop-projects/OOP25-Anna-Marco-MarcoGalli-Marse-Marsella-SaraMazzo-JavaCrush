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

    private static final int SPACING = 30;
    private static final int WIDTH = 200;
    private final VBox root;

    /**
     * Constructor of {@link MenuViewImpl}.
     * 
     * @param controller the controller to notify when buttons are pressed
     */
    public MenuViewImpl(final AppController controller) {

        this.root = new VBox(SPACING);
        this.root.setAlignment(Pos.CENTER);

        final Label title = new Label("JAVACRUSH");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #2800ef;");

        final Button playButton = new Button("Play");
        final Button guideButton = new Button("How to play");

        playButton.setPrefWidth(WIDTH);
        guideButton.setPrefWidth(WIDTH);

        this.root.getChildren().addAll(title, playButton, guideButton);

        playButton.setOnAction(e -> {

            final Event goToLevelsEvent = new Event() {
                @Override
                public AppEventType type() {
                    return AppEventType.GO_TO_LEVELS;
                }

                @Override
                public Optional<Integer> id() {
                    return Optional.empty(); 
                }
            };

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
