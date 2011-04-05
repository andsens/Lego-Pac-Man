package pacman.behaviours.ghosts;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import pacman.behaviours.Behaviour;
import pacman.world.GhostMode;
import pacman.world.maps.Coordinate;
import pacman.world.maps.Direction;


public abstract class GhostBehaviour extends Behaviour {

	public GhostBehaviour() {
		resetHeading();
	}
	
	private boolean reverseHeading = false;
	private Direction nextHeading = heading;
	public void think() {
		Coordinate currentTile = entity.getCurrentTile();
		Coordinate targetTile = null;
		boolean caged = isCaged();
		if(dead) {
			targetTile = getGhostHouseTarget();
			if(targetTile.equals(entity.getCurrentTile())) {
				dead = false;
				resetHeading();
			}
		}
		if(caged && !dead && !jailbreaking) {
			targetTile = getGhostHouseTarget();
			if(!isGhostHouse(heading.getNext(currentTile)))
				heading = heading.reverse();
			return;
		}
		if(jailbreaking) {
			targetTile = getGhostHouseEntrance();
			if(targetTile.equals(entity.getCurrentTile())) {
				jailbreaking = false;
				heading = Direction.LEFT;
			}
		}
		if(!dead && !caged) {
			GhostMode mode = getGhostMode();
			if(mode == GhostMode.SCATTER)
				targetTile = getScatterTarget();
			if(mode == GhostMode.CHASE)
				targetTile = getChaseTarget();
			if(reverseHeading) {
				nextHeading = heading.reverse();
				reverseHeading = false;
			}
		}
		
		heading = nextHeading;
		
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
		if(valid(heading.getNext(nextTile))
		&& isRedZone(nextTile)
		&& !frightened) {
			options.remove(heading.turn(true));
			options.remove(heading.turn(false));
		}
		
		Collection<Direction> removals = new LinkedList<Direction>();
		for(Direction option : options) {
			if(!valid(option.getNext(nextTile)))
				removals.add(option);
		}
		for(Direction removal : removals)
			options.remove(removal);

		if(options.size() == 0) {
			System.err.println("No options left to move to.");
			nextHeading = Direction.NONE;
		} else {
			if(!isFrightened()) {
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
				switch(frightenedDirections.nextInt(3)) {
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
	
	private boolean valid(Coordinate coordinate) {
		if(dead || jailbreaking)
			return isNavigable(coordinate) || isGhostHouse(coordinate) || isGhostHouseGate(coordinate);
		if(isCaged())
			return isGhostHouse(coordinate);
		return isNavigable(coordinate);
	}
	
	private boolean frightened = false;
	public void frighten() {
		if(!dead && !isCaged())
			frightened = true;
		reverseHeading = true;
	}
	
	public void reverseHeading() {
		if(!frightened)
			reverseHeading = true;
	}
	
	public void unfrighten() {
		frightened = false;
	}
	
	public boolean isFrightened() {
		return frightened;
	}
	
	boolean dead = false;
	public boolean isDead() {
		return dead;
	}
	
	public boolean isCaged() {
		Coordinate currentTile = entity.getCurrentTile();
		return isGhostHouse(currentTile) || isGhostHouseGate(currentTile);
	}
	
	private boolean jailbreaking = false;
	public boolean jailbreak() {
		if(!isCaged())
			return false;
		frightened = false;
		jailbreaking = true;
		return true;
	}
	
	public void die() {
		frightened = false;
		dead = true;
		jailbreaking = false;
	}
	
	Random frightenedDirections = new Random();
	public void reset() {
		frightenedDirections.setSeed(13465227);
		resetHeading();
		reverseHeading = false;
		nextHeading = heading;
		frightened = false;
		dead = false;
		jailbreaking = false;
	}
	
	private int dotCount = 0;
	public boolean countDot(int level) {
		if(!isGhostHouse(entity.getCurrentTile()))
			return false;
		int dotLimit = 0;
		if(level < 3)
			dotLimit = getDotLimit(level);
		if(++dotCount > dotLimit)
			jailbreak();
		return true;
	}
	
	protected abstract Coordinate getScatterTarget();
	
	protected abstract Coordinate getChaseTarget();
	
	protected abstract Coordinate getGhostHouseTarget();
	
	protected abstract int getDotLimit(int level);
	
	protected abstract void resetHeading();
}
