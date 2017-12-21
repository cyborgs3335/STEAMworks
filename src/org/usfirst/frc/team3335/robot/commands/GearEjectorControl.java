package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class GearEjectorControl extends Command{
	private DoubleSolenoid.Value val;

	/**
	 * Constructor.
	 * @param f true=gate up, false=gate down
	 */
	public GearEjectorControl(boolean f) {
        requires(Robot.gearEjector);
        val = f ? Value.kReverse : Value.kForward;
    }

    @Override
    protected void initialize() {
        Robot.gearEjector.switchPos(val);
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
