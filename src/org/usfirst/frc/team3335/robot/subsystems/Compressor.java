package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Created by jacob on 1/28/17.
 */
public class Compressor extends Subsystem implements LoggableSubsystem {

	private static edu.wpi.first.wpilibj.Compressor compressor;

    public Compressor() {
        compressor = new edu.wpi.first.wpilibj.Compressor(0);
        compressor.setClosedLoopControl(true); //on
        //compressor.setClosedLoopControl(false); //off
    }

    @Override
    protected void initDefaultCommand() {
    	// Nothing to do here
    }

	@Override
	public void log() {
		SmartDashboard.putBoolean("Compressor: On", compressor.getClosedLoopControl());
	}
}
