package org.usfirst.frc.team3335.robot.commands;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.subsystems.BallShifter;

import edu.wpi.first.wpilibj.command.Command;

public class BallShiftLow extends Command {

	BallShifter ballShifter;

	public BallShiftLow() {
		requires(Robot.ballShifter);
		ballShifter = Robot.ballShifter;
	}

	@Override
	protected void initialize() {
		switchGear();
	}

	/**
	 * Switches solenoid to opposite value.
	 * If solenoid is off, then it will set it to forward
	 */
	protected void switchGear() {
		ballShifter.setSolenoidValue(kReverse);
		

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
