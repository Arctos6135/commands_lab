package frc.robot.commands.arm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.ArmConstants;
import frc.robot.subsystems.arm.Arm;
import java.util.function.DoubleSupplier;

/** Drives the arm from a joystick. Used as the arm's default command. */
public class ManualArm extends Command {
  private final Arm arm;
  private final DoubleSupplier stick;

  public ManualArm(Arm arm, DoubleSupplier stick) {
    this.arm = arm;
    this.stick = stick;
    // TODO Part 3: this command needs the arm.
  }

  @Override
  public void execute() {
    // TODO Part 3: read the stick with stick.getAsDouble(),
    //   pass it through MathUtil.applyDeadband(value, ArmConstants.STICK_DEADBAND),
    //   and send the result to arm.setSpeed(...).
  }

  @Override
  public void end(boolean interrupted) {
    // TODO Part 3: stop the arm.
  }
}
