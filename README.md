# GitHub repository for Team 5421 - BirchBots

## Robot Specs

### Swerve
    - SDS Mk4i Modules
    - REV NEO Motors
    - Spark Max Motor Controllers
    - CANCoder Absolute Encoders

# Descriptions of each file 

## Main.java 
This part launches everything 
## Robot.java
Autonomous code 
- The autonomous code is for when the robot is not being controlled by a human
- Current implementation accomplishes the minimum requirement of "taxiing (moving forward a set distance to obtain auto points)"
- Future implementation goals include:
-     The ability to score and multiple starting positions. As of 11/3/25, no work has begun on this
-     We would like to implement computer vision functionality in autonomous and teleop 
## Robotcontainer.java
Used to control the Xbox controller 
- Future implementation goals include:
-     two controllers instead of one
## Constants.java 
This file stores all relevant IDs. 
-     IDs are ways to identify the Spark Max motor controller and CTRE Can Encoders. Spark maxes are used as motor controllers to transfer code and power to the motors, and CAN encoders are used to give the position of the swerve wheel to the computer through the CAN chain. The CAN chain runs through all the electrical devices on the robot, transferring code between them.
- Motor Controllers
-      IDs for the Spark Maxes are configured in the REV hardware client app, and the ID for each device in the app must correspond to the ID for that device in the constants.java and the subsystem file using that Spark Max.
-  Encoders 
-      Can Encoders each have an ID and can be configured in the Phoenix Tuner app. Each ID on the physical device must match the swerve module it is connected to in the code.
-     As of 11/3/25, all IDs are up to date 

## Unclear 
## Swervesubsystem.java 
## SwerveCND.java
