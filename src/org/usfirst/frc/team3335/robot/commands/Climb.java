package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command {
	boolean finished;
	double speed;
	public Climb(boolean isFinished, double speed) {
		requires(Robot.climber);
		requires(Robot.compressor);
		finished=isFinished;
		this.speed = speed;
	}
	
	@Override
    protected void initialize() {
        Robot.climber.manualClimb(speed);
        Robot.compressor.setClosedLoop(false);
    }
	
	@Override
	protected void end() {
		Robot.climber.manualClimb(0);
		Robot.compressor.setClosedLoop(true);
	}
	
	@Override
	protected boolean isFinished() {
		if (!finished && Robot.climber.isCurrentExceeded()) {
			return true;
		}
		return finished;
	}

}
