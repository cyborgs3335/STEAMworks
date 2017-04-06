package org.usfirst.frc.team3335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearControl extends CommandGroup {
	
	public GearControl(boolean f) {
		addSequential(new GateControl(f));
		addSequential(new Delay(250));
		addSequential(new GearEjectorControl(f));
	}

}
