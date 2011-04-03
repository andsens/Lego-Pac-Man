package pacman.behaviours.ghosts;

import pacman.world.MovingEntity;
import pacman.world.maps.Coordinate;
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
		resetHeading();
	}
	
	public void resetHeading() {
		heading = Direction.DOWN;
	}
	
	protected Coordinate getChaseTarget() {
		MovingEntity pacman = getPacman();
		Direction heading = pacman.getHeading();
		Coordinate targetTile = pacman.getCurrentTile();
		heading.translate(targetTile, 4);
		if(heading == Direction.UP) // Simulate the buffer overflow from the original
			heading.turn().translate(targetTile, 4);
		capTileLocation(targetTile);
		return targetTile;
	}
	
	private Coordinate scatterTarget = new Coordinate(2, 0);
	protected Coordinate getScatterTarget() {
		return scatterTarget;
	}

	protected int getDotLimit(int level) {
		return 0;
	}
}
