package frc.robot.Commands;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystems.SwerveSubsystem;

public class DriveAuto extends Command {

        private SwerveSubsystem m_drive;
        private final Timer m_timer = new Timer();
        private double startTime;

        public DriveAuto(SwerveSubsystem m_drive) {
            this.m_drive = m_drive;
            m_timer.reset();
            m_timer.start();
        }

        public void initialize() {
            startTime = m_timer.get();
        }
     
        public void execute() {
            m_drive.setModuleStates(new SwerveModuleState[]{
                new SwerveModuleState(2, new Rotation2d(0)),
                new SwerveModuleState(2, new Rotation2d(0)),
                new SwerveModuleState(2, new Rotation2d(0)),
                new SwerveModuleState(2, new Rotation2d(0)),
            });
        }
     
        public boolean isFinished() {
            return m_timer.get() - startTime >= 3.5;
        }
     
        protected void end() {
            m_timer.reset();
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
