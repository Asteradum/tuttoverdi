package corbaServer.corba;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import corbaServer.RehearsalDO;
import corbaServer.dao.CorbaOperaHouseDAO;

public class Server extends ICorbaServerPOA {

	private String DBName= null;
	
	Server(String DBName){
		this.DBName=DBName;
	}
	
	public corbaServerRehearsalDTO[] getRehearsals(){
		CorbaOperaHouseDAO bd =new CorbaOperaHouseDAO();
		List<RehearsalDO> list= null;
		RehearsalDO r = null;
		corbaServerRehearsalDTO AList[] = null;
		
		try {
			bd.connect(DBName);
		} catch (SQLException e) {
			//We have to change this
			e.printStackTrace();
		}
		
		try {
			list = bd.getRehearsals();
			AList = new corbaServerRehearsalDTO[list.size()];
			Iterator iter = list.iterator();
			int i=0;
			while (iter.hasNext()){
			  r = (RehearsalDO) iter.next();
			  AList[i]= new corbaServerRehearsalDTO(r.getOperaName(),r.getDate(),r.getSeats());
			  i++;
			}
			
		} catch (SQLException e) {
			//We have to change this
			e.printStackTrace();
		}
		
		try {
			bd.disconnect();
		} catch (SQLException e) {
			//We have to change this
			e.printStackTrace();
		}
		return AList;
	}

	}
