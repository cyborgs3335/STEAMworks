package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.commands.Delay;
import org.usfirst.frc.team3335.robot.commands.BallShiftLow;
import org.usfirst.frc.team3335.robot.commands.GateControl;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceDropGearVision extends CommandGroup {
	public AutoPlaceDropGearVision(double distInitial, double turnAngle, double distPost, double distBack,
			double speed) {
		double scalar = 0.5; // drive speed scalar

		addSequential(new Delay(0));
		addSequential(new BallShiftLow());
		//addSequential(new AutoDriveToPeg(distInitial, speed));
		addSequential(new AutoDriveToPeg(distInitial, speed, true, 24, scalar*speed));
		if (turnAngle != 0) {
			addSequential(new AutoTurnToPegSimple(turnAngle));
		}
		addSequential(new Delay(500));

		// Turn using vision
		//addSequential(new AutoVisionSimple(0.4, 0.3));
		addSequential(new AutoTurnByVisionSimple());
		addSequential(new AutoDriveToPeg(distPost, speed, true, 24, scalar*speed));
		//addSequential(new AutoTurnToPeg(turnAngle));
		//addSequential(new AutoDriveToPeg(distPost));

		// Open gate, pause, backup 12 in, close gate, move forward 10 in, then backup distFinal
		addSequential(new GateControl(false)); // open
		addSequential(new Delay(500));
		addSequential(new AutoDriveToPeg(-12, scalar*speed));
		addSequential(new GateControl(true)); // close
		addSequential(new AutoDriveToPeg(10, scalar*speed));
		//addSequential(new AutoDelay(500));
		addSequential(new AutoDriveToPeg(distBack, scalar*speed));
	}
}
