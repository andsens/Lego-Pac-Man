package pacman.behaviours.factories;

import pacman.behaviours.Pacman;
import pacman.behaviours.PacmanBehaviour;
import pacman.behaviours.RandomBehaviour;
import pacman.behaviours.ghosts.GhostBehaviour;

public class RandomBehaviourFactory extends BehaviourFactory {

	protected PacmanBehaviour getPacman() {
		return new Pacman();
	}

	protected GhostBehaviour getBlinky() {
		return new RandomBehaviour();
	}

	protected GhostBehaviour getPinky() {
		return new RandomBehaviour();
	}

	protected GhostBehaviour getInky() {
		return new RandomBehaviour();
	}

	protected GhostBehaviour getClyde() {
		return new RandomBehaviour();
	}

}
