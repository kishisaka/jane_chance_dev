package us.ttyl.chance.common;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public abstract class Mailer 
{	
	public static boolean mailMessage(String subject, String content, String emailAddressTo)
	{
		try
		{
			//Get system properties
			Properties props = System.getProperties();

			//Setup mail server			
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", ChanceConfiguration.getM_smtpHost());
			props.setProperty("mail.port", ChanceConfiguration.getM_smtpPort());
			props.put("mail.smtp.auth", "true");
			props.setProperty("mail.user", ChanceConfiguration.getM_mailUser());
			props.setProperty("mail.password", ChanceConfiguration.getM_mailPassword());

			//Get session
			Session mailSession = Session.getDefaultInstance(props, null);
			
			//setup transport
			Transport transport = mailSession.getTransport("smtp");
			
			//create message
			MimeMessage message = new MimeMessage(mailSession);			
			message.setContent(content, "text/plain");
			message.setSubject(subject);
			Address toAddress = new InternetAddress(emailAddressTo);
			Address fromAddress = new InternetAddress(ChanceConfiguration.getM_mailSource());
			message.addRecipient(Message.RecipientType.TO, toAddress);
			message.setFrom(fromAddress);
						
			//send message using ref Sun impl
			transport.connect(ChanceConfiguration.getM_smtpHost()
					, Integer.parseInt(ChanceConfiguration.getM_smtpPort())
					, ChanceConfiguration.getM_mailUser()
					, ChanceConfiguration.getM_mailPassword());
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
