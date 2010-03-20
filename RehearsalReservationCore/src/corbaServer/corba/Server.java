package corbaServer.corba;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import corbaServer.RehearsalDO;
import corbaServer.dao.CorbaOperaHouseDAO;

public class Server extends ICorbaServerPOA {

	private String DBName= null;
	
	public Server(String DBName){
		this.DBName=DBName;
	}
	
	public corbaServerRehearsalDTO[] getRehearsals() throws DBErrorException{
		CorbaOperaHouseDAO bd =new CorbaOperaHouseDAO();
		List<RehearsalDO> list= null;
		RehearsalDO r = null;
		corbaServerRehearsalDTO AList[] = null;
		
		try {
			bd.connect(DBName);
		} catch (SQLException e) {
			throw new DBErrorException("Database Connection Error", "Unable to connect to Database: " + DBName);
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
			throw new DBErrorException("Database Error", "Unable to recover data from: " + DBName + " Database");
		}
		
		try {
			bd.disconnect();
		} catch (SQLException e) {
			throw new DBErrorException("Database Connection closing Error", "Unable to close correctly the DataBase connection");
		}
		return AList;
	}

	}
