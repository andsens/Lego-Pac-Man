package pacman.behaviours.ghosts;

import pacman.world.MovingEntity;
import pacman.world.World;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;
import pacman.world.maps.Type;

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
	
	protected Coordinate getChaseTarget(World world) {
		MovingEntity pacman = world.getMovingEntity(Type.PACMAN);
		MovingEntity blinky = world.getMovingEntity(Type.BLINKY);
		
		Coordinate targetTile = pacman.getCurrentTile();
		heading.translate(targetTile, 2);
		if(heading == Direction.UP) // Simulate the buffer overflow from the original
			heading.turn().translate(targetTile, 2);
		
		Coordinate blinkyTile = blinky.getCurrentTile();
		int vectorX = targetTile.x-blinkyTile.x;
		int vectorY = targetTile.y-blinkyTile.y;
		
		targetTile.translate(vectorX, vectorY);
		world.capTileLocation(targetTile);
		return targetTile;
	}
	
	private Coordinate scatterTarget = new Coordinate(27, 34);
	protected Coordinate getScatterTarget(World world) {
		return scatterTarget;
	}
}
