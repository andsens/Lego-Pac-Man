package pacman.behaviours.ghosts;

import pacman.world.World;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;
import pacman.world.maps.Type;

/**
 * Clyde is the orange ghost. See a description of Clyde's behaviour <a href="../../../Ghost Behaviour/index.htm#Clyde">here</a>.
 * Basically it behaves like this <img src="../../../Ghost Behaviour/files/clyde-targeting.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class Clyde extends GhostBehaviour {

	public Clyde() {
		heading = Direction.LEFT;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
	
	protected Coordinate getChaseTarget(World world) {
		Coordinate pacmanTile = world.getMovingEntity(Type.PACMAN).getCurrentTile();
		double distance = entity.getCurrentTile().distance(pacmanTile);
		if(distance >= 8)
			return pacmanTile;
		else
			return scatterTarget;
	}
	
	private Coordinate scatterTarget = new Coordinate(0, 34);
	protected Coordinate getScatterTarget(World world) {
		return scatterTarget;
	}
}
