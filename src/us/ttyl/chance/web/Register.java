package us.ttyl.chance.web;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.ttyl.chance.common.*;
import us.ttyl.chance.domain.*;
import us.ttyl.chance.dao.*;

public class Register extends GenericServlet
{
	private static Integer DEFAULT_ID = new Integer(99);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException 
	{
		//get command mode
		String mode = request.getParameter("mode");
	
		//process command mode
		if(mode != null && (mode.equals("") == false))
		{						
			if (mode.equals(ChanceConfiguration.REGISTER))
			{
				goRegistrationPage(request, response);
			}
			if (mode.equals(ChanceConfiguration.REGISTER_USER))
			{
				registerUser(request, response);
			}
			if (mode.equals(ChanceConfiguration.EDIT_USER))
			{				
				goEditUserPage(request, response);
			}
			if (mode.equals(ChanceConfiguration.UPDATE_USER))
			{				
				updateUser(request, response);
			}
			if (mode.equals(ChanceConfiguration.SHOW_PROFILE))
			{				
				showProfile(request, response);
			}
			if (mode.equals(ChanceConfiguration.SET_MAIN_IMAGE))
			{				
				setMainImage(request, response);
			}
			if (mode.equals(ChanceConfiguration.DELETE_IMAGE))
			{				
				deleteImage(request, response);
			}
			if (mode.equals(ChanceConfiguration.GOTO_UPLOAD_PICTURES_PAGE))
			{				
				gotoUploadPicturesPage(request, response);
			}
			if (mode.equals(ChanceConfiguration.GO_RESET_PASSWORD_PAGE))
			{				
				goResetPaswordPage(request, response);
			}
			if (mode.equals(ChanceConfiguration.RESET_PASSWORD))
			{				
				resetPassword(request, response);
			}
			if (mode.equals(ChanceConfiguration.UPDATE_PASSWORD))
			{				
				changePassword(request, response);
			}
		}
	}
	
	public void showProfile(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		//go to profile page
		String userId = request.getParameter("otheruserid");
		User user = null;
		try
		{
			user = UserManagmentFactory.getUserManager().findUserById(Integer.parseInt(userId));
		}
		catch (Exception e)		
		{
			e.printStackTrace();
		}
		PageUtils.setSelectedProfile(user, request);
		PageUtils.forwardToJspPage("/jsp/show_profile.jsp", request.getSession().getServletContext(), request, response);
	}
	
	public void goRegistrationPage(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		//go to registration page
		User user = new User();
		
		PageUtils.setUser(user, request);
		PageUtils.forwardToJspPage("/jsp/registration.jsp", request.getSession().getServletContext(), request, response);
	}
	
