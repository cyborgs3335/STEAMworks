package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by jacob on 1/21/17.
 */
public class Dumper extends Subsystem implements LoggableSubsystem {

	private Value state;
    private DoubleSolenoid solenoid;
    //private DoubleSolenoid solenoid;

    public Dumper() {
        //solenoid = new Solenoid(3);
        solenoid = new DoubleSolenoid(2, 3); 
        state = Value.kForward;
        solenoid.set(state);
        
    }
    
    public void switchPos() {
    	if (state == Value.kForward){update(Value.kReverse);}
    	else {update(Value.kForward);}
    }
    
    public void update(Value value) {
    	solenoid.set(value);
    }

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public void log() {

    }
}
