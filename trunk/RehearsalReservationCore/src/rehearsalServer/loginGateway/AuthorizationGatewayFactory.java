package rehearsalServer.loginGateway;

import rehearsalServer.houseGateway.OperasHGatewayFactory;

/**
 * Singleton and Factory Class. Given a string "rmi" or "ws", it returns an
 * object of the corresponding gateway, both of which implement the same
 * interface.
 */

public class AuthorizationGatewayFactory {
	// Add your code here
	// THIS CLASS MUST BE A SINGLETON
	private static AuthorizationGatewayFactory instance=null;
	private AuthorizationGatewayFactory(){}
	
	public static AuthorizationGatewayFactory GetInstance()
	{	
		if (instance==null)
			instance=new AuthorizationGatewayFactory();
		return instance;
	}
	
	public IAuthorizeGateway getAuthGateway(String serviceUri,String serviceTech) {
		if (serviceTech.equals("rmi")) {
			return new AuthRMIGateway(serviceUri);
		} else if (serviceTech.equals("ws")) {
			return new AuthWSGateway(serviceUri);
		} else {
			return null;
		}
	}
}
