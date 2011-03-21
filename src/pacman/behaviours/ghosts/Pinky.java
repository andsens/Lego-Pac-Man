package pacman.behaviours.ghosts;

import java.awt.Point;

import pacman.world.World;
import pacman.world.maps.Direction;

/**
 * Pinky is the pink ghost. See a description of Pinky's behaviour <a href="../../../Ghost Behaviour/index.htm#Pinky">here</a>.
 * Basically it behaves like this <img src="../../../Ghost Behaviour/files/pinky-targeting.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class Pinky extends GhostBehaviour {

	public Pinky() {
		heading = Direction.LEFT;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
	
	protected Point getChaseTarget(World world) {
		return world.getPacman().getCurrentTile();
	}
	
	private Point scatterTarget = new Point(2, 0);
	protected Point getScatterTarget(World world) {
		return scatterTarget;
	}
}
