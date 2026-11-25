package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ArmConstants;
import frc.robot.subsystems.arm.Arm;

/**
 * Moves the arm toward a target angle at a fixed speed, and finishes when it is close enough.
 * This is "bang-bang" control: full effort or nothing. PID will do better.
 */
public class ArmToAngle extends Command {
  private final Arm arm;
  private final double target;

  public ArmToAngle(Arm arm, double target) {
    this.arm = arm;
    this.target = target;
    // TODO Part 4: this command needs the arm.
  }

  @Override
  public void execute() {
    // TODO Part 4: work out the error (target - arm.getAngle()), then
    //   arm.setSpeed(ArmConstants.PRESET_SPEED * Math.signum(error));
  }

  @Override
  public boolean isFinished() {
    // TODO Part 4: return true when the arm is within ArmConstants.ANGLE_TOLERANCE of the target.
    //   Hint: Math.abs(...)
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    // TODO Part 4: stop the arm.
  }
}
