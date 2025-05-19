package frc.robot.Subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.spark.SparkAbsoluteEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveModule extends SubsystemBase{

    SparkMax driveMotor;
    int driveMotorID;
    SparkAbsoluteEncoder driveMotorEncoder;
    SparkClosedLoopController  driveController;
    SparkMax steerMotor;
    SparkAbsoluteEncoder steerMotorEncoder;
    PIDController steerController;
    CANcoder moduleEncoder;
    double encoderOffsetRotations;
    final double WHEEL_DIAMETER = Units.inchesToMeters(4);
    final double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
    final double GEAR_RATIO = 1.0 / 6.75;
    final double DRIVE_POSITION_CONVERSION = WHEEL_CIRCUMFERENCE * GEAR_RATIO;
    final double DRIVE_VELOCITY_CONVERSION = DRIVE_POSITION_CONVERSION / 60.0;
    final double STEER_POSITION_CONVERSION = 1;
    final double STEER_VELOCITY_CONVERSION = STEER_POSITION_CONVERSION / 60.0;

        public SwerveModule(int driveMotorID, int steerMotorID, int encoderID, Double encoderOffsetRotations){
            this.driveMotorID = driveMotorID;

            driveMotor = new SparkMax(driveMotorID, MotorType.kBrushless);
            SparkMaxConfig driveConfig = new SparkMaxConfig();
            driveConfig.smartCurrentLimit(40);
            driveConfig.idleMode(IdleMode.kBrake);
            driveMotor.configure(driveConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

            driveMotorEncoder = driveMotor.getAbsoluteEncoder();
            steerMotor = new SparkMax(steerMotorID, MotorType.kBrushless);
            SparkMaxConfig steerConfig = new SparkMaxConfig();
            steerConfig.idleMode(IdleMode.kBrake);
            steerConfig.smartCurrentLimit(40);
            steerMotor.configure(steerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
            moduleEncoder = new CANcoder(encoderID);
            this.encoderOffsetRotations = encoderOffsetRotations;

            steerController = new PIDController(1.5, 0.0, 0.0);
            steerController.enableContinuousInput(0, 1);

        }

        public void setTargetState(SwerveModuleState targetState) {
            double currentAngle = getModuleAngRotations();
            targetState.optimize(new Rotation2d(currentAngle));
            steerMotor.set(-steerController.calculate(currentAngle, targetState.angle.getRotations()));
            targetState.speedMetersPerSecond *= targetState.angle.minus(new Rotation2d(currentAngle*2*Math.PI)).getCos();
            driveMotor.set(targetState.speedMetersPerSecond/4.52); 
        }

        public double getModuleAngRotations(){
            return moduleEncoder.getAbsolutePosition().getValueAsDouble() - encoderOffsetRotations;
        }
        
        public SwerveModulePosition getModulePosition() {
            return new SwerveModulePosition(
                driveMotorEncoder.getPosition(),
                Rotation2d.fromRotations(getModuleAngRotations())
            );  
        }

        public SwerveModuleState getSwerveModuleState() {
            return new SwerveModuleState(
                driveMotorEncoder.getVelocity(),
                Rotation2d.fromRotations(getModuleAngRotations()));
        }
}