package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.behaviours.ghosts.GhostBehaviour;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;
import pacman.world.maps.Type;
import pacman.world.tiles.Tile;

/**
 * Represents a ghost in the level.
 * 
 * @author andsens
 * 
 */
public class Ghost extends MovingEntity {

	private static final long serialVersionUID = -6871946560948009615L;
	
	protected GhostBehaviour behaviour;
	private Point baseTile;
	
	public Ghost(Coordinate coordinate, GhostBehaviour behaviour, Type type) {
		super(coordinate);
		this.behaviour = behaviour;
		this.behaviour.setEntity(this);
		coordinate.translate(-10, -5);
		setLocation(coordinate);
		switch(type) {
		case BLINKY:
			baseTile = new Point(0, 14);
			break;
		case PINKY:
			baseTile = new Point(0, 18);
			break;
		case INKY:
			baseTile = new Point(16, 18);
			break;
		case CLYDE:
			baseTile = new Point(0, 20);
			break;
		}
		spriteTile = baseTile;
	}
	
	protected void think(World world) {
		Point location = getLocation();
		location.translate(width/2, height/2);
		if(location.x % Tile.width == Tile.width / 2
		&& location.y % Tile.height == Tile.height / 2)
			behaviour.think(world);
	}
	
	protected void move() {
		super.move();
	}
	
	protected void act(World world) {
	}
	
	int wiggle = 0;
	int waggle = 0;
	protected void animate() {
		Direction heading = getBehaviour().getHeading();
		if(heading == Direction.NONE)
			return;
		if(++wiggle > 4) {
			wiggle = 0;
			waggle = waggle == 0 ? 2 : 0;
		}
		switch(heading) {
		case UP:
			spriteTile = new Point(baseTile.x + 12 + waggle, baseTile.y);
			break;
		case DOWN:
			spriteTile = new Point(baseTile.x +  4 + waggle, baseTile.y);
			break;
		case LEFT:
			spriteTile = new Point(baseTile.x +  8 + waggle, baseTile.y);
			break;
		case RIGHT:
			spriteTile = new Point(baseTile.x +  0 + waggle, baseTile.y);
			break;
		}
	}
	
	protected int getSpeed() {
		return behaviour.getSpeed();
	}
	
	protected Behaviour getBehaviour() {
		return behaviour;
	}
}
