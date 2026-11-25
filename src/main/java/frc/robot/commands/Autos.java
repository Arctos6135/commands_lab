package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.intake.Intake;

public final class Autos {
  private Autos() {}

  /** Stretch: intake for 1.5 seconds, then raise the arm. */
  public static Command intakeThenRaise(Intake intake, Arm arm) {
    // TODO Stretch: new RunIntake(...).withTimeout(1.5).andThen(new ArmToAngle(...))
    return Commands.none();
  }
}
