package it.unibo.javacrush;

import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.controller.api.MockMenuController;
import it.unibo.javacrush.controller.impl.MockMenuControllerImpl;
import it.unibo.javacrush.view.api.GameView;
import it.unibo.javacrush.view.api.LevelsView;
import it.unibo.javacrush.view.api.MenuView;
import it.unibo.javacrush.view.api.SceneManager;
import it.unibo.javacrush.view.impl.LevelsViewImpl;
import it.unibo.javacrush.view.impl.MenuViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaCrushApp extends Application implements SceneManager{

    private Scene menuScene;
    private Scene levelsScene;
    private Scene gameScene;
    private Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
        
        this.stage = stage;
        MockMenuController menuController = new MockMenuControllerImpl(this);
        MenuView viewroot = new MenuViewImpl(menuController);
        LevelsView levelsView = new LevelsViewImpl();
        this.levelsScene = new Scene(levelsView.getView(), 500, 300);
		this.menuScene = new Scene(viewroot.getView(), 500, 300);
		stage.setTitle("JavaCrush");
		stage.setScene(this.menuScene);
		stage.show();

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showGame'");
    }

    @Override
    public void quit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'quit'");
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

