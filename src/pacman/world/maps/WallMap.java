package pacman.world.maps;

import java.awt.Point;

import pacman.world.tiles.GhostHouseTile;
import pacman.world.tiles.NavigableTile;
import pacman.world.tiles.RedZoneTile;
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
	boolean[][] redZoneTiles;
	public void populateMap(TypeMap map) {
		Type[][] typeMap = map.getTypeMap();
		navigableTiles = new boolean[map.getWidth()][map.getHeight()];
		ghostHouseTiles = new boolean[map.getWidth()][map.getHeight()];
		redZoneTiles = new boolean[map.getWidth()][map.getHeight()];
		for(int x = 0; x < typeMap.length; x++) {
			for(int y = 0; y < typeMap[x].length; y++) {
				Point location = new Point(x*Tile.width, y*Tile.height);
				Tile tile = typeMap[x][y].createTile(location);
				navigableTiles[x][y] = NavigableTile.class.isInstance(tile);
				ghostHouseTiles[x][y] = GhostHouseTile.class.isInstance(tile);
				redZoneTiles[x][y] = RedZoneTile.class.isInstance(tile);
				add(tile);
			}
		}
	}
	
	public boolean isValidPacmanTile(Coordinate coordinate) {
		return navigableTiles[coordinate.x][coordinate.y];
	}
	
	public boolean isValidGhostTile(Coordinate coordinate) {
		return navigableTiles[coordinate.x][coordinate.y] || ghostHouseTiles[coordinate.x][coordinate.y];
	}
	
	public boolean isRedZoneTile(Coordinate coordinate) {
		return redZoneTiles[coordinate.x][coordinate.y];
	}
}
