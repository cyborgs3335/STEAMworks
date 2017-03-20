package org.usfirst.frc.team3335.robot.commands;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3335.robot.subsystems.BallShifter;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
//import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

public class BallShiftHigh extends Command {

	BallShifter ballShifter;

	public BallShiftHigh() {
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
		ballShifter.setSolenoidValue(kForward);
		

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
