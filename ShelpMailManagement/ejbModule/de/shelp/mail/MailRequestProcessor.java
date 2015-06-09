package de.shelp.mail;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;


/**
 * Message-Driven Bean implementation class for: OutputRequestProcessor
 *
 */
@MessageDriven(
		activationConfig = {  
			 @ActivationConfigProperty(
			      propertyName = "destinationType",
			      propertyValue = "javax.jms.Queue"),
			 @ActivationConfigProperty(
			      propertyName = "destination",
			      propertyValue = "queue/ShelpMail"),
			 @ActivationConfigProperty(
			      propertyName = "messageSelector",
			      propertyValue = "DocType LIKE 'Letter'") })
public class MailRequestProcessor implements MessageListener {

	private static final Logger logger = Logger.getLogger(MailRequestProcessor.class);
	
	@Override
	public void onMessage(Message message) {
       try {
    	  TextMessage msg = (TextMessage) message;
          logger.info("Received message from queue/ShelpMail: " + msg.getText());
       }
       catch (JMSException e) {
            throw new EJBException(e);
       }
    }

}
