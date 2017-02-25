package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team3335.robot.RobotMap;
import org.usfirst.frc.team3335.robot.commands.BallShiftHigh;

public class BallShifter extends Subsystem implements LoggableSubsystem{

	private DoubleSolenoid solenoid;

	public BallShifter() {
		solenoid = new DoubleSolenoid(RobotMap.BALL_SHIFTER_FORWARD_CHANNEL, RobotMap.BALL_SHIFTER_REVERSE_CHANNEL);
		setSolenoidValue(DoubleSolenoid.Value.kOff);
	}

	public void setSolenoidValue(DoubleSolenoid.Value value) {
		solenoid.set(value);
	}

	public DoubleSolenoid.Value getSolenoidValue() {
		return solenoid.get();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//setDefaultCommand(new BallShiftHigh());
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

}
