package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.commands.BallShiftLow;
import org.usfirst.frc.team3335.robot.commands.GateControl;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceDropGear extends CommandGroup {
	public AutoPlaceDropGear(double distInitial, double turnAngle, double distPost, double distFinal) {
		// TODO Auto-generated constructor stub
		addSequential(new AutoDelay(2500));
		addSequential(new BallShiftLow());
		addSequential(new AutoDriveToPeg(distInitial));
		addSequential(new AutoTurnToPeg(turnAngle));
		addSequential(new AutoDriveToPeg(distPost));
		addSequential(new GateControl(false));
		addSequential(new AutoDriveToPeg(distFinal));
	}
}
