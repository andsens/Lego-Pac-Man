package pacman.behaviours.factories;

import pacman.behaviours.PacmanBehaviour;
import pacman.behaviours.RandomBehaviour;
import pacman.behaviours.controllers.Controller;
import pacman.behaviours.controllers.KeyboardController;
import pacman.behaviours.ghosts.GhostBehaviour;

public class RandomBehaviourFactory extends BehaviourFactory {

	protected PacmanBehaviour getPacman() {
		return new PacmanBehaviour(getController());
	}
	
	protected Controller getPacmanController() {
		return new KeyboardController();
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
