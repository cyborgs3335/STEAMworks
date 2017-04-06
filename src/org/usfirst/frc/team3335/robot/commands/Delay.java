package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command {
	
	private long timeFinished = 0;
	private final long timeDelay;
	
	public Delay(long delayTimeMillis) {
		timeDelay = delayTimeMillis;
	}
	
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	timeFinished = System.currentTimeMillis() + timeDelay;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (System.currentTimeMillis() > timeFinished) {
    		//Robot.driveTrain.setBrake(false);
    		return true;
    	}
    	//if (!Robot.visionTest.isTargetDetected()) {
    	//	return true;
    	//}
        return false; // Runs until interrupted
    }

}
