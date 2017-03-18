package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.RobotPreferences;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

public class AutoTurnToPegSimple extends Command {

	private static Preferences prefs = Preferences.getInstance();

	private long timeFinished = 0;
	private final long timeMax = (long)(prefs.getDouble("Auto Vision Time Limit", RobotPreferences.VISION_TIME_DEFAULT) * 1000); // millisec
	
	// Setpoint angle
	private final double setPointAngle;

	private final double rotateRate;

	/**
	 * Constructor using default setpoint angle, specified by robot preferences.
	 */
    public AutoTurnToPegSimple() {
    	 this(prefs.getDouble("Turn To Peg Angle", RobotPreferences.TURN_TO_PEG_ANGLE_DEFAULT));
    }

    /**
     * Constructor specifying setpoint angle.
     * @param setPointAngle setpoint angle in degrees
     */
    public AutoTurnToPegSimple(double setPointAngle) {
    	this(setPointAngle, 0.5);
    }

    /**
     * Constructor specifying setpoint angle and rotation speed.
     * @param setPointAngle setpoint angle in degrees
     * @param speed rotation speed, in percent voltage from 0 to 1
     */
    public AutoTurnToPegSimple(double setPointAngle, double speed) {
        requires(Robot.driveTrain);
        //requires(Robot.ultrasonics);
        //requires(Robot.visionTest);
        requires(Robot.navx);

        this.setPointAngle = setPointAngle;
        this.rotateRate = Math.abs(speed);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.driveTrain.setBrake(true);
    	Robot.navx.zeroYaw();
    	timeFinished = System.currentTimeMillis() + timeMax;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed = Math.signum(setPointAngle) * rotateRate;
        //Robot.driveTrain.drive(speed, -speed);  //Mark 1
    	Robot.driveTrain.driveArcade(0, speed, false); //Mark 2
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
