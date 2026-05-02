package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.model.api.Goal;
import it.unibo.javacrush.model.api.GoalFactory;

public class GoalFactoryImpl implements GoalFactory{

    private static class GoalImpl implements Goal{

        @Override
        public CellType getTargetType() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getTargetType'");
        }

        @Override
        public int getTargetAmount() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getTargetAmount'");
        }

        @Override
        public int getCurrentAmount() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCurrentAmount'");
        }

        @Override
        public void addProgress(int count) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'addProgress'");
        }

        @Override
        public boolean isReached() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'isReached'");
        }

    }

    @Override
    public Goal createGoal(CellType type, int targetAmount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createGoal'");
    }
}
