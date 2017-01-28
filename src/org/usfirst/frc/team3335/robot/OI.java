package org.usfirst.frc.team3335.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team3335.robot.commands.DumpFuel;
import org.usfirst.frc.team3335.robot.commands.GateControl;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick joystick;

    public OI() {
        joystick = new Joystick(0);


        JoystickButton dumpFuel = addButton(getJoystick(), 1, "Dump Fuel");
        dumpFuel.whenPressed(new DumpFuel());
        JoystickButton gateControl = addButton(getJoystick(), 2, "Gate Control");
        gateControl.whenPressed(new GateControl());
        
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
