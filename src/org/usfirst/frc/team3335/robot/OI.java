package org.usfirst.frc.team3335.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3335.robot.commands.BallShiftHigh;
import org.usfirst.frc.team3335.robot.commands.BallShiftLow;
import org.usfirst.frc.team3335.robot.commands.Climb;
import org.usfirst.frc.team3335.robot.commands.DumpFuel;
import org.usfirst.frc.team3335.robot.commands.GateControl;
import org.usfirst.frc.team3335.robot.commands.autonomous.AutoSteerDriveToPeg;
import org.usfirst.frc.team3335.robot.commands.autonomous.AutoTurnToPeg;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick joystick;

    public OI() {
        joystick = new Joystick(0);


        //JoystickButton dumpFuel = addButton(getJoystick(), 1, "Dump Fuel");
        //dumpFuel.whenPressed(new DumpFuel());
        JoystickButton gateControl = addButton(getJoystick(), 3, "Gate Control");
        gateControl.whenPressed(new GateControl());

        JoystickButton shooterShiftHigh = addButton(getJoystick(), 6, "Shooter Shift High");
        JoystickButton shooterShiftLow = addButton(getJoystick(), 7, "Shooter Shift Low");

        JoystickButton ballShiftHigh = addButton(getJoystick(), 5, "Ball Shifter High");
        ballShiftHigh.whenPressed(new BallShiftHigh());
        JoystickButton ballShiftLow = addButton(getJoystick(), 4, "Ball Shifter Low");
        ballShiftLow.whenPressed(new BallShiftLow());

        JoystickButton climbUp = addButton(getJoystick(), 2, "Rope Climber");
        JoystickButton climbDown = addButton(getJoystick(), 8, "Rope Climber");
        climbUp.whenPressed(new Climb(false, 1));
        climbUp.whenReleased(new Climb(true, 0));
        climbDown.whenPressed(new Climb(false, -1));
        climbDown.whenReleased(new Climb(true, 0));
        
        // Additional commands to add to dashboard
		SmartDashboard.putData("AutoTurnToPeg", new AutoTurnToPeg());
		SmartDashboard.putData("AutoSteerDriveToPeg", new AutoSteerDriveToPeg(60, 0.7, 9));
    }

    private JoystickButton addButton(Joystick joystick, int buttonNumber, String key) {
        JoystickButton button = new JoystickButton(joystick, buttonNumber);
        SmartDashboard.putData(key, button);
        return button;
    }

    public Joystick getJoystick() {
        return joystick;
    }
}
