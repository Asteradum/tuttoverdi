package euskaldunaBioWS;

import javax.sql.*;
import java.sql.*;
import javax.naming.*;



public class EuskaldunaBioWS {

	private RehearsalDTO list[] = null;
	
	public EuskaldunaBioWS() {
		setRehearsals();
	}
	
	private  void setRehearsals(){
		Connection con =null;
		Statement stmt = null;
		ResultSet rs = null;
		RehearsalDTO r = null;
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource) envCtx.lookup("jdbc/euskaldunaBioDB");
			con = ds.getConnection();
			stmt = con.createStatement();
			String sql = "select count(*) from rehearsalsT";
			rs = stmt.executeQuery(sql);
			list = new RehearsalDTO[rs.getInt(1)];
			sql = "select * from rehearsalsT";
			rs = stmt.executeQuery(sql);
			
			int i=0;
			while(rs.next()){			
				r = new RehearsalDTO(rs.getString(1),rs.getString(2),rs.getInt(3));
				list[i]= r; 
				i++;
			}

				
			con.close();
			stmt.close();
			rs.close();
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public RehearsalDTO[] getRehearsals(){

		return list;
	}
}
