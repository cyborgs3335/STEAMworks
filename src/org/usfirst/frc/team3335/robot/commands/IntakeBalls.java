package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeBalls extends Command {

	private final boolean finished;
	private final double speed;

	public IntakeBalls(boolean isFinished, double speed) {
		requires(Robot.intake);
		finished=isFinished;
		this.speed = speed;
	}
	
	@Override
    protected void initialize() {
        Robot.intake.set(speed);
    }
	
	@Override
	protected void end() {
		Robot.intake.set(0);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

}
