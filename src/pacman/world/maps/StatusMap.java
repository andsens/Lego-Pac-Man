package pacman.world.maps;

import java.awt.Point;

import pacman.world.graphics.status.GhostScore;
import pacman.world.graphics.status.LifeCounter;
import pacman.world.graphics.status.ScoreCounter;
import pacman.world.graphics.status.Status;
import pacman.world.graphics.status.StatusText;
import pacman.world.tiles.Tile;

public class StatusMap extends Map<Status> {
	
	private static final long serialVersionUID = 7239870967929876784L;
	
	public StatusMap(TypeMap map) {
		super(map);
		populate(map);
	}

	StatusText oneUp = new StatusText("1UP");
	StatusText highScore = new StatusText("HIGH SCORE");
	ScoreCounter scoreCounter = new ScoreCounter();
	StatusText ready = new StatusText("READY!");
	LifeCounter lives = new LifeCounter();
	GhostScore[] ghostScores = new GhostScore[4];
	public void populateMap(TypeMap map) {
		oneUp.setLocation(new Point(3*Tile.width, 0*Tile.height));
		oneUp.setVisible(false);
		add(oneUp);
		
		highScore.setLocation(new Point(9*Tile.width, 0*Tile.height));
		add(highScore);
		
		scoreCounter.setLocation(new Point(1*Tile.width, 1*Tile.height));
		add(scoreCounter);
		
		ready.setLocation(new Point(11*Tile.width, 20*Tile.height));
		ready.setVisible(false);
		add(ready);
		
		lives.setLocation(new Point(2*Tile.width, (map.getHeight()-2)*Tile.height));
		add(lives);
		
		for(int i = 0; i < 4; i++) {
			ghostScores[i] = new GhostScore(new Point(0, 0), i);
			ghostScores[i].setVisible(false);
			add(ghostScores[i]);
		}
	}
	
	public void increaseScore(int by) {
		scoreCounter.increaseScore(by);
	}
	
	public int getScore() {
		return scoreCounter.getScore();
	}
	
	public void showReady() {
		ready.setVisible(true);
	}
	
	public void hideReady() {
		ready.setVisible(false);
	}
	
	public void showGhostScore(Coordinate coordinate, int offset) {
		Point location = new Point(coordinate.x*Tile.width, coordinate.y*Tile.height);
		ghostScores[offset].setLocation(location);
		ghostScores[offset].setVisible(true);
	}
	
	public void hideGhostScore() {
		for(GhostScore ghostScore : ghostScores)
			ghostScore.setVisible(false);
	}
	
	public void oneUp() {
		lives.oneUp();
	}
	
	public void subtractLife() {
		lives.subtractLife();
	}
	
	public int getLives() {
		return lives.getLives();
	}
	
	
	public void reset() {
		scoreCounter.reset();
		lives.reset();
		hideGhostScore();
	}
}
