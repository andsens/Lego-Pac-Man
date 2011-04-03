package pacman.behaviours;

import pacman.behaviours.ghosts.GhostBehaviour;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;

public class RandomBehaviour extends GhostBehaviour {
	
	private int dotLimit = 0;
	public void reset() {
		resetHeading();
		dotLimit = (int) Math.random()*60;
	}

	protected Coordinate getScatterTarget() {
		return null;
	}

	protected Coordinate getChaseTarget() {
		return null;
	}

	protected int getDotLimit(int level) {
		return dotLimit;
	}

	protected void resetHeading() {
		heading = Direction.LEFT;
	}
}
