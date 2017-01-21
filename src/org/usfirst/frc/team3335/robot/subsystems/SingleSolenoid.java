package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by jacob on 1/21/17.
 */
public class SingleSolenoid extends Subsystem implements LoggableSubsystem {

	private Solenoid solenoid;
	
	public SingleSolenoid() {
		solenoid = new Solenoid(0);
	}
    @Override
    protected void initDefaultCommand() {
    	
    }

    @Override
    public void log() {

    }
}
