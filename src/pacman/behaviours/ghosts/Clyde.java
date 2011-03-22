package pacman.behaviours.ghosts;

import java.awt.Point;

import pacman.world.MovingEntity;
import pacman.world.World;
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
	
	protected Point getChaseTarget(World world) {
		Point pacmanTile = world.getMovingEntity(Type.PACMAN).getCurrentTile();
		double distance = entity.getCurrentTile().distance(pacmanTile);
		if(distance >= 8)
			return pacmanTile;
		else
			return scatterTarget;
	}
	
	private Point scatterTarget = new Point(0, 34);
	protected Point getScatterTarget(World world) {
		return scatterTarget;
	}
}
