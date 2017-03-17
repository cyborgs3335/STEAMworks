package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveToPeg extends Command {

	private final double feetPerSecond = 4.5; //Mark 1 at .7 voltage
	private double distance;
	private long timeFinished = 0;
	private long driveTime;
	private final long TIME_MAX = 2000; // millisecs
	
    public AutoDriveToPeg(double distance) {
        requires(Robot.driveTrain);
        requires(Robot.ultrasonics);
        //requires(Robot.visionTest);
        requires(Robot.navx);
        
        this.distance = distance;
        driveTime = (long)(distance / feetPerSecond / 12.0 * 1000);
        // TODO remove after testing
        // driveTime *= 1.5;
        // end TODO
        if (driveTime > TIME_MAX) driveTime = TIME_MAX;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.driveTrain.setBrake(true);
    	Robot.driveTrain.zeroEncoders();
    	Robot.navx.zeroYaw();
    	timeFinished = System.currentTimeMillis() + TIME_MAX;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double speed = Math.signum(distance) * 0.7;
        Robot.driveTrain.drive(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Robot.driveTrain.getDistance() > distance) {
    		return true;
    	}
    	if (System.currentTimeMillis() > timeFinished) {
    		//Robot.driveTrain.setBrake(false);
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
