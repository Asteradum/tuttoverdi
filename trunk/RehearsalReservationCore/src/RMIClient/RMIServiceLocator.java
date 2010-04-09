package RMIClient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import rehearsalServer.IOperaRehearsalServer;

public class RMIServiceLocator {
	
	private IOperaRehearsalServer service;

	public IOperaRehearsalServer getService(String serviceURL) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		try {
			service = (IOperaRehearsalServer) Naming.lookup(serviceURL);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return service;
	}

}
