package pacman.world.tiles;

import pacman.world.graphics.Graphic;
import pacman.world.maps.Coordinate;


/**
 * A tile of the world. It can be part of a wall, completely blank
 * or extended by another class to become special tile, like the spawning tile.
 * <a href="../../../Pac-Man Dossier/index.html#CH3_What_Tile_Am_I_In">More about tiles</a>.
 * 
 * @author andsens
 * 
 */
public abstract class Tile extends Graphic {
	
	private static final long serialVersionUID = 3377638094038644024L;
	
	public static int width = 12;
	public static int height = 12;
	
	public Tile(Coordinate coordinate) {
		super(coordinate);
		setSize(width, height);
	}
}
