package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;
import pacman.world.tiles.Tile;

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
	
	public boolean canMove(World world, Direction move, Point location) {
		if(move == Direction.NONE)
			return true;
		location = (Point) location.clone();
		move.translate(location);
		
		int modX = location.x % Tile.width;
		int modY = location.y % Tile.height;
		boolean upLeft = modX <= Tile.width / 2 && modY <= Tile.height / 2;
		boolean downLeft = modX <= Tile.width / 2 && modY >= Tile.height / 2;
		boolean downRight = modX >= Tile.width / 2 && modY >= Tile.height / 2;
		boolean upRight = modX >= Tile.width / 2 && modY <= Tile.height / 2;
		if(move.isDiagonal()) {
			if(!world.isValidPacmanLocation(location))
				return false;
			if(move == Direction.DOWNRIGHT
			&& ((modX == 0 && modY-1 == Tile.height / 2)
				|| (modY == 0 && modX-1 == Tile.width / 2))) {
				return false;
			}
			if((move == Direction.UPLEFT || move == Direction.DOWNRIGHT)
			&& !(downLeft || upRight))
				return false;
			if((move == Direction.UPRIGHT || move == Direction.DOWNLEFT)
			&& !(upLeft || downRight))
					return false;
			return true;
		} else {
			if(!world.isValidPacmanLocation(location))
				return false;
			if((move == Direction.UP || move == Direction.DOWN)
			&& modX != Tile.width / 2)
				return false;
			if((move == Direction.LEFT || move == Direction.RIGHT)
			&& modY != Tile.height / 2)
				return false;
			return true;
		}
	}
	
	protected int getSpeed() {
		return 80;
	}
	
	protected void act(World world) {
		Point location = getLocation();
		location.translate(width/2, height/2);
		Dot dot = world.eatDot(location);
	}
	
	protected void animate() {
	}

	public void energize() {
		
	}
}
