package pacman.behaviours.ghosts;

import pacman.world.MovingEntity;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;

/**
 * Inky is the blue ghost. See a description of Inky behaviour <a href="../../../Ghost Behaviour/index.htm#Inky">here</a>.
 * Basically it behaves like this <img src="../../../Ghost Behaviour/files/inky-targeting.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class Inky extends GhostBehaviour {

	public void resetHeading() {
		heading = Direction.UP;
	}
	
	protected Coordinate getChaseTarget() {
		MovingEntity pacman = getPacman();
		MovingEntity dependency = getBlinky();
		if(dependency == null)
			dependency = getPinky();
		if(dependency == null)
			dependency = getClyde();
		Coordinate targetTile = pacman.getCurrentTile();
		heading.translate(targetTile, 2);
		if(heading == Direction.UP) // Simulate the buffer overflow from the original
			heading.turn().translate(targetTile, 2);
		
		Coordinate dependencyTile = dependency.getCurrentTile();
		int vectorX = targetTile.x-dependencyTile.x;
		int vectorY = targetTile.y-dependencyTile.y;
		
		targetTile.translate(vectorX, vectorY);
		capTileLocation(targetTile);
		return targetTile;
	}
	
	private Coordinate scatterTarget = new Coordinate(27, 34);
	protected Coordinate getScatterTarget() {
		return scatterTarget;
	}

	protected int getDotLimit(int level) {
		if(level == 1)
			return 30;
		return 0;
	}

	protected Coordinate getGhostHouseTarget() {
		return entity.getSpawnPoint();
	}
}
