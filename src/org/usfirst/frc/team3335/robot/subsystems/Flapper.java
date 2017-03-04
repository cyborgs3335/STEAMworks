package org.usfirst.frc.team3335.robot.subsystems;

import org.usfirst.frc.team3335.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

@SuppressWarnings("unused")
public class Flapper extends Subsystem implements LoggableSubsystem {

	private DoubleSolenoid solenoid;
	
	public Flapper() {
		solenoid = new DoubleSolenoid(RobotMap.FLAPPER_FORWARD_CHANNEL, RobotMap.FLAPPER_REVERSE_CHANNEL);
		solenoid.set(Value.kForward);
	}
	
	public void switchPos() {
        switch (solenoid.get()) {
            case kOff: case kReverse:
                solenoid.set(Value.kForward);
                break;
            case kForward:
                solenoid.set(Value.kReverse);
                break;
        }
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
