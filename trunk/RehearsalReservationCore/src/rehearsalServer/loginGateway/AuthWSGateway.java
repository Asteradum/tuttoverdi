package rehearsalServer.loginGateway;

import java.rmi.RemoteException;

import javax.naming.*;
import javax.sql.*;

import authorizationClient.AuthorizationWSStub;

public class AuthWSGateway implements IAuthorizeGateway {

	/**
	 * Add your code to invoke the Authorization WS here Remember you must use
	 * the stub generated in the proxies package in order to invoke the WS. On
	 * the other hand, REMEMBER that you must catch the generic exception
	 * EXCEPTION AND SEE the contained message in order to map it to the
	 * ValidationException
	 * 
	 */
	
	
	public AuthWSGateway(String serviceURL) {
		
	}

	public String login(String user, String pass) throws ValidationException {
		String studentName = null;
		
		try {
			AuthorizationWSStub stub = new AuthorizationWSStub(url);
			String s = stub.login("stud1", "1111");
			System.out.println("Student " + s);
		} catch (Exception e) {
			System.out.println("Exception Type: " + e.getClass().getSimpleName());
			if (e.getMessage().contains("[InvalidUserException]")) {
				System.out.println("*** NON VALID STUDENT ***");
				System.out.println(e.getMessage());
			}else if (e.getMessage().contains("[InvalidPasswordException]")) {
				System.out.println("*** INVALID PASSWORD ***");
				System.out.println(e.getMessage());
			}
			else e.printStackTrace();
		}		
			
		
		return studentName;
	}
}
