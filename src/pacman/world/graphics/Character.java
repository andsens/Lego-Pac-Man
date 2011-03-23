package pacman.world.graphics;

import java.awt.Point;

public class Character extends Graphic {
	
	private static final long serialVersionUID = 5651281522499383679L;
	
	public static final int width = 12;
	public static final int height = 12;
	
	public Character(Point location, char character) {
		super(location);
		switch(character) {
		case '0':
			spriteTile = new Point(0, 0);
			break;
		case '1':
			spriteTile = new Point(1, 0);
			break;
		case '2':
			spriteTile = new Point(2, 0);
			break;
		case '3':
			spriteTile = new Point(3, 0);
			break;
		case '4':
			spriteTile = new Point(4, 0);
			break;
		case '5':
			spriteTile = new Point(5, 0);
			break;
		case '6':
			spriteTile = new Point(6, 0);
			break;
		case '7':
			spriteTile = new Point(7, 0);
			break;
		case '8':
			spriteTile = new Point(8, 0);
			break;
		case '9':
			spriteTile = new Point(9, 0);
			break;
		case ' ':
			spriteTile = new Point(0, 2);
			break;
		case 'a':
			spriteTile = new Point(1, 2);
			break;
		case 'b':
			spriteTile = new Point(2, 2);
			break;
		case 'c':
			spriteTile = new Point(3, 2);
			break;
		case 'd':
			spriteTile = new Point(4, 2);
			break;
		case 'e':
			spriteTile = new Point(5, 2);
			break;
		case 'f':
			spriteTile = new Point(6, 2);
			break;
		case 'g':
			spriteTile = new Point(7, 2);
			break;
		case 'h':
			spriteTile = new Point(8, 2);
			break;
		case 'i':
			spriteTile = new Point(9, 2);
			break;
		case 'j':
			spriteTile = new Point(10, 2);
			break;
		case 'k':
			spriteTile = new Point(11, 2);
			break;
		case 'l':
			spriteTile = new Point(12, 2);
			break;
		case 'm':
			spriteTile = new Point(13, 2);
			break;
		case 'n':
			spriteTile = new Point(14, 2);
			break;
		case 'o':
			spriteTile = new Point(15, 2);
			break;
		case 'p':
			spriteTile = new Point(16, 2);
			break;
		case 'q':
			spriteTile = new Point(17, 2);
			break;
		case 'r':
			spriteTile = new Point(18, 2);
			break;
		case 's':
			spriteTile = new Point(19, 2);
			break;
		case 't':
			spriteTile = new Point(20, 2);
			break;
		case 'u':
			spriteTile = new Point(21, 2);
			break;
		case 'v':
			spriteTile = new Point(22, 2);
			break;
		case 'w':
			spriteTile = new Point(23, 2);
			break;
		case 'x':
			spriteTile = new Point(24, 2);
			break;
		case 'y':
			spriteTile = new Point(25, 2);
			break;
		case 'z':
			spriteTile = new Point(26, 2);
			break;
		case '!':
			spriteTile = new Point(27, 2);
			break;
		case '/':
			spriteTile = new Point(26, 1);
			break;
		case '-':
			spriteTile = new Point(27, 1);
			break;
		default:
			throw new RuntimeException("Character '"+character+"' not recognized.");
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
}
