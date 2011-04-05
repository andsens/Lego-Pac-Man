package pacman.world;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import pacman.behaviours.factories.BehaviourFactory;
import pacman.world.graphics.Graphic;
import pacman.world.graphics.Sprite;
import pacman.world.maps.Coordinate;
import pacman.world.maps.DotMap;
import pacman.world.maps.MovingEntityMap;
import pacman.world.maps.OverlayMap;
import pacman.world.maps.StatusMap;
import pacman.world.maps.Type;
import pacman.world.maps.TypeMap;
import pacman.world.maps.WallMap;
import pacman.world.tiles.Tile;


/**
 * World is the entire game board and the main class holding everything in the game.
 * <img src="../../../Pac-Man Dossier/files/lvl1.png" style="display:block" />
 * 
 * @author andsens
 * 
 */
public class World implements ActionListener {

	private static final long serialVersionUID = -7706020584659519598L;

	public static final int ticksPerSecond = 100;
	public static final int timerSpeed = 10;
	
	private Timer timer;
	
	private BehaviourFactory behaviours;
	
	private TypeMap map;
	private WallMap wallMap;
	private DotMap dotMap;
	private MovingEntityMap movingEntityMap;
	private StatusMap statusMap;
	private OverlayMap overlayMap;

	public World(JFrame window, BehaviourFactory behaviours) throws IOException {
		this.behaviours = behaviours;
		behaviours.setWorld(this);
		if(KeyListener.class.isInstance(behaviours.getController()))
			window.addKeyListener((KeyListener) behaviours.getController());
		
		timer = new Timer(timerSpeed, this);
		
		map = new TypeMap(new File("maps/classic.txt"));
		
		Sprite sprite = new Sprite("sprite.png");
		Graphic.setSprite(sprite);
		
		window.setSize(Tile.width * map.getWidth(), Tile.height * map.getHeight() + 22);
		
		Container layers = window.getLayeredPane();
		
		JPanel background = new JPanel();
		background.setBackground(Color.BLACK);
		background.setSize(Tile.width * map.getWidth(), Tile.height * map.getHeight());
		layers.add(background, new Integer(1));
		
		wallMap = new WallMap(map);
		layers.add(wallMap, new Integer(2));
		
		dotMap = new DotMap(map);
		layers.add(dotMap, new Integer(3));
		
		movingEntityMap = new MovingEntityMap(map, behaviours);
		movingEntityMap.setWorld(this);
		layers.add(movingEntityMap, new Integer(4));
		
		statusMap = new StatusMap(map);
		layers.add(statusMap, new Integer(5));
		
		overlayMap = new OverlayMap(map);
		layers.add(overlayMap, new Integer(6));
		
		layers.paintComponents(layers.getGraphics());
	}

	private long tickCount = 0;
	public long getTickCount() {
		return tickCount;
	}
	
	int level = 0;
	public int getLevel() {
		return level;
	}
	
	long lastEnergizer = 0;
	public int getEnergizerLeft() {
		int energizerSeconds = 0;
		switch(level) {
		case  1: energizerSeconds = 6; break;
		case  2: energizerSeconds = 5; break;
		case  3: energizerSeconds = 4; break;
		case  4: energizerSeconds = 3; break;
		case  5: energizerSeconds = 2; break;
		case  6: energizerSeconds = 5; break;
		case  7: energizerSeconds = 2; break;
		case  8: energizerSeconds = 2; break;
		case  9: energizerSeconds = 1; break;
		case 10: energizerSeconds = 5; break;
		case 11: energizerSeconds = 2; break;
		case 12: energizerSeconds = 1; break;
		case 13: energizerSeconds = 1; break;
		case 14: energizerSeconds = 3; break;
		case 15: energizerSeconds = 1; break;
		case 16: energizerSeconds = 1; break;
		case 17: energizerSeconds = 1; break;
		case 18: energizerSeconds = 1; break;
		}
		if(lastEnergizer == 0)
			return 0;
		return (int) Math.max(0, lastEnergizer + energizerSeconds*ticksPerSecond - tickCount);
	}
	
	private GhostMode ghostMode = GhostMode.SCATTER;
	private GhostMode previousGhostMode = ghostMode;
	public GhostMode getGhostMode() {
		return ghostMode;
	}
	
	private void setGhostMode() {
		previousGhostMode = ghostMode;
		ghostMode = calculateGhostMode();
		if(previousGhostMode != ghostMode)
			for(Ghost ghost : movingEntityMap.getGhosts())
				ghost.reverseHeading();
	}
	
