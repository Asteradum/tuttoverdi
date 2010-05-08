package rehearsalServer.loginGateway;

import org.apache.axis2.AxisFault;


public class AuthWSGateway implements IAuthorizeGateway {

	/**
	 * Add your code to invoke the Authorization WS here Remember you must use
	 * the stub generated in the proxies package in order to invoke the WS. On
	 * the other hand, REMEMBER that you must catch the generic exception
	 * EXCEPTION AND SEE the contained message in order to map it to the
	 * ValidationException
	 * 
	 */
	rehearsalServer.loginGateway.proxies.AuthorizationWSStub stub = null;
	
	public AuthWSGateway(String serviceURL) {
		try {
			stub = new rehearsalServer.loginGateway.proxies.AuthorizationWSStub(serviceURL);
		} catch (AxisFault e) {
			e.printStackTrace();
		}
	}

	public String login(String user, String pass) throws ValidationException {
		String studentName = null;
		
		try {
			studentName = stub.login(user, pass);
			System.out.println(studentName);
		} catch (Exception e) {
			System.out.println("Exception Type: " + e.getClass().getSimpleName());
			if (e.getMessage().contains("[InvalidUserException]")) {
				System.out.println("*** NON VALID STUDENT ***");
				throw new ValidationException(e.getMessage());
			}else if (e.getMessage().contains("[InvalidPasswordException]")) {
				System.out.println("*** INVALID PASSWORD ***");
				throw new ValidationException(e.getMessage());
			}
			else e.printStackTrace();
		}		
			
		
		return studentName;
	}
}
