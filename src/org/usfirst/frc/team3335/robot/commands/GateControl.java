package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GateControl extends Command{

	
	public GateControl() {
        requires(Robot.gate);
    }

    @Override
    protected void initialize() {
        Robot.dumper.switchPos();
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
