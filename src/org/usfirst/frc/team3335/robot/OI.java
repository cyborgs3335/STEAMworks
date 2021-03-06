package org.usfirst.frc.team3335.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3335.robot.commands.*;
//import org.usfirst.frc.team3335.robot.commands.autonomous.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private Joystick joystick;
    private Joystick joystick2;

    public OI(boolean driveMode) {
        joystick = new Joystick(0);
        joystick2 = new Joystick(1);

        // Joystick 1
        //boolean driveMode = true; // Kevin
        //boolean driveMode = false; // Duncan
        int bDefault = -1;
        int bGateUp = bDefault;
        int bGateDown = bDefault;
        int bFlapperUp = bDefault;
        int bFlapperDown = bDefault;
        int bShiftLow = bDefault;
        int bShiftHigh = bDefault;
        int bDriveForward = bDefault;
        int bDriveBackward = bDefault;

        // Joystick 2
        int bClimberUpSlow = 5; // Left Button
        int bClimberUpFast = 6; // Right Button
        int bClimberDown = 7; // "Back"
        int bShooter = 3; // X
        int bIntake = 1; // A

        if (driveMode) {
        	// Joystick 1
        	bGateUp = 3;
        	bGateDown = 2;
        	bFlapperUp = 5;
        	bFlapperDown = 4;
        	bShiftLow = 7;
        	bShiftHigh = 6;
        	bDriveForward = 8;
        	bDriveBackward = 9;
        } else {
        	// Joystick 1
        	bGateUp = 3;
        	bGateDown = 2;
        	bFlapperUp = 6;
        	bFlapperDown = 7;
        	bShiftLow = 4;
        	bShiftHigh = 5;
        	bDriveForward = bDefault;
        	bDriveBackward = bDefault;
        }

        // Gate
        JoystickButton gateControlUp = addButton(getJoystick(), bGateUp, "Gate Up");
        gateControlUp.whenPressed(new GateControl(true));
        JoystickButton gateControlDown = addButton(getJoystick(), bGateDown, "Gate Down");
        gateControlDown.whenPressed(new GateControl(false));

        // Flapper
        JoystickButton flapperUp = addButton(getJoystick(), bFlapperUp, "Flapper Up");
        flapperUp.whenPressed(new FlapperControl(false));
        JoystickButton flapperDown = addButton(getJoystick(), bFlapperDown, "Flapper Down");
        flapperDown.whenPressed(new FlapperControl(true));

        // Drive Mode: Front is Forward vs Back is Forward
        JoystickButton driveForward = addButton(getJoystick(), bDriveForward, "Drive Forward");
        driveForward.whenPressed(new SetDirection(true));
        JoystickButton driveBackward = addButton(getJoystick(), bDriveBackward, "Drive Backward");
        driveBackward.whenPressed(new SetDirection(false));

        // Ball Shifter
        JoystickButton ballShiftHigh = addButton(getJoystick(), bShiftHigh, "Ball Shifter High");
        ballShiftHigh.whenPressed(new BallShiftHigh());
        JoystickButton ballShiftLow = addButton(getJoystick(), bShiftLow, "Ball Shifter Low");
        ballShiftLow.whenPressed(new BallShiftLow());

        // Rope climber
        JoystickButton climbUpSlow = addButton(getJoystick2(), bClimberUpSlow, "Rope Climber Slow");
        climbUpSlow.whenPressed(new Climb(false, 0.5));
        climbUpSlow.whenReleased(new Climb(true, 0));
        JoystickButton climbUpFast = addButton(getJoystick2(), bClimberUpFast, "Rope Climber Fast");
        climbUpFast.whenPressed(new Climb(false, 1));
        climbUpFast.whenReleased(new Climb(true, 0));
        JoystickButton climbDown = addButton(getJoystick2(), bClimberDown, "Rope Climber Down");
        climbDown.whenPressed(new Climb(false, -1));
        climbDown.whenReleased(new Climb(true, 0));

        // Shooter
        JoystickButton shootBalls = addButton(getJoystick2(), bShooter, "Shoot Balls");
        shootBalls.whenPressed(new ShootBalls(false, -1));
        shootBalls.whenReleased(new ShootBalls(true, 0));

        // Intake
        JoystickButton intake = addButton(getJoystick2(), bIntake, "Intake On");
        intake.whenPressed(new IntakeBalls(false, -0.5));
        intake.whenReleased(new IntakeBalls(true, 0));

        // Additional commands to add to dashboard
        /*
		SmartDashboard.putData("AutoTurnToPeg", new AutoTurnToPeg());
		SmartDashboard.putData("AutoSteerDriveToPeg", new AutoSteerDriveToPeg(60, 0.7, 9));
		*/
    }

    private JoystickButton addButton(Joystick joystick, int buttonNumber, String key) {
        JoystickButton button = new JoystickButton(joystick, buttonNumber);
        //TODO uncomment to see commands on dashboard
        //SmartDashboard.putData(key, button);
        return button;
    }

    public Joystick getJoystick() {
        return joystick;
    }

    public Joystick getJoystick2() {
      return joystick2;
    }
}
