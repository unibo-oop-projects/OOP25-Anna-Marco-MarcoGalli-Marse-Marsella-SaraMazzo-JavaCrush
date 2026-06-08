package it.unibo.javacrush.view.impl;

import java.util.Optional;

import it.unibo.javacrush.common.AppEventType;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.Event;
import it.unibo.javacrush.view.api.InstructionsView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Implementation of the {@link InstructionsView} interface.
 */
public class InstructionsViewImpl implements InstructionsView {

    private static final String STYLE = "-fx-font-size: 16px; -fx-font-weight: bold;";

    private final BorderPane root;

    public InstructionsViewImpl(final AppController appController) {
        this.root = new BorderPane();
        this.root.setPadding(new Insets(20));

        final VBox instructionsBox = new VBox();
        instructionsBox.setAlignment(Pos.CENTER);
        instructionsBox.setPadding(new Insets(10));

        final Text mainTitle = new Text("How To Play JavaCrush");
        mainTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        instructionsBox.getChildren().add(mainTitle);

        final Text challengeTitle = new Text("Choose Your Challenge");
        challengeTitle.setStyle(STYLE);
        final Label challengeBody = new Label("The Levels Menu features a variety of stages with different difficulties and unique gravity mechanics. Choose one to start your game!");
        challengeBody.setWrapText(true);
        challengeBody.setMaxWidth(550);

        instructionsBox.getChildren().addAll(challengeTitle, challengeBody);

        final Text goalsTitle = new Text("The Goals");
        goalsTitle.setStyle(STYLE);
        final Label goalsBody = new Label("Your main objective is to complete the goals displayed above the grid (collecting a specific number of elements of a certain type) before running out of moves.");
        goalsBody.setWrapText(true);
        goalsBody.setMaxWidth(550);

        instructionsBox.getChildren().addAll(goalsTitle, goalsBody);

        final Text matchesTitle = new Text("Make a Match");
        matchesTitle.setStyle(STYLE);
        final Label matchesBody = new Label("Swap two adjacent cells to create matches of three or more identical elements. Each element from a valid match is collected and counts towards your objectives.");
        matchesBody.setWrapText(true);
        matchesBody.setMaxWidth(550);

        instructionsBox.getChildren().addAll(matchesTitle, matchesBody);

        final Text powerupsTitle = new Text("Unleash Power-Ups");
        powerupsTitle.setStyle(STYLE);
        final Label powerusBody = new Label("Power-Ups can pave your way to victory by clearing specific cells. To use one, click the Power-Up button and then select your target cell on the grid. Use them wisely, as each Power-Up can only be activated once per game!");
        powerusBody.setWrapText(true);
        powerusBody.setMaxWidth(550);

        instructionsBox.getChildren().addAll(powerupsTitle, powerusBody);

        final Text shuffleTitle = new Text("Stalemate Shuffle");
        shuffleTitle.setStyle(STYLE);
        final Label shuffleBody = new Label("If the board reaches a state where no valid matches are possible, all the cells on the grid will be automatically shuffled to create new opportunities.");
        shuffleBody.setWrapText(true);
        shuffleBody.setMaxWidth(550);

        instructionsBox.getChildren().addAll(shuffleTitle, shuffleBody);

        final Text movesTitle = new Text("Watch Your Moves");
        movesTitle.setStyle(STYLE);
        final Label movesBody = new Label("Keep a close eye on your remaining moves: if they hit zero before you reach your objectives, it's Game Over!");
        movesBody.setWrapText(true);
        movesBody.setMaxWidth(550);

        instructionsBox.getChildren().addAll(movesTitle, movesBody);

        final Text starsTitle = new Text("Earn Your Stars");
        starsTitle.setStyle(STYLE);
        final Label starsBody = new Label("When you complete a level, you will be rewarded with stars. The fewer moves you use to win, the greater your star rating will be!");
        starsBody.setWrapText(true);
        starsBody.setMaxWidth(550);

        instructionsBox.getChildren().addAll(starsTitle, starsBody);

        final ScrollPane scrollPane = new ScrollPane(instructionsBox);
        scrollPane.setFitToWidth(true);

        this.root.setCenter(scrollPane);

        final Button menu = new Button("Menù");
        menu.setPrefWidth(100);

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

        this.root.setBottom(menu);
        BorderPane.setAlignment(menu, Pos.BOTTOM_LEFT);
    }

    @Override
    public Parent getView() {
        return this.root;
    }

}
