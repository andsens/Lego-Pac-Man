package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.world.maps.Coordinate;


/**
 * A moving entity in the level. This is either Pac-man or one of the ghosts.
 * This entity type is controlled by a behvaiour.
 * 
 * @author andsens
 * 
 */
public class MovingEntity extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5115554255945990447L;

	public static int width = 22;
	public static int height = 22;

	private Point speed;

	public MovingEntity(Coordinate coordinate, Behaviour behaviour) {
		super(coordinate);
		setSize(width, height);
	}

	public void tick() {
//		super.getPosition().translate(speed.x, speed.y);
	}
}
