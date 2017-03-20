package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.subsystems.BallShooter;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterShiftLow extends Command {
	BallShooter ballShooter;
	
	public ShooterShiftLow(){
		requires(Robot.ballShooter);
		ballShooter = Robot.ballShooter;
	}
	
	
	@Override
	protected void initialize(){
		ballShooter.switchPos(Value.kReverse);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
