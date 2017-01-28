package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by jacob on 1/21/17.
 */
@SuppressWarnings("unused")
public class Dumper extends Subsystem implements LoggableSubsystem {

	private Value state;
    private DoubleSolenoid solenoid;
    //private Solenoid solenoid;

    public Dumper() {
        //solenoid = new Solenoid(3);
        solenoid = new DoubleSolenoid(2, 3); 
        state = Value.kForward;
        solenoid.set(state);
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

    }

    @Override
    public void log() {

    }
}
