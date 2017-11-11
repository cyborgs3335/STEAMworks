package org.usfirst.frc.team3335.robot.subsystems;

import org.usfirst.frc.team3335.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DoubleUltrasonicPID extends Subsystem implements LoggableSubsystem {

	// distance in inches the robot wants to stay from an object
	private static final double kHoldDistance = 12.0;

	// maximun distance in inches we expect the robot to see
	private static final double kMaxDistance = 24.0;

	// factor to convert sensor values to a distance in inches
	private static final double kValueToInches = 0.125;

	// proportional speed constant
	private static final double kP = 7.0;

	// integral speed constant
	private static final double kI = 0.018;

	// derivative speed constant
	private static final double kD = 1.5;

	private static final int kLeftMotorPort = 0;
	private static final int kRightMotorPort = 1;
	private static final int kUltrasonicPort = 0;

	private AnalogInput ultrasonicLeft = new AnalogInput(RobotMap.ANALOG_ULTRASONIC_LEFT);
	private AnalogInput ultrasonicRight = new AnalogInput(RobotMap.ANALOG_ULTRASONIC_RIGHT);


	private AnalogInput ultrasonic = new AnalogInput(kUltrasonicPort);
	private DifferentialDrive myRobot = new DifferentialDrive(new Talon(kLeftMotorPort), new Talon(kRightMotorPort));
	private PIDController pidController = new PIDController(kP, kI, kD, ultrasonic, new MyPidOutput());


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

	private void test() {
		// Set expected range to 0-24 inches; e.g. at 24 inches from object go
		// full forward, at 0 inches from object go full backward.
		pidController.setInputRange(0, kMaxDistance * kValueToInches);
		// Set setpoint of the pidController
		pidController.setSetpoint(kHoldDistance * kValueToInches);
		pidController.enable(); // begin PID control
	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Ultrasonic: Left", getDistanceLeft());
		SmartDashboard.putNumber("Ultrasonic: Right", getDistanceRight());
	}

	private class MyPidOutput implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			myRobot.arcadeDrive(output, 0);
		}
	}
}


