package pacman.behaviours.ghosts;

import pacman.behaviours.Behaviour;
import pacman.world.World;
import pacman.world.maps.Direction;

public class StationaryBehaviour extends GhostBehaviour {

	Direction heading = Direction.NONE;
	public Direction getMove(World world) {
		return heading;
	}
	
	public void reset() {
		heading = Direction.NONE;
	}
	
}
