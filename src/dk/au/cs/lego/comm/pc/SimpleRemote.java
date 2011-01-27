package dk.au.cs.lego.comm.pc;

import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;

public class SimpleRemote {
	public static void main(String[] args) {
		System.out.println("Simple NXT Remote");
		
		NXTComm comm = null;
		try {
			comm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		} catch (NXTCommException e) {
			System.out.println("Error creating NXTComm");
			e.printStackTrace();
			System.exit(1);
		}
		
		// Search for bluetooth device
		System.out.print("Enter name: ");
		
	}
}
