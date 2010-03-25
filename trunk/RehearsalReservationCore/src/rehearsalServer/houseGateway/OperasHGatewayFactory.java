package rehearsalServer.houseGateway;

public class OperasHGatewayFactory {

	/**
	 * ADD your code here; this class must be a SINGLETON String serviceUri =
	 * ip, port and name of the service String serverTech = technology of the
	 * server, 'corba' or 'ws'
	 */
	private static OperasHGatewayFactory instance=null;
	
	private OperasHGatewayFactory(){}
	
	public static OperasHGatewayFactory GetInstance()
	{	
		if (instance==null)
			instance=new OperasHGatewayFactory();
		return instance;
	}
	
	
	public IOperaHGateway getOperaHGateway(String serviceUri, String serverTech) {
		IOperaHGateway the_gateway = null;
		if (serverTech=="corba")
			the_gateway=new CorbaHouseGateway("scalaMILANO");
		/*if (serverTech=="ws")
			the_gateway=new WSHouseGateway();*/
		

		return the_gateway;
	}
}
