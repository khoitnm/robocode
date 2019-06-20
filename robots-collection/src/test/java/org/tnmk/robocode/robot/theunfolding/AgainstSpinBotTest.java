package org.tnmk.robocode.robot.theunfolding;

import java.util.Arrays;
import java.util.List;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.tnmk.robocode.robot.AbstractBulletDamageTest;
import org.tnmk.robocode.robot.TheUnfoldingRobot;

/**
 * <pre>
 * Some example code:
 * https://hiraidekeone.wordpress.com/2013/02/26/robocode-quality-assurance-and-junit-testing/
 * https://bretkikehara.wordpress.com/2013/02/26/robocode-unit-testing-goodness/
 * https://github.com/robo-code/robocode/blob/master/plugins/testing/robocode.testing.samples/src/main/java/sample/TestWallBehavior.java
 *
 * Set up to run this test:
 * In IntelliJ menu > Run > Edit Configurations > In VM Options, add: " -Drobocode.home=D:\SourceCode\RoboCode\robocode"
 * </pre>
 */
@RunWith(JUnit4.class)
public class AgainstSpinBotTest extends AbstractBulletDamageTest {

    @Override
    public AbstractBulletDamageTest.TestConfig constructTestConfig() {
        List<String> enemyNames = Arrays.asList(
                "sample.SpinBot"
        );

        AbstractBulletDamageTest.TestConfig testConfig = new AbstractBulletDamageTest.TestConfig(TheUnfoldingRobot.class, enemyNames, 0.8, 20, 90);
        return testConfig;
    }
}