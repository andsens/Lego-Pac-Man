package pacman.behaviours.factories;

import pacman.behaviours.PacmanBehaviour;
import pacman.behaviours.ghosts.GhostBehaviour;
import pacman.world.World;

public abstract class BehaviourFactory {
	
	public void setWorld(World world) {
		getPacmanBehaviour().setWorld(world);
		getBlinkyBehaviour().setWorld(world);
		getPinkyBehaviour().setWorld(world);
		getInkyBehaviour().setWorld(world);
		getClydeBehaviour().setWorld(world);
	}
	
	PacmanBehaviour pacman;
	public final PacmanBehaviour getPacmanBehaviour() {
		if(pacman == null)
			pacman = getPacman();
		return pacman;
	}
	
	GhostBehaviour blinky;
	public final GhostBehaviour getBlinkyBehaviour() {
		if(blinky == null)
			blinky = getBlinky();
		return blinky;
	}
	
	GhostBehaviour pinky;
	public final GhostBehaviour getPinkyBehaviour() {
		if(pinky == null)
			pinky = getPinky();
		return pinky;
	}
	
	GhostBehaviour inky;
	public final GhostBehaviour getInkyBehaviour() {
		if(inky == null)
			inky = getInky();
		return inky;
	}
	
	GhostBehaviour clyde;
	public final GhostBehaviour getClydeBehaviour() {
		if(clyde == null)
			clyde = getClyde();
		return clyde;
	}

	protected abstract PacmanBehaviour getPacman();
	protected abstract GhostBehaviour getBlinky();
	protected abstract GhostBehaviour getPinky();
	protected abstract GhostBehaviour getInky();
	protected abstract GhostBehaviour getClyde();
}
