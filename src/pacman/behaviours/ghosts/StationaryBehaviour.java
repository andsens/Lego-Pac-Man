package pacman.behaviours.ghosts;

import java.awt.Point;

import pacman.world.World;
import pacman.world.maps.Direction;

public class StationaryBehaviour extends GhostBehaviour {

	public Direction getMove(World world) {
		return Direction.NONE;
	}
	
	public void reset() {
		heading = Direction.NONE;
	}

	protected Point getChaseTarget(World world) {
		return entity.getCurrentTile();
	}
	
	private Point scatterTarget = new Point(13, 0);
	protected Point getScatterTarget(World world) {
		return scatterTarget;
	}
	
}
