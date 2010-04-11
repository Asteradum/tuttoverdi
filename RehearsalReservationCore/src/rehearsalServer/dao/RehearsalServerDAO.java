package rehearsalServer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public List<String> getOperaHouse() throws SQLException{
		List<String> lista=new ArrayList();
		String query = "select OPERAHOUSE  from ReservationsT";
		Statement stmt = con.createStatement();
		stmt.executeQuery(query);
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()){			
			String nombre = rs.getString("OPERANAME");
			lista.add(nombre); 
		}
		rs.close();
		stmt.close();
		return lista;
	}
	
	public void reserveSeat(String studName, String operaHouse, String operaName) throws SQLException {	
		
		String sentencia = "INSERT INTO ReservationsT VALUES('"+ studName+ "','"+operaHouse+"','"+operaName+"'," + new SimpleDateFormat("dd/MM/yyyy").format(new Date()) + ")";
		Statement stmt;
			stmt = con.createStatement();
			stmt.executeUpdate(sentencia);
			stmt.close();	
	}
	

	public void disconnect()throws SQLException{
		con.close();
	}
}
