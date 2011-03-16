package pacman.world.maps;

import javax.swing.JPanel;

import pacman.world.World;
import pacman.world.graphics.Graphic;
import pacman.world.tiles.Tile;

public abstract class Map<T extends Graphic> extends JPanel {
	
	private static final long serialVersionUID = -6028301353603450607L;

	public Map(TypeMap map) {
		setLayout(null);
		setOpaque(false);
		setSize(map.getWidth() * Tile.width, map.getHeight() * Tile.height);
	}
	
	protected final void populate(TypeMap map) {
		populateMap(map);
		validate();
		setVisible(true);
	}
	
	protected abstract void populateMap(TypeMap map);
	
	public void add(T graphic) {
		super.add(graphic);
	}
	
	public void tick(World world) {
		
	}
}
