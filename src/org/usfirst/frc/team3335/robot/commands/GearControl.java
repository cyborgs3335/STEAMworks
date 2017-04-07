package org.usfirst.frc.team3335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearControl extends CommandGroup {
	
	public GearControl(boolean f) {
		if (f) {
			addSequential(new GearEjectorControl(f));
			addSequential(new Delay(150));
			addSequential(new GateControl(f));
		}
		else {
			addSequential(new GateControl(f));
			addSequential(new Delay(150));
			addSequential(new GearEjectorControl(f));
		}
	}

}
