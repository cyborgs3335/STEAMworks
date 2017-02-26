package org.usfirst.frc.team3335.robot.commands;

import org.usfirst.frc.team3335.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class ClimbWithJoystick extends Command {
	boolean finished;
	double speed;
	private Joystick joystick;
	private int joystickButton;

	public ClimbWithJoystick(Joystick joystick, int joystickButton) {
		requires(Robot.climber);
		this.joystick = joystick;
		this.joystickButton = joystickButton;
	}
	
	@Override
    protected void initialize() {
        //Robot.climber.manualClimb(speed);
    }

	@Override
	protected void execute() {
		double value = joystick.getRawAxis(joystickButton);
		Robot.climber.manualClimb(value);
		if (value <= 0) {
			finished = true;
		}
	}

	@Override
	protected void end() {
		Robot.climber.manualClimb(0);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return finished;
	}

}
