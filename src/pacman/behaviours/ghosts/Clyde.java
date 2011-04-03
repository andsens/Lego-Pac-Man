package pacman.behaviours.ghosts;

import pacman.world.maps.Coordinate;
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
		resetHeading();
	}
	
	public void resetHeading() {
		heading = Direction.UP;
	}
	
	protected Coordinate getChaseTarget() {
		Coordinate pacmanTile = getPacman().getCurrentTile();
		double distance = entity.getCurrentTile().distance(pacmanTile);
		if(distance >= 8)
			return pacmanTile;
		else
			return scatterTarget;
	}
	
	private Coordinate scatterTarget = new Coordinate(0, 34);
	protected Coordinate getScatterTarget() {
		return scatterTarget;
	}
	
	protected int getDotLimit(int level) {
		if(level == 1)
			return 60;
		return 50;
	}

	protected Coordinate getGhostHouseTarget() {
		return entity.getSpawnPoint();
	}
}
