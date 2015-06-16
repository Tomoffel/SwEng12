package de.shelp.integration;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;

import org.jboss.logging.Logger;

/**
 * Session Bean implementation class OutputRequesterBean
 */
@Stateless
@LocalBean
public class MailRequesterBean {

    private static final Logger LOGGER = Logger
	    .getLogger(MailRequesterBean.class);

    @Resource(mappedName = "java:/JmsXA")
    private ConnectionFactory jmsFactory;

    @Resource(mappedName = "java:/queue/ShelpMail")
    private Queue mailQueue;

    /**
     * Sends a Message with the letter text to the output queue, assuming that
     * this causes the letter to be processed and printed.
     * 
     * @param letter
     */
    public void printLetter(String letter) {
	try (JMSContext context = jmsFactory
		.createContext(JMSContext.AUTO_ACKNOWLEDGE)) {
	    TextMessage message = context.createTextMessage();
	    message.setStringProperty("DocType", "Letter");
	    message.setText(letter);
	    context.createProducer().send(mailQueue, message);
	} catch (JMSException e) {
	    LOGGER.error("Fehler beim E-Mail verschicken", e);
	}
    }

}
