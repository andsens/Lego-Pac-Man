package pacman.behaviours.ghosts;

import java.awt.Point;

import pacman.world.World;
import pacman.world.maps.Direction;

public class DirectHomingGhost extends GhostBehaviour {
	
	public DirectHomingGhost() {
		heading = Direction.LEFT;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
	
	public Point getChaseTarget(World world) {
		return world.getPacman().getCurrentTile();
	}
	
	private Point scatterTarget = new Point(13, 0);
	protected Point getScatterTarget(World world) {
		return scatterTarget;
	}
}
