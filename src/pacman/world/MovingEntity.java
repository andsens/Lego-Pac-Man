package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;


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

	private Point speed;

	public MovingEntity(Behaviour behaviour) {

	}

	public void update() {
		super.getPosition().translate(speed.x, speed.y);
	}
}
