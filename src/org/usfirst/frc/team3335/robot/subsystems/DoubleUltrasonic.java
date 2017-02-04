package org.usfirst.frc.team3335.robot.subsystems;

import org.usfirst.frc.team3335.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DoubleUltrasonic extends Subsystem implements LoggableSubsystem {

	// distance in inches the robot wants to stay from an object
	//private static final double kHoldDistance = 12.0;

	// factor to convert sensor values to a distance in inches
	private static final double kValueToInches = 0.125;

	private AnalogInput ultrasonicLeft = new AnalogInput(RobotMap.ANALOG_ULTRASONIC_LEFT);
	private AnalogInput ultrasonicRight = new AnalogInput(RobotMap.ANALOG_ULTRASONIC_RIGHT);

	public double getDistanceLeft() {
		return getDistance(ultrasonicLeft);
	}

	public double getDistanceRight() {
		return getDistance(ultrasonicRight);
	}

	private double getDistance(AnalogInput ultrasonic) {
		return ultrasonic.getValue() * kValueToInches;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Ultrasonic: Left", getDistanceLeft());
		SmartDashboard.putNumber("Ultrasonic: Right", getDistanceRight());
	}

}
