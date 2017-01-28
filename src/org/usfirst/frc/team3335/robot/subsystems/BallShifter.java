package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team3335.robot.commands.BallShiftHigh;

public class BallShifter extends Subsystem implements LoggableSubsystem{

	private DoubleSolenoid solenoid;

	public BallShifter() {
		solenoid = new DoubleSolenoid(4, 5);
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
		setDefaultCommand(new BallShiftHigh());
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

}
