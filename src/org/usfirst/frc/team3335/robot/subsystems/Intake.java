package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3335.robot.RobotMap;

import com.ctre.phoenix.MotorControl.CAN.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem implements LoggableSubsystem {

	private TalonSRX motor;
	
	private double motorValue = 1.0; //0.5;
	
	public Intake() {
		motor = new TalonSRX(RobotMap.INTAKE_MOTOR);
		motor.set(0);
		//motor.setControlMode();
	}
	public void turnOn(){
		set(motorValue);
	}
	
	public void turnOff(){
		set(0);
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
		SmartDashboard.putNumber("Intake Value",motor.get());
	}
}
