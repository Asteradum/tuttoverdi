package corbaServer.dao;

import java.sql.*;


import java.util.ArrayList;
import java.util.List;

import corbaServer.RehearsalDO;

public class CorbaOperaHouseDAO implements ICorbaOperaHouseDAO {

	Connection con= null;

	public void connect() throws SQLException {
		
		try{
			Class.forName("org.sqlite.JDBC");
		}catch(ClassNotFoundException e){
			System.out.println("Unable to load Driver Class");
		}
		// This url is neccesary to change it if we want to make two server programming just one class?
		String url = "jdbc:sqlite:db/corba-db/scalaMilano.db";
		// We need to introduce de Login/Password?
		con = DriverManager.getConnection(url,"(Login)","(Password)");
		
	}

	public List<RehearsalDO> getRehearsals() throws SQLException {
		List<RehearsalDO> rehearsals = new ArrayList();
		RehearsalDO r = null;
		String query = "select * from RehearsalsT ";
		
		Statement stmt = con.createStatement();
		stmt.executeQuery(query);
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){			
			r = new RehearsalDO(rs.getString("OPERANAME"),rs.getString("REHEARSALDATE"),rs.getInt("SEATS"));
			rehearsals.add(r); 
		}
		
		rs.close();
		stmt.close();
		
		return rehearsals;
	}

	public void disconnect() throws SQLException {
		con.close();
	}
}
