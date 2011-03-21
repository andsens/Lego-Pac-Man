package pacman.behaviours;

import java.awt.Point;

import pacman.behaviours.ghosts.GhostBehaviour;
import pacman.world.World;
import pacman.world.maps.Direction;

public class RandomBehaviour extends GhostBehaviour {

	protected Point getTarget(World world) {
		return null;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}

	protected Point getScatterTarget(World world) {
		return null;
	}

	protected Point getChaseTarget(World world) {
		return null;
	}
}
