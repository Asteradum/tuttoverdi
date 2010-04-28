package rehearsalServer.saxParser;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class HouseGatewaysSAXParserHandler extends DefaultHandler {
	
		private GatewayObject gatewayObject;
		private String texto;
		private List<GatewayObject> listGateways=new ArrayList();
		
		
		
		public void startDocument() throws SAXException {
			System.out.println("# Comenzando el parseo del documento...");
			this.gatewayObject = new GatewayObject();
			this.texto = "";
		}

		public void endDocument() throws SAXException {
			System.out.println("# Parseo del documento finalizado.");
		}

		public void startElement(String namespaceURI, String lName, // local name
				String qName, // qualified nameqName contiene el nombre cualificado de la etiqueta encontrada. 
				Attributes attrs) throws SAXException { //es una estructura de datos que contiene la lista de atributos de la etiqueta encontrad

			System.out.println(" * [Etiqueta] -> <" + qName + ">");

			if (qName.equals("gateway")) {
				this.gatewayObject=new GatewayObject();
				this.listGateways.add(this.gatewayObject);
				System.out.println("Adding wateway...");
			} else if (qName.equals("detail")) {
				String name=(attrs.getValue("name"));
				System.out.println(name);
				this.gatewayObject.setDetails(name);
				
		}
			}

		public void endElement(String namespaceURI, String lName, String qName)
				throws SAXException {
			if (this.texto.length() > 0) {
				System.out.println("    - [Texto] -> " + texto);
				if (qName.equals("serviceName")) {
					this.gatewayObject.setServiceName(this.texto);
				} else if (qName.equals("technology")) {
					this.gatewayObject.setTechnology(this.texto);
				}  
			}

			this.texto = "";
			System.out.println(" * [Etiqueta] -> </" + qName + ">");
		}

		public void characters(char[] ch, int start, int length)
				throws SAXException {
			String s = String.valueOf(ch, start, length).trim();
			this.texto += s;
		}

		public void processingInstruction(String target, String data)
				throws SAXException {
			System.out.println(" * [PI] -> <?" + target + data + "?>");
		}

		//============================================================================
		// Métodos de DefaultHandler y pertenecimientes a ErrorHandler sobreescritos
		//============================================================================

		public void error(SAXParseException exception) throws SAXException {
			System.out.println("# [ERROR] -> " + exception.getMessage());
		}

		public void warning(SAXParseException exception) throws SAXException {
			System.out.println("# [WARNING] -> " + exception.getMessage());
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			System.out.println("# [FATAL ERROR] -> " + exception.getMessage());
		}

		//================
		// Métodos propios
		//================
		
		public ArrayList<GatewayObject> getGateways()
		{
			return (ArrayList<GatewayObject>) listGateways;
		}
		public GatewayObject getGateway() {
			return this.gatewayObject;
		}
	}


