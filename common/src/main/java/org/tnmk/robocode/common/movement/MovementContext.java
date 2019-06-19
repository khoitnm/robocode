package org.tnmk.robocode.common.movement;

import java.awt.geom.Point2D;
import org.tnmk.robocode.common.log.DebugHelper;
import org.tnmk.robocode.common.log.LogHelper;
import robocode.AdvancedRobot;
import robocode.StatusEvent;

/**
 * The context to manage {@link MoveStrategy}.<br/>
 * This class help us to know which movement strategy is using.
 * <p/>
 * This object should be managed by some composition movement class such as TheUnfoldingMovement.
 */
public class MovementContext {
    private final AdvancedRobot robot;
    private MoveStrategy moveStrategy = MoveStrategy.NONE;


    /**
     * This value should be constantly updated every tick by {@link robocode.Robot#onStatus(StatusEvent)}.
     * <p/>
     * Note: We may not need this,
     * check {@link org.tnmk.robocode.common.helper.Move2DHelper#setMoveToDestinationWithShortestPath(AdvancedRobot, Point2D)} to see how can it change direction without knowing direction.
     * (hint: by changing heading angle)???
     * <br/>
     * <p/>
     * I tried but it didn't work?! So we still need it. And actually, we don't need to manually update direction.<br/>
     * Just set {@link robocode.Robot#ahead(double)}, and then we can setup {@link robocode.Robot#onStatus(StatusEvent)} to automatically updated direction.
     */
    private int direction = 1;

    public MovementContext(AdvancedRobot robot) {
        this.robot = robot;
    }

    /**
     * Set moveStrategy is {@link MoveStrategy#NONE}
     */
    public void setNone() {
        /** Don't directly set strategy to NONE because we may already has some debugging statements inside {@link #setMoveStrategy(MoveStrategy)} */
        setMoveStrategy(MoveStrategy.NONE);
    }

    /**
     * @return Check if no special movement type
     */
    public boolean isNone() {
        return moveStrategy == null || moveStrategy == MoveStrategy.NONE;
    }

    /**
     * @param moveStrategy
     * @return check if the current moveType is equals to moveStrategy
     */
    public boolean is(MoveStrategy moveStrategy) {
        return this.moveStrategy == moveStrategy;
    }

    public void reverseDirection() {
        direction = -direction;
    }

    public int getDirection() {
        return direction;
    }


    public void setDirection(int direction) {
        if (direction == 0) {
            throw new IllegalArgumentException("Direction cannot be 0");
        }
        if (Math.abs(direction) != 1) {
            this.direction = direction / (Math.abs(direction));
        } else {
            this.direction = direction;
        }
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        if (DebugHelper.isDebugMoveStrategyChange()) {
            LogHelper.logRobotMovement(robot, this.moveStrategy + ": end");//end the old strategy
            LogHelper.logRobotMovement(robot, moveStrategy + ": begin");//begin the new strategy
        }
        this.moveStrategy = moveStrategy;
    }

    public AdvancedRobot getRobot() {
        return robot;
    }

    public boolean isAmong(MoveStrategy... moveStrategies) {
        for (MoveStrategy strategy : moveStrategies) {
            if (strategy == this.moveStrategy) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotAmong(MoveStrategy... moveStrategies) {
        return !isAmong(moveStrategies);
    }

    public boolean hasLowerPriority(MoveStrategy moveStrategy) {
        return this.moveStrategy.getPriorty() < moveStrategy.getPriorty();
    }

    public boolean hasLowerOrEqualPriority(MoveStrategy moveStrategy) {
        return this.moveStrategy.getPriorty() <= moveStrategy.getPriorty();
    }

    public boolean hasLowerOrEqualPriorityButDifferentStrategy(MoveStrategy moveStrategy) {
        boolean result = hasLowerOrEqualPriority(moveStrategy) && moveStrategy != this.moveStrategy;
        return result;
    }
}
