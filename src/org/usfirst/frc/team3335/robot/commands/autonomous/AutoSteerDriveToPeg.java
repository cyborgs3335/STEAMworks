package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSteerDriveToPeg extends Command {

	private long timeFinished = 0;
	//private final long timeMax = 3000; // millisec

	////////////////////////////////////////////////////////////////////////
//	// distance in inches the robot wants to stay from an object
//	private static final double kHoldDistance = 12.0;
//
//	// maximum distance in inches we expect the robot to see
//	private static final double kMaxDistance = 24.0;
//
//	// factor to convert sensor values to a distance in inches
//	private static final double kValueToInches = 0.125;

	// proportional speed constant
	private static final double kP = 0.04;//0.03 too small?; 0.06 too big; 0.04 just right for mark 1

	// integral speed constant
	private static final double kI = 0; //0.018;

	// derivative speed constant
	private static final double kD = 0; //1.5;
	
	// tolerance in degrees
	private static final double kToleranceDegrees = 1.0;
	
	private double rotateRate;
	
	private double setpoint = 0; 
	private final double maxAbsSetpoint = 90;
	
	private final double feetPerSecond = 4.5; //Mark 1 at .7 voltage
	private double distance;
	private long driveTime;
	private final long TIME_MAX = 2000; // millisecs
	
	private double speed = 0;
	private final double maxAbsSpeed = 1;
	
	private PIDController turnController;
///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    private AutoSteerDriveToPeg() {
        requires(Robot.driveTrain);
        //requires(Robot.ultrasonics);
        //requires(Robot.visionTest);
        requires(Robot.navx);
        
        turnController = new PIDController(kP, kI, kD, Robot.navx.getAHRS(), new MyPidOutput());
        turnController.setInputRange(-maxAbsSetpoint, maxAbsSetpoint);
        turnController.setOutputRange(-.7, .7);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
        
        /* Add the PID Controller to the Test-mode dashboard, allowing manual  */
        /* tuning of the Turn Controller's P, I and D coefficients.            */
        /* Typically, only the P value needs to be modified.                   */
        LiveWindow.addSensor("DriveSystem", "RotateController", turnController);
    }
    
    public AutoSteerDriveToPeg(double setpointAngle, double speedSteer, double distance) {
    	this();
    	setpoint = Math.abs(setpointAngle) < maxAbsSetpoint ? setpointAngle : maxAbsSetpoint * Math.signum(setpointAngle);
    	speed = Math.abs(speedSteer) < maxAbsSpeed ? speedSteer : maxAbsSpeed * Math.signum(speedSteer);
    	this.distance = distance;
    	// TODO: 4.5 feet/sec is valid for speed = 0.7 (percent voltage); need to scale if "speed" changes
        driveTime = (long)(distance / feetPerSecond * 1000);
        if (driveTime > TIME_MAX) driveTime = TIME_MAX;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.driveTrain.setBrake(true);
    	Robot.navx.zeroYaw();
    	timeFinished = System.currentTimeMillis() + driveTime;
    	turnController.setSetpoint(setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	turnController.enable();
    	double rotate = -rotateRate;
        //Robot.driveTrain.drive(speed, -speed);
    	Robot.driveTrain.driveA(speed, rotate, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//if (turnController.onTarget())
    	//	return true;
    	if (System.currentTimeMillis() > timeFinished) {
    		Robot.driveTrain.setBrake(false);
    		return true;
    	}
    	//if (!Robot.visionTest.isTargetDetected()) {
    	//	return true;
    	//}
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
    
    private void test() {
		// Set expected range to 0-24 inches; e.g. at 24 inches from object go
		// full forward, at 0 inches from object go full backward.
		//pidController.setInputRange(0, kMaxDistance * kValueToInches);
		// Set setpoint of the pidController
		//pidController.setSetpoint(kHoldDistance * kValueToInches);
		turnController.enable(); // begin PID control
	}
    
    private class MyPidOutput implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			//myRobot.drive(output, 0);
			//Robot.driveTrain.drive(output, 0);
			rotateRate = output;
		}
	}

}
