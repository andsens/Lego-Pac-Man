package pacman.world.graphics;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Sprite {
	
	private static final int minWidth = 12;
	private static final int minHeight = 12;
	private static final int xOffset = 4;
	private static final int yOffset = 5;
	
	private BufferedImage image;
	
	public Sprite(String fileName) throws IOException {
		image = ImageIO.read(new File(fileName));
	}

	public void draw(int x, int y, Graphics graphics, int width, int height) {
		graphics.drawImage(image,
				0, 0, width, height,
				xOffset + x * minWidth, yOffset + y * minHeight,
				xOffset + x * minWidth + width, yOffset + y * minHeight + height, null);
	}
	
	public static class SpriteTester extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7687305393466892866L;
		private Sprite sprite;
		private int x;
		private int y;

		public SpriteTester(Sprite sprite, int x, int y) {
			this.setPreferredSize(new Dimension(48, 48));
			this.sprite = sprite;
			this.x = x;
			this.y = y;
		}

		public void paintComponent(Graphics g) {
			sprite.draw(x, y, g, 24, 24);
		}
	}

	public static void main(String[] args) {
		try {
			Sprite sprite = new Sprite("sprite.png");
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container content = frame.getContentPane();
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 16; j++) {
					content.add(new SpriteTester(sprite, j, i));
				}
			}
			content.setLayout(new GridLayout(12, 16, 0, 0));
			frame.setSize(500, 300);
			frame.validate();
			content.setVisible(true);
			frame.setVisible(true);
			content.paintComponents(content.getGraphics());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
