package corbaServer.corba;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class ScalaMilanoServer {


	public static void main(String[] args) {
		String[] orb_args = {"-ORBInitialHost", args[0], "-ORBInitialPort", args[1]};
		ORB orb = ORB.init(orb_args,null);
		
		try {
			org.omg.CORBA.Object reference = orb.resolve_initial_references("RootPOA");
			POA poa = POAHelper.narrow(reference);
			poa.the_POAManager().activate();
			
			Server Server = new Server("scalaMilano");
			org.omg.CORBA.Object ref = poa.servant_to_reference(Server);
			ICorbaServer ScalaMilanoRef = ICorbaServerHelper.narrow(ref);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			String name = args[2];
			NameComponent[] path = ncRef.to_name(name);
			
			ncRef.rebind(path, ScalaMilanoRef);
			
			System.out.println ("ScalaMilanoServer prepared and waiting requests...");
			
			orb.run();
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
		
		

	}

}
