package pacman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import pacman.MenuItem.Option;
import pacman.behaviours.factories.PacmanWithMacros;
import pacman.behaviours.factories.StandardPacmanBehaviours;
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
		world = new World(this, new PacmanWithMacros());

		JLayeredPane layers = getLayeredPane();
		
		controlScreen = new ControlScreen(this);
		controlScreen.setLocation(78, 174);
		addKeyListener(controlScreen);
		layers.add(controlScreen, new Integer(layers.highestLayer()+1));

		addKeyListener(this);
		
		setVisible(true);
	}
	
	public void menuItemSelected(Option option) {
		switch(option) {
		case NEWGAME:
			restart();
			break;
		case OPTIONS:
			break;
		case EXIT:
			System.exit(0);
		}
	}
	
	public void restart() {
		controlScreen.hideMenu();
		state = State.RUNNING;
		world.reset();
		world.restart();
		unpause();
	}
	
	public void pause() {
		state = State.PAUSED;
		world.pause();
	}
	
	public void unpause() {
		controlScreen.hideMenu();
		state = State.RUNNING;
		world.unpause();
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
