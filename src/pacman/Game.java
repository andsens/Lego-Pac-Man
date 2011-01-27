package pacman;

public class Game {
	
	private State state;
	
	public Game() {
		this.state = State.STOPPED;
	}

	public void start() {
		
	}
	
	public void stop() {
		
	}

	public void pause() {

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

	public static int main(String[] args) {
		Game game;
		try {
			game = new Game();
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
}
