package frc.robot.constants;

public class ArmConstants {
  public static final double MIN_ANGLE = 0.0; // degrees
  public static final double MAX_ANGLE = 120.0;

  // Full speed moves the pretend arm 2 degrees every 20 ms loop (100 degrees per second).
  public static final double MAX_DEGREES_PER_LOOP = 2.0;

  public static final double STICK_DEADBAND = 0.1;

  public static final double UP_ANGLE = 90.0;
  public static final double DOWN_ANGLE = 0.0;
  public static final double PRESET_SPEED = 0.5;
  public static final double ANGLE_TOLERANCE = 1.0; // degrees
}
