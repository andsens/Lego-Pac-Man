package pacman.behaviours.ghosts;

import java.awt.Point;

import pacman.world.World;
import pacman.world.maps.Direction;

/**
 * Inky is the blue ghost. See a description of Inky behaviour <a href="../../../Ghost Behaviour/index.htm#Inky">here</a>.
 * Basically it behaves like this <img src="../../../Ghost Behaviour/files/inky-targeting.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class Inky extends GhostBehaviour {

	public Inky() {
		heading = Direction.LEFT;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
	
	protected Point getChaseTarget(World world) {
		return world.getPacman().getCurrentTile();
	}
	
	private Point scatterTarget = new Point(27, 34);
	protected Point getScatterTarget(World world) {
		return scatterTarget;
	}
}
