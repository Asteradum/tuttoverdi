/* RMI SERVER  - THE REHEARSAL SERVER IN THE ARCHITECTURE
 * CONNECTION TO: RMI AUTHORIZATION SERVER AND AUTHORIZATION WEB SERVICE
 * CONNECTION TO: CORBA OPERA HOUSE COMPONENT
 * CONNECTION TO: EUSKALDUNA BIO WEB SERVICE
 * USE THE COMMAND INTERFACE TO KEEP A TRACE OF THE SERVER ACTIVITIES
 */
package rehearsalServer;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import rehearsalServer.dao.IRehearsalServerDAO;
import rehearsalServer.dao.RehearsalServerDAO;
import rehearsalServer.houseGateway.CorbaHouseGateway;
import rehearsalServer.houseGateway.IOperaHGateway;
import rehearsalServer.houseGateway.OperasHGatewayFactory;
import rehearsalServer.loginGateway.AuthRMIGateway;
import rehearsalServer.loginGateway.AuthorizationGatewayFactory;
import rehearsalServer.loginGateway.IAuthorizeGateway;
import rehearsalServer.loginGateway.ValidationException;

import util.observer.rmi.IRemoteObserver;
import util.observer.rmi.RemoteObservable;

public class OperaRehearsalServer extends UnicastRemoteObject implements IOperaRehearsalServer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * CACHE OF RehearsalRMIDTO objects, organized by Opera House Name To be
	 * loaded at the initialization process
	 */
	private util.observer.rmi.RemoteObservable rO = null;
	private static IAuthorizeGateway gateway = null;
	private IRehearsalServerDAO dao= new RehearsalServerDAO();
	private TreeMap<String, TreeMap<String, RehearsalRMIDTO>> rehearsalCache = null;
	private TreeMap<String,RehearsalRMIDTO> innerMap = null;
	
	public String login(String user, String pass) throws ValidationException,RemoteException {
  	    return   gateway.login(user, pass);	
	}
	
	public  OperaRehearsalServer() throws RemoteException, SQLException{
		super();
		rO=new RemoteObservable();
		rehearsalCache=getRehearsalCache();
	}
	  
	private TreeMap<String, TreeMap<String, RehearsalRMIDTO>> getRehearsalCache() throws SQLException  
	{
		rehearsalCache= new TreeMap<String,TreeMap<String, RehearsalRMIDTO>>();
		innerMap=new TreeMap<String,RehearsalRMIDTO>();
		////////////////////////////////////////////////////////
		OperasHGatewayFactory op= OperasHGatewayFactory.GetInstance();
		IOperaHGateway gateway = op.getOperaHGateway("ScalaMilano", "corba");
		//CorbaHouseGateway gate= gateway.
		 
		List<rehearsalServer.houseGateway.RehearsalDO> lista= new ArrayList<rehearsalServer.houseGateway.RehearsalDO>();
		rehearsalServer.houseGateway.RehearsalDO rehearsal = null;
		lista = gateway.getRehearsals();
		
		RehearsalServerDAO p= new RehearsalServerDAO();
		p.connect();
		for (int i=0;i<lista.size();i++){
			rehearsal = lista.get(i);
			String opera=rehearsal.getOperaName();
			int sitiosOcup=p.getReservationsCount("ScalaMilano",opera);
			int sitiosTotal=rehearsal.getAvailableSeats();
			RehearsalRMIDTO DTO= new RehearsalRMIDTO("ScalaMilano", opera, rehearsal.getDate(), sitiosTotal-sitiosOcup);
			innerMap.put(opera, DTO);
		}
		rehearsalCache.put("ScalaMilano", innerMap);
		p.disconnect();
		return rehearsalCache;
	}
	
	public void reserveSeat (String studName, String operaHouse, String operaName)throws java.rmi.RemoteException{
	   boolean place=true;
	   RehearsalRMIDTO r= null;
	   
	   try {
			dao.connect();
			innerMap=rehearsalCache.get(operaHouse);
			r = innerMap.get(operaName);
			if (r.getAvailableSeats()>0)
			{
				dao.reserveSeat(studName, operaHouse, operaName);
				
				r.decAvailableSeats(1);				
				innerMap.put(operaName, r);
				rehearsalCache.put(operaHouse, innerMap);
			}
			dao.disconnect();
			rO.notifyRemoteObservers(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	      
	}
	
	
	public static void main(String[] args) throws RemoteException, SQLException {
		
		OperaRehearsalServer opRehearsal=new OperaRehearsalServer();
		gateway = AuthorizationGatewayFactory.GetInstance().getAuthGateway("//" + args[0] + ":" + args[1] + "/" + args[2], "rmi");

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
			}
		
		String name ="//" + args[3] + ":" + args[4] + "/" + args[5];
		try {
		IOperaRehearsalServer objServidor = new OperaRehearsalServer();
		Naming.rebind(name, objServidor);
		}
		catch (Exception e) {
			System.err.println("exception: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println ("RehearsalReservationServer prepared and waiting requests...");
	}
	
		
	
	
	
	
	@Override
	public void addRemoteObserver(IRemoteObserver arg0) throws RemoteException {
		// TODO Auto-generated method stub
		rO.addRemoteObserver(arg0);
	}

	@Override
	public void deleteRemoteObserver(IRemoteObserver arg0)throws RemoteException {
		// TODO Auto-generated method stub
		rO.deleteRemoteObserver((arg0));
		
	}
	 public void notifyRemoteObservers(Object arg0)  throws RemoteException {
	 
	 rO.notifyRemoteObservers(arg0);
	 }
	 

	@Override
	public List<RehearsalRMIDTO> getRehearsals()throws java.rmi.RemoteException {
		
		List<RehearsalRMIDTO> list =new ArrayList<RehearsalRMIDTO>();
		for (Iterator it=rehearsalCache.values().iterator();it.hasNext();) {
			innerMap=(TreeMap<String,RehearsalRMIDTO>)it.next();
			for (Iterator it2=innerMap.values().iterator();it2.hasNext();) {
				list.add((RehearsalRMIDTO)it2.next());
			}
			
		}
		
		return list;
		
	}

}
