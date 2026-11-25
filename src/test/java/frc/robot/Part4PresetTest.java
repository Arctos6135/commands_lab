package frc.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.arm.ArmToAngle;
import frc.robot.constants.ArmConstants;
import org.junit.jupiter.api.Test;

class Part4PresetTest extends LabTestBase {
  @Test
  void armToAngleNeedsTheArm() {
    ArmToAngle command = new ArmToAngle(container.arm, ArmConstants.UP_ANGLE);
    assertTrue(
        command.getRequirements().contains(container.arm),
        "ArmToAngle should call addRequirements(arm) in its constructor");
  }

  @Test
  void armToAngleReachesTheTargetAndFinishes() {
    ArmToAngle command = new ArmToAngle(container.arm, 60.0);
    CommandScheduler.getInstance().schedule(command);

    step(5);
    assertTrue(container.arm.getAngle() > 0.0, "ArmToAngle should move the arm in execute()");

    step(150);
    assertFalse(command.isScheduled(),
        "ArmToAngle should finish (isFinished() returns true) once it is close to the target");
    assertEquals(60.0, container.arm.getAngle(), ArmConstants.ANGLE_TOLERANCE,
        "ArmToAngle should stop within ANGLE_TOLERANCE of the target");
  }

  @Test
  void armToAngleCanGoDown() {
    CommandScheduler.getInstance().schedule(new ArmToAngle(container.arm, 100.0));
    step(200);
    ArmToAngle down = new ArmToAngle(container.arm, 30.0);
    CommandScheduler.getInstance().schedule(down);
    step(200);

    assertFalse(down.isScheduled(), "ArmToAngle should finish when it gets close to the target");
    assertEquals(30.0, container.arm.getAngle(), ArmConstants.ANGLE_TOLERANCE,
        "ArmToAngle should move the arm down when the target is below it");
  }

  @Test
  void armToAngleStopsTheArmWhenItEnds() {
    ArmToAngle command = new ArmToAngle(container.arm, 100.0);
    CommandScheduler.getInstance().schedule(command);
    step(5);
    assertTrue(container.arm.getSpeed() != 0.0, "ArmToAngle should set the arm speed in execute()");

    command.cancel();
    // Check right away, before the default command gets a chance to run.
    assertEquals(0.0, container.arm.getSpeed(), 1e-9, "ArmToAngle should stop the arm in end()");
  }
}
