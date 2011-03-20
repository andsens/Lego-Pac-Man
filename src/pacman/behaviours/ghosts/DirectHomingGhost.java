package pacman.behaviours.ghosts;

import java.awt.Point;

import pacman.world.World;
import pacman.world.maps.Direction;
import pacman.world.tiles.Tile;

public class DirectHomingGhost extends GhostBehaviour {
	
	public Direction getMove(World world) {
		Direction move = super.getMove(world);
		if(move != Direction.NONE)
			return move;
		
		Point pacman = world.getPacmanLocation();
		pacman.x = (int) Math.floor(pacman.x/Tile.width);
		pacman.y = (int) Math.floor(pacman.y/Tile.height);
		Direction chase = Direction.NONE;
		if(world.isValidGhostTile(pacman)) {
			chase = getShortestPathDirection(world, heading, pacman);
			if(chase != Direction.NONE)
				heading = chase;
		} else {
			world.markTile(pacman);
			System.err.println(pacman+" is not a valid ghost location.");
		}
		return chase;
	}
	
	public void reset() {
		heading = Direction.LEFT;
	}
}
