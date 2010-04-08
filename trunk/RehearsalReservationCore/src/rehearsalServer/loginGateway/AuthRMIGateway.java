package rehearsalServer.loginGateway;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import authorizationRMI.IAuthorizationRMI;
import authorizationRMI.InvalidPasswordException;
import authorizationRMI.InvalidUserException;


public class AuthRMIGateway implements IAuthorizeGateway  {
	
	/**
	 * Add your code to invoke the Authorization RMI Server here Remember you
	 * must use the interface and exceptions contained in
	 * AuthorizationRMIClient.jar THIS SECTION BELONGS TO THE FIRST ASSIGNMENT
	 */
	private IAuthorizationRMI iauth;	
	public AuthRMIGateway(String serviceUri) {

	}
//cambiar las excepciones, mandarlas al cliente
	public String login(String user, String pass) throws ValidationException {
		String studentName = null;
		try {
			studentName = iauth.login(user, pass);
			System.out.println("The name of the student is: " + studentName);
		} catch (InvalidUserException e) {
			throw new ValidationException ("Invalid Username Exception");
		} catch (InvalidPasswordException e) {
			throw new ValidationException ("Invalid Password Exception");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentName;
		
		
	}
	private void getAuthManager(String host, String port, String nam) 
	//copiado literal del "TestCase" (authorization RMI)
	{
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		try {
			String name = "//" + host + ":" + port + "/" + nam + "";
			iauth = (IAuthorizationRMI) Naming.lookup(name);

		} catch (Exception e) {
			System.err.println("Error in Authorization Manager.  getAuthManager(): " + e.getMessage());
		}
	}
}
