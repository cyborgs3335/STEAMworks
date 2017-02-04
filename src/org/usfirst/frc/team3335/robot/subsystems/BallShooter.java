package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3335.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallShooter extends Subsystem implements LoggableSubsystem {

	private CANTalon motor;
	
	public BallShooter() {
		motor = new CANTalon(RobotMap.BALL_SHOOTER_MOTOR);
		//motor.setControlMode();
	}

	public void set(double value) {
		motor.set(value);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Ball Shooter Value",motor.get());
	}
}
