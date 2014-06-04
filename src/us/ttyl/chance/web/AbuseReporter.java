package us.ttyl.chance.web;

import java.io.IOException;
import us.ttyl.chance.common.*;
import us.ttyl.chance.domain.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AbuseReporter extends GenericServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String mode = request.getParameter("mode");
		if(mode != null && (mode.equals("") == false))
		{
			if (mode.equals(ChanceConfiguration.ABUSE_SEND_PAGE))
			{
				sendAbuseReport(request, response);
			}			
		}
	}
	
	public void sendAbuseReport(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		String abuserUserNames = request.getParameter(ChanceConfiguration.ABUSER_USER_NAME);
		String abuserDescription = request.getParameter(ChanceConfiguration.ABUSER_DESCRIPTION);
		User user = PageUtils.getUser(request);
		String reporterName = "not logged in";
		if (user != null)
		{
			reporterName = user.getUserName();
		}
		System.out.println(abuserUserNames + ":" + abuserDescription + ":" + reporterName);
		Mailer.mailMessage("ABUSE REPORT from (" + reporterName + ") at " + request.getRemoteAddr()
				,"\n abuser description \n" + abuserDescription + "\n\n abuser names\n" + abuserUserNames + "\n"
				, "kurt.ishisaka@gmail.com");
		
		PageUtils.forwardToJspPage("/jsp/abuse_report_result_page.jsp", request.getSession().getServletContext(), request, response);
	}
}
