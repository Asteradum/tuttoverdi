package corbaServer.dao;

// ADDITIONAL OPERATIONS MAY BE NEEDED 
import java.sql.SQLException;

import java.util.List;

import corbaServer.RehearsalDO;

public interface ICorbaOperaHouseDAO {
	public void connect(String DBName) throws SQLException;

	public List<RehearsalDO> getRehearsals() throws SQLException;

	public void disconnect() throws SQLException;
}
