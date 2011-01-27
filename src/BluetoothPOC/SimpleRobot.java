package BluetoothPOC;

import java.io.DataInputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

public class SimpleRobot {
	private final static int MaxMoterPower 			= 100;
	private final static int BitmaskMotorSelect 	= 0x80;	// 1000 0000
	private final static int BitmaskMotorPower		= 0x7F;	// 0111 1111
	private final static int FlagLeftMotor			= 0x00;	// 0000 0000
	private final static int FlagRightMotor		= 0x80;	// 1000 0000
	
	public static void main(String[] args) {
		LCD.drawString("Waiting for BT", 0, 0);
		NXTConnection connection = Bluetooth.waitForConnection();
		
		// Enter reading loop
		DataInputStream in = connection.openDataInputStream();
		int read;
		try {
			while ((read = in.read()) != -1) {
				// Extract flag and motor power
				int motor = read & BitmaskMotorSelect;
				int power = read & BitmaskMotorPower;
				
				// Recalculate motor power
				power = power * MaxMoterPower  / BitmaskMotorPower;
				
				// Execute action
				if (motor == FlagLeftMotor) Motor.A.setPower(power);
				else if (motor == FlagRightMotor) Motor.B.setPower(power);
			}
		} catch (IOException e) {
			LCD.clearDisplay();
			LCD.drawString("I/O Error!", 0, 0);
			LCD.drawString(e.getMessage(), 0, 1);
			System.exit(1);
		}
		
		// Close connection
		try {
			in.close(); 
		} catch (IOException e) {
			// Ignore
		}
		connection.close();
	}
}
