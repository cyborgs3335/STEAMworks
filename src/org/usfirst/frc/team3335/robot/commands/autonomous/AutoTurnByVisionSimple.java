package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.RobotPreferences;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoTurnByVisionSimple extends Command {

	private static Preferences prefs = Preferences.getInstance();

	private long timeFinished = 0;
	private final long timeMax = (long)(prefs.getDouble("Auto Vision Time Limit", RobotPreferences.VISION_TIME_DEFAULT) * 1000); // millisec

	private final long pidUpdateDelay = prefs.getLong("Vision Update Delay", RobotPreferences.VISION_UPDATE_DELAY_DEFAULT); // 500 milliseconds

	private final double forwardSpeed;

	private final double rotateRate;

	private double setPointAngle;

	private long pidTimeSinceUpdate = 0;

	/**
	 * Constructor using default of zero forward speed and rotational speed of 0.5.
	 */
	public AutoTurnByVisionSimple() {
		//this(0, 0.3);
		this(Preferences.getInstance().getDouble("Auto Turn Vision Simple Forward Speed", RobotPreferences.AUTO_TURN_VISION_SIMPLE_FORWARD_SPEED_DEFAULT),
				prefs.getDouble("Auto Turn Vision Simple Rotate Speed", RobotPreferences.AUTO_TURN_VISION_SIMPLE_ROTATE_SPEED_DEFAULT));
	}

	/**
	 * Constructor specifying forward speed and rotational speed.
	 * @param forwardSpeed forward speed, in percent voltage from 0 to 1
	 * @param rotateSpeed rotational speed, in percent voltage from 0 to 1
	 */
    public AutoTurnByVisionSimple(double forwardSpeed, double rotateSpeed) {
        requires(Robot.driveTrain);
        //requires(Robot.ultrasonics);
        requires(Robot.visionTest);
        requires(Robot.navx);

        //this.forwardSpeed = -forwardSpeed;
        this.forwardSpeed = forwardSpeed;
        this.rotateRate = Math.abs(rotateSpeed);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.driveTrain.setBrake(true);
    	Robot.navx.zeroYaw();
    	timeFinished = System.currentTimeMillis() + timeMax;
    	setPointAngle = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	if (System.currentTimeMillis() - pidTimeSinceUpdate > pidUpdateDelay) {
    		setPointAngle = Robot.visionTest.pidGet();
        	Robot.navx.zeroYaw();
    		pidTimeSinceUpdate = System.currentTimeMillis();
    	}
    	double speed = Math.signum(setPointAngle) * rotateRate;
        //Robot.driveTrain.drive(speed, -speed);  //Mark 1
    	//Robot.driveTrain.driveArcade(0, speed, false); //Mark 2
    	Robot.driveTrain.driveArcade(forwardSpeed, speed, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
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
