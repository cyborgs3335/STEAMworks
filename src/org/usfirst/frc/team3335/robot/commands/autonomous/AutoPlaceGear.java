package org.usfirst.frc.team3335.robot.commands.autonomous;

import org.usfirst.frc.team3335.robot.commands.BallShiftLow;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPlaceGear extends CommandGroup {

	/**
	 * Drive straight specified distance, turn by specified angle, then use vision to drive to target.
	 * @param distStraight distance to start driving straight forward
	 * @param turnAngle angle (in degrees) to turn before starting vision for steering/driving;
	 *                  positive for cw, negative for ccw
	 */
	public AutoPlaceGear(double distStraight, double turnAngle) {
		addSequential(new BallShiftLow());
		addSequential(new AutoDriveToPeg(distStraight));
		addSequential(new AutoTurnToPeg(turnAngle));
		//addSequential(new AutoSteerDriveToPeg(60, .7, 9));
		//addSequential(new AutoTurnByVision(0.4));
	}
}
