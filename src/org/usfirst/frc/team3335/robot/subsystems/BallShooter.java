package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3335.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallShooter extends Subsystem implements LoggableSubsystem {

	private WPI_TalonSRX motor;
	
	private DoubleSolenoid solenoid;
	
	public BallShooter() {
		motor = new WPI_TalonSRX(RobotMap.BALL_SHOOTER_MOTOR);
		motor.set(0);
		//motor.setControlMode();
	}
	public void switchPos(DoubleSolenoid.Value value){
		solenoid.set(value);
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
