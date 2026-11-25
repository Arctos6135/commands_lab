package frc.robot.subsystems.arm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.ArmConstants;

/** A pretend arm that remembers its angle in degrees. No motor needed. */
public class Arm extends SubsystemBase {
  private double angle = ArmConstants.MIN_ANGLE;
  private double speed = 0.0;

  /** Sets how fast the arm moves, from -1 (down) to 1 (up). */
  public void setSpeed(double percent) {
    speed = MathUtil.clamp(percent, -1.0, 1.0);
  }

  public double getSpeed() {
    return speed;
  }

  public double getAngle() {
    return angle;
  }

  @Override
  public void periodic() {
    // Pretend physics: move a little every loop, and stop at the limits.
    // On the robot: motor.set(speed), and the angle would come from an encoder.
    angle = MathUtil.clamp(
        angle + speed * ArmConstants.MAX_DEGREES_PER_LOOP,
        ArmConstants.MIN_ANGLE,
        ArmConstants.MAX_ANGLE);

    SmartDashboard.putNumber("Arm/Angle", angle);
    Command current = getCurrentCommand();
    SmartDashboard.putString("Arm/Command", current == null ? "none" : current.getName());
  }
}
