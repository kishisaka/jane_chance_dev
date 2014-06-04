package us.ttyl.chance.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.ttyl.chance.common.*;
import us.ttyl.chance.domain.*;

public class Profiles extends GenericServlet 
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//get command mode
		String mode = request.getParameter("mode");
	
		//process command mode
		if(mode != null && (mode.equals("") == false))
		{						
			if (mode.equals(ChanceConfiguration.FORWARD_PAGE))
			{
				forwardPage(request, response);
			}
			if (mode.equals(ChanceConfiguration.PREVIOUS_PAGE))
			{
				backwardPage(request, response);
			}
			if (mode.equals(ChanceConfiguration.VIEW_PROFILES))
			{
				viewProfiles(request, response);
			}	
			if (mode.equals(ChanceConfiguration.LOG_OUT))
			{
				logout(request, response);
			}
			if (mode.equals(ChanceConfiguration.LOGIN))
			{
				login(request, response);
			}
			if (mode.equals(ChanceConfiguration.SAVE_PROFILE))
			{
				saveProfile(request, response);
			}
		}
	}
	
	public void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			User user = PageUtils.getUser(request);			
			int startingRow = PageUtils.getRecordIndex(request) + ChanceConfiguration.getM_pageSize();			
			UserManager um = new UserManagerImpl();
			PageList users = null;
			users= um.findProfiles(user, startingRow, ChanceConfiguration.getM_pageSize());
			PageUtils.setUserList(users, request);
			PageUtils.setRecordIndex(startingRow, request);
			PageUtils.forwardToJspPage("/jsp/view_saved_profiles.jsp", request.getSession().getServletContext(), request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void backwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			User user = PageUtils.getUser(request);
			int startingRow = PageUtils.getRecordIndex(request) - ChanceConfiguration.getM_pageSize();
			if (startingRow < 0)
			{
				startingRow = 0;
			}
			UserManager um = new UserManagerImpl();
			PageList users = null;
			users = um.findProfiles(user, startingRow, ChanceConfiguration.getM_pageSize());			
			PageUtils.setUserList(users, request);
			PageUtils.setRecordIndex(startingRow, request);
			PageUtils.forwardToJspPage("/jsp/view_saved_profiles.jsp", request.getSession().getServletContext(), request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void viewProfiles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			User user = PageUtils.getUser(request);
			int startingRow = 0;		
			UserManager um = new UserManagerImpl();
			PageList pageList = um.findProfiles(user, startingRow, ChanceConfiguration.getM_pageSize());		
			PageUtils.setUserList(pageList, request);
			PageUtils.setRecordIndex(startingRow, request);
			PageUtils.setMode("user", request);
			PageUtils.forwardToJspPage("/jsp/view_saved_profiles.jsp", request.getSession().getServletContext(), request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void saveProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{						
			User user = PageUtils.getUser(request);
			UserManager ua = UserManagmentFactory.getUserManager();
			User targetUser = ua.findUserById(new Integer(request.getParameter("userid")));
			ua.saveProfile(user, targetUser);
			PageUtils.forwardToJspPage("/search?mode=start", request.getSession().getServletContext(), request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{			
			PageUtils.setUser(null, request);
			PageUtils.forwardToJspPage("/index.jsp", request.getSession().getServletContext(), request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{			
			PageUtils.forwardToJspPage("/jsp/login.jsp", request.getSession().getServletContext(), request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
}

