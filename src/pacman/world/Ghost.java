package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.behaviours.Blinky;
import pacman.behaviours.Clyde;
import pacman.behaviours.Inky;
import pacman.behaviours.Pinky;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;

/**
 * Represents a ghost in the level.
 * 
 * @author andsens
 * 
 */
public class Ghost extends MovingEntity {

	private static final long serialVersionUID = -6871946560948009615L;
	
	public Ghost(Coordinate coordinate, Behaviour behaviour) {
		super(coordinate, behaviour);
		coordinate.translate(-10, -5);
		setLocation(coordinate);
		if(behaviour instanceof Blinky)
			spriteTile = new Point(10, 14);
		else if(behaviour instanceof Pinky)
			spriteTile = new Point(6, 18);
		else if(behaviour instanceof Inky)
			spriteTile = new Point(30, 18);
		else if(behaviour instanceof Clyde)
			spriteTile = new Point(6, 20);
		else
			spriteTile = new Point(10, 14);
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
	
	protected void animate() {
		
	}
}
