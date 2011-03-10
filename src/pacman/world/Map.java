package pacman.world;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

import pacman.world.graphics.Sprite;
import pacman.world.tiles.EmptyTile;
import pacman.world.tiles.GhostHouseTile;
import pacman.world.tiles.NavigableTile;
import pacman.world.tiles.Tile;
import pacman.world.tiles.WallTile;


/**
 * A map detailing what the maze/world looks like.
 * 
 * @author andsens, pchatelain
 * 
 */
public class Map extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7675531091587595873L;
	/**
	 * Classic is 28x36, explained in <a href="../../../Pac-Man Dossier/index.html#Chapter_3">Maze Logic 101</a>
	 */
	private int width;
	private int height;

	public Map(File map) throws IOException {
		setLayout(null);
		Sprite sprite = new Sprite("sprite.png");
		Tile.setSprite(sprite);
		FileReader fileReader = new FileReader(map);
		int x = 0;
		int y = 0;
		int character;
		main: while ((character = fileReader.read()) != -1) {
			if(character == '/') {
				while ((character = fileReader.read()) != 10)
					if(character == -1)
						break main;
			} else if(character == '\n') {
				if (y > 0 && x != width) {
					System.err.println("Error in input file: map is not rectangular.");
					System.exit(1);
				}
				width = x;
				x = 0;
				y++;
			} else {
				Tile tile = Tile.createTile(character);
				if(tile == null) {
					System.err.println("Error in input file: unexpected character '"+character+"'.");
					System.exit(1);
				}
				tile.setLocation(x * Tile.width, y * Tile.height);
				add(tile);
				x++;
			}
		}
		height = y;
		setSize(width * Tile.width, height * Tile.height);
		validate();
		setVisible(true);
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
