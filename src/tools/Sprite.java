package tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private BufferedImage image;
	private int tileSize;
	private int renderSize;

	public Sprite(String fileName) throws IOException {
		image = ImageIO.read(new File(fileName));
		tileSize = 12;
		renderSize = 16;
	}

	public void draw(int x, int y, Graphics g) {
		g.drawImage(image, 0, 0, renderSize, renderSize, x * tileSize, y * tileSize, (x + 1) * tileSize, (y + 1) * tileSize, null);
	}
}
