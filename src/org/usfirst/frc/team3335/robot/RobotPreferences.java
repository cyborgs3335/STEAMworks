package org.usfirst.frc.team3335.robot;

/**
 * Created by jacob on 1/14/17.
 */
public class RobotPreferences {
    //TODO add scalar for drive train

    public static final double DRIVE_TRAIN_TURN_SCALAR = .88;

    public static final boolean DRIVE_MODE_DEFAULT = true; // Kevin=true, Duncan=false
    public static final double DRIVE_KP_DEFAULT = 0.01;
    public static final double DRIVE_KI_DEFAULT = 0.001;
    public static final double DRIVE_KD_DEFAULT = 0.0;
    public static final double VISION_KP_DEFAULT = 0.01; // or 0.015;
    public static final double VISION_KI_DEFAULT = 0.001;
    public static final double VISION_KD_DEFAULT = 0.0;
    public static final long VISION_UPDATE_DELAY_DEFAULT = 500; // milliseconds
    public static final double TURN_TO_PEG_ANGLE_DEFAULT = 90.0;

    public static final int DRIVE_TRAIN_LEFT_AXIS = 1;
    public static final int DRIVE_TRAIN_RIGHT_AXIS = 5;

	public static final double VISION_TIME_DEFAULT = 15;
	public static final double VISION_MAX_OUTPUT_RANGE_DEFAULT = 0.25;

	public static final double AUTO_TURN_VISION_SIMPLE_FORWARD_SPEED_DEFAULT = 0.3;
	public static final double AUTO_TURN_VISION_SIMPLE_ROTATE_SPEED_DEFAULT = 0.15;
}
