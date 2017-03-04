package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FlapperControl extends Command{

	
	public FlapperControl() {
        requires(Robot.flapper);
    }

    @Override
    protected void initialize() {
        Robot.gate.switchPos();
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
