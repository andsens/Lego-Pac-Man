package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.behaviours.PacmanBehaviour;
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
	
	protected PacmanBehaviour behaviour;
	
	public Pacman(Coordinate coordinate, PacmanBehaviour behaviour) {
		super(coordinate);
		this.behaviour = behaviour;
		this.behaviour.setEntity(this);
		coordinate.translate(1, -5);
		setLocation(coordinate);
		spriteTile = new Point(0, 16);
		
	}

	protected void think(World world) {
		Point location = getLocation();
		location.translate(width/2, height/2);
		behaviour.think(world);
	}
	
	protected void move() {
		Direction heading = getBehaviour().getHeading();
		if(heading == Direction.NONE)
			return;
		Point location = getLocation();
		location.translate(width/2, height/2);
		int modX = location.x % Tile.width;
		int modY = location.y % Tile.height;
		switch(heading) {
		case UP:
		case DOWN:
			if(modX != Tile.width / 2)
				break;
			super.move();
			return;
		case LEFT:
		case RIGHT:
			if(modY != Tile.height / 2)
				break;
			super.move();
			return;
		}

		Point destination = getLocation();
		switch(heading) {
		case UP:
			if(modX > Tile.width / 2)
				Direction.UPLEFT.translate(destination);
			else
				Direction.UPRIGHT.translate(destination);
			break;
		case DOWN:
			if(modX > Tile.width / 2)
				Direction.DOWNLEFT.translate(destination);
			else
				Direction.DOWNRIGHT.translate(destination);
			break;
		case LEFT:
			if(modY > Tile.height / 2)
				Direction.UPLEFT.translate(destination);
			else
				Direction.DOWNLEFT.translate(destination);
			break;
		case RIGHT:
			if(modY > Tile.height / 2)
				Direction.UPRIGHT.translate(destination);
			else
				Direction.DOWNRIGHT.translate(destination);
			break;
		default:
		}
		setLocation(destination);
		
	}
	
	int dotEaten = 0;
	protected void act(World world) {
		Point location = getLocation();
		location.translate(width/2, height/2);
		Dot dot = world.eatDot(location);
		if(dot != null) {
			dotEaten = 6;
			if(Energizer.class.isInstance(dot))
				world.energize();
		} else {
			if(dotEaten > 0)
				dotEaten--;
		}
	}
	
	protected void animate() {
		Direction heading = behaviour.getHeading();
		if(heading == Direction.NONE)
			return;
		switch(heading) {
		case UP:
			if(dotEaten > 0)
				spriteTile = new Point(6, 22);
			else
				spriteTile = new Point(2, 22);
			break;
		case DOWN:
			if(dotEaten > 0)
				spriteTile = new Point(30, 14);
			else
				spriteTile = new Point(26, 14);
			break;
		case LEFT:
			if(dotEaten > 0)
				spriteTile = new Point(4, 22);
			else
				spriteTile = new Point(0, 22);
			break;
		case RIGHT:
			if(dotEaten > 0)
				spriteTile = new Point(28, 14);
			else
				spriteTile = new Point(24, 14);
			break;
		}
	}
	
	protected int getSpeed() {
		return 80;
	}
	
	public void energize() {
	}
	
	protected Behaviour getBehaviour() {
		return behaviour;
	}
}
