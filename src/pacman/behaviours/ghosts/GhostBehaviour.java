package pacman.behaviours.ghosts;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import pacman.behaviours.Behaviour;
import pacman.world.GhostMode;
import pacman.world.World;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;


public abstract class GhostBehaviour extends Behaviour {
	
	private Direction nextHeading = heading;
	private GhostMode previousMode = GhostMode.SCATTER;
	public void think(World world) {
		heading = nextHeading;
		GhostMode currentMode = world.getGhostMode();
		if(currentMode != previousMode) {
			if(previousMode != GhostMode.FRIGHTENED
			&& currentMode != GhostMode.FRIGHTENED) {
				nextHeading = heading.reverse();
				previousMode = currentMode;
				return;
			}
			previousMode = currentMode;
		}
		
		Coordinate currentTile = entity.getCurrentTile();
		
		Coordinate targetTile;
		if(currentMode == GhostMode.SCATTER)
			targetTile = getScatterTarget(world);
		else
			targetTile = getChaseTarget(world);
		
		Coordinate nextTile = heading.getNext(currentTile);
		
		List<Direction> options = new LinkedList<Direction>();
		options.add(Direction.UP);
		options.add(Direction.LEFT);
		options.add(Direction.DOWN);
		options.add(Direction.RIGHT);
		
		options.remove(heading.reverse());
		
		/*
		 * Implementation of the tile which disallows any upwards movement in the maze,
		 * when a ghost is not frightened. This is a generalized version,
		 * should one wish to use these tiles in a vertical fashion.
		 */
		if(valid(world, heading.getNext(nextTile))
		&& world.isRedZoneTile(nextTile)
		&& currentMode != GhostMode.FRIGHTENED) {
			options.remove(heading.turn(true));
			options.remove(heading.turn(false));
		}
		
		Collection<Direction> removals = new LinkedList<Direction>();
		for(Direction option : options) {
			if(!valid(world, option.getNext(nextTile)))
				removals.add(option);
		}
		for(Direction removal : removals)
			options.remove(removal);

		if(options.size() == 0) {
			System.err.println("No options left to move to.");
			nextHeading = Direction.NONE;
		} else {
			if(currentMode != GhostMode.FRIGHTENED) {
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
				switch(frightenedBehaviour.nextInt(3)) {
				case 0:
					nextHeading = Direction.UP;
					break;
				case 1:
					nextHeading = Direction.LEFT;
					break;
				case 2:
					nextHeading = Direction.DOWN;
					break;
				case 3:
					nextHeading = Direction.RIGHT;
					break;
				}
				if(!options.contains(nextHeading))
					nextHeading = options.get(0);
			}
		}
	}
	
	public void frighten() {
		nextHeading = heading.reverse();
	}
	
	protected boolean valid(World world, Coordinate location) {
		return world.isValidGhostTile(location);
	}
	
	Random frightenedBehaviour = new Random();
	public void reset() {
		frightenedBehaviour.setSeed(13465227);
		nextHeading = heading;
		previousMode = GhostMode.SCATTER;
	}
	
	protected abstract Coordinate getScatterTarget(World world);
	
	protected abstract Coordinate getChaseTarget(World world);
}
