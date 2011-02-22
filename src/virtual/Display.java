package virtual;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Display extends JPanel implements ActionListener {

	private static final long serialVersionUID = 4781630719190526473L;

	private VirtualRobot robot;
	
	public Display (VirtualRobot robot) {
		this.robot = robot;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Point position = robot.getPosition();
		g.clearRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.fillOval(position.x, position.y, 5, 5);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.repaint();
	}
}
