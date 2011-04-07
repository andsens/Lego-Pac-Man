package pacman.world.graphics.status;

import java.awt.Point;

import javax.swing.JPanel;

import pacman.world.graphics.Character;

public class ScoreCounter extends JPanel {

	private static final long serialVersionUID = -936949527009924404L;
	
	private int score;
	
	public ScoreCounter() {
		setLayout(null);
		setOpaque(false);
		reset();
	}
	
	public void increaseScore(int by) {
		score += by;
		String scoreText = Integer.toString(score);
		if(scoreText.length() < 2)
			scoreText = "0"+scoreText;
		while(scoreText.length() < 6)
			scoreText = " "+scoreText;
		setSize(scoreText.length()*(Character.width), Character.height);
		removeAll();
		
		for(int i = 0; i < scoreText.length(); i++)
			add(new Character(new Point(i*(Character.width), 0), scoreText.charAt(i)));
		validate();
		repaint();
	}
	
	public int getScore() {
		return score;
	}
	
	public void reset() {
		score = 0;
		increaseScore(0);
	}
}
