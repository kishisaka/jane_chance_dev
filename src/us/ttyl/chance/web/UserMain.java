package us.ttyl.chance.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.ttyl.chance.common.*;
import us.ttyl.chance.dao.LocationDAO;
import us.ttyl.chance.domain.*;

public class UserMain extends GenericServlet 
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
			if (mode.equals(ChanceConfiguration.START_PAGE))
			{
				startPage(request, response);
			}	
			if (mode.equals(ChanceConfiguration.START_LOOKING))
			{
				startLookingPage(request, response);
			}
			if (mode.equals(ChanceConfiguration.LOG_OUT))
			{
				logout(request, response);
			}
			if (mode.equals(ChanceConfiguration.LOGIN))
			{
				login(request, response);
			}	
			if (mode.equals(ChanceConfiguration.ADVANCED_SEARCH))
			{
				startAdvancedSearch(request, response);
			}
			if (mode.equals(ChanceConfiguration.ADVANCED_SEARCH_FIND_USERS))
			{
				searchAdvancedSearch(request, response);
			}
		}
	}
	
	public void startAdvancedSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			PageUtils.forwardToJspPage("/jsp/advanced_search.jsp", request.getSession().getServletContext(), request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void searchAdvancedSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			UserManager um = new UserManagerImpl();
			int startingRow = 0;
			User user = new User();
			
			int heightFeet = 0;
			int heightInches = 0;
			int heightFeetEnd = 0;
			int heightInchesEnd = 0;
			int heightFeetStart = 0;
			int heightInchesStart = 0;
			
			//process user
			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements())
			{
				String key = (String)e.nextElement();
				String value = request.getParameter(key);
				
				if (key.equals(ChanceConfiguration.HEIGHT_FEET))
				{
					try
					{
						heightFeet = Integer.parseInt(value);
					}
					catch(Exception f)
					{
						heightFeet = 0;
					}
				}
				if (key.equals(ChanceConfiguration.HEIGHT_INCHES))
				{
					try
					{
						heightInches = Integer.parseInt(value);
					}
					catch(Exception f)
					{
						heightInches = 0;
					}
				}
				if (key.equals(ChanceConfiguration.CATCH_PHRASE))
				{
					user.setCatchphrase(value);
				}
				if (key.equals(ChanceConfiguration.MARITAL_STATUS))
				{
					user.setMaritalStatus((TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(new Integer(value)));					
				}
				if (key.equals(ChanceConfiguration.WANTS_CHILDREN))
				{
					user.setWantChildern((TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(new Integer(value)));					
				}
				if (key.equals(ChanceConfiguration.SMOKE_LOOKING_FOR))
				{
					user.setIdealSmokes((TChoice)ChanceConfiguration.getM_choice().get(new Integer(value)));	
				}
				if (key.equals(ChanceConfiguration.HAS_CHILDREN_LOOKING_FOR))
				{
					user.setIdealHasChildern((THasChildren)ChanceConfiguration.getM_hasChildren().get(new Integer(value)));	
				}
				if (key.equals(ChanceConfiguration.SEX_LOOKING_FOR))
				{
					user.setSexLookingFor((TSex)ChanceConfiguration.getM_sex().get(new Integer(value)));	
				}
				if (key.equals(ChanceConfiguration.DRINK))
				{
					user.setDrink((TChoice)ChanceConfiguration.getM_choice().get(new Integer(value)));	
				}
				if (key.equals(ChanceConfiguration.MARITAL_STATUS_LOOKING_FOR))
				{
					user.setIdealMaritalStatus((TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(new Integer(value)));	
				}
				if (key.equals(ChanceConfiguration.ABOUT_ME))
				{
					user.setAboutme(value);
				}
				if (key.equals(ChanceConfiguration.HEIGHT_LOOKING_FOR_FEET_END))
				{					
					try
					{
						heightFeetEnd = Integer.parseInt(value);
					}
					catch(Exception f)
					{
						heightFeetEnd = 0;
					}
				}
				if (key.equals(ChanceConfiguration.HEIGHT_LOOKING_FOR_INCHES_END))
				{
					try
					{
						heightInchesEnd = Integer.parseInt(value);
					}
					catch(Exception f)
					{
						heightInchesEnd = 0;
					}
				}
				if (key.equals(ChanceConfiguration.BODY_TYPE))
				{
					user.setBodyType((TBodytype)ChanceConfiguration.getM_bodyType().get(new Integer(value)));
				}
				if (key.equals(ChanceConfiguration.SMOKE))
				{
					user.setSmoke((TChoice)ChanceConfiguration.getM_choice().get(new Integer(value)));
				}
				if (key.equals(ChanceConfiguration.SEX))
				{
					user.setSex((TSex)ChanceConfiguration.getM_sex().get(new Integer(value)));
				}
				if (key.equals(ChanceConfiguration.HAIR_COLOR))
				{
					user.setHairColor((TColor)ChanceConfiguration.getM_color().get(new Integer(value)));	
				}
				if (key.equals(ChanceConfiguration.WANTS_CHILDREN_LOOKING_FOR))
				{
					user.setIdealWantsChildern((TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(new Integer(value)));
				}
				if (key.equals(ChanceConfiguration.DRINK_LOOKING_FOR))
				{
					user.setIdealDrinks((TChoice)ChanceConfiguration.getM_choice().get(new Integer(value)));
				}
				if (key.equals(ChanceConfiguration.HEIGHT_LOOKING_FOR_FEET_START))
				{
					try
					{
						heightFeetStart = Integer.parseInt(value);
					}
					catch(Exception f)
					{
						heightFeetStart = 0;
					}
				}
				if (key.equals(ChanceConfiguration.HEIGHT_LOOKING_FOR_INCHES_START))
				{
					try
					{
						heightInchesStart = Integer.parseInt(value);
					}
					catch(Exception f)
					{
						heightInchesStart = 0;
					}
				}
				if (key.equals(ChanceConfiguration.ETHNICITY))
				{
					user.setEthnicity((TEthnicity)ChanceConfiguration.getM_ethnicity().get(new Integer(value)));
				}
				if (key.equals(ChanceConfiguration.EYE_COLOR))
				{
					user.setEyeColor((TColor)ChanceConfiguration.getM_color().get(new Integer(value)));	
				}
				if (key.equals(ChanceConfiguration.ZIPCODE))
				{								
					user.setZipcode((Zipcode)ChanceConfiguration.getM_zipcodeTable().get(new Integer(value)));
				}
				if (key.equals(ChanceConfiguration.HAS_CHILDREN))
				{
					user.setHaveChildern((THasChildren)ChanceConfiguration.getM_hasChildren().get(new Integer(value)));	
				}	
				
				if (key.equals(ChanceConfiguration.AGE_START))
				{
					user.setIdealAgeStart(new Integer(value));
				}
				
				if (key.equals(ChanceConfiguration.AGE_END))
				{
					user.setIdealAgeEnd(new Integer(value));
				}
				
				if (key.equals(ChanceConfiguration.DISTANCE))
				{
					user.setIdealDistance(new Integer(value));
				}
				
				if (key.equals(ChanceConfiguration.ZIPCODE_LOOKING_FOR))
				{
					if (value != null && value.length() > 0)
					{
						user.setIdealZipcode((Zipcode)ChanceConfiguration.getM_zipcodeTable().get(new Integer(value)));
					}
					else
					{
						user.setIdealZipcode((Zipcode)ChanceConfiguration.getM_zipcodeTable().get(new Integer(94085)));
						user.setIdealDistance(new Integer(100));
					}
				}
				
				// check for ethnicity keys (checkboxes)
				if (user.getIdealEthnicities() == null)
				{
					user.setIdealEthnicities(new ArrayList());
				}
				Enumeration ehtnicityKeys = ChanceConfiguration.getM_ethnicity().keys();
				while (ehtnicityKeys.hasMoreElements())
				{
					Integer ehtnicityKey = (Integer)ehtnicityKeys.nextElement();
					TEthnicity ethnicity = (TEthnicity)ChanceConfiguration.getM_ethnicity().get(ehtnicityKey);
					if (ethnicity.getEthnicityDesc().equals(key))
					{
						user.getIdealEthnicities().add(ethnicity);
					}
				}
				
				// check for bodyType keys (checkboxes)
				if (user.getIdealBodyTypes() == null)
				{
					user.setIdealBodyTypes(new ArrayList());
				}
				Enumeration bodyTypeKeys = ChanceConfiguration.getM_bodyType().keys();
				while (bodyTypeKeys.hasMoreElements())
				{
					Integer bodyKey = (Integer)bodyTypeKeys.nextElement();
					TBodytype bodyType = (TBodytype)ChanceConfiguration.getM_bodyType().get(bodyKey);
					if (bodyType.getBodytypeDesc().equals(key))
					{
						user.getIdealBodyTypes().add(bodyType);
					}
				}
				
				// check for lookingFor keys (checkboxes)
				if (user.getIdealLookingfor() == null)
				{
					user.setIdealLookingfor(new ArrayList());
				}
				Enumeration lookingForKeys = ChanceConfiguration.getM_lookingFor().keys();
				while (lookingForKeys.hasMoreElements())
				{
					Integer lookingForKey = (Integer)lookingForKeys.nextElement();
					TLookingfor lookingFor = (TLookingfor)ChanceConfiguration.getM_lookingFor().get(lookingForKey);
					if (lookingFor.getLookingforDesc().equals(key))
					{
						user.getIdealLookingfor().add(lookingFor);
					}
				}							
			}
				
			//process heights			
			user.setHeight((heightFeet * 12) + heightInches);			
			user.setIdealHeightStart((heightFeetStart * 12) + heightInchesStart);
			user.setIdealHeightEnd((heightFeetEnd * 12) + heightInchesEnd);
			
			PageList pageList = um.loadUsersForUser(user, startingRow, ChanceConfiguration.getM_pageSize());		
			PageUtils.setUserList(pageList, request);
			PageUtils.setRecordIndex(startingRow, request);
			PageUtils.setMode("advSearch", request);
			PageUtils.setAdvancedSearchUser(user, request);
			
			PageUtils.forwardToJspPage("/jsp/search.jsp", request.getSession().getServletContext(), request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			User user = PageUtils.getUser(request);
			
			UserManager um = new UserManagerImpl();
			if(user != null)
			{
				PageUtils.setNewUserMessageCount(request, um.getNewMessageCount(user));				
			}
			
			int startingRow = PageUtils.getRecordIndex(request) + ChanceConfiguration.getM_pageSize();			
			PageList users = null;
			if (PageUtils.getMode(request).equals("user"))
			{
				users= um.loadUsersForUser(user, startingRow, ChanceConfiguration.getM_pageSize());
			}
			else
			{
				if (PageUtils.getMode(request).equals("advSearch"))
				{
					users= um.loadUsersForUser(PageUtils.getAdvancedSearchUser(request), startingRow, ChanceConfiguration.getM_pageSize());
				}
				else
				{
					if (PageUtils.getMode(request).equals("basicSearch") && user != null)
					{
						SearchParameters sp = PageUtils.getSearchParameters(request); 				
						users = um.baseSearch(sp.getM_gender(), sp.getM_startAge(), sp.getM_endAge(), sp.getM_zipcode(), 
							sp.getM_distance(), startingRow, ChanceConfiguration.getM_pageSize(), user.getUserId());
					}
					else
					{
						SearchParameters sp = PageUtils.getSearchParameters(request); 	
						users = um.baseSearch(sp.getM_gender(), sp.getM_startAge(), sp.getM_endAge(), sp.getM_zipcode(), 
								sp.getM_distance(), startingRow, ChanceConfiguration.getM_pageSize(), -1);
					}				
				}
			}
			PageUtils.setUserList(users, request);
			PageUtils.setRecordIndex(startingRow, request);
			PageUtils.forwardToJspPage("/jsp/search.jsp", request.getSession().getServletContext(), request, response);
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
			
			UserManager um = new UserManagerImpl();
			if(user != null)
			{
				PageUtils.setNewUserMessageCount(request, um.getNewMessageCount(user));				
			}
			
			int startingRow = PageUtils.getRecordIndex(request) - ChanceConfiguration.getM_pageSize();
			if (startingRow < 0)
			{
				startingRow = 0;
			}
			PageList users = null;
			
			if (PageUtils.getMode(request).equals("user"))
			{
				users = um.loadUsersForUser(user, startingRow, ChanceConfiguration.getM_pageSize());
			}
			else
			{	
				if (PageUtils.getMode(request).equals("advSearch"))
				{
					users= um.loadUsersForUser(PageUtils.getAdvancedSearchUser(request), startingRow, ChanceConfiguration.getM_pageSize());
				}
				else
				{
					if (PageUtils.getMode(request).equals("basicSearch") && user != null)
					{
						SearchParameters sp = PageUtils.getSearchParameters(request); 				
						users = um.baseSearch(sp.getM_gender(), sp.getM_startAge(), sp.getM_endAge(), 
							sp.getM_zipcode(), sp.getM_distance(), startingRow, 
							ChanceConfiguration.getM_pageSize(), user.getUserId());
					}
					else
					{
						SearchParameters sp = PageUtils.getSearchParameters(request); 				
						users = um.baseSearch(sp.getM_gender(), sp.getM_startAge(), sp.getM_endAge(), 
							sp.getM_zipcode(), sp.getM_distance(), startingRow, 
							ChanceConfiguration.getM_pageSize(), -1);
					}
				}
			}			
			PageUtils.setUserList(users, request);
			PageUtils.setRecordIndex(startingRow, request);
			PageUtils.forwardToJspPage("/jsp/search.jsp", request.getSession().getServletContext(), request, response);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void startPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			User user = PageUtils.getUser(request);
			
			UserManager um = new UserManagerImpl();
			if(user != null)
			{
				PageUtils.setNewUserMessageCount(request, um.getNewMessageCount(user));				
			}
			
			int startingRow = 0;
			if (PageUtils.getSearchParameters(request) != null)
			{
				PageList pageList = null;
				SearchParameters params = PageUtils.getSearchParameters(request);
				if (params.getM_zipcode() != null && params.getM_zipcode().length() > 0)
				{								
					pageList = um.baseSearch(params.getM_gender(), params.getM_startAge(), 
							params.getM_endAge(), params.getM_zipcode(), params.getM_distance(), 
							startingRow, ChanceConfiguration.getM_pageSize(), -1);
				}
				else
				{
					PageUtils.setSearchParameters(new SearchParameters("", params.getM_gender(), params.getM_startAge(), params.getM_endAge(), 0), request);
					pageList = um.baseSearch(params.getM_gender(), params.getM_startAge(), 
							params.getM_endAge(), "", 0, startingRow, 
							ChanceConfiguration.getM_pageSize(), -1);
				}
				PageUtils.setUserList(pageList, request);
				PageUtils.setRecordIndex(startingRow, request);
				PageUtils.setMode("search", request);
				PageUtils.forwardToJspPage("/jsp/search.jsp", request.getSession().getServletContext(), request, response);
			}
			else
			{
				if (user != null)
				{						
					PageList pageList = um.loadUsersForUser(user, startingRow, ChanceConfiguration.getM_pageSize());		
					PageUtils.setUserList(pageList, request);
					PageUtils.setRecordIndex(startingRow, request);
					PageUtils.setMode("user", request);
					PageUtils.forwardToJspPage("/jsp/search.jsp", request.getSession().getServletContext(), request, response);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void startLookingPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{			
			int startingRow = 0;	
			//TODO: delete
			String referer = request.getHeader("referer");
			System.out.println("referer: " + referer);
			String errorNotice = "";
			
			PageUtils.setMode("basicSearch", request);
			
			int gender = Integer.parseInt(request.getParameter(ChanceConfiguration.GENDER));
			int ageStart = Integer.parseInt(request.getParameter(ChanceConfiguration.AGE_START));
			int ageEnd = Integer.parseInt(request.getParameter(ChanceConfiguration.AGE_END));				
			String zipcode = request.getParameter(ChanceConfiguration.ZIPCODE).trim();						
			if (zipcode != null && zipcode.length() > 0)
			{
				try
				{
					Zipcode zipcodeSrc = (Zipcode)ChanceConfiguration.getM_zipcodeTable().get(new Integer(zipcode));
					if (zipcodeSrc == null)
					{
						//set default zipcode if zipcode does not match						
						errorNotice = "Zipcode: "+zipcode+" doesn't exist. ";
						zipcode = "";
					}
				}
				catch (Exception e)
				{
					// set default zipcode if zipcode does not match 				
					errorNotice = "Zipcode: "+zipcode+" doesn't exist. ";
					zipcode = "";
				}
			}			
			int distance = Integer.parseInt(request.getParameter(ChanceConfiguration.DISTANCE));			
			PageList pageList = null;
			
			UserManager um = new UserManagerImpl();
			User user = PageUtils.getUser(request);
			if(user != null)
			{
				PageUtils.setNewUserMessageCount(request, um.getNewMessageCount(user));				
			}
			
			if (zipcode != null && zipcode.length() > 0)
			{			
				PageUtils.setSearchParameters(new SearchParameters(zipcode, gender, ageStart, ageEnd, distance), request);
				pageList = um.baseSearch(gender, ageStart, ageEnd, zipcode, distance, 
						startingRow, ChanceConfiguration.getM_pageSize(), -1);
			}
			else
			{
				PageUtils.setSearchParameters(new SearchParameters("", gender, ageStart, ageEnd, 0), request);
				pageList = um.baseSearch(gender, ageStart, ageEnd, "", 0, startingRow,
						ChanceConfiguration.getM_pageSize(), -1);
			}
			PageUtils.setUserList(pageList, request);
			PageUtils.setRecordIndex(startingRow, request);
			PageUtils.setMode("search", request);
			PageUtils.forwardToJspPage("/jsp/search.jsp?"+ChanceConfiguration.ERROR_NOTICE+"="+errorNotice, request.getSession().getServletContext(), request, response);
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
