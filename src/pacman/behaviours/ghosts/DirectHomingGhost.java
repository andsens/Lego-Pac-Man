package pacman.behaviours.ghosts;

import java.awt.Point;

import pacman.world.World;
import pacman.world.maps.Direction;
import pacman.world.maps.Type;

public class DirectHomingGhost extends GhostBehaviour {
	
	public DirectHomingGhost() {
		heading = Direction.LEFT;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
	
	public Point getChaseTarget(World world) {
		return world.getMovingEntity(Type.PACMAN).getCurrentTile();
	}
	
	private Point scatterTarget = new Point(13, 0);
	protected Point getScatterTarget(World world) {
		return scatterTarget;
	}
}
