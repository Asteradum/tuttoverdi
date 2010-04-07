package rehearsalServer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	
	public void reduce (String operaHouse, String opera)
	{  int num=0;
		String query="SELECT SEATS from "+operaHouse+"where  OPERANAME='"+opera+"' ";
		   Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				String number=rs.toString();
				num = Integer.parseInt(number);
				num--;
				}
			String sentencia = " INSERT INTO "+operaHouse+"(SEAT) values ("+ num +")" ;
			Statement stmt1;
			stmt1 = con.createStatement();
			
			stmt1.executeUpdate(sentencia);
			stmt1.close();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
	public void reserveSeat(String studName, String operaHouse, String operaName) {
		
		
		String sentencia = " INSERT INTO ReservationsT VALUES('"+ studName+ "','"+operaHouse+"','"+operaName+"',"+ "14/02/2010)";
		Statement stmt;
		try {
			stmt = con.createStatement();
		
		stmt.executeUpdate(sentencia);
		stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	   public boolean placeAvailable (String opera, String operaHouse){ 

		   boolean place =true;
		   String query="SELECT SEATS from "+operaHouse+"where  OPERANAME='"+opera+"' ";
		   Statement stmt;
		try {
			stmt = con.createStatement();
		
			stmt.executeQuery(query);
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){			
				String number=rs.toString();
				if (number.equals("0")==true)
					place = false;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return place;
		   
	   }
	
	public void disconnect()throws SQLException{
		con.close();
	}

}
