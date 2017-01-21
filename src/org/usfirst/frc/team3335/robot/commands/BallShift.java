package org.usfirst.frc.team3335.robot.commands;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3335.robot.subsystems.BallShifter;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
//import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;

public class BallShift extends Command {

	BallShifter ballShifter;

	public BallShift() {
		requires(Robot.ballShifter);
		ballShifter = Robot.ballShifter;
	}

	@Override
	protected void end() {
		switchGear();
	}

	/**
	 * Switches solenoid to opposite value.
	 * If solenoid is off, then it will set it to forward
	 */
	protected void switchGear() {
		switch (ballShifter.getSolenoidValue()) {
			case kOff: case kReverse:
				ballShifter.setSolenoidValue(kForward);
				break;
			case kForward:
				ballShifter.setSolenoidValue(kReverse);
				break;
		}

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
