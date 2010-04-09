package RMIClient.gui;

import RMIClient.RehearsalController;

import rehearsalServer.RehearsalRMIDTO;

import java.rmi.RemoteException;
import java.util.Observer;

import javax.swing.JFrame;

/**
 * CHECK THE GUI SOURCE CODE PROVIDED AS A REFERENCE FEEL FREE TO ADD OR
 * ORGANIZE THE GUI AS YOU PLEASE KEEP IT SIMPLE
 * 
 */

public class RMIClientGUI extends JFrame implements Observer {

	
	
	public void main(String[] args){
		
	}
	
	public void update(java.util.Observable o, Object arg) {
		//El contoller avisa a la GUI para que cambie el valor que tiene por pantalla
		
	}
}