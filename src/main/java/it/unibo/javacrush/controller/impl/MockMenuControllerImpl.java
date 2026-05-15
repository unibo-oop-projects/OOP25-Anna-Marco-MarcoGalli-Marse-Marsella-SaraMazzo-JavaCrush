package it.unibo.javacrush.controller.impl;

import it.unibo.javacrush.controller.api.MockMenuController;
import it.unibo.javacrush.view.api.SceneManager;

public class MockMenuControllerImpl implements MockMenuController{

    private final SceneManager sceneManager;

    public MockMenuControllerImpl(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void playButton() {
        sceneManager.showLevels();
    }

    @Override
    public void howToPlayButton() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'howToPlayButton'");
    }



}
