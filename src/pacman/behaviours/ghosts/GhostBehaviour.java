package pacman.behaviours.ghosts;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;

import pacman.behaviours.Behaviour;
import pacman.world.MovingEntity;
import pacman.world.World;
import pacman.world.maps.Direction;
import pacman.world.tiles.Tile;


public abstract class GhostBehaviour extends Behaviour {

	protected Direction heading = Direction.LEFT;
	public Direction getMove(World world) {
		if(!canMove(world, heading)) {
			if(!canMove(world, heading.turn(false))) {
				if(!canMove(world, heading.turn(true))) {
					if(!canMove(world, heading.turn().turn())) {
						System.err.println("Ghost can't move anywhere. Heading is "+heading+".");
					} else {
						return heading = heading.turn().turn();
					}
				} else {
					return heading = heading.turn(true);
				}
			} else {
				if(!canMove(world, heading.turn(true))) {
					return heading = heading.turn(false);
				}
			}
		} else {
			if(!canMove(world, heading.turn(false))
			&& !canMove(world, heading.turn(true))) {
				return heading;
			}
		}
		return Direction.NONE;
	}
	
	private int best;
	private Collection<Point> visitedTiles;
	protected Direction getShortestPathDirection(World world, Direction heading, Point destinationTile) {
		Point locationTile = entity.getLocation();
		locationTile.translate(MovingEntity.width/2, MovingEntity.height/2);
		locationTile.x = (int) Math.floor(locationTile.x/Tile.width);
		locationTile.y = (int) Math.floor(locationTile.y/Tile.height);
		
		best = 54;
		visitedTiles = new LinkedList<Point>();
		Direction direction = Direction.NONE;
		
		int straight = getDistance(world, heading, locationTile, destinationTile, 0);
		if(straight != -1)
			direction = heading;
		
		int ccw = getDistance(world, heading.turn(false), locationTile, destinationTile, 0);
		if(ccw != -1)
			if(straight == -1 || ccw < straight)
				direction = heading.turn(false);
		
		int cw = getDistance(world, heading.turn(true), locationTile, destinationTile, 0);
		if(cw != -1)
			if(((straight != -1 || ccw != -1) && (cw < straight || cw < ccw))
			|| (straight == -1 && ccw == -1))
				direction = heading.turn(true);
		return direction;
	}
	
	private int getDistance(World world, Direction heading, Point startTile, Point destinationTile, int distance) {
		if(++distance >= best)
			return -1;
		startTile = (Point) startTile.clone();
		
		heading.translate(startTile);
		if(!world.isValidGhostTile(startTile))
			return -1;
		
		if(startTile.equals(destinationTile)) {
			if(distance < best)
				best = distance;
			return 0;
		}
		
		for(Point visited : visitedTiles)
			if(visited.equals(startTile))
				return -1;
		visitedTiles.add(startTile);
		
		int min = -1;
		
		int straight = getDistance(world, heading, startTile, destinationTile, distance);
		min = straight;
		
		int ccw = getDistance(world, heading.turn(false), startTile, destinationTile, distance);
		if(min == -1)
			min = ccw;
		else if(ccw != -1)
			min = Math.min(min, ccw);
		
		int cw = getDistance(world, heading.turn(true), startTile, destinationTile, distance);
		if(min == -1)
			min = cw;
		else if(cw != -1)
			min = Math.min(min, cw);
		
		if(min != -1)
			min++;
		
		visitedTiles.remove(startTile);
		return min;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
}
