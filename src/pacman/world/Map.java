package pacman.world;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JPanel;

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
	private int height;
	private int width;

	public Map(File map) throws IOException {
		FileReader fileReader = new FileReader(map);
		int i = 1;
		int j = 1;
		for (int character = fileReader.read(); character == -1; character = fileReader.read()) {
			switch (character) {
			case 13:
				if ((j > 1) && !(i == height)) {
					System.err.println("Error in input file: map is not rectangular.");
					System.exit(1);
				}
				height = i - 1;
				i = 1;
				j++;
				break;
			case 'o':
			case 'w':
			case 'd':
			case 'e':
			case 'n':
				this.add(new Tile(character, i, j));
				break;
			default:
				System.err.println("Error in input file: unexpected character.");
				System.exit(1);
			}
		}
		width = j - 1;
		this.setLayout(new GridLayout(height, width, 0, 0));
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
