package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class GateControl extends Command{
	private DoubleSolenoid.Value val;

	/**
	 * Constructor.
	 * @param f true=gate up, false=gate down
	 */
	public GateControl(boolean f) {
        requires(Robot.gate);
        val = f ? Value.kReverse : Value.kForward;
    }

    @Override
    protected void initialize() {
        Robot.gate.switchPos(val);
    }

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
