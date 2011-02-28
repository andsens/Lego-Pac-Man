package tools;

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

	private BufferedImage image;
	private int smallTileSize;
	private int bigTileSize;
	private int renderSize;
	private int xOffset;
	private int yOffset;

	public Sprite(String fileName) throws IOException {
		image = ImageIO.read(new File(fileName));
		smallTileSize = 12;
		bigTileSize = 24;
		renderSize = 12;
		xOffset = 4;
		yOffset = 5;
	}

	public void draw(int x, int y, Graphics g, SpriteSize size) {
		int tileSize;
		if (size == SpriteSize.BIG)
			tileSize = bigTileSize;
		else
			tileSize = smallTileSize;
		g.drawImage(image, 0, 0, renderSize, renderSize, xOffset + x * tileSize, yOffset + y * tileSize, xOffset + (x + 1) * tileSize, yOffset + (y + 1) * tileSize, null);
	}

	public enum SpriteSize {
		SMALL, BIG
	}

	public static class SpriteTester extends JPanel {

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
			sprite.draw(x, y, g, SpriteSize.BIG);
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
