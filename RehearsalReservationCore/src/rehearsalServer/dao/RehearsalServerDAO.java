package rehearsalServer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import corbaServer.RehearsalDO;

public class RehearsalServerDAO implements IRehearsalServerDAO{
	Connection con= null;
	public void connect() throws SQLException{
		try{
			Class.forName("org.sqlite.JDBC");
		}catch(ClassNotFoundException e){
			System.out.println("Unable to load Driver Class");
		}
		// This url is neccesary to change it if we want to make two server programming just one class?
		String url = "jdbc:sqlite:db/rmi-db/reservations.db";
		// We need to introduce de Login/Password?
		con = DriverManager.getConnection(url,"(Login)","(Password)");
	}
	
	public int getReservationsCount(String operaHouse, String operaName) throws SQLException{
		int i=0;
		String query = "select  count (*) from ReservationsT where  OPERAHOUSE='"+operaHouse+"' and OPERANAME='"+operaName+"'";
		Statement stmt = con.createStatement();
		stmt.executeQuery(query);
		ResultSet rs = stmt.executeQuery(query);
		i=rs.getInt(1);
		rs.close();
		stmt.close();
		return i;
	}

	public void reserveSeat(String studName, String operaHouse, String operaName) throws SQLException{
		
		
		String sentencia = " INSERT INTO ReservationsT VALUES('"+ studName+ "','"+operaHouse+"','"+operaName+"',"+ "14/02/2010)";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sentencia);
		stmt.close();
	}

	
	public void disconnect()throws SQLException{
		con.close();
	}

}
