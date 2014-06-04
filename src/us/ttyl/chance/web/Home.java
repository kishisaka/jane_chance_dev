package us.ttyl.chance.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.ttyl.chance.common.*;

public class Home extends GenericServlet
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
				goLogin(request, response);
			}				
		}				
	}
	
	public void goLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PageUtils.forwardToJspPage("/jsp/login.jsp", request.getSession().getServletContext(), request, response);
	}	
}
