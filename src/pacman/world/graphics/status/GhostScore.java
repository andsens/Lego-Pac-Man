package pacman.world.graphics.status;

import java.awt.Point;

import pacman.world.graphics.Graphic;

public class GhostScore extends Graphic {
	
	private static final long serialVersionUID = -194790976062157248L;

	public static int width = 22;
	public static int height = 22;
	
	public GhostScore(Point location, int offset) {
		super(location);
		setSize(width, height);
		spriteTile = new Point(16 + offset * 2, 14);
	}
	
	protected Point getOffset() {
		return new Point(-6, -4);
	}
	
	public void setLocation(Point location) {
		Point offset = getOffset();
		location.translate(offset.x, offset.y);
		super.setLocation(location);
	}
}
