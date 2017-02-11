package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.commands.BallShiftLow;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceGear extends CommandGroup {

	public AutoPlaceGear() {
		addSequential(new BallShiftLow());
		addSequential(new AutoDriveToPeg());
		addSequential(new AutoSteerDriveToPeg(45, .7));
	}
}
