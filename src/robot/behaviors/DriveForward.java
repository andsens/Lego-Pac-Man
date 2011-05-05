package robot.behaviors;

import lejos.robotics.subsumption.Behavior;
import tools.BlackWhiteGreySensor;
import tools.Car;
import tools.LineDisplayWriter;

public class DriveForward implements Behavior {

	private boolean _suppressed;
	private BlackWhiteGreySensor sensor;
	private int line;
	private int power;
	
	public DriveForward(BlackWhiteGreySensor sensor) {
		this.sensor = sensor;
		this.power = 70;
		this.line = 0;
	}

	public boolean takeControl() {
		return true; // this behavior always wants control.
	}

	public void suppress() {
		_suppressed = true;// standard practice for suppress methods
	}

	public void action() {
		_suppressed = false;
		sensor.reset();
		while (!_suppressed) {
			switch(sensor.getColor()) {
			case BLACK:
		        Car.forward(power, power);
				break;
			case WHITE:
		        Car.forward(power, power);
				break;
			case GREY:
				Car.forward(power, power);
				LineDisplayWriter.writeLine(line+"", line%7);
				line++;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.exit(1);
			}
		}
		Car.stop();
		sensor.close();
	}
	
	public void speedUp() {
		power++;
	}
	
	public void slowDown() {
		power--;
	}
}