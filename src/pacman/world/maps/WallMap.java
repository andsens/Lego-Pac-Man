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
	
	
	boolean[][] navigableTiles;
	boolean[][] ghostHouseTiles;
	public void populateMap(TypeMap map) {
		Type[][] typeMap = map.getTypeMap();
		navigableTiles = new boolean[map.getWidth()][map.getHeight()];
		ghostHouseTiles = new boolean[map.getWidth()][map.getHeight()];
		for(int x = 0; x < typeMap.length; x++) {
			for(int y = 0; y < typeMap[x].length; y++) {
				Coordinate coordinate = new Coordinate(x, y);
				coordinate.scale(Tile.width, Tile.height);
				Tile tile = typeMap[x][y].createTile(coordinate);
				navigableTiles[x][y] = NavigableTile.class.isInstance(tile);
				ghostHouseTiles[x][y] = GhostHouseTile.class.isInstance(tile);
				add(tile);
			}
		}
	}
	
	public boolean isValidPacmanLocation(Point location) {
		return isValidPacmanLocation(location, false);
	}
	
	public boolean isValidPacmanLocation(Point location, boolean plot) {
		int x = (int) Math.floor(location.x/Tile.width);
		int y = (int) Math.floor(location.y/Tile.height);
		if(!navigableTiles[x][y])
			return false;
		int modX = location.x % Tile.width;
		int modY = location.y % Tile.height;
		if(modX != Tile.width / 2) {
			int adjacentX = modX > Tile.width / 2 ? x+1 : x-1;
			if(!navigableTiles[adjacentX][y])
				return false;
		}
		if(modY != Tile.height / 2) {
			int adjacentY = modY > Tile.height / 2 ? y+1 : y-1;
			if(!navigableTiles[x][adjacentY])
				return false;
		}
		return true;
	}
	
	public boolean isValidGhostTile(Point tileLocation) {
		return navigableTiles[tileLocation.x][tileLocation.y] || ghostHouseTiles[tileLocation.x][tileLocation.y];
	}
	
	public boolean isValidGhostLocation(Point location) {
		int x = (int) Math.floor(location.x/Tile.width);
		int y = (int) Math.floor(location.y/Tile.height);
		
		if(!navigableTiles[x][y] && !ghostHouseTiles[x][y])
			return false;
		int modX = location.x % Tile.width;
		int modY = location.y % Tile.height;
		
		if(modX != Tile.width / 2
		&& modY != Tile.height / 2)
			return false;
		if(modX != Tile.width / 2) {
			int adjacentX = modX > Tile.width / 2 ? x+1 : x-1;
			if(!navigableTiles[adjacentX][y] && !ghostHouseTiles[adjacentX][y])
				return false;
		}
		if(modY != Tile.height / 2) {
			int adjacentY = modY > Tile.height / 2 ? y+1 : y-1;
			if(!navigableTiles[x][adjacentY] && !ghostHouseTiles[x][adjacentY])
				return false;
		}
		return true;
	}
}
