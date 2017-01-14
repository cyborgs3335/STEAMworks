package org.usfirst.frc.team3335.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.RobotMap;
import org.usfirst.frc.team3335.robot.RobotPreferences;
import org.usfirst.frc.team3335.robot.commands.TankDrive;

/**
 * Created by jacob on 1/14/17.
 * A subsystem for a west coast drive train.
 */
public class DriveTrain extends Subsystem implements LoggableSubsystem{

    private CANTalon frontLeft, frontRight, backLeft, backRight;
    private RobotDrive drive;

    public DriveTrain() {
        super();
        frontLeft = new CANTalon(RobotMap.DRIVE_TRAIN_FRONT_LEFT);
        frontRight = new CANTalon(RobotMap.DRIVE_TRAIN_FRONT_RIGHT);
        backLeft = new CANTalon(RobotMap.DRIVE_TRAIN_BACK_LEFT);
        backRight = new CANTalon(RobotMap.DRIVE_TRAIN_BACK_RIGHT);

        frontLeft.set(0);
        frontRight.set(0);
        backLeft.set(0);
        backRight.set(0);

        drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }

    public void drive(Joystick joystick) {
        drive(joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_LEFT_AXIS), joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_RIGHT_AXIS));
    }

    public void drive(double left, double right) {
        drive.tankDrive(left, right, true);
    }

    @Override
    public void log() {

    }
}
