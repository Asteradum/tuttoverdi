package rehearsalServer.saxParser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class HouseGatewaysSAXHandler extends DefaultHandler {
	
	private GatewayObject gatewayObject;
		private String texto;
		
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

		/*	if (qName.equals("gateway")) {
				this.gatewayObject.(attrs.getValue("prioridad"));
				System.out.println("    - [Atributo] -> prioridad='"+ attrs.getValue("prioridad") + "'");
			} else if (qName.equals("fecha")) {
				this.mensaje.setDia(attrs.getValue("dia"));
				this.mensaje.setHora(attrs.getValue("hora"));
				System.out.println("    - [Atributo] -> dia='"+ attrs.getValue("dia") + "'");
				System.out.println("    - [Atributo] -> hora='"+ attrs.getValue("hora") + "'");*/
			
		}

		public void endElement(String namespaceURI, String lName, String qName)
				throws SAXException {
			if (this.texto.length() > 0) {
				System.out.println("    - [Texto] -> " + texto);
				if (qName.equals("serviceName")) {
					this.gatewayObject.setServiceName(this.texto);
				} else if (qName.equals("technology")) {
					this.gatewayObject.setTechnology(this.texto);
				} else if (qName.equals("details")) {
					this.gatewayObject.addDetails(this.texto);
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
		public GatewayObject getGateway() {
			return this.gatewayObject;
		}
	}


