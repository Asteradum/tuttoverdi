package rehearsalServer.saxParser;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class prueba {
	static List<GatewayObject> gatewaysXML;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SAXParserFactory factory = SAXParserFactory.newInstance();        
	    factory.setValidating(true);
	    
	    try {
	    	System.out.println("ANALISIS DE UN DOCUMENTO XML USANDO SAX");
			System.out.println("-------- -- -- --------- --- ------ ---");            
	    	SAXParser saxParser = factory.newSAXParser();
	        HouseGatewaysSAXParserHandler handler = new HouseGatewaysSAXParserHandler();                        
	        saxParser.parse("src/rehearsalServer/saxParser/Gateway.xml", handler);
	        
	         gatewaysXML = handler.getGateways();
	    } catch (Exception e) {
	        System.out.println("Error -> Main():" + e.getMessage());
	        e.printStackTrace();}
	      for  (int i=0; i<gatewaysXML.size();i++){
	    	  System.out.println("LALALAL"+gatewaysXML.get(i).getDetails());
	    	 }

	    }}
