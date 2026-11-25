package frc.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.intake.RunIntake;
import frc.robot.constants.IntakeConstants;
import org.junit.jupiter.api.Test;

class Part2IntakeTest extends LabTestBase {
  @Test
  void runIntakeNeedsTheIntake() {
    RunIntake command = new RunIntake(container.intake, IntakeConstants.INTAKE_SPEED);
    assertTrue(
        command.getRequirements().contains(container.intake),
        "RunIntake should call addRequirements(intake) in its constructor");
  }

  @Test
  void runIntakeStartsAndStopsTheIntake() {
    RunIntake command = new RunIntake(container.intake, 0.3);
    CommandScheduler.getInstance().schedule(command);
    step(1);
    assertEquals(0.3, container.intake.getSpeed(), 1e-9,
        "RunIntake should set the intake to its speed in initialize()");

    command.cancel();
    step(1);
    assertEquals(0.0, container.intake.getSpeed(), 1e-9,
        "RunIntake should stop the intake in end()");
  }

  @Test
  void holdingAIntakes() {
    xbox.setAButton(true);
    step(2);
    assertEquals(IntakeConstants.INTAKE_SPEED, container.intake.getSpeed(), 1e-9,
        "Holding A should run the intake at INTAKE_SPEED (use whileTrue)");

    xbox.setAButton(false);
    step(2);
    assertEquals(0.0, container.intake.getSpeed(), 1e-9,
        "Letting go of A should stop the intake");
  }

  @Test
  void holdingBEjects() {
    xbox.setBButton(true);
    step(2);
    assertEquals(IntakeConstants.EJECT_SPEED, container.intake.getSpeed(), 1e-9,
        "Holding B should run the intake at EJECT_SPEED (use whileTrue)");

    xbox.setBButton(false);
    step(2);
    assertEquals(0.0, container.intake.getSpeed(), 1e-9,
        "Letting go of B should stop the intake");
  }
}
