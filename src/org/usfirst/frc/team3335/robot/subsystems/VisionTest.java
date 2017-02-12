package org.usfirst.frc.team3335.robot.subsystems;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class VisionTest extends Subsystem implements LoggableSubsystem, PIDSource {

	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;

	private VisionThread visionThread;
	private double centerX = 0.0;
	private double targetDistance;
	private double targetAzimuth;
	private boolean targetDetected = false;
	private PIDSourceType pidSourceType = PIDSourceType.kDisplacement;

	private final Object imgLock = new Object();

	public VisionTest() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);

//		CvSource cs= CameraServer.getInstance().putVideo("name", IMG_WIDTH, IMG_HEIGHT);

		visionThread = new VisionThread(camera, new GripPipeline(), pipeline -> {
			Mat IMG_MOD = pipeline.hslThresholdOutput();
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	            //Rect recCombine = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
                Rect recCombine = computeBoundingRectangle(pipeline.filterContoursOutput());
                if (recCombine == null) {
                	targetDetected = false;
                	return;
                }
                targetDetected = true;
		        computeCoords(recCombine);
	            synchronized (imgLock) {
	                centerX = recCombine.x + (recCombine.width / 2);
	            }
	            
	            Imgproc.rectangle(IMG_MOD, new Point(recCombine.x, recCombine.y),new Point(recCombine.x + recCombine.width,recCombine.y + recCombine.height), new Scalar(255, 20, 0), 5);
	            
	        } else {
	        	targetDetected = false;
	        }
           
//	        cs.putFrame(IMG_MOD);
	    });

	    visionThread.start();
	    Relay relay = new Relay(0,Direction.kForward);
	    relay.set(Relay.Value.kOn);
	    //this.processImage();
	}
	
	/*public void processImage() {
		cs.putFrame()
	}*/

	private Rect computeBoundingRectangle(ArrayList<MatOfPoint> contours) {
		if(contours.size() == 2){
			MatOfPoint mop1 = contours.get(0);
			Rect rec1 = Imgproc.boundingRect(mop1);
			MatOfPoint mop2 = contours.get(1);
			Rect rec2 = Imgproc.boundingRect(mop2);
			int x = Math.min(rec1.x, rec2.x);
			int y = Math.min(rec1.y, rec2.y);
			int width = Math.max(rec1.x + rec1.width, rec2.x + rec2.width)-x;
			int height = Math.max(rec1.y + rec1.height, rec2.y + rec2.height)-y;
			Rect recCombine = new Rect(x, y, width, height);
			return recCombine;
		}
		return null;
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	private void computeCoords(Rect rec) {
		double pixelFOV = IMG_WIDTH;
		//double targetFeet = 20.0 / 12.0; // Stronghold target width / 12 in
		//double targetFeet = 10.25 / 12.0; // Steamworks target width / 12 in
		double targetFeet = 10.25; // INCHES!!!! // Steamworks target width in inches
		double diagonalFOVDegrees = 68.5;  // Field-Of-View angle in degress for Microsoft LifeCam HD-3000(?)
		double distance = targetFeet * pixelFOV / (2 * rec.width * Math.tan(Math.toRadians(diagonalFOVDegrees / 2)));
		distance *= 1.47; // Fudge factor [equal to 1/tan(68.5/2)]
		double targetCx = rec.x + rec.width / 2;
		double width = (targetCx - pixelFOV / 2) * targetFeet / rec.width;
		double azimuth = Math.toDegrees(Math.atan2(width,  distance));
		targetDistance = distance;
		targetAzimuth =  azimuth;
	}

	public boolean isTargetDetected() {
		return targetDetected;
	}

	@Override
	public void log() {
		double cx = centerX;
		SmartDashboard.putNumber("Vision: Center Value", cx);
		SmartDashboard.putNumber("Vision: Target Distance", targetDistance);
		SmartDashboard.putNumber("Vision: Target Azimuth", targetAzimuth);
		SmartDashboard.putBoolean("Vision: Target Detected", targetDetected);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		pidSourceType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pidSourceType;
	}

	@Override
	public double pidGet() {
		return centerX;
	}

}
