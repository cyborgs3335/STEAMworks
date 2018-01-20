package org.usfirst.frc.team3335.robot.subsystems;

import org.usfirst.frc.team3335.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem implements LoggableSubsystem {

	private WPI_TalonSRX bagMotor;
	private WPI_TalonSRX bagMotor2;

	private final double currentLimit = 100.0; // amps; 100 may be too much
	//private final double currentLimit = 45.0; // amps; 45 may be too much
	//private final double currentLimit = 25.0; // amps, for testing only
	private final double timeOut = 1000; // milliseconds
	private double timeCurrentExceeded = 0;

	public Climber() {
		bagMotor = new WPI_TalonSRX(RobotMap.CLIMBING_MOTOR);
		bagMotor.setNeutralMode(NeutralMode.Coast);
		bagMotor2 = new WPI_TalonSRX(RobotMap.CLIMBING_MOTOR2);
		bagMotor2.setNeutralMode(NeutralMode.Coast);
		//while(true)if (joystick.getRawButton(4))manualClimb(joystick);
	}

	protected void climb(double rotations) {
		// 2017: bagMotor.getAnalogInPosition();
		double firstNum = bagMotor.getSensorCollection().getAnalogIn();
		bagMotor.set(-1);
		bagMotor2.set(-1);
		while(bagMotor.getSensorCollection().getAnalogIn()<firstNum+(rotations*1024)){;}
		bagMotor.set(0);
		bagMotor2.set(0);
	}

	public void manualClimb(double speed) {
		if (isCurrentExceeded()) {
			bagMotor.set(0);
			bagMotor2.set(0);
			timeCurrentExceeded = System.currentTimeMillis();
			return;
		}
		if (System.currentTimeMillis() - timeCurrentExceeded < timeOut) {
			bagMotor.set(0);
			bagMotor2.set(0);
			return;
		}
		bagMotor.set(speed);
		bagMotor2.set(speed);
	}

	public boolean isCurrentExceeded() {
		return bagMotor.getOutputCurrent() >= currentLimit||bagMotor2.getOutputCurrent() >= currentLimit;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		//new ClimbWithJoystick(Robot.oi.getJoystick2(), 3); // Right Trigger on PS3
	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Climber: talon current", bagMotor.getOutputCurrent());
		SmartDashboard.putNumber("Climber2: talon current",bagMotor2.getOutputCurrent());
	}

}
