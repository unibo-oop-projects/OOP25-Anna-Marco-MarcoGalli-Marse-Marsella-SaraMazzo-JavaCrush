package it.unibo.javacrush.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.impl.LevelFactory;

public class LevelFactoryTest {

    @Test
    void testIsLevelGenerationConsistent() {
        for (int i = 1; i <= 5; i++) {
            LevelConfig config = LevelFactory.createLevel(i);

            assertNotNull(config, "LevelFactory should create a non-null config for level " + i);

            assertTrue(config.rows() > 3, "Level " + i + " should have at least more than 3 rows");
            assertTrue(config.cols() > 3, "Level " + i + " should have at least more than 3 columns");
            assertTrue(config.moves() > 0, "Level " + i + " should have a positive number of moves");
            assertNotNull(config.gravity(), "Level " + i + " should have a non-null gravity engine");

            assertNotNull(config.goals(), "Level " + i + " should have non-null goals");
            assertTrue(!config.goals().isEmpty(), "Level " + i + " should have at least one goal");
        }
    }
}
