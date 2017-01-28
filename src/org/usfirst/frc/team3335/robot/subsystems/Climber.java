package org.usfirst.frc.team3335.robot.subsystems;

import org.usfirst.frc.team3335.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	private CANTalon bagMotor;
	
	public Climber(Joystick joystick) {
		bagMotor = new CANTalon(RobotMap.CLIMBING_MOTOR);
		//while(true)if (joystick.getRawButton(4))manualClimb(joystick);
	}
	
	protected void climb(double rotations) {
		double firstNum = bagMotor.getAnalogInPosition();
		bagMotor.set(-1);
		while(bagMotor.getAnalogInPosition()<firstNum+(rotations*1024)){;}
		bagMotor.set(0);
	}
	
	public void manualClimb(Joystick joystick) {
		while(joystick.getRawButton(4)){
			bagMotor.set(-1);
			//joystick.setRumble(RumbleType.kRightRumble, 1.0);
			//joystick.setRumble(RumbleType.kLeftRumble, 1.0);
		}
		bagMotor.set(0);
		
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
