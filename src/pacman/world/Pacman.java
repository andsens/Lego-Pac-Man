package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;

/**
 * Represents Pac-man in the level.
 * 
 * @author andsens
 * 
 */
public class Pacman extends MovingEntity {

	private static final long serialVersionUID = 4731453043247286964L;
	
	
	public Pacman(Coordinate coordinate, Behaviour behaviour) {
		super(coordinate, behaviour);
		coordinate.translate(1, -5);
		setLocation(coordinate);
		spriteTile = new Point(0, 16);
		
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
		return world.isValidPacmanLocation(location);
	}
	
	protected int getSpeed() {
		return 80;
	}
	
	protected void animate() {
		
	}
}
