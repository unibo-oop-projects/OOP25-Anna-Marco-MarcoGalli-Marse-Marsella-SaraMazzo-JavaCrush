package it.unibo.javacrush;

import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.controller.impl.AppControllerImpl;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.model.impl.LevelManagerImpl;
import it.unibo.javacrush.view.api.GameView;
import it.unibo.javacrush.view.api.LevelsView;
import it.unibo.javacrush.view.api.MenuView;
import it.unibo.javacrush.view.api.SceneManager;
import it.unibo.javacrush.view.impl.GameViewImpl;
import it.unibo.javacrush.view.impl.LevelsViewImpl;
import it.unibo.javacrush.view.impl.MenuViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaCrushApp extends Application implements SceneManager{

    private final LevelManager levelManager = new LevelManagerImpl();
    private Scene menuScene;
    private Scene levelsScene;
    private Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
        
        this.stage = stage;
        this.stage.setWidth(800);
        this.stage.setHeight(600);

        AppController appController = new AppControllerImpl(this, this.levelManager);
        MenuView menuView = new MenuViewImpl(appController);
        menuScene = new Scene(menuView.getView(), this.stage.getWidth(), this.stage.getHeight());

        LevelsView levelsView = new LevelsViewImpl(appController, this.levelManager);
        levelsScene = new Scene(levelsView.getView(), this.stage.getWidth(), this.stage.getHeight());

		this.stage.setTitle("JavaCrush");
        this.stage.setScene(menuScene);
        this.stage.show();

	}

    @Override
    public void showMenu() {
        this.stage.setScene(this.menuScene);
    }

    @Override
    public void showLevels() {
        this.stage.setScene(this.levelsScene);
    }

    @Override
    public void showGame(GameController gameController) {
        GameView gameview = new GameViewImpl();
        gameview.setController(gameController);
        Scene gameScene = new Scene(gameview.getView(),this.stage.getWidth(), this.stage.getHeight());
        this.stage.setScene(gameScene);
    }

    @Override
    public void quit() {
        this.showLevels();
    }

    @Override
    public void showInstructions() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showInstructions'");
    }

    @Override
    public GameView getGameView() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameView'");
    }
}

