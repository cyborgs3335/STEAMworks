package org.usfirst.frc.team3335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceGear extends CommandGroup {

	public AutoPlaceGear() {
		addSequential(new BallShiftLow());
		addSequential(new AutoDriveToPeg());
	}
}
