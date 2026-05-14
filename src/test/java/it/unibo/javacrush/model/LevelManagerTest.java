package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.api.LevelManager;
import it.unibo.javacrush.model.impl.LevelManagerImpl;

public class LevelManagerTest {

    private LevelManager levelManager;

    @BeforeEach
    void setUp() {
        this.levelManager = new LevelManagerImpl();
    }

    @Test
    void testDefaultLevel() {
        LevelConfig config = levelManager.getLevelSetup(-999);
        assertNotNull(config);
        assertEquals(8, config.rows());
    }

    @Test
    void testStarsRecording() {
        assertEquals(0, levelManager.getStarsForLevel(1), "Initially, stars for level 1 should be 0");
        levelManager.updateStars(1, 2);
        assertEquals(2, levelManager.getStarsForLevel(1), "the recorded stars for level 1 should be 2");
        levelManager.updateStars(1, 1);
        assertEquals(2, levelManager.getStarsForLevel(1), "the recorded stars for level 1 should not decrease");
        levelManager.updateStars(1, 3);
        assertEquals(3, levelManager.getStarsForLevel(1), "the recorded stars for level 1 should be 3");
    }
}
