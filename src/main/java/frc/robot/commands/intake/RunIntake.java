package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;

/** Runs the intake at one speed until the command ends. */
public class RunIntake extends Command {
  private final Intake intake;
  private final double speed;

  public RunIntake(Intake intake, double speed) {
    this.intake = intake;
    this.speed = speed;
    // TODO Part 2: tell the scheduler this command needs the intake.
    //   Hint: addRequirements(...)
  }

  @Override
  public void initialize() {
    // TODO Part 2: start the intake at this command's speed.
  }

  @Override
  public void end(boolean interrupted) {
    // TODO Part 2: stop the intake.
  }

  // No isFinished(): it returns false by default, so this runs until it is interrupted.
}
