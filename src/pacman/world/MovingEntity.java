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

	private Point position;
	private Point speed;

	public MovingEntity(Behaviour behaviour) {

	}

	public void update() {
		position.translate(speed.x, speed.y);
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getPosition() {
		return position;
	}

	public void setSpeed(Point speed) {
		this.speed = speed;
	}

	public Point getSpeed() {
		return speed;
	}

}
