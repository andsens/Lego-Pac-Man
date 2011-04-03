package pacman.behaviours.ghosts;

import pacman.world.maps.Coordinate;
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
		resetHeading();
	}
	
	public void resetHeading() {
		heading = Direction.LEFT;
	}
	
	protected Coordinate getChaseTarget() {
		return getPacman().getCurrentTile();
	}
	
	private Coordinate scatterTarget = new Coordinate(25, 0);
	protected Coordinate getScatterTarget() {
		return scatterTarget;
	}
	
	protected int getDotLimit(int level) {
		return 0;
	}
}
