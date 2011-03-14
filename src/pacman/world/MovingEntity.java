package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;


/**
 * A moving entity in the level. This is either Pac-man or one of the ghosts.
 * This entity type is controlled by a behvaiour.
 * 
 * @author andsens
 * 
 */
public abstract class MovingEntity extends Entity implements Changeable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5115554255945990447L;

	public static int width = 22;
	public static int height = 22;
	
	protected Behaviour behaviour;
	
	public MovingEntity(Coordinate coordinate, Behaviour behaviour) {
		super(coordinate);
		this.behaviour = behaviour;
		behaviour.setEntity(this);
		setSize(width, height);
	}

	public final void tick(World world) {
		boolean shouldMove = true;
		int speed = getSpeed();
		if(speed == 0) {
			shouldMove = false;
		} else if(speed < 100) {
			float rate = 100/(100-speed);
			int tickCount = world.getTickCount();
			if(tickCount % rate < 0.5
			|| (tickCount % rate == 0.5 && tickCount + 1 % rate < 0.5)) {
				shouldMove = false;
			}
		}
		if(shouldMove) {
			Direction move = getMove(world);
			Point location = getLocation();
			move.translate(location);
			setLocation(location);
		}
		animate();
	}
	
	protected Point getEdge(Direction side) {
		switch(side) {
		case UP:
			return new Point(11, 6);
		case LEFT:
			return new Point(6, 11);
		case DOWN:
			return new Point(11, 16);
		case RIGHT:
			return new Point(16, 11);
		case NONE:
		default:
			return new Point(11, 11);
		}
	}
	
	public void reset() {
		super.reset();
		behaviour.reset();
	}
	
	public abstract boolean canMove(World world, Direction direction);
	
	protected abstract Direction getMove(World world);
	
	protected abstract int getSpeed();
	
	protected abstract void animate();
}
