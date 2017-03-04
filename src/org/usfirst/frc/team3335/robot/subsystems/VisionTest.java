package org.usfirst.frc.team3335.robot.subsystems;

import java.util.ArrayList;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team3335.robot.RobotMap;

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

	private final double cameraOffset = 12; // inches - Mark 2

	private VisionThread visionThread;
	private double centerX = 0.0;
	private double targetDistance;
	private double targetAzimuth;
	private double targetOffsetX;
	private boolean targetDetected = false;
	private PIDSourceType pidSourceType = PIDSourceType.kDisplacement;

	private final Object imgLock = new Object();

	public VisionTest() {
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(); // cam0 by default
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
		camera.setBrightness(0);
//		camera.setExposureManual(100);
		camera.setExposureAuto();

		CvSource cs= CameraServer.getInstance().putVideo("name", IMG_WIDTH, IMG_HEIGHT);

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
           
	        cs.putFrame(IMG_MOD);
	    });

	    visionThread.start();
	    Relay relay = new Relay(RobotMap.RELAY_CHANNEL, Direction.kForward);
	    relay.set(Relay.Value.kOn);
	    //this.processImage();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	/*public void processImage() {
		cs.putFrame()
	}*/

	private Rect computeBoundingRectangle(ArrayList<MatOfPoint> contours) {
		if (contours.size() == 2) {
			return computeBoundingRectangle(contours.get(0), contours.get(1));
		} else if (contours.size() > 2) {
			MatOfPoint[] topTwoContours = findTopTwoContours(contours);
			if (topTwoContours == null)
				return null;
			return computeBoundingRectangle(topTwoContours[0], topTwoContours[1]);
		}
		return null;
	}

	private Rect computeBoundingRectangle(MatOfPoint mop1, MatOfPoint mop2) {
		Rect rec1 = Imgproc.boundingRect(mop1);
		Rect rec2 = Imgproc.boundingRect(mop2);
		int x = Math.min(rec1.x, rec2.x);
		int y = Math.min(rec1.y, rec2.y);
		int width = Math.max(rec1.x + rec1.width, rec2.x + rec2.width)-x;
		int height = Math.max(rec1.y + rec1.height, rec2.y + rec2.height)-y;
		Rect recCombine = new Rect(x, y, width, height);
		return recCombine;
	}

	/**
	 * Score all contours in the contour list, and return the top two.
	 * @param contours list of contours found by vision pipeline
	 * @return two contours with the top scores, or null if two contours not available
	 */
	private MatOfPoint[] findTopTwoContours(ArrayList<MatOfPoint> contours) {
		double score1 = 0;
		double score2 = 0;
		MatOfPoint mop1 = null;
		MatOfPoint mop2 = null;
		for (MatOfPoint mop : contours) {
			double score = scoreContour(mop);
			if (score > score1) {
				score2 = score1;
				mop2 = mop1;
				score1 = score;
				mop1 = mop;
			} else if (score > score2) {
				score2 = score;
				mop2 = mop;
			}
		}
		// Only return contours if we have 2
		if (mop1 != null && mop2 != null) {
			return new MatOfPoint[] { mop1, mop2 };
		}
		return null;
	}

	/**
	 * Score contour based on a variety of factors
	 * @param mop contour to score
	 * @return contour score (range from 0 to infinity?)
	 */
	private double scoreContour(MatOfPoint mop) {
		// Score on solidity -- ideally find 100% solid rectangle
		final MatOfInt hull = new MatOfInt(); // is this expensive?
		final double area = Imgproc.contourArea(mop);
		Imgproc.convexHull(mop, hull);
		MatOfPoint mopHull = new MatOfPoint();
		mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
		for (int j = 0; j < hull.size().height; j++) {
			int index = (int)hull.get(j, 0)[0];
			double[] point = new double[] { mop.get(index, 0)[0], mop.get(index, 0)[1]};
			mopHull.put(j, 0, point);
		}
		final double solid = 100 * area / Imgproc.contourArea(mopHull);
		double scoreSolidity = solid;

		// Score aspect ratio -- exact ratio is 0.4
		final Rect bb = Imgproc.boundingRect(mop);
		final double ratio = bb.width / (double)bb.height;
		final double targetRatio = 0.4;
		final double minRatio = 0.0;
		final double maxRatio = 1.0;
		double scoreAspectRatio = 0;
		if (ratio < minRatio || ratio > maxRatio) {
			scoreAspectRatio = 0;
		} else {
			if (ratio > targetRatio) {
				scoreAspectRatio = 1 - (ratio - targetRatio) / (maxRatio - targetRatio);
			} else {
				scoreAspectRatio = 1 - (targetRatio - ratio) / (targetRatio - minRatio);
			}
			scoreAspectRatio *= 100;
		}

		// Compute final score
		double score = scoreSolidity + scoreAspectRatio;
		return score;
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
		targetOffsetX = width;
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
		SmartDashboard.putNumber("Vision: Target X Offset", targetOffsetX);
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
		//return centerX;
		return targetOffsetX;
	}

}
