package pacman.world.graphics.status;

import java.awt.Point;

import javax.swing.JPanel;

import pacman.world.graphics.Character;

public class StatusText extends JPanel {
	
	private static final long serialVersionUID = -3715739099555370962L;

	public StatusText(String word) {
		word = word.toLowerCase();
		
		setSize(word.length()*(Character.width), Character.height);
		setLayout(null);
		setOpaque(false);
		
		for(int i = 0; i < word.length(); i++)
			add(new Character(new Point(i*(Character.width), 0), word.charAt(i)));
		validate();
	}
}
