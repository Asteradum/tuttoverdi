package JMSOperaHouse;

import javax.naming.*;
import javax.jms.*;

import rehearsalServer.dao.IRehearsalServerDAO;
import rehearsalServer.dao.RehearsalServerDAO;
import rehearsalServer.houseGateway.RehearsalDO;



import JMSOperaHouse.dao.IJMSOperaHouseDAO;
import JMSOperaHouse.dao.JMSOperaHouseDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
public class JMSOperaHouseSender {
	
	private static IJMSOperaHouseDAO operaDao= new JMSOperaHouseDAO();

	 public static void main(String[] args) {
		 List<RehearsalJMSDTO> rehearsals=new ArrayList<RehearsalJMSDTO>();
		 try {
			operaDao.connect();
			rehearsals=operaDao.getRehearsals();
			 operaDao.disconnect();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		 
		 
		 
		 	String                  queueName = null;
	        Context                 jndiContext = null;
	        QueueConnectionFactory  queueConnectionFactory = null;
	        QueueConnection         queueConnection = null;
	        QueueSession            queueSession = null;
	        Queue                   queue = null;
	        QueueSender             queueSender = null;
	        ObjectMessage			objectMessage = null;
	        
	       
	       queueName=new String("JMSRehearsal");
	        try {
	           	Properties props = new Properties();
	        	props.load(new FileInputStream("jndi.properties"));
	            jndiContext = new InitialContext(props);
	        } catch (NamingException e) {
	            System.out.println("Could not create JNDI API context: " + e.toString());
	            System.exit(1);
	        }catch(IOException ioe){
	            System.out.println("Could not find properties file: " + ioe.toString());
	            System.exit(1);        	
	        }
	        
	        try {
	            queueConnectionFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
	            queue = (Queue) jndiContext.lookup(queueName);
	        } catch (NamingException e) {
	            System.out.println("JNDI API lookup failed: " + e.toString());
	            System.exit(1);
	        }

	        try {
	            queueConnection = queueConnectionFactory.createQueueConnection();
	            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	            queueSender = queueSession.createSender(queue);
	            objectMessage = queueSession.createObjectMessage();
	            
	            for (int i = 0; i < rehearsals.size(); i++) {            		
	            	RehearsalJMSDTO dto=new RehearsalJMSDTO(rehearsals.get(i).getOperaName(),rehearsals.get(i).getDate(),rehearsals.get(i).getSeats());
	            	System.out.println(dto.getOperaName());
	            	objectMessage.setObject(dto);
	            	System.out.println("Sending "+i+"º object message ");
	            		queueSender.send(objectMessage);
	            		System.out.println(objectMessage.toString());
	            }
	            
	            
	            queueSender.send(queueSession.createMessage());
	        } catch (JMSException e) {
	            System.out.println("Exception occurred Sending Messages: " + e.toString());
	        } finally {
	            if (queueConnection != null) {
	                try {
	                    queueConnection.close();
	                } catch (JMSException e) {
	                	System.out.println("Exception occurred closing the Queue Connection: " + e.toString());                	
	                }
	            }
	        }
	    }
	}

