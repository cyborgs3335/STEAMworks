package org.usfirst.frc.team3335.robot.subsystems;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class VisionTest extends Subsystem implements LoggableSubsystem{

	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	
	private VisionThread visionThread;
	private double centerX = 0.0;
	
	
	private final Object imgLock = new Object();
	
	public VisionTest() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		
		CvSource cs= CameraServer.getInstance().putVideo("name", 320, 240);
		
		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			Mat IMG_MOD = pipeline.hslThresholdOutput();
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            Rect rect = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
	            synchronized (imgLock) {
	                centerX = rect.x + (rect.width / 2);
	            }
	            
	            Imgproc.rectangle(IMG_MOD, new Point(rect.x, rect.y),new Point(rect.x + rect.width,rect.y + rect.height), new Scalar(0, 255, 0), 5);
	            
	        }
	        cs.putFrame(IMG_MOD);
	    });
		
	    visionThread.start();
	    Relay relay = new Relay(0,Direction.kForward);
	    relay.set(Relay.Value.kOn);
	    //this.processImage();
	}
	
	/*public void processImage() {
		cs.putFrame()
	}*/
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	@Override
	public void log() {
		SmartDashboard.putNumber("Center Value", centerX);
		
	}

}
