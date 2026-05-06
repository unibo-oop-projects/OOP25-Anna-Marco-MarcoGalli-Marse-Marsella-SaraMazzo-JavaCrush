package it.unibo.javacrush.view.impl;

import java.lang.reflect.Modifier;

import javax.print.attribute.standard.Media;
import javax.sound.midi.Track;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.spi.AudioFileReader;
import javax.swing.ImageIcon;
import javax.xml.transform.Source;

import it.unibo.javacrush.common.CellType;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class JavaFXView extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        final TilePane griglia = new TilePane(Orientation.HORIZONTAL);
        griglia.setMaxHeight(Screen.getPrimary().getVisualBounds().getMaxY());
        griglia.setMaxWidth(Screen.getPrimary().getVisualBounds().getMaxX());        

        for (int i = 0; i < 16; i++) {
            ImageView img = new ImageView(new Image(CellType.getRandomType().toString().toLowerCase() + ".png"));

            img.setFitHeight(griglia.getMaxHeight() / 4);
            img.setFitWidth(griglia.getMaxWidth() / 4);
            
            griglia.getChildren().add(img);
        }

        griglia.autosize();
		stage.setTitle("Hello");
		stage.setScene(new Scene(griglia, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight()));
		
        stage.show();
    }
}
