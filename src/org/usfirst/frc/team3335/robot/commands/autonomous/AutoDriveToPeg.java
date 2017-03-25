package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoDriveToPeg extends Command {

	private final double feetPerSecond = 4.5; //Mark 1 at .7 voltage
	private double distance;
	private double speedForward;
	private long timeFinished = 0;
	private long driveTime;
	private final long TIME_MAX = 5000; // millisecs
	private final boolean limitSpeedByDistance = false;

    public AutoDriveToPeg(double distance) {
    	this(distance, 0.7);
    }

    /**
     * Move specified distance with specified speed.
     * @param distance distance to move, can be positive or negative
     * @param speed speed (percent voltage), should be between 0 and 1 inclusive
     */
    public AutoDriveToPeg(double distance, double speed) {
        requires(Robot.driveTrain);
        requires(Robot.ultrasonics);
        //requires(Robot.visionTest);
        requires(Robot.navx);

        this.speedForward = Math.abs(speed);
        this.distance = distance;
        driveTime = (long)(Math.abs(distance) / feetPerSecond / 12.0 * 1000);
        // TODO remove after testing
        driveTime *= 1.5;
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
    	double speed = Math.signum(distance) * speedForward;
    	if (limitSpeedByDistance) {
    		speed = limitSpeedByDistance(speed, distance, 12);
    	}
        Robot.driveTrain.drive(speed, speed);
    }

    private double limitSpeedByDistance(double speedIn, double maxDistance, double limitDistance) {
    	if (Math.abs(Robot.driveTrain.getDistance()) > Math.abs(maxDistance) - Math.abs(limitDistance)) {
    		return speedIn * 0.5;
    	}
    	return speedIn;
    }

    private double limitSpeedByDistanceUltrasonic(double speedIn, double limitDistance) {
    	if (Robot.ultrasonics.getDistance() < Math.abs(limitDistance)) {
    		return speedIn * 0.5;
    	}
    	return speedIn;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (Math.abs(Robot.driveTrain.getDistance()) > Math.abs(distance)) {
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
