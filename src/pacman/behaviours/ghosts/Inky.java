package pacman.behaviours.ghosts;

import java.awt.Point;

import pacman.world.MovingEntity;
import pacman.world.World;
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
	
	protected Point getChaseTarget(World world) {
		MovingEntity pacman = world.getMovingEntity(Type.PACMAN);
		MovingEntity blinky = world.getMovingEntity(Type.BLINKY);
		
		Point targetTile = pacman.getCurrentTile();
		heading.translate(targetTile, 2);
		if(heading == Direction.UP) // Simulate the buffer overflow from the original
			heading.turn().translate(targetTile, 2);
		
		Point blinkyTile = blinky.getCurrentTile();
		int vectorX = targetTile.x-blinkyTile.x;
		int vectorY = targetTile.y-blinkyTile.y;
		
		targetTile.translate(vectorX, vectorY);
		world.capTileLocation(targetTile);
		return targetTile;
	}
	
	private Point scatterTarget = new Point(27, 34);
	protected Point getScatterTarget(World world) {
		return scatterTarget;
	}
}
