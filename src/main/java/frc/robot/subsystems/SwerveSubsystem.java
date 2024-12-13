package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class SwerveSubsystem extends SubsystemBase {
        //This rotation is just for simulation purposes, will be replaced with encoders on the bot
        double rot = 0;
        // Swerve module defined in subsystems, class to represesnt each corner
        // Parameters: drive motor id, turn motor id, whether the motor is reversed, absolute encoder id, absolute encoder offset, whether the encoder is reversed
        // These can all be physically found on the components or in rev software (will show u)
        // Repeat x4
    private final SwerveModule frontLeft = new SwerveModule(
            DriveConstants.kFrontLeftDriveMotorPort,
            DriveConstants.kFrontLeftTurningMotorPort,
            DriveConstants.kFrontLeftDriveEncoderReversed,
            DriveConstants.kFrontLeftTurningEncoderReversed,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderPort,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kFrontLeftDriveAbsoluteEncoderReversed);

    private final SwerveModule frontRight = new SwerveModule(
            DriveConstants.kFrontRightDriveMotorPort,
            DriveConstants.kFrontRightTurningMotorPort,
            DriveConstants.kFrontRightDriveEncoderReversed,
            DriveConstants.kFrontRightTurningEncoderReversed,
            DriveConstants.kFrontRightDriveAbsoluteEncoderPort,
            DriveConstants.kFrontRightDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kFrontRightDriveAbsoluteEncoderReversed);

    private final SwerveModule backLeft = new SwerveModule(
            DriveConstants.kBackLeftDriveMotorPort,
            DriveConstants.kBackLeftTurningMotorPort,
            DriveConstants.kBackLeftDriveEncoderReversed,
            DriveConstants.kBackLeftTurningEncoderReversed,
            DriveConstants.kBackLeftDriveAbsoluteEncoderPort,
            DriveConstants.kBackLeftDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kBackLeftDriveAbsoluteEncoderReversed);

    private final SwerveModule backRight = new SwerveModule(
            DriveConstants.kBackRightDriveMotorPort,
            DriveConstants.kBackRightTurningMotorPort,
            DriveConstants.kBackRightDriveEncoderReversed,
            DriveConstants.kBackRightTurningEncoderReversed,
            DriveConstants.kBackRightDriveAbsoluteEncoderPort,
            DriveConstants.kBackRightDriveAbsoluteEncoderOffsetRad,
            DriveConstants.kBackRightDriveAbsoluteEncoderReversed);


    public SwerveSubsystem() {}

    public void stopModules() {
        //When u wanna stop the swerve modules, stop the damn swerve modules
        frontLeft.stop();
        frontRight.stop();
        backLeft.stop();
        backRight.stop();
    }

    //This method is just for simulating purposes, same with the rot variable
    public void addRot(double spd) {
        rot += spd/180*Math.PI;
        SmartDashboard.putNumber("rot", rot);
    }

    public void setModuleStates(SwerveModuleState[] desiredStates) {
        // Create an array of numbers with the rotation and speeds (what they should be, NOT WHAT THEY ARE)
        double[] states = new double[] {
                desiredStates[0].angle.getRadians(),
                desiredStates[0].speedMetersPerSecond,
                desiredStates[1].angle.getRadians(),
                desiredStates[1].speedMetersPerSecond,
                desiredStates[2].angle.getRadians(),
                desiredStates[2].speedMetersPerSecond,
                desiredStates[3].angle.getRadians(),
                desiredStates[3].speedMetersPerSecond
              };
        // Desaturate wheel speeds normalizes all the speeds, if theyre going faster than they physically can, divide by that number to get max speed of 1
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
        // Swerve module states each store an angle and speed, were given an array of 4 states for each corner, give each state to each corner
        frontRight.setDesiredState(desiredStates[0]);
        frontLeft.setDesiredState(desiredStates[1]);
        backRight.setDesiredState(desiredStates[2]);
        backLeft.setDesiredState(desiredStates[3]);
        // SmartDashboard is just a place to put data, this can be obtained from simulation software, put speeds/ rotations
        SmartDashboard.putNumberArray("states", states);

    }

    @Override
    public void periodic() {}
}