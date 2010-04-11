package rehearsalServer;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import rehearsalServer.loginGateway.ValidationException;


public class RehearsalServerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			OperaRehearsalServer ors = new OperaRehearsalServer();
			
			try {
				ors.login("stud1", "1111");
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			List<RehearsalRMIDTO> list = ors.getRehearsals();
			RehearsalRMIDTO r= null;
			Iterator iter = list.iterator();
			while (iter.hasNext()){
			  r = (RehearsalRMIDTO) iter.next();
			  System.out.println(r.getOperaName() + "     " + r.getDate() + "     " + r.getAvailableSeats());
			}
			
			iter = list.iterator();
			while (iter.hasNext()){
			  r = (RehearsalRMIDTO) iter.next();
			  System.out.println(r.getOperaName() + "     " + r.getDate() + "     " + r.getAvailableSeats());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
