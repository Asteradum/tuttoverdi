package corbaServer.dao;

import java.sql.SQLException;

import java.util.List;

import corbaServer.RehearsalDO;

public class CorbaOperaHouseDAO implements ICorbaOperaHouseDAO {

	Connection con= null;

	public void connect() {
		
		try{
			Class.forName("org.sqlite.JDBC");
		}catch(ClassNotFoundException e){
			System.out.println(“Unable to load Driver Class”);
		}
		// This url is neccesary to change it if we want to make two server programming just one class?
		String url = "jdbc:sqlite:db/corba-db/scalaMilano.db";
		// We need to introduce de Login/Password?
		con = DriverManager.getConnection(url,"(Login)","(Password)");
		
	}

	public List<RehearsalDO> getRehearsals() throws SQLException {
		List<RehearsalDO> rehearsals = null;
		
		String query = "select * from RehearsalsT ";
		
		stmt.executeQuery(query);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){
			rs.getString("OperaName");
			rs.getString("RehearsalsDate");
			rs.getInt("Seats");
		}
		
		rs.close();
		stmt.close();
		
		return rehearsals;
	}

	public void disconnect() {
		con.close();
	}
}
