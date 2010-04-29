package JMSOperaHouse.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import JMSOperaHouse.RehearsalJMSDTO;



public class JMSOperaHouseDAO implements IJMSOperaHouseDAO {
Connection con= null;
	
	public void connect() throws SQLException{
		try{
			Class.forName("org.sqlite.JDBC");
		}catch(ClassNotFoundException e){
			System.out.println("Unable to load Driver Class");
		}
		// This url is neccesary to change it if we want to make two server programming just one class?
		String url = "jdbc:sqlite:db/jms-db/royalOperaLondon.db";
		con = DriverManager.getConnection(url,"(Login)","(Password)");
	}
	
	public List<RehearsalJMSDTO> getRehearsals() throws SQLException {
		List<RehearsalJMSDTO> rehearsals = new ArrayList();
		RehearsalJMSDTO r = null;
		String query = "select * from RehearsalsT ";
		
		Statement stmt = con.createStatement();
		stmt.executeQuery(query);
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()){			
			r = new RehearsalJMSDTO(rs.getString("OPERANAME"),rs.getString("REHEARSALDATE"),rs.getInt("SEATS"));
			rehearsals.add(r); 
		}
		
		rs.close();
		stmt.close();
		
		return rehearsals;
	}
		
	

	public void disconnect()throws SQLException{
		con.close();
	}

	
}


