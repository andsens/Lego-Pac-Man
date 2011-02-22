package pacman.world;

import java.awt.Container;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * A map detailing what the maze/world looks like.
 * 
 * @author andsens, pchatelain
 * 
 */
public class Map extends JFrame {

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
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container content = this.getContentPane();
		FileReader fileReader = new FileReader(map);
		int i = 0;
		int j = 1;
		int character = fileReader.read();
		while (!(character == -1)) {
			switch (character) {
			case 10:
				if ((j > 1) && !(i == height)) {
					System.err.println("Error in input file: map is not rectangular.");
					System.exit(1);
				}
				height = i;
				i = 0;
				j++;
				break;
			case 'o':
			case 'w':
			case 'd':
			case 'e':
			case 'n':
			case 'g':
				i++;
				content.add(new Tile(character, i, j));
				break;
			default:
				System.err.println("Error in input file: unexpected character.");
				System.exit(1);
			}
			character = fileReader.read();
		}
		width = j;
		this.setTitle("Pac-Man control screen");
		this.setSize(16 * height, 16 * width);
		this.setResizable(false);
		content.setLayout(new GridLayout(width, height, 0, 0));
		this.validate();
		content.setVisible(true);
		this.setVisible(true);
		content.paintComponents(content.getGraphics());
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
