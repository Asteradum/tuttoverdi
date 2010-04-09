package rehearsalServer.loginGateway;

import authorizationRMI.IAuthorizationRMI;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import authorizationRMI.IAuthorizationRMI;
import authorizationRMI.InvalidPasswordException;
import authorizationRMI.InvalidUserException;

public class prueba {
	private static IAuthorizationRMI iauth;
	
	public static void main(String[] args) {
	//getAuthManager(args[0], args[1], args[2]);
	
		String name;
		try {
			name = iauth.login("stud1", "1111");
			System.out.println("The name of the student is: " + name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
