package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.commands.BallShiftLow;
import org.usfirst.frc.team3335.robot.commands.GateControl;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceDropGearVision extends CommandGroup {
	public AutoPlaceDropGearVision(double distInitial, double turnAngle, double distPost, double distFinal,
			double speed) {
		addSequential(new AutoDelay(0));
		addSequential(new BallShiftLow());
		addSequential(new AutoDriveToPeg(distInitial, speed));
		addSequential(new AutoTurnToPegSimple(turnAngle));
		addSequential(new AutoDelay(500));

		//addSequential(new AutoVisionSimple(0.4, 0.3));
		addSequential(new AutoTurnByVisionSimple());
		addSequential(new AutoDriveToPeg(66, speed));
		//addSequential(new AutoTurnToPeg(turnAngle));
		//addSequential(new AutoDriveToPeg(distPost));

		// Open gate, pause, backup 12 in, close gate, move forward 10 in, then backup distFinal
		addSequential(new GateControl(false)); // open
		addSequential(new AutoDelay(500));
		//addSequential(new AutoDriveToPeg(-12, speed));
		//a//ddSequential(new AutoDriveToPeg(10, speed));
		//addSequential(new AutoDelay(500));
		addSequential(new AutoDriveToPeg(distFinal, speed));
		addSequential(new GateControl(true)); // close
	}
}
