package corbaServer.corba;

import java.sql.SQLException;

import corbaServer.dao.CorbaOperaHouseDAO;

import java.util.Iterator;
import java.util.List;

import corbaServer.RehearsalDO;


public class CorbaPruebaBD {
	public static void main(String[] args) throws SQLException{
		CorbaOperaHouseDAO bd =new CorbaOperaHouseDAO();
		List<RehearsalDO> lista= null;
		RehearsalDO r = null;
		
		bd.connect();
		lista = bd.getRehearsals();
		Iterator iter = lista.iterator();
		while (iter.hasNext()){
		  r = (RehearsalDO) iter.next();
		  System.out.println(r.getOperaName() + "     " + r.getDate() + "     " + r.getSeats());
		}
		
		bd.disconnect();
	}

}
