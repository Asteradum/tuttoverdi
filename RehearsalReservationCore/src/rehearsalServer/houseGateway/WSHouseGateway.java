package rehearsalServer.houseGateway;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;

import rehearsalServer.houseGateway.proxies.EuskaldunaBioStub;
import rehearsalServer.houseGateway.proxies.EuskaldunaBioStub.RehearsalDTO;

public class WSHouseGateway implements IOperaHGateway {

	public List<RehearsalDO> getRehearsals() {
		EuskaldunaBioStub stub = null;
		try {
			stub = new EuskaldunaBioStub();
			RehearsalDTO[] list = stub.getRehearsals();
			List<RehearsalDO> returnList = new ArrayList<RehearsalDO>();
			RehearsalDO rDO= null;
			for (int i=0; i <list.length;i++){
				rDO= new RehearsalDO(list[i].getOperaName(),list[i].getDate(),list[i].getSeats());
				returnList.add(rDO);
			}
			return returnList;
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getServer() {
		// TODO Auto-generated method stub
		return null;
	}
}
