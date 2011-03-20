package pacman.behaviours.factories;

import pacman.behaviours.Pacman;
import pacman.behaviours.ghosts.DirectHomingGhost;
import pacman.behaviours.ghosts.GhostBehaviour;

public class HomingGhostsBehaviourFactory extends BehaviourFactory {

	protected Pacman getPacman() {
		return new Pacman();
	}

	protected GhostBehaviour getBlinky() {
		return new DirectHomingGhost();
	}

	protected GhostBehaviour getPinky() {
		return new DirectHomingGhost();
	}

	protected GhostBehaviour getInky() {
		return new DirectHomingGhost();
	}

	protected GhostBehaviour getClyde() {
		return new DirectHomingGhost();
	}
	

}
