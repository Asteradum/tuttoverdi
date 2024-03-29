package rehearsalServer;

import java.rmi.RemoteException;
import java.util.List;

import rehearsalServer.loginGateway.ValidationException;

/**
 * REMOTE INTERFACE TO BE IMPLEMENTED BY THE REMOTE SERVER WHICH IS THE REMOTE
 * OBSERVABLE FACADE
 * 
 * TO BE COMPLETED BY THE STUDENTS
 */
public interface IOperaRehearsalServer extends util.observer.rmi.IRemoteObservable {
	
	
	public String login(String user, String pass) throws ValidationException,RemoteException;
	public List<RehearsalRMIDTO> getRehearsals()throws RemoteException;
	public void reserveSeat (String studName, String operaHouse, String operaName)throws RemoteException;
}
