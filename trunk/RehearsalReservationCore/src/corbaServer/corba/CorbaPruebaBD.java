package corbaServer.corba;

import java.sql.SQLException;

import corbaServer.dao.CorbaOperaHouseDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rehearsalServer.houseGateway.CorbaHouseGateway;

import corbaServer.RehearsalDO;


public class CorbaPruebaBD {
	public static void main(String[] args) throws SQLException{
		CorbaOperaHouseDAO bd =new CorbaOperaHouseDAO();
		List<RehearsalDO> lista= null;
		RehearsalDO r = null;
		
		bd.connect("scalaMilano");
		lista = bd.getRehearsals();
		Iterator iter = lista.iterator();
		while (iter.hasNext()){
		  r = (RehearsalDO) iter.next();
		  System.out.println(r.getOperaName() + "     " + r.getDate() + "     " + r.getSeats());
		}
		
		bd.disconnect();
		
		// Aprovecho este main para hacer prueba con los metodos de la clase Server
		
		Server Server = new Server("scalaMilano");
		corbaServerRehearsalDTO AList[] = Server.getRehearsals();
		
		for (int i=0;i<AList.length;i++){
			System.out.println(AList[i].operaName + "     " + AList[i].date  + "     " + AList[i].seats);
			  
		}
		
		// Aprovecho este main para hacer prueba con los metodos de la clase CorbaHouseGateway
		
		CorbaHouseGateway gate = new CorbaHouseGateway("scalaMilano");
		List<rehearsalServer.houseGateway.RehearsalDO> lista2= new ArrayList<rehearsalServer.houseGateway.RehearsalDO>();
		rehearsalServer.houseGateway.RehearsalDO r2 = null;
		
		lista2 = gate.getRehearsals();
		for (int i=0;i<lista2.size();i++){
		  r2 = lista2.get(i);
		  System.out.println(r2.getOperaName() + "     " + r2.getDate() + "     " + r2.getAvailableSeats());
		}
	}

}
