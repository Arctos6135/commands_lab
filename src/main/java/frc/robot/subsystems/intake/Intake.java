package frc.robot.subsystems.intake;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** A pretend intake. On the real robot, periodic() would send the speed to a motor. */
public class Intake extends SubsystemBase {
  private double speed = 0.0;

  /** Sets the intake speed, from -1 (full eject) to 1 (full intake). */
  public void setSpeed(double value) {
    speed = MathUtil.clamp(value, -1.0, 1.0);
  }

  public double getSpeed() {
    return speed;
  }

  @Override
  public void periodic() {
    // On the robot: motor.set(speed);
    SmartDashboard.putNumber("Intake/Speed", speed);
    Command current = getCurrentCommand();
    SmartDashboard.putString("Intake/Command", current == null ? "none" : current.getName());
  }
}
