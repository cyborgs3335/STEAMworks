package org.usfirst.frc.team3335.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3335.robot.commands.*;
import org.usfirst.frc.team3335.robot.commands.autonomous.AutoSteerDriveToPeg;
import org.usfirst.frc.team3335.robot.commands.autonomous.AutoTurnToPeg;
import org.usfirst.frc.team3335.robot.subsystems.Intake;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick joystick;
    private Joystick joystick2;

    public OI() {
        joystick = new Joystick(0);
        joystick2 = new Joystick(1);

        //JoystickButton dumpFuel = addButton(getJoystick(), 1, "Dump Fuel");
        //dumpFuel.whenPressed(new DumpFuel());
        JoystickButton gateControlUp = addButton(getJoystick(), 3, "Gate Control Up");
        gateControlUp.whenPressed(new GateControl(true));
        JoystickButton gateControlDown = addButton(getJoystick(), 2, "Gate Control Down");
        gateControlDown.whenPressed(new GateControl(false));

        JoystickButton driveForward = addButton(getJoystick(), 6, "Shooter Shift High");
        driveForward.whenPressed(new SetDirection(true));
        JoystickButton driveBackward = addButton(getJoystick(), 7, "Shooter Shift Low");
        driveBackward.whenPressed(new SetDirection(false));

        JoystickButton ballShiftHigh = addButton(getJoystick(), 5, "Ball Shifter High");
        ballShiftHigh.whenPressed(new BallShiftHigh());
        JoystickButton ballShiftLow = addButton(getJoystick(), 4, "Ball Shifter Low");
        ballShiftLow.whenPressed(new BallShiftLow());

        // Rope climber
        //JoystickButton climbUp = addButton(getJoystick(), 2, "Rope Climber");
        JoystickButton climbUp = addButton(getJoystick2(), 2, "Rope Climber Slow");
        JoystickButton climbDown = addButton(getJoystick(), 8, "Rope Climber");
        climbUp.whenPressed(new Climb(false, 0.5));
        climbUp.whenReleased(new Climb(true, 0));
        climbDown.whenPressed(new Climb(false, -1));
        climbDown.whenReleased(new Climb(true, 0));
//        JoystickButton climbUp = addButton(getJoystick2(), 2, "Rope Climber");
        JoystickButton climbUp2 = addButton(getJoystick(), 9, "Rope Climber Fast");
        climbUp2.whenPressed(new Climb(false, 1));
        climbUp2.whenReleased(new Climb(true, 0));

        JoystickButton shootBalls = addButton(getJoystick(), 1, "Shoot Balls");
        shootBalls.whenPressed(new ShootBalls(false, -1));
        shootBalls.whenReleased(new ShootBalls(true, 0));

        // Intake
        JoystickButton intakeOn = addButton(getJoystick(), 10, "Intake On");
        intakeOn.whenPressed(new IntakeBalls(false, -0.5));
        JoystickButton intakeOff = addButton(getJoystick(), 11, "Intake Off");
        intakeOff.whenPressed(new IntakeBalls(true, 0));
        
        // Flapper
        JoystickButton flapperUp = addButton(getJoystick2(), 7, "Flapper Up");
        flapperUp.whenPressed(new FlapperControl(false));
        JoystickButton flapperDown = addButton(getJoystick2(), 8, "Flapper Down");
        flapperDown.whenPressed(new FlapperControl(true));
        
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

    public Joystick getJoystick2() {
      return joystick2;
    }
}
