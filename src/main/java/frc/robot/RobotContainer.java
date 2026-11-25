package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Autos;
import frc.robot.constants.ControllerConstants;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.intake.Intake;

/** Where the robot's parts, the controller and the button bindings live. */
public class RobotContainer {
  private final CommandXboxController driver =
      new CommandXboxController(ControllerConstants.DRIVER_CONTROLLER);

  public final Intake intake = new Intake();
  public final Arm arm = new Arm();

  public RobotContainer() {
    // TODO Part 3: make ManualArm the arm's default command, reading the left stick:
    //   arm.setDefaultCommand(new ManualArm(arm, () -> -driver.getLeftY()));
    //   (VS Code will offer to add the import for ManualArm.)

    configureBindings();
  }

  private void configureBindings() {
    // TODO Part 2: hold A to run new RunIntake(intake, IntakeConstants.INTAKE_SPEED)
    //   driver.a().whileTrue(...);
    // TODO Part 2: hold B to run it at IntakeConstants.EJECT_SPEED

    // TODO Part 4: tap Y for new ArmToAngle(arm, ArmConstants.UP_ANGLE),
    //   and X for ArmConstants.DOWN_ANGLE. (Use onTrue.)
  }

  public Command getAutonomousCommand() {
    return Autos.intakeThenRaise(intake, arm);
  }
}
