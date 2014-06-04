package us.ttyl.chance.dao.test;

import us.ttyl.chance.common.*;

public class MailTester 
{
	public static void main(String args[])
	{
		new MailTester();
	}
	
	public MailTester()
	{
		ChanceConfiguration.setM_mailSource("admin@johnchance.com");		
		System.out.println("getM_mailSource: " + ChanceConfiguration.getM_mailSource());
		
		ChanceConfiguration.setM_mailUser("admin@johnchance.com");		
		System.out.println("getM_mailUser: " + ChanceConfiguration.getM_mailUser());
		
		ChanceConfiguration.setM_mailPassword("admin1234");		
		System.out.println("getM_mailPassword: " + ChanceConfiguration.getM_mailPassword());
		
		ChanceConfiguration.setM_smtpHost("smtpout.secureserver.net");
		System.out.println("getM_smtpHost: " + ChanceConfiguration.getM_smtpHost());
		
		ChanceConfiguration.setM_smtpPort("25");
		System.out.println("getM_smtpPort: " + ChanceConfiguration.getM_smtpPort());
		
		Mailer.mailMessage("test3", "test3", "kurt.ishisaka@gmail.com");
	}	
}
