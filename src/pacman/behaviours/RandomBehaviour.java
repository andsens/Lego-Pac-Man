package pacman.behaviours;

import pacman.behaviours.ghosts.GhostBehaviour;
import pacman.world.World;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;

public class RandomBehaviour extends GhostBehaviour {
	
	public void reset() {
		heading = Direction.LEFT;
	}

	protected Coordinate getScatterTarget(World world) {
		return null;
	}

	protected Coordinate getChaseTarget(World world) {
		return null;
	}
}
