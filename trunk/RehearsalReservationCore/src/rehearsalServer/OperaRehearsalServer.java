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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import rehearsalServer.dao.RehearsalServerDAO;
import rehearsalServer.houseGateway.CorbaHouseGateway;

import util.observer.rmi.IRemoteObserver;

public class OperaRehearsalServer implements IOperaRehearsalServer{

	/**
	 * CACHE OF RehearsalRMIDTO objects, organized by Opera House Name To be
	 * loaded at the initialization process
	 */
	private static  Map<String, Map<String, RehearsalRMIDTO>> rehearsalCache;
	private static Map<String,RehearsalRMIDTO> innerMap;
	
	
	public  OperaRehearsalServer() throws RemoteException{
		super();
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
		Naming.rebind(name, objServidor);
		rehearsalCache= new HashMap<String,Map<String, RehearsalRMIDTO>>();
		innerMap=new HashMap<String,RehearsalRMIDTO>();
		////////////////////////////////////////////////////////
		CorbaHouseGateway gate = new CorbaHouseGateway("scalaMilano");
		List<rehearsalServer.houseGateway.RehearsalDO> lista= new ArrayList<rehearsalServer.houseGateway.RehearsalDO>();
		rehearsalServer.houseGateway.RehearsalDO rehearsal = null;
		lista = gate.getRehearsals();
		
		RehearsalServerDAO p= new RehearsalServerDAO();
		p.connect();
		
		
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
		}
		catch (Exception e) {
		e.printStackTrace();}

	}

	
	
	@Override
	public void addRemoteObserver(IRemoteObserver arg0) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRemoteObserver(IRemoteObserver arg0)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
