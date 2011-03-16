package pacman.world.maps;

import java.awt.Point;

import pacman.world.tiles.GhostHouseTile;
import pacman.world.tiles.NavigableTile;
import pacman.world.tiles.Tile;


/**
 * A map detailing what the maze/world looks like.
 * 
 * @author andsens, pchatelain
 * 
 */
public class WallMap extends Map<Tile> {

	public WallMap(TypeMap map) {
		super(map);
		populate(map);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7675531091587595873L;
	/**
	 * Classic is 28x36, explained in <a href="../../../Pac-Man Dossier/index.html#Chapter_3">Maze Logic 101</a>
	 */

	public void populateMap(TypeMap map) {
		Type[][] typeMap = map.getTypeMap();
		for(int x = 0; x < typeMap.length; x++) {
			for(int y = 0; y < typeMap[x].length; y++) {
				Coordinate coordinate = new Coordinate(x, y);
				coordinate.scale(Tile.width, Tile.height);
				Tile tile = typeMap[x][y].createTile(coordinate);
				add(tile);
			}
		}
	}
	
	public boolean isValidPacmanLocation(Point location) {
		if(!isNavigable(location, false))
			return false;
		int modX = location.x % Tile.width;
		int modY = location.y % Tile.height;
		if(modX != Tile.width / 2) {
			int adjacentX = location.x + (modX > Tile.width / 2 ? Tile.width : -Tile.width);
			if(!isNavigable(adjacentX, location.y))
				return false;
		}
		if(location.y % Tile.height != Tile.height / 2) {
			int adjacentY = location.y + (modY > Tile.height / 2 ? Tile.height : -Tile.height);
			if(!isNavigable(location.x, adjacentY))
				return false;
		}
		return true;
	}
	
	public boolean isValidGhostLocation(Point location) {
		if(!isNavigable(location, true))
			return false;
		int modX = location.x % Tile.width;
		int modY = location.y % Tile.height;
		if(modX != Tile.width / 2
		&& modY != Tile.height / 2) {
				return false;
		}
		if(modX != Tile.width / 2) {
			int adjacentX = location.x + (modX > Tile.width / 2 ? Tile.width : -Tile.width);
			if(!isNavigable(adjacentX, location.y, true))
				return false;
		}
		if(modY != Tile.height / 2) {
			int adjacentY = location.y + (modY > Tile.height / 2 ? Tile.height : -Tile.height);
			if(!isNavigable(location.x, adjacentY, true))
				return false;
		}
		return true;
	}

	private boolean isNavigable(int x, int y) {
		return isNavigable(x, y, false);
	}
	private boolean isNavigable(Point location, boolean includeGhosthouse) {
		return isNavigable(location.x, location.y, includeGhosthouse);
	}
	
	private boolean isNavigable(int x, int y, boolean includeGhosthouse) {
		return
			NavigableTile.class.isInstance(getComponentAt(x, y))
		|| (includeGhosthouse && GhostHouseTile.class.isInstance(getComponentAt(x, y)));
	}
}
