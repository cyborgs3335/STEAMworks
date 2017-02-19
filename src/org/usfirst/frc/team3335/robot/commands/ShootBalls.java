package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShootBalls extends Command {

	private final boolean finished;
	private final double speed;

	public ShootBalls(boolean isFinished, double speed) {
		requires(Robot.ballShooter);
		finished=isFinished;
		this.speed = speed;
	}
	
	@Override
    protected void initialize() {
        Robot.ballShooter.set(speed);
    }
	
	@Override
	protected void end() {
		Robot.ballShooter.set(0);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

}
