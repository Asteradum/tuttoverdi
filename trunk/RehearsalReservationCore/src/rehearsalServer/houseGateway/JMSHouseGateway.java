package rehearsalServer.houseGateway;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import JMSOperaHouse.RehearsalJMSDTO;

public class JMSHouseGateway implements IOperaHGateway {
	private String serverURL = null;
	private String serverName= null;
	private String queueName = null;
	
	public JMSHouseGateway (String servURL){
		serverURL= servURL;
		StringTokenizer tokens = new StringTokenizer(servURL,":");  
		queueName= tokens.nextToken();
		serverName = tokens.nextToken();
	}
	
	public List<RehearsalDO> getRehearsals() {
		
		List<RehearsalDO> rehearsals = null;
		rehearsals=receiver(this.queueName);
		return rehearsals;
	}

	public List<RehearsalDO> receiver(String args)
	
	{
		List<RehearsalDO> rehearsalList = new ArrayList<RehearsalDO>();
		
		String                  queueName = null;
        Context                 jndiContext = null;
        QueueConnectionFactory  queueConnectionFactory = null;
        QueueConnection         queueConnection = null;
        QueueSession            queueSession = null;
        Queue                   queue = null;
        QueueReceiver           queueReceiver = null;
        ObjectMessage           objectMessage = null;
                
        
        //queueName = new String(args[0]);
        queueName= new String("JMSRehearsal");
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
            int i=0;
            while (i==0) {
                Message m = queueReceiver.receive(1);
                if (m != null) {
                	i=1;
            		if (m instanceof ObjectMessage) {
            			objectMessage = (ObjectMessage) m;
		                RehearsalJMSDTO dto =  (RehearsalJMSDTO) objectMessage.getObject();
		                System.out.println("Reading message: " + dto.getDate() + ", " + dto.getOperaName());
		                RehearsalDO DO = new RehearsalDO (dto.getOperaName(),dto.getDate(),dto.getSeats());
		                rehearsalList.add(DO);
		                System.out.println(rehearsalList.get(0).getDate()+"message has been added");
		                i=0;
            		  }
            		}
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
        return rehearsalList;
	}

	
		

	@Override
	public String getServer() {
		
		// TODO Auto-generated method stub
		return this.serverName;
	}

}
