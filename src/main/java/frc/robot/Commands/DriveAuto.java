package frc.robot.Commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.SwerveSubsystem;

public class DriveAuto extends Command {

        private final SwerveSubsystem m_drive = new SwerveSubsystem();
        private final Timer m_timer = new Timer();
        private double startTime;

        public void initialize() {
            startTime = m_timer.get();
        }
     
        public void execute() {
            m_drive.setModuleStates(new SwerveModuleState[]{
                new SwerveModuleState(0.1, new Rotation2d(0)),
                new SwerveModuleState(0.1, new Rotation2d(0)),
                new SwerveModuleState(0.1, new Rotation2d(0)),
                new SwerveModuleState(0.1, new Rotation2d(0)),
            });
        }
     
        public boolean isFinished() {
            return m_timer.get() - startTime >= 2;
        }
     
        protected void end() {
            m_drive.setModuleStates(new SwerveModuleState[]{
                new SwerveModuleState(0, new Rotation2d(0)),
                new SwerveModuleState(0, new Rotation2d(0)),
                new SwerveModuleState(0, new Rotation2d(0)),
                new SwerveModuleState(0, new Rotation2d(0)),
            });
        }
     
        protected void interrupted() {
            end();
        }
}
