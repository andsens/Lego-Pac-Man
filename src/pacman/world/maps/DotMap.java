package pacman.world.maps;

import java.awt.Component;
import java.awt.Point;

import pacman.world.Changeable;
import pacman.world.Dot;
import pacman.world.World;
import pacman.world.tiles.Tile;

public class DotMap extends Map<Dot> implements Changeable {
	
	private static final long serialVersionUID = 1270779768329741235L;
	
	public DotMap(TypeMap map) {
		super(map);
		populate(map);
	}
	
	public void populateMap(TypeMap map) {
		Type[][] typeMap = map.getTypeMap();
		for(int x = 0; x < typeMap.length; x++) {
			for(int y = 0; y < typeMap[x].length; y++) {
				Coordinate coordinate = new Coordinate(x, y);
				coordinate.scale(Tile.width, Tile.height);
				Dot dot = typeMap[x][y].createDot(coordinate);
				if(dot == null)
					continue;
				add(dot);
			}
		}
	}
	
	public void tick(World world) {
	}
	
	public void reset() {
		for(Component entity : getComponents())
			entity.setVisible(true);
	}
	
	public Dot eat(Point location) {
		Object object = getComponentAt(location);
		if(!Dot.class.isInstance(object))
			return null;
		Dot dot = (Dot) object;
		if(dot.isVisible()) {
			dot.setVisible(false);
			return dot;
		}
		return null;
	}
}
