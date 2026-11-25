# Lab: Commands and Controller Bindings

Written for **WPILib 2026** (Commands v2), in the same style as our team's robot code. Commands are classes in `commands/<subsystem>/`, constants live in `constants/`, and each subsystem has its own folder. No robot or motor is needed: everything runs in the WPILib simulator.

## What's in this project

| Folder | What's there | Do you edit it? |
|---|---|---|
| `subsystems/intake/`, `subsystems/arm/` | A pretend intake and arm. They publish their speed and angle so you can watch them. | No |
| `constants/` | Speeds, angles and the controller port | No (use them!) |
| `commands/intake/RunIntake.java` | Runs the intake at one speed | **Part 2** |
| `commands/arm/ManualArm.java` | Drives the arm from the stick | **Part 3** |
| `commands/arm/ArmToAngle.java` | Moves the arm to a preset angle | **Part 4** |
| `RobotContainer.java` | Creates everything and binds the buttons | **Parts 2–4** |
| `commands/Autos.java` | The autonomous routine | Stretch |
| `src/test/java/frc/robot/` | Tests that check each part. Read them if you're stuck: the failure messages give hints. | No |

## Part 1: Get the code (5 min)

1. On GitHub, open the lab repo and click **Fork**. This makes your own copy.
2. In VS Code, press `Ctrl+Shift+P`, run **Git: Clone**, and choose your fork. Open the folder when it asks.
3. Run **WPILib: Simulate Robot Code** (`Ctrl+Shift+P`) and tick **Sim GUI** when asked.
4. In the Sim GUI:
   - Drag **Keyboard 0** from *System Joysticks* onto **Joystick 0**. If you have an Xbox controller plugged in, drag that instead.
   - Click **Teleoperated** under *Robot State*.
   - Open the **NetworkTables** window and expand **SmartDashboard**. You should see `Intake/Speed`, `Arm/Angle`, and the command each part is running.
5. Run **WPILib: Test Robot Code**. Every test fails for now. They turn green as you finish each part.

### Keyboard controls

The defaults are below. To check or change them, use the Sim GUI menu **DS → Keyboard 0 Settings**.

| Xbox control | Keyboard key |
|---|---|
| A (button 1) | Z |
| B (button 2) | X |
| X (button 3) | C |
| Y (button 4) | V |
| Left stick up/down | W / S |

Careful: the keyboard **X** key is the Xbox **B** button.

After each change, stop the simulation (the red square in VS Code) and start it again.

## Part 2: Intake (6 min)

- In `RunIntake.java`, call `addRequirements(intake)`, start the intake in `initialize()`, and stop it in `end()`.
- In `RobotContainer.configureBindings()`, bind A and B with `whileTrue`:
  `driver.a().whileTrue(new RunIntake(intake, IntakeConstants.INTAKE_SPEED));`
- ✅ **Check:** hold Z and `Intake/Speed` shows 0.8. Let go and it shows 0. Hold X and it shows -0.5. `Part2IntakeTest` passes.
- **Commit:** "Part 2: intake command and bindings"

## Part 3: Arm default command (6 min)

- In `ManualArm.java`, add the requirement, and in `execute()` send the stick reading (with a deadband) to `arm.setSpeed(...)`. Stop the arm in `end()`.
- In the `RobotContainer` constructor: `arm.setDefaultCommand(new ManualArm(arm, () -> -driver.getLeftY()));`
- ✅ **Check:** hold W and `Arm/Angle` goes up, but stops at 120. Hold S and it goes down to 0. `Arm/Command` reads `ManualArm`. `Part3ArmTest` passes.
- **Commit:** "Part 3: manual arm"

## Part 4: Presets (5 min)

- In `ArmToAngle.java`, add the requirement, move toward the target in `execute()`, return `true` from `isFinished()` once you're close enough, and stop in `end()`.
- Bind `driver.y().onTrue(new ArmToAngle(arm, ArmConstants.UP_ANGLE))`, and do the same for X with `DOWN_ANGLE`.
- ✅ **Check:** tap V and the arm drives to 90 by itself. While it moves, `Arm/Command` reads `ArmToAngle`, then switches back to `ManualArm`. `Part4PresetTest` and `BindingsTest` pass.
- 🤔 **Think:** what happens if you hold W while the preset is running? Why?
- **Commit:** "Part 4: arm presets"

## Part 5: Hand it in (3 min)

1. Run **WPILib: Test Robot Code** one more time.
2. Push your commits: use **Sync Changes** in VS Code's Source Control panel.
3. On GitHub, open your fork and click **Contribute → Open pull request**. Title it **Lab 2 – Your Name**, and answer the questions in the description.
4. Wait for the check on your pull request. Green means every required test passed (`StretchAutoTest` is optional). If it's red, click **Details** to see which test failed, fix it, and push again. New commits update the same pull request.

Not finished? Open the pull request anyway with what you have.

## Stretch goals

1. **Autonomous:** in `Autos.intakeThenRaise`, intake for 1.5 seconds and then raise the arm. Use `.withTimeout(...)` and `.andThen(...)`. Test it by clicking **Autonomous** in the Sim GUI. `StretchAutoTest` checks it.
2. **Toggle:** change the intake binding to `toggleOnTrue`. How does it feel different?
3. **Think ahead:** a real arm has weight and momentum. What would go wrong with the "half speed until close" preset? (That's next lesson's topic.)