	public void goResetPaswordPage(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{		
		PageUtils.forwardToJspPage("/jsp/forgot_password.jsp", request.getSession().getServletContext(), request, response);		
	}
	
	public void resetPassword(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		//go to registration page
		try
		{
			String loginName = request.getParameter(ChanceConfiguration.USER_NAME);
			PasswordInfo pi = UserManagmentFactory.getUserManager().resetUserPassword(loginName);
			
			Mailer.mailMessage("Jane Chance password reset.", "Your new password is " + pi.getM_newPassword() + ". Please use this password to login and on that login, you will be asked to change your password to one that will probably be easier to rememmber.", pi.getM_userEmail());
			
			//go to registration page
			PageUtils.forwardToJspPage("/jsp/reset_password_confirmation.jsp", request.getSession().getServletContext(), request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void changePassword(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{
		//change user password
		try
		{
			String password = request.getParameter(ChanceConfiguration.PASSWORD);
			String passwordConfirm = request.getParameter(ChanceConfiguration.PASSWORD_CONFIRM);
			if (password.equals(passwordConfirm))
			{
				User user = PageUtils.getUser(request);
				UserManager um = UserManagmentFactory.getUserManager();
				um.changeUserPassword(user, password);
										
				PageUtils.setRecordIndex(0, request);
				
				//go to search servlet
				PageUtils.forwardToJspPage("/search?mode=" + ChanceConfiguration.START_PAGE, request.getSession().getServletContext(), request, response);
			}
			else
			{
				PageUtils.forwardToJspPage("/jsp/change_password.jsp", request.getSession().getServletContext(), request, response);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void registerUser(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{	
		
		String hostName = request.getRequestURL().toString().toLowerCase();			
		if (hostName.indexOf("janechance") > -1)
		{
			hostName="www.janechance.com";	
		}
		else
		{
			hostName="www.johnchance.com";
		}
		
		//clean up old error table before processing
		PageUtils.setErrorTable(null, request);
		HashMap errorTable = new HashMap();
		try
		{
			UserManager um = UserManagmentFactory.getUserManager();
			
			//save data
			String loginName = request.getParameter(ChanceConfiguration.LOGIN_NAME);
			String password = request.getParameter(ChanceConfiguration.PASSWORD);
			String passwordConfirm = request.getParameter(ChanceConfiguration.PASSWORD_CONFIRM);
			String email = request.getParameter(ChanceConfiguration.EMAIL);
			String emailConfirm = request.getParameter(ChanceConfiguration.EMAIL_CONFIRM);
			String strZipcode = request.getParameter(ChanceConfiguration.ZIPCODE);
			String birthdateMonth = request.getParameter(ChanceConfiguration.BIRTHDATE_MONTH);
			String birthdateYear = request.getParameter(ChanceConfiguration.BIRTHDATE_YEAR);			
			String catchPhrase = request.getParameter(ChanceConfiguration.CATCH_PHRASE);
			String aboutMe = request.getParameter(ChanceConfiguration.ABOUT_ME);		
			String termsAndConditions = request.getParameter(ChanceConfiguration.TERMS_AND_CONDITIONS);
			Integer tcFlag = null;
		
			Calendar birthdate = Calendar.getInstance();
			Zipcode zipcode = null;
			
			//various checks (highlight errors in red)
			if (loginName == null || loginName.length() == 0 || UserManagmentFactory.getUserManager().checkLoginNameAlreadyUsed(loginName) == true)
			{
				errorTable.put("LOGINNAME", "LOGINNAME");
			}
			
			if (password == null || password.length() == 0 || password.equals(passwordConfirm) == false)
			{
				errorTable.put("PWD", "PWD");
			}
			
			if (email == null || email.length() == 0 || email.equals(emailConfirm) == false)
			{
				errorTable.put("EMAIL", "EMAIL");
			}
			
			if (termsAndConditions == null || termsAndConditions.equals("on") == false)
			{
				errorTable.put("T&C", "T&C");
				tcFlag = new Integer(1);
			}
			else
			{
				tcFlag = new Integer(0);
			}
			
			try
			{				
				birthdate.set(Calendar.YEAR, Integer.parseInt(birthdateYear));
				birthdate.set(Calendar.MONTH, Integer.parseInt(birthdateMonth));
				birthdate.set(Calendar.DAY_OF_MONTH, 1);
				int ageTest = (Calendar.getInstance().get(Calendar.YEAR)) - birthdate.get(Calendar.YEAR);  
				if (ageTest < 18 )
				{
					throw new Exception();
				}
			}
			catch (Exception e)
			{
				errorTable.put("BIRTHDATE", "BIRTHDATE");
			}
			
			try
			{
				Integer.parseInt(strZipcode);	
				zipcode = um.findZipcode(strZipcode);
			}
			catch (Exception e)
			{
				errorTable.put("ZIPCODE", "ZIPCODE");
			}
			
			User user = PageUtils.getUser(request);
			user.setBirthdate(birthdate.getTime());
			user.setCatchphrase(catchPhrase);
			user.setAboutme(aboutMe);
			user.setTermserviceagreement(tcFlag);
			
			if (errorTable.get("ZIPCODE") == null)
			{
				user.setZipcode(zipcode);
			}
			
			user.setPassword(PasswordEncryptor.md5(password));
			user.setUserName(loginName);
			user.setEmail(email);
			
			user.setEthnicity((TEthnicity)ChanceConfiguration.getM_ethnicity().get(DEFAULT_ID));
			user.setSex((TSex)ChanceConfiguration.getM_sex().get(DEFAULT_ID));
			user.setSexLookingFor((TSex)ChanceConfiguration.getM_sex().get(DEFAULT_ID));
			user.setEyeColor((TColor)ChanceConfiguration.getM_color().get(DEFAULT_ID));
			user.setHairColor((TColor)ChanceConfiguration.getM_color().get(DEFAULT_ID));
			user.setSmoke((TChoice)ChanceConfiguration.getM_choice().get(DEFAULT_ID));
			user.setDrink((TChoice)ChanceConfiguration.getM_choice().get(DEFAULT_ID));
			user.setHaveChildern((THasChildren)ChanceConfiguration.getM_hasChildren().get(DEFAULT_ID));
			user.setWantChildern((TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(DEFAULT_ID));
			user.setMaritalStatus((TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(DEFAULT_ID));
			user.setBodyType((TBodytype)ChanceConfiguration.getM_bodyType().get(DEFAULT_ID));
			user.setIdealSmokes((TChoice)ChanceConfiguration.getM_choice().get(DEFAULT_ID));
			user.setIdealDrinks((TChoice)ChanceConfiguration.getM_choice().get(DEFAULT_ID));
			user.setIdealHasChildern((THasChildren)ChanceConfiguration.getM_hasChildren().get(DEFAULT_ID));
			user.setIdealWantsChildern((TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(DEFAULT_ID));			
			user.setIdealMaritalStatus((TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(DEFAULT_ID));
			
			//default start and end age, ideal distance, use existing zipcode
			user.setIdealAgeStart(new Integer(18));
			user.setIdealAgeEnd(new Integer(65));
			user.setIdealDistance(100);
			user.setIdealZipcode(zipcode);
			
			//default height
			user.setHeight(-1);
			user.setIdealHeightStart(0);
			user.setIdealHeightEnd(120);

			PageUtils.setErrorTable(errorTable, request);
			PageUtils.setUser(user, request);
			
			//do the save if no errors in hashtable
			if (errorTable.isEmpty() == true)
			{	
				um.saveUser(user);
				
				//update session with new user (includes id)
				PageUtils.setUser(um.findUserById(user.getUserId()), request);
				
				//save user into session							
				PageUtils.setRecordIndex(0, request);
				
				//go to search servlet
				response.sendRedirect("http://"+hostName+"/"+ChanceConfiguration.CONTEXT_ROOT+"search?mode=" + ChanceConfiguration.START_PAGE);
			}
			else
			{
				//display data not directly available from user when there is error
				request.getSession().setAttribute(ChanceConfiguration.ZIPCODE, strZipcode);
				request.getSession().setAttribute(ChanceConfiguration.BIRTHDATE_MONTH, birthdateMonth);
				request.getSession().setAttribute(ChanceConfiguration.BIRTHDATE_YEAR, birthdateYear);
				
				//forward to registration for repairs
				PageUtils.forwardToJspPage("/jsp/registration.jsp", request.getSession().getServletContext(), request, response);
			}			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void goEditUserPage(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{	
		try
		{			
			//go to edit page
			PageUtils.forwardToJspPage("/jsp/edit_user.jsp", request.getSession().getServletContext(), request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setMainImage(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{	
		try
		{			
			int pictureId = Integer.parseInt(request.getParameter(ChanceConfiguration.IMAGE_ID));
			UserManager um = UserManagmentFactory.getUserManager();
			um.setMainPicture(PageUtils.getUser(request), pictureId);
			
			// update user in session 
			PageUtils.setUser(um.findUserById(PageUtils.getUser(request).getUserId()), request);
			
			// go to edit page.
			PageUtils.forwardToJspPage("/jsp/edit_user.jsp", request.getSession().getServletContext(), request, response);							
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void deleteImage(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{	
		try
		{		
			UserManager um = UserManagmentFactory.getUserManager();
			int pictureId = Integer.parseInt(request.getParameter(ChanceConfiguration.IMAGE_ID));
			
			UserManagmentFactory.getUserManager().deletePicture(pictureId);			
			
			//update user in session 
			PageUtils.setUser(um.findUserById(PageUtils.getUser(request).getUserId()), request);
			
			// go to main page
			PageUtils.forwardToJspPage("/jsp/edit_user.jsp", request.getSession().getServletContext(), request, response);							
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	public void gotoUploadPicturesPage(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{	
		try
		{		
			
			// go to add pictures page
			PageUtils.forwardToJspPage("/jsp/upload_pictures.jsp",  request.getSession().getServletContext(), request, response);							
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateUser(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException
	{				
		try
		{					
			//get user
			User user = PageUtils.getUser(request);
						
			int heightFeetStart = 0;
			int heightInchesStart = 0;
			
			int heightFeetEnd = 0;			
			int heightInchesEnd = 0;
			
			int heightFeet = 0;
			int heightInches = 0;
			
			//wipe the sets for repopulation
			user.getIdealEthnicities().clear();
			user.getIdealBodyTypes().clear();
			user.getIdealLookingfor().clear();
			
			//get param names and update as necessary
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
					LocationDAO locationDao = new LocationDAO();					
					user.setZipcode(locationDao.getZipcodeById(Integer.parseInt(value)));
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
				if (key.equals(ChanceConfiguration.ZIPCODE_LOOKING_FOR))
				{
					user.setIdealZipcode((Zipcode)ChanceConfiguration.getM_zipcodeTable().get(new Integer(value)));
				}
				if (key.equals(ChanceConfiguration.DISTANCE))
				{
					user.setIdealDistance(new Integer(value));
				}
				
				
				// check for ethnicity keys (checkboxes)
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
			
			//update the user
			UserManagmentFactory.getUserManager().updateUser(user);
			
			//update the save profiles
			PageUtils.setUser(user, request);
			
			//go to main page
			PageUtils.forwardToJspPage("/search?mode=" + ChanceConfiguration.START_PAGE, request.getSession().getServletContext(), request, response);							
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
