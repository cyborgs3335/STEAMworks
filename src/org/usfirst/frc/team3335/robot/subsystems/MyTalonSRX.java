package org.usfirst.frc.team3335.robot.subsystems;

import com.ctre.phoenix.MotorControl.CAN.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;

public class MyTalonSRX extends TalonSRX implements SpeedController {

	public MyTalonSRX(int deviceNumber) {
		super(deviceNumber);
	}
}
