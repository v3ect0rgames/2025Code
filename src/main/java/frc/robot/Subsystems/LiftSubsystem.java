
package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;

public class LiftSubsystem extends SubsystemBase {
    
    private final SparkMax leftMotor = new SparkMax(9, MotorType.kBrushless);
    private final SparkMax rightMotor = new SparkMax(10, MotorType.kBrushless);

    private final static double speed = 0.4;
 
    public void up() {
        leftMotor.set(speed);
        rightMotor.set(-speed);
    }

    public void down(){
        leftMotor.set(-speed);
        rightMotor.set(speed);
    }

    public void stop() {
        leftMotor.set(0);
        rightMotor.set(0);
    }
}