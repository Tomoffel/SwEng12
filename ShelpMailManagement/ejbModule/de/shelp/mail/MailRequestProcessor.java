package de.shelp.mail;

import java.util.Properties;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJBException;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.jboss.logging.Logger;

/**
 * Message-Driven Bean implementation class for: OutputRequestProcessor
 *
 */
@MessageDriven(activationConfig = {
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/ShelpMail"),
	@ActivationConfigProperty(propertyName = "messageSelector", propertyValue = "DocType LIKE 'Letter'") })
public class MailRequestProcessor implements MessageListener {

    private static final Logger logger = Logger
	    .getLogger(MailRequestProcessor.class);

    @Override
    public void onMessage(javax.jms.Message messageNew) {

	final String username = "wildfly@thomassennekamp.de";
	final String password = "Testen,,12";

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "thomassennekamp.de");
	props.put("mail.smtp.port", "25");

	Session session = Session.getInstance(props,
		new javax.mail.Authenticator() {
		    protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		    }
		});

	try {
	    TextMessage msg = (TextMessage) messageNew;
	    String wholeMessage = msg.getText();

	    String[] parts = wholeMessage.split(";");
	    String text = parts[0]; // Text
	    String mailRecipient = parts[1]; // Mailadresse

	    Message message = new MimeMessage(session);
	    message.setFrom(new InternetAddress(
		    "wildfly-status@thomassennekamp.de"));
	    message.setRecipients(Message.RecipientType.TO,
		    InternetAddress.parse(mailRecipient));
	    message.setSubject("Statusmail Shelp");

	    message.setText(text);

	    Transport.send(message);

	    logger.info("Statusmail an " + mailRecipient + " gesendet! " + text);

	} catch (JMSException e) {
	    throw new EJBException(e);
	} catch (MessagingException en) {
	    throw new EJBException(en);

	}
    }

}
