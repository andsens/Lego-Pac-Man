package pacman;

import pacman.world.graphics.Word;

public class MenuItem extends Word {
	
	private static final long serialVersionUID = -3163172670267742403L;
	
	public enum Option {
		NEWGAME, CONTINUE, OPTIONS, EXIT
	}
	
	Option option;
	
	public MenuItem(String text, Option option) {
		super(text);
		this.option = option;
	}
	
	public Option getOption() {
		return option;
	}
}
