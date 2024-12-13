// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveCmd;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {
  private final SwerveSubsystem m_robotDrive = new SwerveSubsystem();
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  public RobotContainer() {
    m_robotDrive.setDefaultCommand(new SwerveCmd(m_robotDrive,
                () -> -m_driverController.getLeftY(),
                () -> -m_driverController.getLeftX(),
                () -> m_driverController.getRightX())
    );
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return null;
  }
}
