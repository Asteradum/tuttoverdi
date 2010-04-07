package RMIClient;

import RMIClient.gui.RMIClientGUI;

import rehearsalServer.IOperaRehearsalServer;
import rehearsalServer.OperaRehearsalServer;
import rehearsalServer.RehearsalRMIDTO;
import rehearsalServer.dao.RehearsalServerDAO;
import rehearsalServer.loginGateway.ValidationException;

import util.observer.local.LocalObservable;
import java.util.Observer;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.sql.SQLException;

import java.util.List;

public class RehearsalController {
	// Some data you may need
	// Feel free to add what you need
	private IOperaRehearsalServer server;
	
	private LocalObservable observable;
	private RehearsalRemoteObserver observer;

	// CLIENT SESSION STATE - state management field.
	private String stuName;

	/**
	 * Creates a new instance of ReservationController
	 * 
	 * @throws RemoteException
	 */
	public RehearsalController(String[] args) throws RemoteException {

		observable = new LocalObservable();

	}

	// --------------- System Events - Remote Method Invocation --------
	// TO BE COMPLETELY PROGRAMMED BY THE STUDENTS - 1st Assignment

	public String login(String user, String pass) throws ValidationException {
		stuName=server.login(user, pass);
		return stuName;
	}

	public List<RehearsalRMIDTO> getRehearsals() {
		List<RehearsalRMIDTO> subjects = null;
		// add your code here
		return subjects;
	}

	public void reserveSeat(String operaHouse, String operaName)  {
		// add your code here
		server.reserveSeat(stuName, operaHouse, operaName);
	}

	// -------- Remote Observer Notification ---------------
	public void updateRehearsal(RehearsalRMIDTO reh) {
		// propagate the changed rehearsal so that the GUI can update the
		// rehearsal details

	}

	// ------------------ End of Remote Observer Notification --------
	public void exit() throws RemoteException {
		observer.stop();
		System.exit(0);
	}

	// ---------------------- Local Observable ---------------------------
	public void addLocalObserver(Observer observer) {
		observable.addObserver(observer);
	}

	public void deleteLocalObserver(Observer observer) {
		observable.deleteObserver(observer);
	}

	public void notifyLocalObservers(Object arg) {
		observable.setChanged();
		observable.notifyObservers(arg);
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws RemoteException
	 * @throws SQLException 
	 */
	
	
	//sonia
	public static void main(String[] args) throws RemoteException, SQLException {
		// TODO code application logic here
		new RehearsalController(args);
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			}
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			System.out.println("Nombre del servidor: " + name);
			try
			{
				IOperaRehearsalServer operaRehearsal = new OperaRehearsalServer();
				Naming.rebind(name, operaRehearsal);
				} catch (RemoteException re) {
				System.err.println("RemoteException: " + re.getMessage());
				re.printStackTrace();
				System.exit(-1);
				} catch (MalformedURLException murle) {
				System.err.println("MalformedURLException: " +
				murle.getMessage());
				murle.printStackTrace();
				System.exit(-1);
				}
		}
}
	