	private GhostMode calculateGhostMode() {
		int gameSeconds = (int) Math.ceil(tickCount/ticksPerSecond);
		if(level == 1) {
			if(gameSeconds <= 7)
				return GhostMode.SCATTER;
			if(gameSeconds <= 27)
				return GhostMode.CHASE;
			if(gameSeconds <= 34)
				return GhostMode.SCATTER;
			if(gameSeconds <= 54)
				return GhostMode.CHASE;
			if(gameSeconds <= 59)
				return GhostMode.SCATTER;
			if(gameSeconds <= 79)
				return GhostMode.CHASE;
			if(gameSeconds <= 84)
				return GhostMode.SCATTER;
			return GhostMode.CHASE;
		}
		if(level <= 4) {
			if(gameSeconds <= 7)
				return GhostMode.SCATTER;
			if(gameSeconds <= 27)
				return GhostMode.CHASE;
			if(gameSeconds <= 34)
				return GhostMode.SCATTER;
			if(gameSeconds <= 54)
				return GhostMode.CHASE;
			if(gameSeconds <= 59)
				return GhostMode.SCATTER;
			if(gameSeconds <= 1092)
				return GhostMode.CHASE;
			if(tickCount/ticksPerSecond <= 1092+1/60)
				return GhostMode.SCATTER;
			return GhostMode.CHASE;
		}
		if(gameSeconds <= 5)
			return GhostMode.SCATTER;
		if(gameSeconds <= 25)
			return GhostMode.CHASE;
		if(gameSeconds <= 30)
			return GhostMode.SCATTER;
		if(gameSeconds <= 50)
			return GhostMode.CHASE;
		if(gameSeconds <= 55)
			return GhostMode.SCATTER;
		if(gameSeconds <= 1092)
			return GhostMode.CHASE;
		if(tickCount/ticksPerSecond <= 1092+1/60)
			return GhostMode.SCATTER;
		return GhostMode.CHASE;
	}

	long lastDotEaten;
	public Dot eatDot(Coordinate coordinate) {
		Dot dot = dotMap.eat(coordinate);
		if(dot != null) {
			lastDotEaten = tickCount;
			countDot();
			if(Energizer.class.isInstance(dot)) {
				energize();
				statusMap.increaseScore(50);
			} else {
				statusMap.increaseScore(10);
			}
		}
		return dot;
	}
	
	private boolean countGlobal = false;
	private int globalDotCount = 0;
	private void countDot() {
		Ghost blinky = (Ghost) movingEntityMap.get(Type.BLINKY);
		Ghost pinky = (Ghost) movingEntityMap.get(Type.PINKY);
		Ghost inky = (Ghost) movingEntityMap.get(Type.INKY);
		Ghost clyde = (Ghost) movingEntityMap.get(Type.CLYDE);
		
		if(countGlobal) {
			globalDotCount++;
			switch(globalDotCount) {
			case 7:
				pinky.jailbreak();
				break;
			case 17:
				inky.jailbreak();
				break;
			case 32:
				if(isGhostHouse(clyde.getCurrentTile())
				|| isGhostHouseGate(clyde.getCurrentTile())) {
					clyde.jailbreak();
					globalDotCount = 0;
					countGlobal = false;
				}
				break;
			}
			return;
		}
		if(blinky.countDot(level))
			return;
		if(pinky.countDot(level))
			return;
		if(inky.countDot(level))
			return;
		if(clyde.countDot(level))
			return;
	}
	
	private int ghostsKilled = 0;
	public Ghost eatGhost(Coordinate coordinate) {
		List<Ghost> ghosts = movingEntityMap.getGhosts();
		for(Ghost ghost : ghosts) {
			if(ghost.isDead())
				continue;
			if(!ghost.isFrightened())
				continue;
			if(ghost.getCurrentTile().equals(coordinate)) {
				statusMap.showGhostScore(coordinate, ghostsKilled);
				statusMap.increaseScore((int) Math.pow(2, ghostsKilled) * 200);
				ghostsKilled++;
				pauseFor = (int) (ticksPerSecond);
				ghost.setVisible(false);
				movingEntityMap.get(Type.PACMAN).setVisible(false);
				ghost.die();
				return ghost;
			}
		}
		return null;
	}
	
	private boolean pacmanDying = false;
	public void killPacman(Ghost ghost) {
		if(ghost.isFrightened() || ghost.isDead())
			return;
		Pacman pacman = (Pacman) movingEntityMap.get(Type.PACMAN);
		if(pacman.getCurrentTile().equals(ghost.getCurrentTile())) {
			pacmanDying = true;
			pauseFor = (int) (1 * ticksPerSecond);
		}
	}
	
