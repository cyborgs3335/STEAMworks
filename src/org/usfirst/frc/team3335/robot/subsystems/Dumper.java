package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by jacob on 1/21/17.
 */
public class Dumper extends Subsystem implements LoggableSubsystem {

    private Solenoid solenoid;
    //private DoubleSolenoid solenoid;

    public Dumper() {
        solenoid = new Solenoid(3);
        //solenoid = new DoubleSolenoid(3, 4);
    }

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public void log() {

    }
}
