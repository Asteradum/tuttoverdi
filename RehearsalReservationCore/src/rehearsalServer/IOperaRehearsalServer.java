package rehearsalServer;

import java.util.List;

import rehearsalServer.loginGateway.ValidationException;

/**
 * REMOTE INTERFACE TO BE IMPLEMENTED BY THE REMOTE SERVER WHICH IS THE REMOTE
 * OBSERVABLE FACADE
 * 
 * TO BE COMPLETED BY THE STUDENTS
 */
public interface IOperaRehearsalServer extends util.observer.rmi.IRemoteObservable,java.rmi.Remote {
	
	
	public String login(String user, String pass) throws ValidationException ;
	public List<RehearsalRMIDTO> getRehearsals()throws java.rmi.RemoteException;
	public void reserveSeat (String studName, String operaHouse, String operaName)throws java.rmi.RemoteException;
}
