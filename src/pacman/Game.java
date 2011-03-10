package pacman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import pacman.world.World;

public class Game implements ActionListener {
	
	private State state;
	private World world;
	private Timer timer;
	
	public Game() throws IOException {
		this.state = State.STOPPED;
		this.world = new World();
		this.timer = new Timer(40, this);
		timer.addActionListener(this);
//		 start();
	}

	public void start() {
		this.state = State.STARTED;
		timer.start();
	}
	
	public void stop() {
		this.state = State.STOPPED;
		timer.stop();
	}

	public void pause() {
		this.state = State.PAUSED;
		timer.stop();
	}

	public State getState() {
		return this.state;
	}

	enum State {
		STOPPED(0), STARTED(1), PAUSED(2);

		private int state;

		State(int state) {
			this.state = state;
		}
	}

	public static void main(String[] args) {
		Game game;
		try {
			game = new Game();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e) {
		System.out.print(".");
		if (e.getSource().equals(this.timer)) {
			this.world.update();
		} else if (e.getID() == ActionEvent.KEY_EVENT_MASK) {
			if (e.getActionCommand().equals("s")) {
				this.start();
			}
			if (e.getActionCommand().equals("p")) {
				this.pause();
			}
		}
	}
}
