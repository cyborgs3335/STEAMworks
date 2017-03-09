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
    private double deadzone = .1;
    private final double trainingSpeedMax = 1;
	private int direction = 1; // Mark2 = 1; Mark3 = -1?
    //private final double joystickScalar = 1/(1-deadzone);

    /*
     * Encoder Ratios
     * 
     * Vex Planetary #217-2615
     * 
     * 36:12 ENCODER:SHAFT (encoder spins 3X faster)
     * 
     * 2.27x spread between high and low gear [60:24 / 44:40 = 2.5/1.1 = 2.2727]
     * 
     * 1st stage:             40:12 [3.3333x]
     * 2nd stage (low gear):  60:24 [2.5000x]
     * 2nd stage (high gear): 44:40 [1.1000x]
     * 3rd stage:             50:34 [1.4706x]
     * 
     * Motor to shaft (low gear): 12.255
     *               (high gear):  5.392
     */
    
    
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
        
        double voltageRampRate = 150;//20;
        frontLeft.setVoltageRampRate(voltageRampRate);
        frontRight.setVoltageRampRate(voltageRampRate);
        backLeft.setVoltageRampRate(voltageRampRate);
        backRight.setVoltageRampRate(voltageRampRate);
        
        //backRight.setCurrentLimit(0);

        setBrake(false);

        drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);

        // Scale encoder pulses to distance in inches
        double wheelDiameter = 4.0; // inches
        double encoderToShaftRatio = 3; // 3X gear reduction
        double pulsesPerRevolution = 256;
        double stage3Ratio = 50.0 / 34.0;
        double distancePerPulse = Math.PI * wheelDiameter / (encoderToShaftRatio * pulsesPerRevolution);
        distancePerPulse /= stage3Ratio;

        leftEncoder = new Encoder(RobotMap.DRIVE_TRAIN_ENCODER_LEFT_A, RobotMap.DRIVE_TRAIN_ENCODER_LEFT_B,
        		true, EncodingType.k4X);
        leftEncoder.reset();
        leftEncoder.setDistancePerPulse(distancePerPulse);
        rightEncoder = new Encoder(RobotMap.DRIVE_TRAIN_ENCODER_RIGHT_A, RobotMap.DRIVE_TRAIN_ENCODER_RIGHT_B,
        		true, EncodingType.k4X);
        rightEncoder.reset();
        rightEncoder.setDistancePerPulse(distancePerPulse);
    }

    public void setBrake(boolean brake) {
    	frontLeft.enableBrakeMode(brake);
        frontRight.enableBrakeMode(brake);
        backLeft.enableBrakeMode(brake);
        backRight.enableBrakeMode(brake);
    }

    public void zeroEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }

    public void drive(Joystick joystick) {
    	//driveOrig(joystick);
    	driveNew(joystick);
    }

    public void driveOrig(Joystick joystick) {
    	if (useTankDrive) {
    		int sign = -1;
    		if (-joystick.getRawAxis(1)>=0) sign = 1;
    		if (Math.abs(joystick.getRawAxis(1))>=trainingSpeedMax)
    			drive(trainingSpeedMax*sign, map(-joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_RIGHT_AXIS)));
    		else
    			drive(map(-joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_LEFT_AXIS)), map(-joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_RIGHT_AXIS)));
    	} 
    	else {
    		int sign = -1;
    		if (-joystick.getRawAxis(1)>=0) sign = 1;
    		if(Math.abs(joystick.getRawAxis(0))<=deadzone) {
    			if (Math.abs(joystick.getRawAxis(1))>=trainingSpeedMax)
    				driveArcade(trainingSpeedMax*sign, 0.0);
    			else
    				driveArcade(map(-joystick.getRawAxis(1)), 0.0);
    		}
    		else {
    			if (Math.abs(joystick.getRawAxis(1))>=trainingSpeedMax)
    				driveArcade(trainingSpeedMax*sign, map(-joystick.getRawAxis(0)));
    			else
    				driveArcade(map(-joystick.getRawAxis(1)), map(-joystick.getRawAxis(0)));
    		}
    	}
    }
    
    public double map(double input ) {
    	if (Math.abs(input) < deadzone)
    		return 0;
    	if(input>0) {
    		return (input - deadzone)/(1 - deadzone);
    	}
    	else if(input<0) {
    		return (input + deadzone)/(1 - deadzone);
    	}
    	else 
    		return 0;
    }

	/**
	 *
	 * @param direction : 1 for forward, -1 for backward
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}
    
    public void driveNew(Joystick joystick) {
    	if (useTankDrive) {
    		int sign = direction;
    		if (joystick.getRawAxis(1) <= 0) sign = -direction;
    		if (Math.abs(joystick.getRawAxis(1))>trainingSpeedMax)
    			drive(trainingSpeedMax*sign, map(-joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_RIGHT_AXIS)));
    		else
    			drive(map(direction *joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_LEFT_AXIS)),
    					map(direction *joystick.getRawAxis(RobotPreferences.DRIVE_TRAIN_RIGHT_AXIS)));
    	} 
    	else {
    		int sign = direction;
    		if (joystick.getRawAxis(1) <= 0) sign = -direction;
    		if (Math.abs(joystick.getRawAxis(1))>trainingSpeedMax)
    			driveArcade(sign*trainingSpeedMax, map(direction *joystick.getRawAxis(0)));
    		else
    			driveArcade(map(direction *joystick.getRawAxis(1)), map(direction *joystick.getRawAxis(0)));
    	}
    }

    public void drive(double left, double right) {
        drive.tankDrive(left, right, true);
    }
    
    public void driveArcade(double move, double rotate, boolean squared) {
    	drive.arcadeDrive(move, rotate, squared);
    }

    public void driveArcade(double move, double rotate) {
    	driveArcade(move, rotate, true);
    }
    
    @Override
    public void log() {
    	SmartDashboard.putNumber("DriveTrain: left distance", leftEncoder.getDistance());
    	SmartDashboard.putNumber("DriveTrain: left velocity", leftEncoder.getRate());
    	SmartDashboard.putNumber("DriveTrain: right distance", rightEncoder.getDistance());
    	SmartDashboard.putNumber("DriveTrain: right velocity", rightEncoder.getRate());
    	SmartDashboard.putNumber("DriveTrain: front right current", frontRight.getOutputCurrent());
    	SmartDashboard.putNumber("DriveTrain: front right current pdp", Robot.pdp.getCurrent(12));
    	SmartDashboard.putNumber("DriveTrain: front left  current", frontLeft.getOutputCurrent());
    	SmartDashboard.putNumber("DriveTrain: front left  current pdp", Robot.pdp.getCurrent(10));
    	SmartDashboard.putNumber("DriveTrain: back  right current", backRight.getOutputCurrent());
    	SmartDashboard.putNumber("DriveTrain: back  right current pdp", Robot.pdp.getCurrent(13));
    	SmartDashboard.putNumber("DriveTrain: back  left  current", backLeft.getOutputCurrent());
    	SmartDashboard.putNumber("DriveTrain: back  left  current pdp", Robot.pdp.getCurrent(11));
    }
}
