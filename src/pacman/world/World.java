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

import pacman.behaviours.BehaviourFactory;
import pacman.world.graphics.Graphic;
import pacman.world.graphics.Sprite;
import pacman.world.maps.DotMap;
import pacman.world.maps.MovingEntityMap;
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
	
	private Timer timer;
	private int tickCount = 1;
	
	private JFrame window;
	
	private BehaviourFactory behaviours;
	
	private TypeMap map;
	private WallMap wallMap;
	private DotMap dotMap;
	private MovingEntityMap movingEntityMap;

	public World(JFrame window, BehaviourFactory behaviours) throws IOException {
		this.window = window;
		this.behaviours = behaviours;
		
		timer = new Timer(20, this);
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
		
		layers.paintComponents(layers.getGraphics());
	}
	
	public boolean isValidPacmanLocation(Point location) {
		return wallMap.isValidPacmanLocation(location);
	}
	
	public boolean isValidGhostLocation(Point location) {
		return wallMap.isValidGhostLocation(location);
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
	
	public void start() {
		timer.start();
		if(KeyListener.class.isInstance(behaviours.getPacmanBehaviour()))
			window.addKeyListener((KeyListener) behaviours.getPacmanBehaviour());
	}
	
	public void pause() {
		timer.stop();
		if(KeyListener.class.isInstance(behaviours.getPacmanBehaviour()))
			window.removeKeyListener((KeyListener) behaviours.getPacmanBehaviour());
	}
	
	public void reset() {
		dotMap.reset();
		movingEntityMap.reset();
		tickCount = 1;
	}
}
