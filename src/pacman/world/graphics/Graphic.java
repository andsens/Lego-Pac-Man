package pacman.world.graphics;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public abstract class Graphic extends JPanel {
	
	private static final long serialVersionUID = 3407930846614614333L;
	
	protected static Sprite sprite;
	
	protected Point spriteTile;
	
	public Graphic(Point location) {
		Point offset = getOffset();
		location.translate(offset.x, offset.y);
		setLocation(location);
	}
	
	protected Point getOffset() {
		return new Point(0, 0);
	}
	
	public void paintComponent(Graphics graphics) {
		sprite.draw(spriteTile.x, spriteTile.y, graphics, getWidth(), getHeight());
	}
	
	public static void setSprite(Sprite sprite) {
		Graphic.sprite = sprite;
	}
}
