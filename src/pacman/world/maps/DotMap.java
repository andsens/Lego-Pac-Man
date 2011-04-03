package pacman.world.maps;

import java.awt.Component;
import java.awt.Point;

import pacman.world.Dot;
import pacman.world.tiles.Tile;

public class DotMap extends Map<Dot> {
	
	private static final long serialVersionUID = 1270779768329741235L;
	
	public DotMap(TypeMap map) {
		super(map);
		populate(map);
	}
	
	public void populateMap(TypeMap map) {
		Type[][] typeMap = map.getTypeMap();
		for(int x = 0; x < typeMap.length; x++) {
			for(int y = 0; y < typeMap[x].length; y++) {
				Point location = new Point(x*Tile.width, y*Tile.height);
				Dot dot = typeMap[x][y].createDot(location);
				if(dot == null)
					continue;
				add(dot);
			}
		}
	}
	
	public void reset() {
		for(Component entity : getComponents())
			entity.setVisible(true);
		dotsEaten = 0;
	}
	
	private int dotsEaten = 0;
	public Dot eat(Coordinate coordinate) {
		Point location = new Point(coordinate.x*Tile.width, coordinate.y*Tile.height);
		Object object = getComponentAt(location);
		if(!Dot.class.isInstance(object))
			return null;
		Dot dot = (Dot) object;
		if(dot.isVisible()) {
			dot.setVisible(false);
			dotsEaten++;
			return dot;
		}
		return null;
	}
	
	public int getDotsEaten() {
		return dotsEaten;
	}
}
