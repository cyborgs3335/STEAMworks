package org.usfirst.frc.team3335.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team3335.robot.Robot;

/**
 * Created by jacob on 1/14/17.
 */
public class TankDrive extends Command{

    public TankDrive() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        // System.out.println(this.getClass().getName() + ": initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        // System.out.println(this.getClass().getName() + ": execute");
        Robot.driveTrain.drive(Robot.oi.getJoystick());
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false; // Runs until interrupted
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
