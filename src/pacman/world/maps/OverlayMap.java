package pacman.world.maps;

import java.awt.Component;
import java.awt.Point;

import pacman.world.Marker;
import pacman.world.tiles.Tile;


/**
 * A map detailing what the maze/world looks like.
 * 
 * @author andsens, pchatelain
 * 
 */
public class OverlayMap extends Map<Tile> {
	
	private static final long serialVersionUID = 7675531091587595873L;

	public OverlayMap(TypeMap map) {
		super(map);
		populate(map);
	}
	
	Marker[][] markers;
	public void populateMap(TypeMap map) {
		Type[][] typeMap = map.getTypeMap();
		markers = new Marker[map.getWidth()][map.getHeight()];
		for(int x = 0; x < typeMap.length; x++) {
			for(int y = 0; y < typeMap[x].length; y++) {
				Point location = new Point(x*Tile.width, y*Tile.height);
				Marker marker = new Marker(location);
				markers[x][y] = marker;
				marker.setVisible(false);
				add(marker);
			}
		}
	}
	
	public void markTile(Coordinate coordinate) {
		markers[coordinate.x][coordinate.y].setVisible(true);
	}
	
	public void reset() {
		for(Component entity : getComponents())
			entity.setVisible(false);
	}
}
