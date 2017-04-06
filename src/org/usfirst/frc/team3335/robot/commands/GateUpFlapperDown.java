package org.usfirst.frc.team3335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GateUpFlapperDown extends CommandGroup {
	
	public GateUpFlapperDown() {
		addSequential(new GearControl(true));
		addSequential(new FlapperControl(true));
	}

}
