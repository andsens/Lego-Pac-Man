package pacman.world.maps;

import java.awt.Point;
import java.util.HashMap;

import pacman.world.tiles.GhostHouseGateTile;
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

	private HashMap<Type, Coordinate> spawnPoints;
	public WallMap(TypeMap map) {
		super(map);
		spawnPoints = new HashMap<Type, Coordinate>();
		populate(map);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7675531091587595873L;
	/**
	 * Classic is 28x36, explained in <a href="../../../Pac-Man Dossier/index.html#Chapter_3">Maze Logic 101</a>
	 */
	
	
	private boolean[][] navigableTiles;
	private boolean[][] ghostHouseTiles;
	private boolean[][] ghostHouseGateTiles;
	private boolean[][] redZoneTiles;
	public void populateMap(TypeMap map) {
		Type[][] typeMap = map.getTypeMap();
		navigableTiles = new boolean[map.getWidth()][map.getHeight()];
		ghostHouseTiles = new boolean[map.getWidth()][map.getHeight()];
		ghostHouseGateTiles = new boolean[map.getWidth()][map.getHeight()];
		redZoneTiles = new boolean[map.getWidth()][map.getHeight()];
		for(int x = 0; x < typeMap.length; x++) {
			for(int y = 0; y < typeMap[x].length; y++) {
				Type type = typeMap[x][y];
				Point location = new Point(x*Tile.width, y*Tile.height);
				Tile tile = type.createTile(location);
				switch(type) {
				case BLINKY:
				case PINKY:
				case INKY:
				case CLYDE:
					spawnPoints.put(type, new Coordinate(x, y));
				}
				navigableTiles[x][y] = NavigableTile.class.isInstance(tile);
				ghostHouseTiles[x][y] = GhostHouseTile.class.isInstance(tile);
				if(GhostHouseGateTile.class.isInstance(tile))
					if(!((GhostHouseGateTile) tile).isOOB())
						ghostHouseGateTiles[x][y] = GhostHouseGateTile.class.isInstance(tile);
				redZoneTiles[x][y] = RedZoneTile.class.isInstance(tile);
				add(tile);
			}
		}
	}
	
	public boolean isNavigable(Coordinate coordinate) {
		return navigableTiles[coordinate.x][coordinate.y];
	}
	
	public boolean isGhostHouse(Coordinate coordinate) {
		return ghostHouseTiles[coordinate.x][coordinate.y];
	}
	
	public boolean isGhostHouseGate(Coordinate coordinate) {
		return ghostHouseGateTiles[coordinate.x][coordinate.y];
	}
	
	public boolean isRedZone(Coordinate coordinate) {
		return redZoneTiles[coordinate.x][coordinate.y];
	}
	
	public Coordinate getSpawnPoint(Type type) {
		return spawnPoints.get(type);
	}
	
	public Coordinate getGhostHouseEntrance() {
		return getSpawnPoint(Type.BLINKY);
	}
}
