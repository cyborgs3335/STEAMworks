package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BallShift extends Command {

	public BallShift() {
		requires(Robot.ballShifter);
	}
	
	//Switches to opposite gear 
	protected void switchGear() {
		
	}
	
	//Switches into low gear
	protected void switchLow() {
		
	}
	
	//Switches into high gear
	protected void switchHigh() {
		//if()
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
