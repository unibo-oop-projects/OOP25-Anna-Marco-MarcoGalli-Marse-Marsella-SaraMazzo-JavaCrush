package it.unibo.javacrush.view.api;

public interface SceneManager {
    void showMenu();
    void showLevels();
    void showGame(String difficulty); // Puoi passare parametri!
    void showInstructions(); // UNDERSTAND IF WE WANT IT
    void quit(); // UNDERSTAND IF WE WANT IT
}