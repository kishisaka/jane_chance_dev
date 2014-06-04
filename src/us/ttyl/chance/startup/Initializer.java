package us.ttyl.chance.startup;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import java.util.*;

import us.ttyl.chance.dao.*;
import us.ttyl.chance.domain.Zipcode;
import us.ttyl.chance.common.*;
import us.ttyl.chance.hibernate.*;

import org.hibernate.*;

public class Initializer extends HttpServlet
{	
	public void init() throws ServletException
	{
		Hashtable params = new Hashtable();
		params.put("image_directory", getInitParameter("image_directory"));
		params.put("smtp_host", getInitParameter("smtp_host"));
		params.put("mail_source", getInitParameter("mail_source"));
		params.put("mail_user", getInitParameter("mail_user"));
		params.put("mail_password", getInitParameter("mail_password"));
		params.put("hour", getInitParameter("hour"));
		params.put("sleep_interval", getInitParameter("sleep_interval"));
		params.put("smtp_port", getInitParameter("smtp_port"));
		SystemStartup.startSystem(params);
	}
}
