package org.usfirst.frc.team3335.robot.subsystems;

import org.usfirst.frc.team3335.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

@SuppressWarnings("unused")
public class GenericDoubleSolenoid extends Subsystem implements LoggableSubsystem {

	private final DoubleSolenoid solenoid;

	public GenericDoubleSolenoid(int forwardChannel, int reverseChannel) {
		this(forwardChannel, reverseChannel, DoubleSolenoid.Value.kReverse);
	}

	public GenericDoubleSolenoid(int forwardChannel, int reverseChannel, DoubleSolenoid.Value value) {
		this(DoubleSolenoid.getDefaultSolenoidModule(), forwardChannel, reverseChannel, value);
	}

	public GenericDoubleSolenoid(int moduleNumber, int forwardChannel, int reverseChannel) {
		this(moduleNumber, forwardChannel, reverseChannel, DoubleSolenoid.Value.kReverse);
	}

	public GenericDoubleSolenoid(int moduleNumber, int forwardChannel, int reverseChannel, DoubleSolenoid.Value value) {
		solenoid = new DoubleSolenoid(moduleNumber, forwardChannel, reverseChannel);
		solenoid.set(value);
	}

	public void switchPos(DoubleSolenoid.Value value) {
		solenoid.set(value);
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
