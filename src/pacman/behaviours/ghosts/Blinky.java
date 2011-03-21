package pacman.behaviours.ghosts;

import java.awt.Point;

import pacman.world.World;
import pacman.world.maps.Direction;

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
	
	protected Point getChaseTarget(World world) {
		return world.getPacman().getCurrentTile();
	}
	
	private Point scatterTarget = new Point(25, 0);
	protected Point getScatterTarget(World world) {
		return scatterTarget;
	}
}
