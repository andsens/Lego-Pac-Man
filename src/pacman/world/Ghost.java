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
		return new Point(-5, -5);
	}
	
	protected void think() {
		if(behaviour.isFrightened() && world.getEnergizerLeft() == 0)
			behaviour.unfrighten();
		Point location = getLocation();
		location.translate(width/2, height/2);
		if((location.x % Tile.width == Tile.width / 2
		&& location.y % Tile.height == Tile.height / 2))
			behaviour.think();
	}
	
	public void frighten() {
		behaviour.frighten();
	}
	
	protected void act() {
	}
	
	int wiggle = 0;
	int waggle = 0;
	protected void animate() {
		if(++wiggle > 4) {
			wiggle = 0;
			waggle = waggle == 0 ? 2 : 0;
		}

		if(behaviour.isFrightened()) {
			int energizerLeft = world.getEnergizerLeft();
			int blink = energizerLeft % 40;
			if(energizerLeft <= 2*World.ticksPerSecond && blink < 20)
				spriteTile = new Point(8 + waggle, 22);
			else
				spriteTile = new Point(24 + waggle, 12);
			return;
		}
		Point baseTile;
		if(behaviour.isDead())
			baseTile = new Point(16, 20);
		else
			baseTile = this.baseTile;
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
	
	protected int getSpeed(int level) {
		if(behaviour.isDead())
			return 100;
		if(behaviour.isCaged())
			return 50;
		boolean frightened = behaviour.isFrightened();
		if(level == 1)
			return frightened ? 50 : 75;
		if(level <= 4)
			return frightened ? 55 : 85;
		return frightened ? 60 : 95;
	}
	
	protected Behaviour getBehaviour() {
		return behaviour;
	}
	
	public boolean countDot(int level) {
		return behaviour.countDot(level);
	}
	
	public boolean jailbreak() {
		return behaviour.jailbreak();
	}
	
	public void die() {
		behaviour.die();
	}
}
