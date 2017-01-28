package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by jacob on 1/28/17.
 */
public class Compressor extends Subsystem{
    private static edu.wpi.first.wpilibj.Compressor compressor;

    public Compressor() {
        compressor = new edu.wpi.first.wpilibj.Compressor(0);
        compressor.setClosedLoopControl(true);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
