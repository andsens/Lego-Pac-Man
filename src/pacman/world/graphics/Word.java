package pacman.world.graphics;

import java.awt.Point;

import javax.swing.JPanel;

public class Word extends JPanel {

	private static final long serialVersionUID = 1282809343771899182L;
	
	public Word(String word) {
		this(word, 2);
	}
	
	public Word(String word, int spacing) {
		word = word.toLowerCase();
		
		setSize(word.length()*(Character.width+spacing), Character.height);
		setLayout(null);
		setOpaque(false);
		
		for(int i = 0; i < word.length(); i++)
			add(new Character(new Point(i*(Character.width+spacing), 0), word.charAt(i)));
		validate();
	}
}
