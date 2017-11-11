# STEAMworks
The Programming project for Team 3335's 2017 robot.

## [Updating robot firmware](docs/UpdateRobot.md)

## [Controls](docs/Controls.md)

## Changes for 2018 beta software
* ```RobotDrive``` is deprecated; use ```DifferentialDrive``` instead.
    ```java
    import edu.wpi.first.wpilibj.drive.DifferentialDrive;
    DifferentialDrive myRobot = new DifferentialDrive(new Talon(0), new Talon(1));
    myRobot.arcadeDrive(move, rotate);
    // curvatureDrive() is an alternate replacement for RobotDrive.drive()
    myRobot.curvatureDrive(move, rotate, isQuickTurn);
    ```
* ```CANTalon``` has changed to ```TalonSRX```
  * 2017:
    ```java
    import com.ctre.CANTalon;
    CANTalon motor = new CANTalon(1);
    ```
  * 2018 beta:
    ```java
    import com.ctre.phoenix.MotorControl.CAN.TalonSRX;
    TalonSRX motor = new TalonSRX(1);
    ```
  * For changing a ```RobotDrive``` with more than two motors:
    ```java
    MyTalonSRX frontLeft = new MyTalonSRX(1);
    MyTalonSRX frontRight = new MyTalonSRX(2);
    MyTalonSRX backLeft = new MyTalonSRX(3);
    MyTalonSRX backRight = new MyTalonSRX(4);
    SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeft, backLeft);
    SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRight, backRight);
    drive = new DifferentialDrive(leftGroup, rightGroup);
    ```
  * where ```MyTalonSRX``` is a simple wrapper around ```TalonSRX``` to tag the ```SpeedController```
    interface.  The wrapper should not be needed by the time the 2018 season rolls around.
    ```java
    package org.usfirst.frc.team3335.robot.subsystems;

    import com.ctre.phoenix.MotorControl.CAN.TalonSRX;

    import edu.wpi.first.wpilibj.SpeedController;

    public class MyTalonSRX extends TalonSRX implements SpeedController {

        public MyTalonSRX(int deviceNumber) {
            super(deviceNumber);
        }
    }
    ```
