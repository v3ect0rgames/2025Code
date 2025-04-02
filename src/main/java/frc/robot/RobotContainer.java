package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.LiftSubsystem;

public class RobotContainer {

  private final XboxController controller = new XboxController(OIConstants.kDriverControllerPort);

  private final ClimbSubsystem climb = new ClimbSubsystem();
  private final LiftSubsystem lift = new LiftSubsystem();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(controller, XboxController.Button.kX.value)
      .onTrue(new InstantCommand(() -> climb.up(), climb))
      .onFalse(new InstantCommand(() -> climb.stop(), climb));
    new JoystickButton(controller, XboxController.Button.kB.value)
      .onTrue(new InstantCommand(() -> climb.down(), climb))
      .onFalse(new InstantCommand(() -> climb.stop(), climb));

    new POVButton(controller, 0)
      .onTrue(new InstantCommand(() -> lift.up(), lift))
      .onFalse(new InstantCommand(() -> lift.stop(), lift));
    new POVButton(controller, 180)
      .onTrue(new InstantCommand(() -> lift.down(), lift))
      .onFalse(new InstantCommand(() -> lift.stop(), lift));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
