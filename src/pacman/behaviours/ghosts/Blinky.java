package pacman.behaviours.ghosts;

import pacman.world.World;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;
import pacman.world.maps.Type;

/**
 * Blinky is the red ghost. See a description of Blinky's behaviour <a href="../../../Ghost Behaviour/index.htm#Blinky">here</a>.
 * Basically it behaves like this <img src="../../../Ghost Behaviour/files/blinky-targeting.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class Blinky extends GhostBehaviour {
	
	public Blinky() {
		heading = Direction.LEFT;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
	
	protected Coordinate getChaseTarget(World world) {
		return world.getMovingEntity(Type.PACMAN).getCurrentTile();
	}
	
	private Coordinate scatterTarget = new Coordinate(25, 0);
	protected Coordinate getScatterTarget(World world) {
		return scatterTarget;
	}
}
