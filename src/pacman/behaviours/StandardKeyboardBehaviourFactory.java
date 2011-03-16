package pacman.behaviours;

public class StandardKeyboardBehaviourFactory extends BehaviourFactory {

	protected Pacman getPacman() {
		return new Pacman();
	}

	protected GhostBehaviour getBlinky() {
		return new Blinky();
	}

	protected GhostBehaviour getPinky() {
		return new Pinky();
	}

	protected GhostBehaviour getInky() {
		return new Inky();
	}

	protected GhostBehaviour getClyde() {
		return new Clyde();
	}
	

}
