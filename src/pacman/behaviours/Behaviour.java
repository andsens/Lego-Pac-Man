package pacman.behaviours;

import pacman.world.MovingEntity;
import pacman.world.World;
import pacman.world.maps.Direction;

/**
 * An abstract class for describing the common behaviour of all ghosts and the
 * player.
 * 
 * @author andsens
 * 
 */
public abstract class Behaviour {
	
	protected MovingEntity entity;
	public void setEntity(MovingEntity entity) {
		this.entity = entity;
	}
	
	public abstract void think(World world);
	
	protected Direction heading = Direction.NONE;
	public Direction getHeading() {
		return heading;
	}
	
	public void reset() {
		heading = Direction.NONE;
	}
	
}
