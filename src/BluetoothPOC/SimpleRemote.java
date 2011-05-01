package BluetoothPOC;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import lejos.pc.comm.NXTCommException;

/** Test class using the keyboard to control a remote NXT brick.
 * 
 * @author Pierre
 *
 */
public class SimpleRemote extends JFrame implements KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int speed = 0;
	public static boolean rightKey = false;
	public static boolean leftKey = false;
	public static boolean upKey = false;
	public static boolean downKey = false;
	public static boolean stop = false;
	public static InstructionSender sender;
	
	public SimpleRemote() {
		try {
			sender = new InstructionSender("NXT");
		} catch (NXTCommException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.addKeyListener(this);
		this.setSize(100, 100);
		this.setFocusable(true);
		this.setVisible(true);
		this.requestFocus();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Pressed "+e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT: try {
				sender.turnRight();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} break;
		case KeyEvent.VK_LEFT: try {
				sender.turnLeft();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} break;
		case KeyEvent.VK_UP: try {
				sender.setSpeed(speed+=10);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} break;
		case KeyEvent.VK_DOWN: try {
				sender.setSpeed(speed-=10);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} break;
		case KeyEvent.VK_ESCAPE: System.exit(0); break;
		default: break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
