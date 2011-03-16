package pacman.behaviours;

public abstract class BehaviourFactory {
	
	Behaviour pacman;
	public final Behaviour getPacmanBehaviour() {
		if(pacman == null)
			pacman = getPacman();
		return pacman;
	}
	
	Behaviour blinky;
	public final Behaviour getBlinkyBehaviour() {
		if(blinky == null)
			blinky = getBlinky();
		return blinky;
	}
	
	Behaviour pinky;
	public final Behaviour getPinkyBehaviour() {
		if(pinky == null)
			pinky = getPinky();
		return pinky;
	}
	
	Behaviour inky;
	public final Behaviour getInkyBehaviour() {
		if(inky == null)
			inky = getInky();
		return inky;
	}
	
	Behaviour clyde;
	public final Behaviour getClydeBehaviour() {
		if(clyde == null)
			clyde = getClyde();
		return clyde;
	}

	protected abstract Behaviour getPacman();
	protected abstract Behaviour getBlinky();
	protected abstract Behaviour getPinky();
	protected abstract Behaviour getInky();
	protected abstract Behaviour getClyde();
}
