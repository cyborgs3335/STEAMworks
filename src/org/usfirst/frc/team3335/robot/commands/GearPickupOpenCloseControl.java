package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.subsystems.GenericDoubleSolenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class GearPickupOpenCloseControl extends Command{
	private DoubleSolenoid.Value val;
	private final GenericDoubleSolenoid doubleSolenoid = Robot.gearPickupOpenClose;

	/**
	 * Constructor.
	 * @param b true=gear pickup open, false=gear pickup close
	 */
	public GearPickupOpenCloseControl(boolean b) {
		if (doubleSolenoid == null) {
			return;
		}
        requires(doubleSolenoid);
        val = b ? Value.kReverse : Value.kForward;
    }

    @Override
    protected void initialize() {
    	if (doubleSolenoid == null) {
    		return;
    	}
    	doubleSolenoid.switchPos(val);
    }

	@Override
	protected boolean isFinished() {
		return true;
	}

}
