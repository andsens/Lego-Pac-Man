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
	
	protected boolean canMove(World world, Direction direction) {
		return entity.canMove(world, direction);
	}
	
	public abstract Direction getMove(World world);

	public abstract void reset();
}
