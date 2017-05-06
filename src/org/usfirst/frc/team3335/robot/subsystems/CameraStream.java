package org.usfirst.frc.team3335.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraStream extends Subsystem implements LoggableSubsystem {

	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;

	public CameraStream() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		camera.setBrightness(0);
		camera.setExposureAuto();
	}

	@Override
	protected void initDefaultCommand() {
		// Nothing to do here
	}
	
	@Override
	public void log() {
		// Nothing to do here
	}
}
