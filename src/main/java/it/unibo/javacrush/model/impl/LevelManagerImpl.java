package it.unibo.javacrush.model.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.api.LevelManager;

public class LevelManagerImpl implements LevelManager {

    private final Map<Integer, Integer> starsRecords = new HashMap<>();

    @Override
    public LevelConfig getLevelSetup(int level) {    
        return LevelFactory.createLevel(level);
    }

    @Override
    public void updateStars(int level, int stars) {
        int currentBest = starsRecords.getOrDefault(level, 0);
        if (stars > currentBest) {
            starsRecords.put(level, stars);
        }
    }

    @Override
    public int getStarsForLevel(int level) {
        return starsRecords.getOrDefault(level, 0);
    }

}
