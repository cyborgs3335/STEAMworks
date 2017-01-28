package org.usfirst.frc.team3335.robot.subsystems;


// The NavX library is availble at http://www.pdocs.kauailabs.com/navx-mxp/software/roborio-libraries/java/
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by jacob on 1/28/17.
 */
public class NavX extends Subsystem implements LoggableSubsystem {
    private AHRS navX;

    public NavX() {
        navX = new AHRS(I2C.Port.kMXP);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public double getYaw() {
        return navX.getYaw();
    }

    @Override
    public void log() {

    }
}
