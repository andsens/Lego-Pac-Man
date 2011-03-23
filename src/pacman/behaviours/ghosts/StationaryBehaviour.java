package pacman.behaviours.ghosts;

import pacman.world.World;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;

public class StationaryBehaviour extends GhostBehaviour {

	public Direction getMove(World world) {
		return Direction.NONE;
	}
	
	public void reset() {
		heading = Direction.NONE;
	}

	protected Coordinate getChaseTarget(World world) {
		return entity.getCurrentTile();
	}
	
	protected Coordinate getScatterTarget(World world) {
		return entity.getCurrentTile();
	}
	
}
