package pacman.world.graphics.status;

import java.awt.Point;

import javax.swing.JPanel;

public class LifeCounter extends JPanel {
	
	private static final long serialVersionUID = 5842389742903256368L;
	
	private static final int maxLivesDisplay = 4;
	
	private int lives;
	
	public LifeCounter() {
		setSize(maxLivesDisplay*(Life.width), Life.height);
		setLayout(null);
		setOpaque(false);
		reset();
	}
	
	public void oneUp() {
		lives++;
		render();
	}
	
	private void render() {
		removeAll();
		for(int i = 0; i < Math.min(maxLivesDisplay, lives); i++)
			add(new Life(new Point((maxLivesDisplay - i - 1) * Life.width, 0)));
		validate();
		repaint();
	}
	
	public void subtractLife() {
		lives--;
		render();
	}
	
	public int getLives() {
		return lives;
	}
	
	public void reset() {
		lives = 2;
		render();
	}
}
