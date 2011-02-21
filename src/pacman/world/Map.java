package pacman.world;

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
				// create out-game tile
				break;
			case 'w':
				// create wall
				break;
			case 'd':
				// create navigable tile with dot
				break;
			case 'e':
				// create navigable tile with energizer
				break;
			case 'n':
				// create navigable tile
				break;
			default:
				System.err.println("Error in input file: unexpected character.");
				System.exit(1);
			}
		}
	}
}
