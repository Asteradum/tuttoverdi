package rehearsalServer.dao;

import java.sql.SQLException;

public interface IRehearsalServerDAO {
	public void connect()throws SQLException;;

	public int getReservationsCount(String operaHouse, String operaName)throws SQLException;

	public void reserveSeat(String studName, String operaHouse, String operaName) throws SQLException;
	
	public void disconnect() throws SQLException;
}
