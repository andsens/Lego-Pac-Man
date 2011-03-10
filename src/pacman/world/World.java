package pacman.world;

import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

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
	private Map map;
	private Vector<Entity> entities;

	public World() throws IOException {

		setTitle("Pac-Man control screen");
		setSize(Tile.width * 28, Tile.height * 36);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(ImageIO.read(new File("icon.png")));
		setResizable(false);

		Container layers = getLayeredPane();

		map = new Map(new File("./maps/classic.txt"));
		layers.add(map, new Integer(1));

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
