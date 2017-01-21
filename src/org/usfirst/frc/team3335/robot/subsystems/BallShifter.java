package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallShifter extends Subsystem implements LoggableSubsystem{

	private DoubleSolenoid solenoid;

	public BallShifter() {
		solenoid = new DoubleSolenoid(1, 2);
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
		
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

}
