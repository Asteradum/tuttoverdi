/**
 * RMI SERVER  - THE REHEARSAL SERVER IN THE ARCHITECTURE
 * CONNECTION TO: RMI AUTHORIZATION SERVER AND AUTHORIZATION WEB SERVICE
 * CONNECTION TO: CORBA OPERA HOUSE COMPONENT
 * CONNECTION TO: EUSKALDUNA BIO WEB SERVICE
 * USE THE COMMAND INTERFACE TO KEEP A TRACE OF THE SERVER ACTIVITIES
 */
package rehearsalServer;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import rehearsalServer.dao.IRehearsalServerDAO;
import rehearsalServer.dao.RehearsalServerDAO;
import rehearsalServer.houseGateway.CorbaHouseGateway;
import rehearsalServer.houseGateway.IOperaHGateway;
import rehearsalServer.houseGateway.OperasHGatewayFactory;
import rehearsalServer.loginGateway.IAuthorizeGateway;
import rehearsalServer.loginGateway.ValidationException;

import util.observer.rmi.IRemoteObserver;
import util.observer.rmi.RemoteObservable;

public class OperaRehearsalServer implements IOperaRehearsalServer{

	/**
	 * CACHE OF RehearsalRMIDTO objects, organized by Opera House Name To be
	 * loaded at the initialization process
	 */
	private util.observer.rmi.RemoteObservable rO;
	private static IAuthorizeGateway gateway;
	private IRehearsalServerDAO dao;
	private static  Map<String, Map<String, RehearsalRMIDTO>> rehearsalCache;
	private static Map<String,RehearsalRMIDTO> innerMap;
	
	public String login(String user, String pass) throws ValidationException {
		
  	    return   gateway.login(user, pass);	
}
	
	public  OperaRehearsalServer() throws RemoteException, SQLException{
		super();
		rO=new RemoteObservable();
		rehearsalCache=getRehearsalCache();
	}
	  
	Map<String, Map<String, RehearsalRMIDTO>> getRehearsalCache() throws SQLException
	  
	{
	rehearsalCache= new TreeMap<String,Map<String, RehearsalRMIDTO>>();
	innerMap=new TreeMap<String,RehearsalRMIDTO>();
	////////////////////////////////////////////////////////
	OperasHGatewayFactory op= OperasHGatewayFactory.GetInstance();
	IOperaHGateway gateway = op.getOperaHGateway("scalaMilano", "corba");
	//CorbaHouseGateway gate= gateway.
	 
	List<rehearsalServer.houseGateway.RehearsalDO> lista= new ArrayList<rehearsalServer.houseGateway.RehearsalDO>();
	rehearsalServer.houseGateway.RehearsalDO rehearsal = null;
	lista = gateway.getRehearsals();
	
	RehearsalServerDAO p= new RehearsalServerDAO();
	for (int i=0;i<lista.size();i++){
		rehearsal = lista.get(i);
		String opera=rehearsal.getOperaName();
		int sitiosOcup=p.getReservationsCount("ScalaMILANO",opera);
		int sitiosTotal=rehearsal.getAvailableSeats();
		RehearsalRMIDTO DTO= new RehearsalRMIDTO("ScalaMILANO", opera, rehearsal.getDate(), sitiosTotal-sitiosOcup);
		innerMap.put(opera, DTO);
	}
	rehearsalCache.put("ScalaMILANO", innerMap);
	p.disconnect();
	System.out.println(rehearsalCache.get("ScalaMILANO").get("Nabbuco").getOperaHouse());
	return rehearsalCache;
	
	}
	
	public void reserveSeat (String studName, String operaHouse, String operaName){
	   boolean place=true;
	   place=dao.placeAvailable(operaName, operaHouse);
	   if (place)
	   {
		   dao.reserveSeat(studName, operaHouse, operaName);
		   dao.reduce(operaHouse, operaName);
		}
	   }
	
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("uso: java [policy] [codebase] servidor.Servidor[host] [port] [server]");
			System.exit(0);
			}
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			}
		String name = "//127.0.0.1 :"  + "900" + "/" + null;
		try {
		IOperaRehearsalServer objServidor = new OperaRehearsalServer();
		Naming.rebind(name, objServidor);}
		catch (Exception e) {
			e.printStackTrace();}
	
		
	}
	
	
	
	@Override
	public void addRemoteObserver(IRemoteObserver arg0) throws RemoteException {
		// TODO Auto-generated method stub
		rO.addRemoteObserver((IRemoteObserver) rO);
	}

	@Override
	public void deleteRemoteObserver(IRemoteObserver arg0)
			throws RemoteException {
		// TODO Auto-generated method stub
		rO.deleteRemoteObserver((IRemoteObserver) rO);
		
	}
}
