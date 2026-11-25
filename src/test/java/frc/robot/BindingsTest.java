package frc.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import frc.robot.constants.ArmConstants;
import org.junit.jupiter.api.Test;

class BindingsTest extends LabTestBase {
  @Test
  void tappingYRaisesTheArmToThePreset() {
    tap(() -> xbox.setYButton(true), () -> xbox.setYButton(false));
    assertEquals("ArmToAngle", armCommand(),
        "Tapping Y should start ArmToAngle (use onTrue)");

    step(150);
    assertEquals(ArmConstants.UP_ANGLE, container.arm.getAngle(), ArmConstants.ANGLE_TOLERANCE,
        "After tapping Y the arm should drive itself to UP_ANGLE");
    assertEquals("ManualArm", armCommand(),
        "Once the preset finishes, the arm should go back to its default command");
  }

  @Test
  void tappingXLowersTheArmToThePreset() {
    tap(() -> xbox.setYButton(true), () -> xbox.setYButton(false));
    step(150);

    tap(() -> xbox.setXButton(true), () -> xbox.setXButton(false));
    assertEquals("ArmToAngle", armCommand(),
        "Tapping X should start ArmToAngle (use onTrue)");

    step(150);
    assertEquals(ArmConstants.DOWN_ANGLE, container.arm.getAngle(), ArmConstants.ANGLE_TOLERANCE,
        "After tapping X the arm should drive itself to DOWN_ANGLE");
  }
}
