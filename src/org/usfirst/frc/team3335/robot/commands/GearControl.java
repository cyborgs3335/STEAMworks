package org.usfirst.frc.team3335.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearControl extends CommandGroup {
	
	public GearControl(boolean b) {
		if (b) {
			// Pull gear ejectors back in, then raise gate
			addSequential(new GearEjectorControl(b));
			addSequential(new Delay(150));
			addSequential(new GateControl(b));
		}
		else {
			// Lower gate, gear ejectors thrust out, then pull back in
			addSequential(new GateControl(b));
			addSequential(new Delay(150));
			addSequential(new GearEjectorControl(b));
			addSequential(new Delay(500));
			addSequential(new GearEjectorControl(!b));
		}
	}

}
