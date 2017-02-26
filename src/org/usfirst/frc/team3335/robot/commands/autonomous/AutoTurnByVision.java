package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.Robot;
import org.usfirst.frc.team3335.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoTurnByVision extends Command {

	private long timeFinished = 0;
	private final long timeMax = 10000; // millisec

	////////////////////////////////////////////////////////////////////////
//	// distance in inches the robot wants to stay from an object
//	private static final double kHoldDistance = 12.0;
//
//	// maximum distance in inches we expect the robot to see
//	private static final double kMaxDistance = 24.0;
//
//	// factor to convert sensor values to a distance in inches
//	private static final double kValueToInches = 0.125;

	// proportional speed constant
	private static final double kP = 0.01;//0.03 too small?; 0.06 too big; 0.04 just right for mark 1

	// integral speed constant
	private static final double kI = 0; //0.018;

	// derivative speed constant
	private static final double kD = 0; //1.5;
	
	// tolerance in degrees
	private static final double kToleranceDegrees = 1.0;
	
	private double rotateRate;
	
	private PIDController turnController;
///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
    public AutoTurnByVision() {
        requires(Robot.driveTrain);
        //requires(Robot.ultrasonics);
        requires(Robot.visionTest);
        //requires(Robot.navx);

        turnController = new PIDController(kP, kI, kD, Robot.visionTest, new MyPidOutput());
        turnController.setInputRange(-180, 180);
        turnController.setOutputRange(-.7, .7);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
        
        /* Add the PID Controller to the Test-mode dashboard, allowing manual  */
        /* tuning of the Turn Controller's P, I and D coefficients.            */
        /* Typically, only the P value needs to be modified.                   */
        LiveWindow.addSensor("DriveSystem", "RotateController", turnController);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	Robot.driveTrain.setBrake(true);
    	//Robot.navx.zeroYaw();
    	timeFinished = System.currentTimeMillis() + timeMax;
    	turnController.setSetpoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	turnController.enable();
    	double speed = rotateRate;
//    	speed /= 2.0;
        //Robot.driveTrain.drive(speed, -speed);
    	Robot.driveTrain.driveArcade(0, -speed, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
    	//if (turnController.onTarget())
    	//	return true;
    	if (System.currentTimeMillis() > timeFinished) {
    		Robot.driveTrain.setBrake(false);
    		return true;
    	}
    	//if (!Robot.visionTest.isTargetDetected()) {
    	//	return true;
    	//}
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.drive(0, 0);
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
