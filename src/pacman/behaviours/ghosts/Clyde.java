package pacman.behaviours.ghosts;

import java.awt.Point;

import pacman.world.World;
import pacman.world.maps.Direction;

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
		return world.getPacman().getCurrentTile();
	}
	
	private Point scatterTarget = new Point(0, 34);
	protected Point getScatterTarget(World world) {
		return scatterTarget;
	}
}
