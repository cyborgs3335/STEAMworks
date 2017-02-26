package org.usfirst.frc.team3335.robot.subsystems;

import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.RobotMap;
import org.usfirst.frc.team3335.robot.commands.ClimbWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem implements LoggableSubsystem {

	private CANTalon bagMotor;
	
	public Climber() {
		bagMotor = new CANTalon(RobotMap.CLIMBING_MOTOR);
		bagMotor.enableBrakeMode(false);
		//while(true)if (joystick.getRawButton(4))manualClimb(joystick);
	}
	
	protected void climb(double rotations) {
		double firstNum = bagMotor.getAnalogInPosition();
		bagMotor.set(-1);
		while(bagMotor.getAnalogInPosition()<firstNum+(rotations*1024)){;}
		bagMotor.set(0);
	}
	
	public void manualClimb(double speed) {
		bagMotor.set(speed);
		
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//new ClimbWithJoystick(Robot.oi.getJoystick2(), 3); // Right Trigger on PS3
	}

	@Override
	public void log() {
		// TODO Auto-generated method stub
		
	}

}
