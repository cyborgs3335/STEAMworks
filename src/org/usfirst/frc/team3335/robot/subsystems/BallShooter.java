package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3335.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallShooter extends Subsystem implements LoggableSubsystem {

	private CANTalon motor;
	
	private DoubleSolenoid solenoid;
	
	public BallShooter() {
		motor = new CANTalon(RobotMap.BALL_SHOOTER_MOTOR);
		//motor.setControlMode();
	}
	public void switchPos(){
		switch(solenoid.get()){
			case kOff: case kReverse: 
				solenoid.set(Value.kForward);
				break;
			case kForward: 
				solenoid.set(Value.kReverse);
				break;
		}
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
