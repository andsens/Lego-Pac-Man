package pacman.world;

import java.awt.Point;

import pacman.behaviours.Behaviour;
import pacman.behaviours.ghosts.GhostBehaviour;
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
	
	public Ghost(Point location, GhostBehaviour behaviour, Type type) {
		super(location);
		this.behaviour = behaviour;
		this.behaviour.setEntity(this);
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
	
	protected Point getOffset() {
		return new Point(-10, -5);
	}
	
	protected void think(World world) {
		Point location = getLocation();
		location.translate(width/2, height/2);
		if(location.x % Tile.width == Tile.width / 2
		&& location.y % Tile.height == Tile.height / 2)
			behaviour.think(world);
	}
	
	public void frighten() {
		behaviour.frighten();
	}
	
	protected void act(World world) {
	}
	
	int wiggle = 0;
	int waggle = 0;
	protected void animate(World world) {
		if(++wiggle > 4) {
			wiggle = 0;
			waggle = waggle == 0 ? 2 : 0;
		}
		long energizerLeft = world.getEnergizerLeft();
		if(energizerLeft > 0
		&& !(energizerLeft <= 2000 && energizerLeft % 400 > 199)) {
			spriteTile = new Point(24 + waggle, 12);
			return;
		}
		Direction heading = getBehaviour().getHeading();
		if(heading == Direction.NONE)
			return;
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
	
	protected int getSpeed(World world) {
		int level = world.getLevel();
		GhostMode mode = world.getGhostMode();
		if(level == 1) {
			if(mode == GhostMode.FRIGHTENED)
				return 50;
			return 75;
		}
		if(level <= 4) {
			if(mode == GhostMode.FRIGHTENED)
				return 55;
			return 85;
		}
		if(mode == GhostMode.FRIGHTENED)
			return 60;
		return 95;
	}
	
	protected Behaviour getBehaviour() {
		return behaviour;
	}
}
