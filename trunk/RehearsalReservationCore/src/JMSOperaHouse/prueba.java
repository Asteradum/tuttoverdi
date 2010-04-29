package JMSOperaHouse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rehearsalServer.houseGateway.RehearsalDO;

public class prueba {
	
	public static void main(String[] args) {
		String                  queueName = null;
        Context                 jndiContext = null;
        QueueConnectionFactory  queueConnectionFactory = null;
        QueueConnection         queueConnection = null;
        QueueSession            queueSession = null;
        Queue                   queue = null;
        QueueReceiver           queueReceiver = null;
        ObjectMessage           objectMessage = null;
                
        
        queueName = new String("JMSRehearsal");
        System.out.println("Queue name is " + queueName);
        
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
            queueReceiver = queueSession.createReceiver(queue);
            queueConnection.start();
            while (true) {
            	Message m = queueReceiver.receive(1);
                objectMessage = (ObjectMessage) m;
                RehearsalJMSDTO dto =  (RehearsalJMSDTO) objectMessage.getObject();
                System.out.println("lalalalal");
                System.out.println("Reading message: " + dto.getDate() + ", " + dto.getOperaName());
                RehearsalDO DO = new RehearsalDO (dto.getOperaName(),dto.getDate(),dto.getSeats());
                System.out.println(DO.getOperaName());
            }
        } catch (JMSException e) {
            System.out.println("Exception occurred: " + e.toString());
        } finally {
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (JMSException e) {}
            }
        }
		
	}


		

	


}