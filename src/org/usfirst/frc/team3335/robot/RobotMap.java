package org.usfirst.frc.team3335.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	// Mark 1
	/*
	public static final int DRIVE_TRAIN_FRONT_LEFT = 1;
    public static final int DRIVE_TRAIN_FRONT_RIGHT = 3;
    public static final int DRIVE_TRAIN_BACK_LEFT = 2;
    public static final int DRIVE_TRAIN_BACK_RIGHT = 4;
	public static final int DRIVE_TRAIN_ENCODER_LEFT_A = 0;
    public static final int DRIVE_TRAIN_ENCODER_LEFT_B = 1;
    public static final int DRIVE_TRAIN_ENCODER_RIGHT_A = 2;
    public static final int DRIVE_TRAIN_ENCODER_RIGHT_B = 3;
	public static final int CLIMBING_MOTOR = 5;
	public static final int BALL_SHOOTER_MOTOR = 7;
	public static final int ANALOG_ULTRASONIC_LEFT = 0;
	public static final int ANALOG_ULTRASONIC_RIGHT = 1;
	public static final int BALL_SHIFTER_FORWARD_CHANNEL = 4;
	public static final int BALL_SHIFTER_REVERSE_CHANNEL = 5;
	public static final int GATE_FORWARD_CHANNEL = 0;
	public static final int GATE_REVERSE_CHANNEL = 1;
	*/

	// Mark 2
	/*
	public static final int DRIVE_TRAIN_FRONT_LEFT = 1;
    public static final int DRIVE_TRAIN_FRONT_RIGHT = 3;
    public static final int DRIVE_TRAIN_BACK_LEFT = 2;
    public static final int DRIVE_TRAIN_BACK_RIGHT = 4;
    public static final int DRIVE_TRAIN_FORWARD_DIRECTION = 1;
	public static final int DRIVE_TRAIN_ENCODER_LEFT_A = 8;
    public static final int DRIVE_TRAIN_ENCODER_LEFT_B = 9;
	public static final boolean DRIVE_TRAIN_ENCODER_LEFT_REVERSE = true;
    public static final int DRIVE_TRAIN_ENCODER_RIGHT_A = 3;
    public static final int DRIVE_TRAIN_ENCODER_RIGHT_B = 4;
	public static final boolean DRIVE_TRAIN_ENCODER_RIGHT_REVERSE = false;
	public static final int CLIMBING_MOTOR = 7;
    public static final int INTAKE_MOTOR = 5;
	public static final int BALL_SHOOTER_MOTOR = 6;
	public static final int ANALOG_ULTRASONIC_LEFT = 0;
	public static final int ANALOG_ULTRASONIC_RIGHT = 1;
	public static final int RELAY_CHANNEL = 0;
	// PCM 0
	public static final int FLAPPER_FORWARD_CHANNEL = 4;
	public static final int FLAPPER_REVERSE_CHANNEL = 5;
	public static final int BALL_SHIFTER_FORWARD_CHANNEL = 1;
	public static final int BALL_SHIFTER_REVERSE_CHANNEL = 0;
	public static final int GATE_FORWARD_CHANNEL = 2;
	public static final int GATE_REVERSE_CHANNEL = 3;
	public static final int GEAR_EJECTOR_FORWARD_CHANNEL = 6;
	public static final int GEAR_EJECTOR_REVERSE_CHANNEL = 7;
	// PCM 1
	public static final int GEAR_PICKUP_UP_DOWN_FORWARD_CHANNEL = 0;
	public static final int GEAR_PICKUP_UP_DOWN_REVERSE_CHANNEL = 1;
	public static final int GEAR_PICKUP_OPEN_CLOSE_FORWARD_CHANNEL = 2;
	public static final int GEAR_PICKUP_OPEN_CLOSE_REVERSE_CHANNEL = 3;
	/**/

	// Mark 3
	/**/
	public static final int DRIVE_TRAIN_FRONT_LEFT = 1;
	public static final int DRIVE_TRAIN_FRONT_RIGHT = 3;
	public static final int DRIVE_TRAIN_BACK_LEFT = 2;
	public static final int DRIVE_TRAIN_BACK_RIGHT = 4;
    public static final int DRIVE_TRAIN_FORWARD_DIRECTION = 1;
	public static final int DRIVE_TRAIN_ENCODER_LEFT_A = 2;//8;//1; //8?
	public static final int DRIVE_TRAIN_ENCODER_LEFT_B = 3;//9;//0; //9?
	public static final boolean DRIVE_TRAIN_ENCODER_LEFT_REVERSE = false;
	public static final int DRIVE_TRAIN_ENCODER_RIGHT_A = 0;//3;//2; //3?
	public static final int DRIVE_TRAIN_ENCODER_RIGHT_B = 1;//4;//3; //4?
	public static final boolean DRIVE_TRAIN_ENCODER_RIGHT_REVERSE = true;
	public static final int CLIMBING_MOTOR = 7;
	public static final int CLIMBING_MOTOR2 = 8;
	public static final int INTAKE_MOTOR = 5;
	public static final int BALL_SHOOTER_MOTOR = 6;
	public static final int ANALOG_ULTRASONIC_LEFT = 0;
	public static final int ANALOG_ULTRASONIC_RIGHT = 1;
	public static final int RELAY_CHANNEL = 0;
	// PCM 0
	public static final int FLAPPER_FORWARD_CHANNEL = 5;
	public static final int FLAPPER_REVERSE_CHANNEL = 4;
	public static final int BALL_SHIFTER_FORWARD_CHANNEL = 0;
	public static final int BALL_SHIFTER_REVERSE_CHANNEL = 1;
	public static final int GATE_FORWARD_CHANNEL = 3;
	public static final int GATE_REVERSE_CHANNEL = 2;
	public static final int GEAR_EJECTOR_FORWARD_CHANNEL = 7;
	public static final int GEAR_EJECTOR_REVERSE_CHANNEL = 6;
	// PCM 1
	public static final int GEAR_PICKUP_UP_DOWN_FORWARD_CHANNEL = 0;
	public static final int GEAR_PICKUP_UP_DOWN_REVERSE_CHANNEL = 1;
	public static final int GEAR_PICKUP_OPEN_CLOSE_FORWARD_CHANNEL = 2;
	public static final int GEAR_PICKUP_OPEN_CLOSE_REVERSE_CHANNEL = 3;
	/**/

	//Stronghold bot ports
	/*
    public static final int DRIVE_TRAIN_FRONT_LEFT = 6;
    public static final int DRIVE_TRAIN_FRONT_RIGHT = 4;
    public static final int DRIVE_TRAIN_BACK_LEFT = 1;
    public static final int DRIVE_TRAIN_BACK_RIGHT = 3;
	public static final int CLIMBING_MOTOR = 5;
	public static final int BALL_SHOOTER_MOTOR = 7;
	public static final int ANALOG_ULTRASONIC_LEFT = 0;
	public static final int ANALOG_ULTRASONIC_RIGHT = 1;
	*/

}
