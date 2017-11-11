package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.RobotMap;
import org.usfirst.frc.team3335.robot.RobotPreferences;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoTurnToPeg3 extends Command {

	private long timeFinished = 0;
	private final long timeMax = (long)(prefs.getDouble("Auto Vision Time Limit", RobotPreferences.VISION_TIME_DEFAULT) * 1000); // millisec

	////////////////////////////////////////////////////////////////////////
//	// distance in inches the robot wants to stay from an object
//	private static final double kHoldDistance = 12.0;
//
//	// maximum distance in inches we expect the robot to see
//	private static final double kMaxDistance = 24.0;
//
//	// factor to convert sensor values to a distance in inches
//	private static final double kValueToInches = 0.125;

	private static Preferences prefs = Preferences.getInstance();
	
	//proportional speed constant
	//private static final double kP = 0.04;//0.03 too small?; 0.06 too big; 0.04 just right for mark 1
	// 0.015
	private static final double kP = prefs.getDouble("Vision Kp", RobotPreferences.VISION_KP_DEFAULT);//0.03 too small?; 0.06 too big; 0.04 just right for mark 1

	// integral speed constant
	//private static final double kI = 0; //0.018;\
	// 0.001
	private static final double kI = prefs.getDouble("Vision Ki", RobotPreferences.VISION_KI_DEFAULT); //0.018;

	// derivative speed constant
	//private static final double kD = 0; //1.5;
	private static final double kD = prefs.getDouble("Vision Kd", RobotPreferences.VISION_KD_DEFAULT); //1.5;

	// Setpoint angle
	private double setPointAngle;

	// tolerance in degrees
	private static final double kToleranceDegrees = 1.0;

	// maximum output range
	//private final double maxOutputRange = 0.4;
	private final double maxOutputRange = prefs.getDouble("Vision Max Output Range", RobotPreferences.VISION_MAX_OUTPUT_RANGE_DEFAULT);

	private double rotateRate;

	private final long pidUpdateDelay = prefs.getLong("Vision Update Delay", RobotPreferences.VISION_UPDATE_DELAY_DEFAULT); // 500 milliseconds

	private long pidTimeSinceUpdate = 0;

	private double distance;

	private double distanceLastMoved = 0;

	private double limitSpeedForward = 0.25;

	private long distanceMovedTimeout = 500; // 500 ms

	private boolean limitSpeedByDistance = true;

	private double limitDistance = 24;

	private double forwardSpeed;

	private boolean haveTarget;

	private boolean updateSetPoint = false;

	private PIDController turnController;
///////////////////////////////////////////////////////////////////////////////////////////////////////////

	public AutoTurnToPeg3() {
		//this(Preferences.getInstance().getDouble("Auto Turn Vision Simple Forward Speed", RobotPreferences.AUTO_TURN_VISION_SIMPLE_FORWARD_SPEED_DEFAULT));
		//this(0);
		this(0.7);
		//this(0.6);
	}

	/**
	 * Constructor using default setpoint angle, specified by robot preferences.
	 */
    public AutoTurnToPeg3(double forwardSpeed) {
        requires(Robot.driveTrain);
        //requires(Robot.ultrasonics);
        requires(Robot.visionTest);
        requires(Robot.navx);

        this.forwardSpeed = forwardSpeed;

        turnController = new PIDController(kP, kI, kD, Robot.navx.getAHRS(), new MyPidOutput());
        turnController.setInputRange(-180, 180);
        turnController.setOutputRange(-maxOutputRange, maxOutputRange);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
        
        /* Add the PID Controller to the Test-mode dashboard, allowing manual  */
        /* tuning of the Turn Controller's P, I and D coefficients.            */
        /* Typically, only the P value needs to be modified.                   */
        //LiveWindow.addSensor("DriveSystem", "RotateController", turnController);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.driveTrain.setBrake(true);
    	Robot.navx.zeroYaw();
    	Robot.driveTrain.zeroEncoders();
    	Robot.driveTrain.setRampRate(5/*20*/);

        this.haveTarget = Robot.visionTest.isTargetDetected();
        if (haveTarget) {
        	this.setPointAngle = Robot.visionTest.pidGet();
        	this.distance = Robot.visionTest.getTargetDistance();
        	pidTimeSinceUpdate = System.currentTimeMillis();
        } else {
        	this.setPointAngle = 0;
        	this.distance = 0;
        }

    	timeFinished = System.currentTimeMillis() + timeMax;
    	turnController.setSetpoint(setPointAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	// Update setpoints periodically
    	if (updateSetPoint && System.currentTimeMillis() - pidTimeSinceUpdate > pidUpdateDelay) {
    		if (Robot.visionTest.isTargetDetected()) {
    			double pidVal = Robot.visionTest.pidGet();
    			turnController.setSetpoint(pidVal);
    			Robot.navx.zeroYaw();
    			pidTimeSinceUpdate = System.currentTimeMillis();
    		}
    	}
    	turnController.enable();
    	double rotateSpeed = rotateRate;
    	//rotateSpeed /= 2.0;
        //Robot.driveTrain.drive(rotateSpeed, -rotateSpeed);  //Mark 1
    	//Robot.driveTrain.driveArcade(0, rotateSpeed, false); //Mark 2

    	double speed = Math.signum(distance) * forwardSpeed;
    	double limitSpeed = Math.signum(distance) * limitSpeedForward;
    	if (limitSpeedByDistance) {
    		speed = limitSpeedByDistance(speed, distance, limitDistance, limitSpeed);
    	}
    	Robot.driveTrain.driveArcade(speed, rotateSpeed, false); //Mark 2
    }

    private double limitSpeedByDistance(double speedIn, double maxDistance, double limitDistance, double limitSpeed) {
    	if (Math.abs(Robot.driveTrain.getDistance()) > Math.abs(maxDistance) - Math.abs(limitDistance)) {
    		return limitSpeed;
    	}
    	return speedIn;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	if (!haveTarget) {
    		return true;
    	}
    	double driveTrainDist = Robot.driveTrain.getDistance();
    	if (Math.abs(driveTrainDist) > Math.abs(distance)) {
    		return true;
    	}
    	//if (turnController.onTarget())
    	//	return true;
    	if (System.currentTimeMillis() > timeFinished) {
    		Robot.driveTrain.setBrake(false);
    		return true;
    	}
    	//if ()
    	//if (!Robot.visionTest.isTargetDetected()) {
    	//	return true;
    	//}
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.drive(0, 0);
        Robot.driveTrain.setBrake(false);
        Robot.driveTrain.setDefaltRampRate();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
    
    private void test() {
		// Set expected range to 0-24 inches; e.g. at 24 inches from object go
		// full forward, at 0 inches from object go full backward.
		//pidController.setInputRange(0, kMaxDistance * kValueToInches);
		// Set setpoint of the pidController
		//pidController.setSetpoint(kHoldDistance * kValueToInches);
		turnController.enable(); // begin PID control
	}
    
    private class MyPidOutput implements PIDOutput {
		@Override
		public void pidWrite(double output) {
			//myRobot.drive(output, 0);
			//Robot.driveTrain.drive(output, 0);
			rotateRate = output;
		}
	}

}
