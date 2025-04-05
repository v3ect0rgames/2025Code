package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.Constants.IOConstants;
import frc.robot.Commands.SwerveCmd;
import frc.robot.Subsystems.ClimbSubsystem;
import frc.robot.Subsystems.CoralSubsystem;
import frc.robot.Subsystems.LiftSubsystem;
import frc.robot.Subsystems.SwerveSubsystem;

public class RobotContainer {

  private final XboxController m_controller = new XboxController(IOConstants.kDriverControllerPort);

  private final ClimbSubsystem m_climb = new ClimbSubsystem();
  private final LiftSubsystem m_lift = new LiftSubsystem();
  private final CoralSubsystem m_coral = new CoralSubsystem();
  private final SwerveSubsystem m_drive = new SwerveSubsystem();


  public RobotContainer() {    
    configureBindings();
  }

  private void configureBindings() {

    m_drive.setDefaultCommand(new SwerveCmd(m_drive,
      () -> -m_controller.getLeftY(),
      () -> -m_controller.getLeftX(),
      () -> m_controller.getRightX())
    );

    new JoystickButton(m_controller, XboxController.Button.kX.value)
      .onTrue(new InstantCommand(() -> m_climb.up(), m_climb))
      .onFalse(new InstantCommand(() -> m_climb.stop(), m_climb));
    new JoystickButton(m_controller, XboxController.Button.kB.value)
      .onTrue(new InstantCommand(() -> m_climb.down(), m_climb))
      .onFalse(new InstantCommand(() -> m_climb.stop(), m_climb));

    new POVButton(m_controller, 0)
      .onTrue(new InstantCommand(() -> m_lift.up(), m_lift))
      .onFalse(new InstantCommand(() -> m_lift.stop(), m_lift));
    new POVButton(m_controller, 180)
      .onTrue(new InstantCommand(() -> m_lift.down(), m_lift))
      .onFalse(new InstantCommand(() -> m_lift.stop(), m_lift));

      new POVButton(m_controller, 90)
      .onTrue(new InstantCommand(() -> m_coral.up(), m_coral))
      .onFalse(new InstantCommand(() -> m_coral.stopTurn(), m_coral));
    new POVButton(m_controller, 270)
      .onTrue(new InstantCommand(() -> m_coral.down(), m_coral))
      .onFalse(new InstantCommand(() -> m_coral.stopTurn(), m_coral));;
  
    new JoystickButton(m_controller, XboxController.Button.kRightBumper.value)
      .onTrue(new InstantCommand(() -> m_coral.shoot(), m_coral))
      .onFalse(new InstantCommand(() -> m_coral.stopCoral(), m_coral));
    new JoystickButton(m_controller, XboxController.Button.kLeftBumper.value)
      .onTrue(new InstantCommand(() -> m_coral.intake(), m_coral))
      .onFalse(new InstantCommand(() -> m_coral.stopCoral(), m_coral));
  }

  public Command getAutonomousCommand() {
    return new SwerveCmd(m_drive, () -> 0.1, () -> 0.0, () -> 0.0).withTimeout(5);
  }
}