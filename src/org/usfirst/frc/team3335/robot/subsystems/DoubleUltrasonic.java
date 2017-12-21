package org.usfirst.frc.team3335.robot.subsystems;

import org.usfirst.frc.team3335.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DoubleUltrasonic extends Subsystem implements LoggableSubsystem {

	// distance in inches the robot wants to stay from an object
	//private static final double kHoldDistance = 12.0;

	// factor to convert sensor values to a distance in inches
	//private static final double kValueToInches = 0.125;

	// factor to convert voltage values to a distance in inches
	// kVoltageToInches = (value V) * (1000mV/V) * (1mm/0.977mV) * (1cm/10mm) * (1in/2.54cm)
	private static final double kVoltageToInches = 40.2969;

	private AnalogInput ultrasonicLeft = new AnalogInput(RobotMap.ANALOG_ULTRASONIC_LEFT);
	private AnalogInput ultrasonicRight = new AnalogInput(RobotMap.ANALOG_ULTRASONIC_RIGHT);

	public double getDistanceLeft() {
		return getDistance(ultrasonicLeft);
	}

	public double getDistanceRight() {
		return getDistance(ultrasonicRight);
	}

    public double getDistance() {
    	return 0.5 * (getDistanceLeft() + getDistanceRight());
    }

	private double getDistance(AnalogInput ultrasonic) {
		//return ultrasonic.getValue() * kValueToInches;
		return ultrasonic.getAverageVoltage()*kVoltageToInches;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Ultrasonic: Left Avg Volt", ultrasonicLeft.getAverageVoltage());
		SmartDashboard.putNumber("Ultrasonic: Right Avg Volt", ultrasonicRight.getAverageVoltage());
		SmartDashboard.putNumber("Ultrasonic: Left Distance", getDistanceLeft());
		SmartDashboard.putNumber("Ultrasonic: Right Distance", getDistanceRight());
	}

}
