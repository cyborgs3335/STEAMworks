package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.RobotPreferences;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class AutoVisionSimple extends Command {

	private static Preferences prefs = Preferences.getInstance();

	private long timeFinished = 0;
	private final long timeMax = (long)(prefs.getDouble("Auto Vision Time Limit", RobotPreferences.VISION_TIME_DEFAULT) * 1000); // millisec

	private final long pidUpdateDelay = prefs.getLong("Vision Update Delay", RobotPreferences.VISION_UPDATE_DELAY_DEFAULT); // 500 milliseconds

	private final double forwardSpeed;

	private final double rotateRate;

	private double setPointAngle;

	private long pidTimeSinceUpdate = 0;

	private final double feetPerSecond = 4.5; //Mark 1 at .7 voltage
	private double distance;
	//private double speedForward;
	//private long driveTime;
	//private final long TIME_MAX = 5000; // millisecs

	/**
	 * Constructor using default of zero forward speed and rotational speed of 0.5.
	 */
	public AutoVisionSimple() {
		this(0, 0.5);
	}

	/**
	 * Constructor specifying forward speed and rotational speed.
	 * @param forwardSpeed forward speed, in percent voltage from 0 to 1
	 * @param rotateSpeed rotational speed, in percent voltage from 0 to 1
	 */
    public AutoVisionSimple(double forwardSpeed, double rotateSpeed) {
        requires(Robot.driveTrain);
        //requires(Robot.ultrasonics);
        requires(Robot.visionTest);
        requires(Robot.navx);

        this.forwardSpeed = Math.abs(forwardSpeed);
        this.rotateRate = Math.abs(rotateSpeed);

        //driveTime = (long)(Math.abs(distance) / feetPerSecond / 12.0 * 1000);
        //driveTime *= 1.5;
        //if (driveTime > TIME_MAX) driveTime = TIME_MAX;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.driveTrain.setBrake(true);
    	Robot.driveTrain.zeroEncoders();
    	Robot.navx.zeroYaw();
    	timeFinished = System.currentTimeMillis() + timeMax;
    	distance = 0;
    	setPointAngle = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	if (System.currentTimeMillis() - pidTimeSinceUpdate > pidUpdateDelay) {
    		distance = Robot.visionTest.getTargetDistance();
    		setPointAngle = Robot.visionTest.pidGet();
        	Robot.driveTrain.zeroEncoders();
        	Robot.navx.zeroYaw();
    		pidTimeSinceUpdate = System.currentTimeMillis();
    	}
    	double speedForward = Math.signum(distance) * forwardSpeed;
    	double speedRotate = Math.signum(setPointAngle) * rotateRate;
        //Robot.driveTrain.drive(speed, -speed);  //Mark 1
    	//Robot.driveTrain.driveArcade(0, speed, false); //Mark 2
    	Robot.driveTrain.driveArcade(speedForward, speedRotate, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Math.abs(Robot.driveTrain.getDistance()) > Math.abs(distance)) {
    		return true;
    	}
    	/*
    	if (setPointAngle < 0) {
    		if (Robot.navx.getYaw() <= setPointAngle) {
    			return true;
    		}
    	} else {
    		if (Robot.navx.getYaw() >= setPointAngle) {
    			return true;
    		}
    	}
    	if (System.currentTimeMillis() > timeFinished) {
    		return true;
    	}
    	*/
    	//if (!Robot.visionTest.isTargetDetected()) {
    	//	return true;
    	//}
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.drive(0, 0);
        Robot.driveTrain.setBrake(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }

}
