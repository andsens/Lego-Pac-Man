package robot;

import tools.BlackWhiteGreySensor;
import lejos.nxt.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

import behaviors.*;

public class LineFollower {

	public static void main(String[] args) {
		BlackWhiteGreySensor sensor = new BlackWhiteGreySensor(SensorPort.S1);
		sensor.calibrate();
		Behavior b1 = new DriveForward(sensor);
		Behavior b2 = new ExitBehavior();
		Behavior[] behaviorList = {b1, b2};
		Arbitrator arbitrator = new Arbitrator(behaviorList);
		arbitrator.start();
	}
}
