package pacman;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import pacman.MenuItem.Option;
import pacman.behaviours.factories.HomingGhostsBehaviourFactory;
import pacman.behaviours.factories.RandomBehaviourFactory;
import pacman.world.World;

public class Game extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = -58459108523070338L;
	
	private State state;
	private World world;
	private ControlScreen controlScreen;
	
	public Game() throws IOException {
		
		setTitle("Pac-Man");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setIconImage(ImageIO.read(new File("icon.png")));
		setResizable(false);

		state = State.STOPPED;
		world = new World(this, new HomingGhostsBehaviourFactory());

		JLayeredPane layers = getLayeredPane();
		
		controlScreen = new ControlScreen(this);
		controlScreen.setLocation(100, 100);
		addKeyListener(controlScreen);
		layers.add(controlScreen, new Integer(layers.highestLayer()+1));

		addKeyListener(this);
		
		setVisible(true);
	}
	
	public void menuItemSelected(Option option) {
		switch(option) {
		case NEWGAME:
			stop();
			start();
		case CONTINUE:
			unpause();
		case OPTIONS:
			break;
		case EXIT:
			System.exit(0);
		}
	}
	
	public void start() {
		controlScreen.hideMenu();
		state = State.RUNNING;
		world.start();
	}
	
	public void stop() {
		state = State.STOPPED;
		world.pause();
		world.reset();
	}
	
	public void unpause() {
		start();
	}
	
	public void pause() {
		state = State.PAUSED;
		world.pause();
	}
	
	public State getGameState() {
		return this.state;
	}
	
	enum State {
		STOPPED, RUNNING, PAUSED;
	}
	
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(state == State.RUNNING) {
				pause();
				controlScreen.showMenu();
			} else {
				unpause();
			}
		}
		if(event.getKeyCode() == KeyEvent.VK_SPACE)
			if(state == State.RUNNING)
				pause();
			else
				unpause();
	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {
	}
	
	public static void main(String[] args) {
		try {
			new Game();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
