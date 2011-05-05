package robot.behaviors;

import lejos.nxt.Button;
import lejos.robotics.subsumption.Behavior;

public class ExitBehavior implements Behavior {

	public ExitBehavior() {
	}

	public boolean takeControl() {
		return Button.ESCAPE.isPressed();
	}

	public void suppress() {
		// Since this is highest priority behavior, suppress will never be
		// called.
	}

	public void action() {
		System.exit(0);
	}
}
