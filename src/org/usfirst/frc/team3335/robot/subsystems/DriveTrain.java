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

    //get the cantalons at http://www.ctr-electronics.com/downloads/installers/CTRE%20Toolsuite%20v4.4.1.9-nonadmin.zip for windows,
    //http://www.ctr-electronics.com//downloads/lib/CTRE_FRCLibs_NON-WINDOWS_v4.4.1.9.zip for other
    private CANTalon frontLeft, frontRight, backLeft, backRight;
    private RobotDrive drive;
    private final boolean useTankDrive = false;

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
    	if (useTankDrive) {
    		drive(-joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_LEFT_AXIS), -joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_RIGHT_AXIS));
    	} else {
    		driveA(-joystick.getRawAxis(5), -joystick.getRawAxis(4));
    	}
    }

    public void drive(double left, double right) {
        drive.tankDrive(left, right, true);
    }

    public void driveA(double move, double rotate) {
    	drive.arcadeDrive(move, rotate, true);
    }
    
    @Override
    public void log() {

    }
}
