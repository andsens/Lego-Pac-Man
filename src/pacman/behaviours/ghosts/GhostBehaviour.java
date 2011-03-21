package pacman.behaviours.ghosts;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;

import pacman.behaviours.Behaviour;
import pacman.world.GhostMode;
import pacman.world.World;
import pacman.world.maps.Direction;


public abstract class GhostBehaviour extends Behaviour {

	protected GhostMode currentMode = GhostMode.SCATTER;
	protected GhostMode nextMode = GhostMode.SCATTER;
	public void changeMode(GhostMode newMode) {
		this.nextMode = newMode;
	}
	
	protected Direction nextHeading = Direction.LEFT;
	public void think(World world) {
		if(currentMode != nextMode) {
			if(currentMode != GhostMode.FRIGHTENED
			|| nextMode == GhostMode.FRIGHTENED)
				heading = heading.reverse();
			currentMode = nextMode;
		}
		heading = nextHeading;
		
		Point currentTile = entity.getCurrentTile();
		
		Point targetTile = getTarget(world);
		
		Point nextTile = heading.getNext(currentTile);
		
		Collection<Direction> options = new LinkedList<Direction>();
		options.add(Direction.UP);
		options.add(Direction.LEFT);
		options.add(Direction.DOWN);
		options.add(Direction.RIGHT);
		
		options.remove(heading.reverse());
		
		Collection<Direction> removals = new LinkedList<Direction>();
		for(Direction option : options) {
			if(!valid(world, option.getNext(nextTile)))
				removals.add(option);
		}
		for(Direction removal : removals)
			options.remove(removal);
		
		if(options.size() > 0) {
			double minDistance = -1;
			for(Direction option : options) {
				double distance = option.getNext(nextTile).distance(targetTile);
				if(minDistance == -1) {
					minDistance = distance;
					nextHeading = option;
				} else if(distance < minDistance) {
					minDistance = distance;
					nextHeading = option;
				}
			}
		} else {
			System.err.println("No options left to move to.");
			nextHeading = Direction.NONE;
		}
	}
	
	protected Point getTarget(World world) {
		switch(currentMode) {
		default:
		case SCATTER:
			return getScatterTarget(world);
		case CHASE:
			return getChaseTarget(world);
		case FRIGHTENED:
			return getFrightenedTarget();
		}
	}

	protected abstract Point getScatterTarget(World world);
	
	protected abstract Point getChaseTarget(World world);
	
	protected Point getFrightenedTarget() {
		return new Point(0, 0);
	}
	
	protected boolean valid(World world, Point location) {
		return world.isValidGhostTile(location);
	}
	
	public int getSpeed() {
		return 75;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
}
