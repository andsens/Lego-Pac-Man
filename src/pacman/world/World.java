package pacman.world;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

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
	
	private static final int tickSpeed = 20;
	
	private Timer timer;
	private int tickCount = 1;
	
	private JFrame window;
	
	private BehaviourFactory behaviours;
	
	private TypeMap map;
	private WallMap wallMap;
	private DotMap dotMap;
	private MovingEntityMap movingEntityMap;
	private OverlayMap overlayMap;

	public World(JFrame window, BehaviourFactory behaviours) throws IOException {
		this.window = window;
		this.behaviours = behaviours;
		
		timer = new Timer(tickSpeed, this);
		timer.addActionListener(this);
		
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
		layers.add(movingEntityMap, new Integer(4));
		
		overlayMap = new OverlayMap(map);
		layers.add(overlayMap, new Integer(5));
		
		layers.paintComponents(layers.getGraphics());
	}
	
	int level = 1;
	public int getLevel() {
		return level;
	}
	
	long lastEnergizer = 0;
	public long getEnergizerLeft() {
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
		return Math.max(0, energizerSeconds*1000 - System.currentTimeMillis() + lastEnergizer);
	}
	
	public GhostMode getGhostMode() {
		if(getEnergizerLeft() > 0)
			return GhostMode.FRIGHTENED;
		
		int gameSeconds = (int) Math.ceil((System.currentTimeMillis()-gameStart)/1000);
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
			if(System.currentTimeMillis()-gameStart <= 1092+1/60)
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
		if(System.currentTimeMillis()-gameStart <= 1092+1/60)
			return GhostMode.SCATTER;
		return GhostMode.CHASE;
	}
	
	public boolean isValidPacmanTile(Coordinate coordinate) {
		return wallMap.isValidPacmanTile(coordinate);
	}
	
	public boolean isValidGhostTile(Coordinate coordinate) {
		return wallMap.isValidGhostTile(coordinate);
	}
	
	public boolean isRedZoneTile(Coordinate coordinate) {
		return wallMap.isRedZoneTile(coordinate);
	}
	
	public MovingEntity getMovingEntity(Type entityType) {
		return movingEntityMap.get(entityType);
	}
	
	int dotsEaten = 0;
	public Dot eatDot(Point location) {
		Dot dot = dotMap.eat(location);
		if(dot != null)
			dotsEaten++;
		return dot;
	}
	
	public void energize() {
		lastEnergizer = System.currentTimeMillis();
		if(getGhostMode() != GhostMode.FRIGHTENED)
			movingEntityMap.frightenGhosts();
	}
	
	public void markTile(Coordinate coordinate) {
		overlayMap.markTile(coordinate);
	}
	
	public void clearMarkings() {
		overlayMap.reset();
	}

	public void capTileLocation(Coordinate tile) {
		tile.x = tile.x > 0 ? Math.min(tile.x, map.getWidth()-1) : 0;
		tile.y = tile.y > 0 ? Math.min(tile.y, map.getHeight()-1) : 0;
	}
	
	public void tick() {
		if(++tickCount > 100)
			tickCount = 1;
		movingEntityMap.tick(this);
		dotMap.tick(this);
	}
	
	public int getTickCount() {
		return tickCount;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			tick();
		}
	}
	
	long gameStart;
	public void restart() {
		reset();
		unpause();
	}
	
	long gamePause;
	public void unpause() {
		gameStart += gamePause - gameStart;
		timer.start();
		if(KeyListener.class.isInstance(behaviours.getPacmanBehaviour()))
			window.addKeyListener((KeyListener) behaviours.getPacmanBehaviour());
	}
	
	public void pause() {
		gamePause = System.currentTimeMillis();
		timer.stop();
		if(KeyListener.class.isInstance(behaviours.getPacmanBehaviour()))
			window.removeKeyListener((KeyListener) behaviours.getPacmanBehaviour());
	}
	
	public void reset() {
		dotsEaten = 0;
		gameStart = System.currentTimeMillis();
		dotMap.reset();
		movingEntityMap.reset();
		overlayMap.reset();
		tickCount = 1;
	}
}
