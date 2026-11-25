package frc.robot;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj.simulation.SimHooks;
import edu.wpi.first.wpilibj.simulation.XboxControllerSim;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Shared setup for the lab tests. You don't need to change anything in src/test.
 *
 * <p>Each test gets a fresh RobotContainer, an enabled robot, and a pretend Xbox controller on
 * port 0. {@link #step(int)} runs the robot loop like the real robot does every 20 ms.
 */
public abstract class LabTestBase {
  protected RobotContainer container;
  protected XboxControllerSim xbox;

  @BeforeEach
  void setUpRobot() {
    HAL.initialize(500, 0);
    SimHooks.pauseTiming();
    resetScheduler();

    DriverStationSim.resetData();
    DriverStationSim.setDsAttached(true);
    DriverStationSim.setAutonomous(false);
    DriverStationSim.setEnabled(true);

    xbox = new XboxControllerSim(0);
    xbox.setButtonCount(10);
    xbox.setAxisCount(6);
    DriverStationSim.notifyNewData();
    DriverStation.refreshData();

    container = new RobotContainer();
  }

  @AfterEach
  void tearDownRobot() {
    resetScheduler();
    SimHooks.resumeTiming();
  }

  private static void resetScheduler() {
    CommandScheduler scheduler = CommandScheduler.getInstance();
    scheduler.cancelAll();
    scheduler.unregisterAllSubsystems();
    scheduler.getActiveButtonLoop().clear();
    scheduler.clearComposedCommands();
  }

  /** Runs the robot loop {@code loops} times, 20 ms each. */
  protected void step(int loops) {
    for (int i = 0; i < loops; i++) {
      DriverStationSim.notifyNewData();
      DriverStation.refreshData();
      CommandScheduler.getInstance().run();
      SimHooks.stepTiming(0.02);
    }
  }

  /** The name of the command the arm is running, or "none". */
  protected String armCommand() {
    var current = container.arm.getCurrentCommand();
    return current == null ? "none" : current.getName();
  }

  /** Presses a button for one loop, then lets go (a "tap"). */
  protected void tap(Runnable press, Runnable release) {
    press.run();
    step(1);
    release.run();
    step(1);
  }
}
