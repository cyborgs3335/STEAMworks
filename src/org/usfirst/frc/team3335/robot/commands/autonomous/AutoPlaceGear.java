package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.commands.BallShiftLow;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceGear extends CommandGroup {

	public AutoPlaceGear() {
		addSequential(new BallShiftLow());
		addSequential(new AutoDriveToPeg(108));
		addSequential(new AutoTurnToPeg());
		//addSequential(new AutoSteerDriveToPeg(60, .7, 9));
		addSequential(new AutoTurnByVision(0.4));
	}
}
