package org.usfirst.frc.team3335.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3335.robot.Robot;

/**
 * Created by jacob on 1/28/17.
 */
public class DumpFuel extends Command{

    public DumpFuel() {
        requires(Robot.dumper);
    }

    @Override
    protected void initialize() {
        Robot.dumper.switchPos();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