	private void ghoustHouseTimer() {
		Ghost blinky = (Ghost) movingEntityMap.get(Type.BLINKY);
		Ghost pinky = (Ghost) movingEntityMap.get(Type.PINKY);
		Ghost inky = (Ghost) movingEntityMap.get(Type.INKY);
		Ghost clyde = (Ghost) movingEntityMap.get(Type.CLYDE);
		
		long lastDotEatenThreshold = level <= 4 ? 4 * ticksPerSecond : 3 * ticksPerSecond;
		if(tickCount - lastDotEaten > lastDotEatenThreshold) {
			lastDotEaten = tickCount;
			if(blinky.jailbreak())
				return;
			if(pinky.jailbreak())
				return;
			if(inky.jailbreak())
				return;
			if(clyde.jailbreak())
				return;
		}
	}
	
	public void energize() {
		ghostsKilled = 0;
		lastEnergizer = tickCount;
		for(Ghost ghost : movingEntityMap.getGhosts())
			ghost.frighten();
	}
	
	public void markCoordinate(Coordinate coordinate) {
		overlayMap.markCoordinate(coordinate);
	}
	
	public boolean isNavigable(Coordinate coordinate) {
		return wallMap.isNavigable(coordinate);
	}
	
	public boolean isGhostHouse(Coordinate coordinate) {
		return wallMap.isGhostHouse(coordinate);
	}
	
	public boolean isGhostHouseGate(Coordinate coordinate) {
		return wallMap.isGhostHouseGate(coordinate);
	}
	
	public boolean isRedZone(Coordinate coordinate) {
		return wallMap.isRedZone(coordinate);
	}
	
	public Coordinate getSpawnPoint(Type type) {
		return wallMap.getSpawnPoint(type);
	}
	
	public Coordinate getGhostHouseEntrance() {
		return wallMap.getGhostHouseEntrance();
	}
	
	public MovingEntity getMovingEntity(Type entityType) {
		return movingEntityMap.get(entityType);
	}
	
	public int getMapWidth() {
		return map.getWidth();
	}
	
	public int getMapHeight() {
		return map.getHeight();
	}
	
	public void clearMarkings() {
		overlayMap.reset();
	}
	
	int pauseFor = 0;
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() != timer)
			return;
		if(pauseFor > 0) {
			pauseFor--;
			behaviours.getController().listen(false);
			if(pauseFor == 0)
				resumed();
			else
				return;
		}
		tickCount++;
		if(pacmanDying) {
			Pacman pacman = (Pacman) movingEntityMap.get(Type.PACMAN);
			if(pacman.die(tickCount)) {
				pacmanDying = false;
				if(statusMap.getLives() == 0) {
					gameOver();
				} else {
					nextLife();
				}
				return;
			}
			for(Ghost ghost : movingEntityMap.getGhosts())
				ghost.setVisible(false);
			return;
		}
		if(dotMap.getDotsLeft() == 0) {
			nextLevel();
			return;
		}
		setGhostMode();
		movingEntityMap.tick(tickCount);
		ghoustHouseTimer();
	}
	
	private void resumed() {
		statusMap.hideGhostScore();
		for(MovingEntity entity : movingEntityMap.getEntities())
			entity.setVisible(true);
		statusMap.hideReady();
		behaviours.getController().listen(true);
	}
	
	private void nextLife() {
		statusMap.subtractLife();
		countGlobal = true;
		globalDotCount = 0;
		tickCount = 0;
		lastDotEaten = 0;
		ghostsKilled = 0;
		movingEntityMap.reset();
		pauseFor = (int) (1.5 * ticksPerSecond);
	}
	
	private void nextLevel() {
		level++;
		tickCount = 0;
		lastDotEaten = 0;
		ghostsKilled = 0;
		dotMap.reset();
		movingEntityMap.reset();
		overlayMap.reset();
		pauseFor = (int) (1.5 * ticksPerSecond);
		statusMap.showReady();
	}
	
	private void gameOver() {
		
	}
	
	public void restart() {
		reset();
		nextLevel();
		unpause();
	}
	
	public void unpause() {
		timer.start();
		behaviours.getController().listen(true);
	}
	
	public void pause() {
		timer.stop();
		behaviours.getController().listen(false);
	}
	
	public void reset() {
		level = 0;
		statusMap.reset();
	}
}
