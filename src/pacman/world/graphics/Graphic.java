package pacman.world.graphics;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

import pacman.world.maps.Coordinate;

public abstract class Graphic extends JPanel {
	
	private static final long serialVersionUID = 3407930846614614333L;
	
	protected static Sprite sprite;
	
	protected Point spriteTile;
	
	public Graphic(Coordinate coordinate) {
		setLocation(coordinate);
	}
	
	public void paintComponent(Graphics graphics) {
		sprite.draw(spriteTile.x, spriteTile.y, graphics, getWidth(), getHeight());
	}
	
	public static void setSprite(Sprite sprite) {
		Graphic.sprite = sprite;
	}
}
