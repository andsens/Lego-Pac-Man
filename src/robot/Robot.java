package robot;

import lejos.nxt.SensorPort;
import tools.BlackWhiteGreySensor;

public class Robot {
	
	public static void main(String[] args) {
		BlackWhiteGreySensor sensor = new BlackWhiteGreySensor(SensorPort.S1);
		sensor.calibrate();
	}
}
