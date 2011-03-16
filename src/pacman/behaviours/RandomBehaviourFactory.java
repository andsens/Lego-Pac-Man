package pacman.behaviours;

public class RandomBehaviourFactory extends BehaviourFactory {

	protected Behaviour getPacman() {
		return new RandomBehaviour();
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
