package frc.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.constants.ArmConstants;
import frc.robot.constants.IntakeConstants;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/** Stretch goal. This one doesn't have to pass for your pull request check to go green. */
@Tag("stretch")
class StretchAutoTest extends LabTestBase {
  @Test
  void autoIntakesThenRaisesTheArm() {
    DriverStationSim.setAutonomous(true);
    Command auto = container.getAutonomousCommand();
    CommandScheduler.getInstance().schedule(auto);

    step(25); // 0.5 s
    assertEquals(IntakeConstants.INTAKE_SPEED, container.intake.getSpeed(), 1e-9,
        "Auto should start by running the intake at INTAKE_SPEED");
    assertEquals(ArmConstants.MIN_ANGLE, container.arm.getAngle(), 1e-9,
        "The arm shouldn't move while the intake is running");

    step(55); // 1.6 s
    assertEquals(0.0, container.intake.getSpeed(), 1e-9,
        "After 1.5 s the intake should stop (withTimeout(1.5))");
    assertTrue(container.arm.getAngle() > ArmConstants.MIN_ANGLE,
        "After the intake, auto should raise the arm (andThen(new ArmToAngle(...)))");

    step(150);
    assertFalse(auto.isScheduled(), "Auto should finish once the arm reaches the preset");
    assertEquals(ArmConstants.UP_ANGLE, container.arm.getAngle(), ArmConstants.ANGLE_TOLERANCE,
        "Auto should raise the arm to UP_ANGLE");
  }
}
