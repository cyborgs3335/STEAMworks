package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Climb extends Command {
	boolean finished;
	double speed;
	public Climb(boolean isFinished, double speed) {
		requires(Robot.climber);
		finished=isFinished;
		this.speed = speed;
	}
	
	@Override
    protected void initialize() {
        Robot.climber.manualClimb(speed);
    }
	
	@Override
	protected void end() {
		Robot.climber.manualClimb(0);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

}
