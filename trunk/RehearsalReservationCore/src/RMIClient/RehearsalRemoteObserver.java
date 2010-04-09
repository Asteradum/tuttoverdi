/**
 * THIS OBJECT IS MEANT TO BE THE 'SERVER SIDE' OF THE CLIENT
 * IN OTHER WORDS, IT IS SUPPOSED TO RECEIVE INVOCATIONS FROM THE RMI SERVER
 * So it behaves as a REMOTE OBSERVER
 * TO BE COMPLETED BY THE STUDENTS
 */
package RMIClient;

import rehearsalServer.IOperaRehearsalServer;
import rehearsalServer.RehearsalRMIDTO;
import util.observer.rmi.RemoteObserver;

import java.rmi.RemoteException;

public class RehearsalRemoteObserver extends RemoteObserver {
	private static final long serialVersionUID = 1L;
	
	private RehearsalController myController = null; // In order to send to the Controller the update notification
	private IOperaRehearsalServer controllerServer = null; // In order to delete the the remote Observer from the RehearsalReservationServer
	/**
	 * Creates a new instance of RemoteObserver ADD AS MUCH INFORMATION AS
	 * NEEDED
	 */

	public RehearsalRemoteObserver(RehearsalController controller, IOperaRehearsalServer server) throws RemoteException {
		super();
		myController = controller;
		controllerServer = server;
		
	}

	public void stop() throws RemoteException {
		controllerServer.deleteRemoteObserver(this);
	}

	public void update(Object arg) throws java.rmi.RemoteException {
		
		myController.updateRehearsal((RehearsalRMIDTO)arg);

	}
}