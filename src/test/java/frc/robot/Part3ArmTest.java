package frc.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import frc.robot.commands.arm.ManualArm;
import frc.robot.constants.ArmConstants;
import org.junit.jupiter.api.Test;

class Part3ArmTest extends LabTestBase {
  @Test
  void manualArmNeedsTheArm() {
    ManualArm command = new ManualArm(container.arm, () -> 0.0);
    assertTrue(
        command.getRequirements().contains(container.arm),
        "ManualArm should call addRequirements(arm) in its constructor");
  }

  @Test
  void manualArmIsTheDefaultCommand() {
    assertInstanceOf(ManualArm.class, container.arm.getDefaultCommand(),
        "Use arm.setDefaultCommand(new ManualArm(...)) in the RobotContainer constructor");
  }

  @Test
  void stickUpRaisesTheArmAndStopsAtTheTop() {
    xbox.setLeftY(-1.0); // pushing the stick up gives a negative number
    step(10);
    assertTrue(container.arm.getAngle() > 5.0,
        "Pushing the left stick up should raise the arm. Did you flip the sign with -driver.getLeftY()?");
    assertEquals("ManualArm", armCommand());

    step(100);
    assertEquals(ArmConstants.MAX_ANGLE, container.arm.getAngle(), 1e-9,
        "The arm should stop at MAX_ANGLE");
  }

  @Test
  void stickDownLowersTheArm() {
    xbox.setLeftY(-1.0);
    step(30);
    double high = container.arm.getAngle();

    xbox.setLeftY(1.0);
    step(10);
    assertTrue(container.arm.getAngle() < high, "Pulling the left stick down should lower the arm");
  }

  @Test
  void smallStickMovementsAreIgnored() {
    xbox.setLeftY(-1.0);
    step(5);
    assertTrue(container.arm.getSpeed() > 0.5, "Pushing the stick up should give the arm speed");

    xbox.setLeftY(-0.05); // inside the deadband
    step(2);
    assertEquals(0.0, container.arm.getSpeed(), 1e-9,
        "Stick values inside STICK_DEADBAND should give zero speed (MathUtil.applyDeadband)");
  }
}
