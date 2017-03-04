package org.usfirst.frc.team3335.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3335.robot.Robot;

/**
 * Created by jacob on 3/4/17.
 */
public class SetDirection extends Command {
    boolean forward;

    public SetDirection(boolean forward) {
        requires(Robot.driveTrain);
        this.forward = forward;
    }

    @Override
    protected void initialize() {
        Robot.driveTrain.setDirection(forward ? 1 : -1);
    }

    @Override
    protected boolean isFinished() {
        return true;
    }
}
