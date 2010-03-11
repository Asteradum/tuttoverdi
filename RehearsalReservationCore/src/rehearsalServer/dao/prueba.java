package rehearsalServer.dao;

import java.sql.SQLException;

public class prueba {
	public static void main(String[] args) throws SQLException{
		
		RehearsalServerDAO p= new RehearsalServerDAO();
		p.connect();
		int filasOcup=p.getReservationsCount("ScalaMILANO", "Nabucco");
		System.out.println("numero de filas reservadas: " + filasOcup);
		p.reserveSeat("Sonia pierola","ScalaMILANOs", "Nabucco");
		System.out.println("numero de filas reservadas: " + p.getReservationsCount("ScalaMILANO", "Nabucco"));
		p.disconnect();

}}
