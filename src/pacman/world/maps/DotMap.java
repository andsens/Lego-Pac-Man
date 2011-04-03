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
	
	private Dot[][] dots;
	private int dotsTotal;
	public void populateMap(TypeMap map) {
		Type[][] typeMap = map.getTypeMap();
		dots = new Dot[map.getWidth()][map.getHeight()];
		for(int x = 0; x < typeMap.length; x++) {
			for(int y = 0; y < typeMap[x].length; y++) {
				Point location = new Point(x*Tile.width, y*Tile.height);
				Dot dot = typeMap[x][y].createDot(location);
				if(dot == null)
					continue;
				dots[x][y] = dot;
				dotsTotal++;
				add(dot);
			}
		}
	}
	
	public void reset() {
		for(Component entity : getComponents())
			entity.setVisible(true);
		dotsEaten = 0;
		dotsLeft = dotsTotal;
	}

	private int dotsEaten = 0;
	private int dotsLeft = 0;
	public Dot eat(Coordinate coordinate) {
		Dot dot = dots[coordinate.x][coordinate.y];
		if(dot == null)
			return null;
		if(dot.isVisible()) {
			dot.setVisible(false);
			dotsEaten++;
			dotsLeft--;
			return dot;
		}
		return null;
	}
	
	public int getDotsEaten() {
		return dotsEaten;
	}

	public int getDotsLeft() {
		return dotsLeft;
	}
}
