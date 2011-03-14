package pacman.world;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import pacman.behaviours.BehaviourFactory;
import pacman.world.graphics.Graphic;
import pacman.world.graphics.Sprite;
import pacman.world.maps.DotMap;
import pacman.world.maps.MovingEntityMap;
import pacman.world.maps.TypeMap;
import pacman.world.maps.WallMap;
import pacman.world.tiles.Tile;


/**
 * World is the entire game board and the main class holding everything in the game.
 * <img src="../../../Pac-Man Dossier/files/lvl1.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class World extends JFrame {

	private static final long serialVersionUID = -7706020584659519598L;
	
	private TypeMap map;
	private WallMap wallMap;
	private DotMap dotMap;
	private MovingEntityMap movingEntityMap;
	
	private int tickCount = 0;

	public World(BehaviourFactory behaviours) throws IOException {
		map = new TypeMap(new File("./maps/classic.txt"));
		
		Sprite sprite = new Sprite("sprite.png");
		Graphic.setSprite(sprite);
		
		setTitle("Pac-Man control screen");
		setSize(Tile.width * map.getWidth(), Tile.height * map.getHeight() + 22);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(ImageIO.read(new File("icon.png")));
		setResizable(false);
		
		Container layers = getLayeredPane();

		wallMap = new WallMap(map);
		layers.add(wallMap, new Integer(1));
		
		dotMap = new DotMap(map);
		layers.add(dotMap, new Integer(2));
		
		movingEntityMap = new MovingEntityMap(map, behaviours);
		layers.add(movingEntityMap, new Integer(3));
		
		setVisible(true);
		layers.paintComponents(layers.getGraphics());
		
		if(KeyListener.class.isInstance(behaviours.getPacmanBehaviour()))
			addKeyListener((KeyListener) behaviours.getPacmanBehaviour());
	}
	
	public boolean isValidPacmanLocation(Point location) {
		return wallMap.isValidPacmanLocation(location);
	}
	
	public boolean isValidGhostLocation(Point location) {
		return wallMap.isValidGhostLocation(location);
	}

	public void tick() {
		if(++ tickCount > 100)
			tickCount = 1;
		movingEntityMap.tick(this);
		dotMap.tick(this);
	}
	
	public int getTickCount() {
		return tickCount;
	}
}
