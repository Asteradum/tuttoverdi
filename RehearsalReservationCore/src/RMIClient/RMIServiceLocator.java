package RMIClient;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
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
