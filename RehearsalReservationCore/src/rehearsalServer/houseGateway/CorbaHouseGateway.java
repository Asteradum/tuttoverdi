package rehearsalServer.houseGateway;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import corbaServer.corba.ICorbaServer;
import corbaServer.corba.ICorbaServerHelper;
import corbaServer.corba.corbaServerRehearsalDTO;


public class CorbaHouseGateway implements IOperaHGateway {
	private String serverURL = null;
	private String serverIP = null;
	private String serverPort = null;
	private String serverName = null;
	

	public CorbaHouseGateway (String servURL){
		serverURL= servURL;
		StringTokenizer tokens = new StringTokenizer(serverURL, ":");  
			serverIP = tokens.nextToken();
			serverPort = tokens.nextToken();
			serverName = tokens.nextToken();
	}
	
	public List<RehearsalDO> getRehearsals() {
		List<RehearsalDO> result = new ArrayList<RehearsalDO>();
		try {
			String[] orb_args = {"-ORBInitialHost", serverIP , "-ORBInitialPort" , serverPort};
			ORB orb = ORB.init(orb_args,null);
			
			org.omg.CORBA.Object objRef =orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			ICorbaServer serverObject = ICorbaServerHelper.narrow(ncRef.resolve_str(serverName));
			corbaServerRehearsalDTO List[] = serverObject.getRehearsals();
			
		    for (int i=0;i<List.length;i++){
				RehearsalDO rDO= new RehearsalDO(List[i].operaName,List[i].date,List[i].seats);
				result.add(rDO);	
		    }
		    System.out.println("pasa por akiiiiiii");
			  
		}
		
		catch(Exception e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace(System.out);
			}
			
		 return result;	
		}

	@Override
	public String getServer() {
		// TODO Auto-generated method stub
		return this.serverName;
	}
		
}
