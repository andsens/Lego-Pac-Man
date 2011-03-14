package pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import pacman.behaviours.RandomBehaviourFactory;
import pacman.world.World;

public class Game implements ActionListener {
	
	private State state;
	private World world;
	private Timer timer;
	
	public Game() throws IOException {
		this.state = State.STOPPED;
		this.world = new World(new RandomBehaviourFactory());
		this.timer = new Timer(20, this);
		timer.addActionListener(this);
		start();
	}

	public void start() {
		state = State.RUNNING;
		timer.start();
	}
	
	public void stop() {
		state = State.STOPPED;
		timer.stop();
	}

	public void pause() {
		state = State.PAUSED;
		timer.stop();
	}

	public State getState() {
		return this.state;
	}

	enum State {
		STOPPED, RUNNING, PAUSED;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(this.timer)) {
			world.tick();
		} else if (e.getID() == ActionEvent.KEY_EVENT_MASK) {
			if (e.getActionCommand().equals(" "))
				if(state == State.RUNNING)
					pause();
				else
					start();
		}
	}

	public static void main(String[] args) {
		try {
			new Game();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
