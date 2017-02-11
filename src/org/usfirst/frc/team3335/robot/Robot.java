
package org.usfirst.frc.team3335.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

import org.usfirst.frc.team3335.robot.commands.AutoNone;
import org.usfirst.frc.team3335.robot.commands.AutoPlaceGear;
import org.usfirst.frc.team3335.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	// List of subsystems, convenient for logging, etc.
	private ArrayList<LoggableSubsystem> subsystemsList = new ArrayList<LoggableSubsystem>();

	// Subsystems
	public static Compressor compressor;
	public static DriveTrain driveTrain;
	public static VisionTest visionTest;
	public static BallShifter ballShifter;
	public static BallShooter ballShooter;
	public static Dumper dumper;
	public static Gate gate;
	public static Climber climber;
	public static DoubleUltrasonic ultrasonics;
	public static NavX navx;

	public static final String AUTO_MODE = "Auto Mode";
	public static final String AUTO_PLACE_GEAR = "Auto Place Gear";
	public static final String AUTO_DEFAULT = "Default Auto";
	public static final String AUTO_NONE = "None";

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Instantiate subsystems and add to subsystem list (e.g., for logging to dashboard)
		compressor = new Compressor();
		subsystemsList.add(compressor);
		driveTrain = new DriveTrain();
		subsystemsList.add(driveTrain);
		//visionTest = new VisionTest();
		//subsystemsList.add(visionTest);
		dumper = new Dumper();
		subsystemsList.add(dumper);
		gate = new Gate();
		subsystemsList.add(gate);
		ballShifter = new BallShifter();
		subsystemsList.add(ballShifter);
		ballShooter = new BallShooter();
		subsystemsList.add(ballShooter);
		climber = new Climber();
		subsystemsList.add(climber);
		ultrasonics = new DoubleUltrasonic();
		subsystemsList.add(ultrasonics);
		navx = new NavX();
		subsystemsList.add(navx);
		
		//autonomous
		chooser.addObject(AUTO_PLACE_GEAR, new AutoPlaceGear());
		chooser.addDefault(AUTO_NONE, new AutoNone());
		SmartDashboard.putData(AUTO_MODE, chooser);


		//Instantiate after all subsystems and preferences - or the world will die
		//We don't want that, do we?
		oi = new OI();

		addSubsystemsToDashboard(subsystemsList);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

//		String autoSelected = SmartDashboard.getString(AUTO_MODE, AUTO_DEFAULT);
//		switch(autoSelected) {
//		case AUTO_PLACE_GEAR:
//			autonomousCommand = new AutoPlaceGear();
//			break;
//		case AUTO_NONE:
//		case AUTO_DEFAULT:
//		default:
//			autonomousCommand = new AutoNone();
//			break;
//		}

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	private void log() {
		for (LoggableSubsystem subsystem : subsystemsList) {
			if (subsystem != null) {
				subsystem.log();
			}
		}
	}

	private void addSubsystemsToDashboard(ArrayList<LoggableSubsystem> subsystems) {
		for (LoggableSubsystem subsystem : subsystems) {
			if (subsystem != null && subsystem instanceof Subsystem) {
				SmartDashboard.putData((Subsystem) subsystem);
			}
		}
	}
}
