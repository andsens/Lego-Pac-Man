package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;
import pacman.world.maps.Type;

/**
 * Represents a ghost in the level.
 * 
 * @author andsens
 * 
 */
public class Ghost extends MovingEntity {

	private static final long serialVersionUID = -6871946560948009615L;
	
	public Ghost(Coordinate coordinate, Behaviour behaviour, Type type) {
		super(coordinate, behaviour);
		coordinate.translate(-10, -5);
		setLocation(coordinate);
		switch(type) {
		case BLINKY:
			spriteTile = new Point(10, 14);
			break;
		case PINKY:
			spriteTile = new Point(6, 18);
			break;
		case INKY:
			spriteTile = new Point(30, 18);
			break;
		case CLYDE:
			spriteTile = new Point(6, 20);
			break;
		}
	}

	protected Direction getMove(World world) {
		return behaviour.getMove(world);
	}
	
	public boolean canMove(World world, Direction move) {
		if(move == Direction.NONE)
			return true;
		Point location = getLocation();
		location.translate(width/2, height/2);
		move.translate(location);
		return world.isValidGhostLocation(location);
	}
	
	protected int getSpeed() {
		return 75;
	}
	
	protected void act(World world) {
		
	}
	
	protected void animate() {
		
	}
}
