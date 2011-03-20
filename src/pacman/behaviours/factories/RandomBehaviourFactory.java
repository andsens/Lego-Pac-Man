package pacman.behaviours.factories;

import pacman.behaviours.Behaviour;
import pacman.behaviours.Pacman;
import pacman.behaviours.RandomBehaviour;

public class RandomBehaviourFactory extends BehaviourFactory {

	protected Behaviour getPacman() {
		return new Pacman();
	}

	protected Behaviour getBlinky() {
		return new RandomBehaviour();
	}

	protected Behaviour getPinky() {
		return new RandomBehaviour();
	}

	protected Behaviour getInky() {
		return new RandomBehaviour();
	}

	protected Behaviour getClyde() {
		return new RandomBehaviour();
	}

}
