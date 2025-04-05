package frc.robot.Subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralSubsystem extends SubsystemBase {

   private final SparkMax m_turnMotor = new SparkMax(12, MotorType.kBrushless);
   private final SparkMax m_shootMotor = new SparkMax(13, MotorType.kBrushless);
    
   private final static double turnSpeed = 0.35;
   private final static double shootSpeed = 1.0;

   public void down() { 
      m_turnMotor.set(-turnSpeed);
   }

   public void up() {
      m_turnMotor.set(turnSpeed);
   }

   public void stopTurn() {
      m_turnMotor.set(0);
   }

   public void shoot() {
      m_shootMotor.set(shootSpeed);
   }
   public void intake() {
      m_shootMotor.set(-shootSpeed);
   }

   public void stopCoral() {
      m_shootMotor.set(0);
   }
}