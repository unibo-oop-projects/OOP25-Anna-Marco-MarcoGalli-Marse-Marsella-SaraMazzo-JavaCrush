package it.unibo.javacrush;

import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.controller.impl.AppControllerImpl;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.model.impl.LevelManagerImpl;
import it.unibo.javacrush.view.api.GameView;
import it.unibo.javacrush.view.api.InstructionsView;
import it.unibo.javacrush.view.api.LevelsView;
import it.unibo.javacrush.view.api.MenuView;
import it.unibo.javacrush.view.api.SceneManager;
import it.unibo.javacrush.view.impl.GameViewImpl;
import it.unibo.javacrush.view.impl.InstructionsViewImpl;
import it.unibo.javacrush.view.impl.LevelsViewImpl;
import it.unibo.javacrush.view.impl.MenuViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaCrushApp extends Application implements SceneManager{

    AppController appController;
    private final LevelManager levelManager = new LevelManagerImpl();
    private MenuView menuView;
    private LevelsView levelsView;
    private InstructionsView instructionsView;
    private GameView gameView;
    private Scene scene;
    private Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
        
        this.stage = stage;
        this.stage.setWidth(1000);
        this.stage.setHeight(600);

        this.appController = new AppControllerImpl(this, this.levelManager);

        this.menuView = new MenuViewImpl(this.appController);
        this.levelsView = new LevelsViewImpl(this.appController, this.levelManager);
        this.instructionsView = new InstructionsViewImpl(this.appController);
        this.gameView = new GameViewImpl();

        this.scene = new Scene(this.menuView.getView(), this.stage.getWidth(), this.stage.getHeight());
		this.stage.setTitle("JavaCrush");
        this.stage.setScene(this.scene);
        this.stage.show();

	}

    @Override
    public void showMenu() {
        this.scene.setRoot(menuView.getView());
    }

    @Override
    public void showLevels() {
        this.scene.setRoot(levelsView.getView());
    }

    @Override
    public void showGame(GameController gameController) {
        gameView.setController(gameController, this.appController);
        this.scene.setRoot(gameView.getView());
    }

    @Override
    public void quit() {
        this.showLevels();
    }

    @Override
    public void showInstructions() {
        this.scene.setRoot(instructionsView.getView());
    }

    @Override
    public GameView getGameView() {
        return this.gameView;
    }
}

