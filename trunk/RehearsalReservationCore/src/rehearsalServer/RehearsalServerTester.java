package rehearsalServer;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import corbaServer.RehearsalDO;

public class RehearsalServerTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			OperaRehearsalServer ors = new OperaRehearsalServer();
			
			List<RehearsalRMIDTO> list = ors.getRehearsals();
			RehearsalRMIDTO r= null;
			Iterator iter = list.iterator();
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
