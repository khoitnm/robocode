package org.tnmk.robocode.robot.theunfolding;

import java.util.Arrays;
import java.util.List;
import org.tnmk.robocode.robot.AbstractWinRateTest;
import org.tnmk.robocode.robot.TheUnfoldingRobot;
import supersample.SuperRamFire;

public class AgainstSuperRamFireTest extends AbstractWinRateTest {

    @Override
    public TestConfig constructTestConfig() {
        List<String> enemyNames = Arrays.asList(
                SuperRamFire.class.getCanonicalName()
        );

        /**
         * Run 100 (v.2.4.0): 78%, 84.0%
         */
        TestConfig testConfig = new TestConfig(TheUnfoldingRobot.class, enemyNames, 0.7, 100);
        return testConfig;
    }
}
