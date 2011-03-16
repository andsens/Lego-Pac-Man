package pacman;

import java.awt.Label;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import pacman.MenuItem.Option;

public class ControlScreen extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = -4348722066426165984L;
	
	MenuItem[] menuItems;
	int selection = 0;
	Label arrow;
	
	Game game;
	
	public ControlScreen(Game game) {
		this.game = game;
		
		setSize(200, 200);
		setLayout(null);
//		setOpaque(false);
		menuItems = new MenuItem[4];
		menuItems[0] = new MenuItem("NEW GAME", Option.NEWGAME);
		menuItems[0].setLocation(50, 0);
		menuItems[1] = new MenuItem("CONTINUE", Option.CONTINUE);
		menuItems[1].setLocation(50, 30);
		menuItems[2] = new MenuItem("OPTIONS", Option.OPTIONS);
		menuItems[2].setLocation(50, 60);
		menuItems[3] = new MenuItem("EXIT", Option.EXIT);
		menuItems[3].setLocation(50, 90);
		
		arrow = new Label(">");
		add(arrow);
		updateArrow();
		for(MenuItem menuItem : menuItems)
			add(menuItem);
		validate();
	}
	
	public void previous() {
		if(selection > 0)
			selection--;
		else
			selection = menuItems.length-1;
		updateArrow();
	}
	
	public void next() {
		if(selection < menuItems.length-1)
			selection++;
		else
			selection = 0;
		updateArrow();
	}
	
	public void confirm() {
		game.menuItemSelected(menuItems[selection].getOption());
	}
	
	public void updateArrow() {
		Point position = menuItems[selection].getLocation();
		arrow.setLocation(position.x-20, position.y);
	}
	
	public void showMenu() {
		setVisible(true);
	}
	
	public void hideMenu() {
		setVisible(false);
	}
	
	public void keyPressed(KeyEvent event) {
		if(!isVisible())
			return;
		switch(event.getKeyCode()) {
		case KeyEvent.VK_UP:
			previous();
			break;
		case KeyEvent.VK_DOWN:
			next();
			break;
		case KeyEvent.VK_SPACE:
		case KeyEvent.VK_ENTER:
			confirm();
			break;
		default:
		}
	}

	public void keyReleased(KeyEvent event) {
	}

	public void keyTyped(KeyEvent event) {
	}

}
