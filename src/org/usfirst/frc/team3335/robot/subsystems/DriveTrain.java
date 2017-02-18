package org.usfirst.frc.team3335.robot.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private final boolean useTankDrive = false;
    private final double deadzone = .3;
    private final double trainingSpeedMax = 1;

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

        setBrake(false);

        drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);

        leftEncoder = new Encoder(0, 1, false, EncodingType.k4X);
        leftEncoder.reset();
        rightEncoder = new Encoder(2, 3, false, EncodingType.k4X);
        rightEncoder.reset();
    }

    public void setBrake(boolean brake) {
    	frontLeft.enableBrakeMode(brake);
        frontRight.enableBrakeMode(brake);
        backLeft.enableBrakeMode(brake);
        backRight.enableBrakeMode(brake);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }

    public void drive(Joystick joystick) {
    	if (useTankDrive) {
    		int sign = -1;
    		if (-joystick.getRawAxis(1)>=0) sign = 1;
    		if (Math.abs(joystick.getRawAxis(1))>=trainingSpeedMax)drive(trainingSpeedMax*sign, -joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_RIGHT_AXIS));
    		else drive(-joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_LEFT_AXIS), -joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_RIGHT_AXIS));
    	} 
    	else {
    		int sign = -1;
    		if (-joystick.getRawAxis(1)>=0) sign = 1;
    		if(Math.abs(joystick.getRawAxis(0))<=deadzone) {
    			if (Math.abs(joystick.getRawAxis(1))>=trainingSpeedMax)driveA(trainingSpeedMax*sign, 0.0);
    			else driveA(-joystick.getRawAxis(1), 0.0);
    		}
    		else {
    			if (Math.abs(joystick.getRawAxis(1))>=trainingSpeedMax)driveA(trainingSpeedMax*sign, -joystick.getRawAxis(0));
    			else driveA(-joystick.getRawAxis(1), -joystick.getRawAxis(0));
    		}
    	}
    }

    public void drive(double left, double right) {
        drive.tankDrive(left, right, true);
    }
    
    public void driveA(double move, double rotate, boolean squared) {
    	drive.arcadeDrive(move, rotate, squared);
    }

    public void driveA(double move, double rotate) {
    	driveA(move, rotate, true);
    }
    
    @Override
    public void log() {
    	SmartDashboard.putNumber("DriveTrain: left distance", leftEncoder.getDistance());
    	SmartDashboard.putNumber("DriveTrain: left velocity", leftEncoder.getRate());
    	SmartDashboard.putNumber("DriveTrain: right distance", rightEncoder.getDistance());
    	SmartDashboard.putNumber("DriveTrain: right velocity", rightEncoder.getRate());
    }
}
