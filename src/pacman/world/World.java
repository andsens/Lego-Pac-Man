package pacman.world;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import pacman.world.graphics.Graphic;
import pacman.world.graphics.Sprite;
import pacman.world.tiles.Tile;
import pacman.world.tiles.Type;


/**
 * World is the entire game board and the main class holding everything in the game.
 * <img src="../../../Pac-Man Dossier/files/lvl1.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class World extends JFrame {

	private static final long serialVersionUID = -7706020584659519598L;
	private Map map;
	private WallMap wallMap;
	private DotMap dotMap;
	private Vector<Entity> entities;

	public World() throws IOException {
		map = new Map(new File("./maps/classic.txt"));
		
		Sprite sprite = new Sprite("sprite.png");
		Graphic.setSprite(sprite);
		
		setTitle("Pac-Man control screen");
		setSize(Tile.width * 28, Tile.height * 36);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(ImageIO.read(new File("icon.png")));
		setResizable(false);
		
		Container layers = getLayeredPane();

		wallMap = new WallMap(map, sprite);
		layers.add(wallMap, new Integer(1));
		
		dotMap = new DotMap(map, sprite);
		layers.add(dotMap, new Integer(2));

		setVisible(true);
		layers.paintComponents(layers.getGraphics());
	}

	public void update() {
		Container layers = getLayeredPane();
		layers.update(layers.getGraphics());
		// for (Entity entity : entities) {
		// entity.update();
		// }
	}
}
