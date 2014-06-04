package us.ttyl.chance.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import us.ttyl.chance.common.*;
import us.ttyl.chance.domain.*;

public class Login extends GenericServlet 
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//get command mode
		String mode = request.getParameter(ChanceConfiguration.MODE);
	
		//process command mode
		if(mode != null && (mode.equals("") == false))
		{
			if(mode.equals(ChanceConfiguration.LOGIN))
			{
				login(request, response);
			}
		}		
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String userName = request.getParameter(ChanceConfiguration.USER_NAME);
		String password = request.getParameter(ChanceConfiguration.USER_PASSWORD);
		
		String hostName = request.getRequestURL().toString().toLowerCase();			
		if (hostName.indexOf("janechance") > -1)
		{
			hostName="www.janechance.com";	
		}
		else
		{
			hostName="www.johnchance.com";
		}
		
		try
		{
			UserManager um = UserManagmentFactory.getUserManager();
			User user = um.login(userName, password);
			if (user != null)
			{
				PageUtils.setUser(user, request);	
				if (user.getChangePasswordCode()!= null && user.getChangePasswordCode() == 1)
				{
					PageUtils.forwardToJspPage("/jsp/change_password.jsp", request.getSession().getServletContext(), request, response);							
				}
				else
				{
					//save user into session							
					PageUtils.setRecordIndex(0, request);
					
					//reset search parameters
					PageUtils.setSearchParameters(null, request);
					
					//go to search servlet
					response.sendRedirect("http://"+hostName+"/"+ChanceConfiguration.CONTEXT_ROOT+"search?mode=" + ChanceConfiguration.START_PAGE);
				}
			}
			else
			{
				//stay at login page, show error notes
				request.getSession().setAttribute("message", "Username or password values are invalid or blank. Please try again.");
				PageUtils.forwardToJspPage("/jsp/login.jsp", request.getSession().getServletContext(), request, response);							
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
			//error occured, stay at login page, show error notes
			request.getSession().setAttribute("message", "Username or password values are invalid of blank. Please try again.");
			PageUtils.forwardToJspPage("/jsp/login.jsp", request.getSession().getServletContext(), request, response);							
		}						
	}
}
