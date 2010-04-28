package rehearsalServer.saxParser;

import java.util.ArrayList;
import java.util.List;

// One Gateway Object per gateway in the XML file
// For the 2nd assignment
/*The details needed
are:
1. The name of the service, whichever it is.
2. The connection details, whichever they are.
3. The technology used, ‘corba’, ‘ws’ or ‘jms’.*/


public class GatewayObject {
	private String serviceName;
	private String technology;
	private  List<String> details= new ArrayList<String>();;
	
	
	public GatewayObject() {
		super();
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(String detailName) {
		this.details.add(detailName);
	}
	
	
	
	

}
