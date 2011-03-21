package pacman.behaviours.factories;

import pacman.behaviours.Pacman;
import pacman.behaviours.ghosts.Blinky;
import pacman.behaviours.ghosts.Clyde;
import pacman.behaviours.ghosts.GhostBehaviour;
import pacman.behaviours.ghosts.Inky;
import pacman.behaviours.ghosts.Pinky;
import pacman.behaviours.ghosts.StationaryBehaviour;

public class StandardPacmanBehaviours extends BehaviourFactory {

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
		return new StationaryBehaviour();
	}
	

}
