package us.ttyl.chance.dao.test;

import us.ttyl.chance.common.*;
import java.util.Date;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import us.ttyl.chance.common.ChanceConfiguration;
import us.ttyl.chance.common.UserAttributes;
import us.ttyl.chance.common.UserManager;
import us.ttyl.chance.common.UserManagerImpl;
import us.ttyl.chance.dao.BodytypeDAO;
import us.ttyl.chance.dao.ChoiceDAO;
import us.ttyl.chance.dao.ColorDAO;
import us.ttyl.chance.dao.UserDAO;
import us.ttyl.chance.dao.EthnicityDAO;
import us.ttyl.chance.dao.HasChildrenDAO;
import us.ttyl.chance.dao.LocationDAO;
import us.ttyl.chance.dao.LookingForDAO;
import us.ttyl.chance.dao.MaritalStatusDAO;
import us.ttyl.chance.dao.PictureDAO;
import us.ttyl.chance.dao.SexDAO;
import us.ttyl.chance.dao.WantsChildrenDAO;
import us.ttyl.chance.domain.*;
import us.ttyl.chance.hibernate.HibernateSessionFactory;
import us.ttyl.chance.startup.SystemStartup;

public class UserTester 
{
	public static void main(String args[])
	{
		new UserTester();
	}
	
	public UserTester()
	{
		try
		{
			Hashtable params = new Hashtable();
			params.put("image_directory", "F:/dev/jane_chance_web/image_dir");
			params.put("smtp_host", "smtpout.secureserver.net");
			params.put("smtp_port", "3550");
			params.put("mail_source", "admin@johnchance.com");
			params.put("mail_user", "admin@johnchance.com");
			params.put("mail_password", "admin1234");
			params.put("hour", "5");
			params.put("sleep_interval", "3600000");
			SystemStartup.startSystem(params);	
		}
		catch (Exception e)
		{
			
		}
		testBasicUserSearch();			
	}
	
	public void testBasicUserSearch()
	{		
		try
		{
			UserDAO userDAO = new UserDAO();
			Session session = HibernateSessionFactory.getSession();
			List <Distance> zipcodes = userDAO.searchZipcodeDistance("94085", 20);
			StringBuffer queryString = new StringBuffer();
			queryString.append(" select " +
					" user.user_id " +
					" ," + userDAO.generateDistanceSQL(zipcodes)+
					" ,user.zipcode_id " +
					" ,picture.filename_path picture_filename" +
					" ,user.user_name " +
					" ,user.catchphrase " + 
					" ,city.city_desc city_desc" +
					" ,state.state_desc state_desc" +				
					" from " +
					" t_user user " +
					" join t_zipcode zipcode on user.zipcode_id = zipcode.zipcode_id" +
					" join t_city city on zipcode.city_id = city.city_id" +
					" join t_state state on zipcode.state_id = state.state_id" +
					" left outer join t_picture picture on user.user_id = picture.user_id and picture.main_page_pic = 1" +
					" order by distance, user.zipcode_id asc ");
			System.out.println(queryString.toString());
			SQLQuery query = session.createSQLQuery(queryString.toString());
			query.addEntity("user", SimpleUserInfo.class);
			List <SimpleUserInfo> list = query.list();
			for(int i = 0; i < list.size(); i ++)
			{
				System.out.println(list.get(i).toString());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void testLoadUsersForUser()
	{
		try
		{
			UserManager um = new UserManagerImpl();			
			User user = um.login("test1234", "password");
			PageList users = um.loadUsersForUser(user, 6, 6);
			System.out.println("next: " + users.isM_hasNext() + " : previous: " + users.isM_hasPrevious());
			for(int i = 0; i < users.getM_data().size(); i ++)
			{
				User currentUser = (User)users.getM_data().get(i);
				System.out.println(currentUser.getUserId());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void testBaseSearch()
	{
		try
		{
			UserManager um = new UserManagerImpl();
			PageList users = um.baseSearch(1, 10, 70, "94085", 5, 0, 10, -1);
			for(int i = 0;i < users.getM_data().size(); i ++)
			{				
				User u = (User)users.getM_data().get(i);
				System.out.println(u.getUserId() + ":" + u.getHairColor().getColorDesc());
			}
			System.out.println(users.isM_hasNext() + ":" + users.isM_hasPrevious());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void testGetMessages()
	{
		try
		{
			UserManager um = new UserManagerImpl();
			User sender = um.findUserById(28);
			User receiver = um.findUserById(29);
			
			// um.addUserMessage(sender, receiver, "fsasgsadgdsg","sdgdasgddsags");
			
			List messages = um.getUserMessages(receiver);
			for(int i = 0; i < messages.size(); i ++)
			{
				TUsermessages message = (TUsermessages)messages.get(i);
				System.out.println("---------");
				System.out.println("message: " + message.getMessageContent());
				System.out.println("from: " + message.getUserIdFrom().getUserName());
				System.out.println("to:" + message.getUserIdTo().getUserName());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public void testGenerateUser() 
	{
		try
		{	
			System.out.println("setup user");
			
			UserManager um = new UserManagerImpl();									
			
			um.removeAllUsers();
			
			//set users for testing
			Calendar cal1 = Calendar.getInstance();
			cal1.set(1980, 0, 1);
			
			List idealEthnicities = new ArrayList();
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(23));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(24));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(25));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(26));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(27));
			
			List idealBodyTypes = new ArrayList();
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(1));
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(2));
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(4));
			
			List idealLookingFor = new ArrayList();
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(6));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(5));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(4));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(3));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(2));
		
			int userId = um.createUser(
					cal1.getTime()
					, (TEthnicity)ChanceConfiguration.getM_ethnicity().get(26)
					, (TSex)ChanceConfiguration.getM_sex().get(1) //sex
					, (TSex)ChanceConfiguration.getM_sex().get(2) //sex looking for
					, new Integer(20) //height 
					, (TColor)ChanceConfiguration.getM_color().get(9) //eye color
					, (TColor)ChanceConfiguration.getM_color().get(10) //hair color
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //smoke
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //drink
					, (THasChildren)ChanceConfiguration.getM_hasChildren().get(1)
					, (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(1)
					, (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(1)
					, (TBodytype)ChanceConfiguration.getM_bodyType().get(1)
					, "test cacth phrase"
					, "test about me"
					, new Integer(20) // ideal height start
					, new Integer(30) // ideal height end
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //ideal smoke
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //ideal drink
					, (THasChildren)ChanceConfiguration.getM_hasChildren().get(1) //ideal has childern
					, (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(1) //ideal wants children
					, (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(1) //ideal marital status
					, new Integer(1)
					, new Integer(1)
					, (Zipcode)ChanceConfiguration.getM_zipcodeTable().get(94085)				
					, "test1"
					, "test1"
					, "test1@test.com"					
					, idealEthnicities
					, idealBodyTypes
					, idealLookingFor
					, (Zipcode)ChanceConfiguration.getM_zipcodeTable().get(new Integer(94085))
					, new Integer(18)
					, new Integer(65)
					, new Integer(100)
			);
			
			Calendar cal2 = Calendar.getInstance();
			cal2.set(1970, 0, 1);
			
			idealEthnicities = new ArrayList();
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(23));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(24));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(25));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(26));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(27));
			
			idealBodyTypes = new ArrayList();
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(1));
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(2));
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(4));
			
			idealLookingFor = new ArrayList();
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(6));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(5));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(4));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(3));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(2));
		
			userId = um.createUser(
					cal2.getTime()
					, (TEthnicity)ChanceConfiguration.getM_ethnicity().get(26)
					, (TSex)ChanceConfiguration.getM_sex().get(1) //sex
					, (TSex)ChanceConfiguration.getM_sex().get(2) //sex looking for
					, new Integer(20) //height 
					, (TColor)ChanceConfiguration.getM_color().get(1) //eye color
					, (TColor)ChanceConfiguration.getM_color().get(2) //hair color
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //smoke
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //drink
					, (THasChildren)ChanceConfiguration.getM_hasChildren().get(1)
					, (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(1)
					, (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(1)
					, (TBodytype)ChanceConfiguration.getM_bodyType().get(1)
					, "test cacth phrase"
					, "test about me"
					, new Integer(20) // ideal height start
					, new Integer(30) // ideal height end
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //ideal smoke
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //ideal drink
					, (THasChildren)ChanceConfiguration.getM_hasChildren().get(1) //ideal has childern
					, (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(1) //ideal wants children
					, (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(1) //ideal marital status
					, new Integer(1)
					, new Integer(1)
					, (Zipcode)ChanceConfiguration.getM_zipcodeTable().get(96782)				
					, "test2"
					, "test2"
					, "test2@test.com"					
					, idealEthnicities
					, idealBodyTypes
					, idealLookingFor
					, (Zipcode)ChanceConfiguration.getM_zipcodeTable().get(new Integer(94085))
					, new Integer(18)
					, new Integer(65)
					, new Integer(100)
			);
			 
			Calendar cal3 = Calendar.getInstance();			
			cal3.set(1950, 11, 01);
			
			idealEthnicities = new ArrayList();
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(23));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(24));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(25));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(26));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(27));
			
			idealBodyTypes = new ArrayList();
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(1));
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(2));
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(4));
			
			idealLookingFor = new ArrayList();
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(6));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(5));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(4));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(3));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(2));
		
			userId = um.createUser(
					cal3.getTime()
					, (TEthnicity)ChanceConfiguration.getM_ethnicity().get(26)
					, (TSex)ChanceConfiguration.getM_sex().get(2) //sex
					, (TSex)ChanceConfiguration.getM_sex().get(1) //sex looking for
					, new Integer(20) //height 
					, (TColor)ChanceConfiguration.getM_color().get(3) //eye color
					, (TColor)ChanceConfiguration.getM_color().get(4) //hair color
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //smoke
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //drink
					, (THasChildren)ChanceConfiguration.getM_hasChildren().get(1)
					, (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(1)
					, (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(1)
					, (TBodytype)ChanceConfiguration.getM_bodyType().get(1)
					, "test cacth phrase"
					, "test about me"
					, new Integer(20) // ideal height start
					, new Integer(30) // ideal height end
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //ideal smoke
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //ideal drink
					, (THasChildren)ChanceConfiguration.getM_hasChildren().get(1) //ideal has childern
					, (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(1) //ideal wants children
					, (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(1) //ideal marital status
					, new Integer(1)
					, new Integer(1)
					, (Zipcode)ChanceConfiguration.getM_zipcodeTable().get(95134)					
					, "test3"
					, "test3"
					, "test3@test.com"				
					, idealEthnicities
					, idealBodyTypes
					, idealLookingFor
					, (Zipcode)ChanceConfiguration.getM_zipcodeTable().get(new Integer(94085))
					, new Integer(18)
					, new Integer(65)
					, new Integer(100)
			);
			
			Calendar cal4 = Calendar.getInstance();
			cal4.set(1960, 11, 01);
			
			idealEthnicities = new ArrayList();
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(23));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(24));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(25));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(26));
			idealEthnicities.add((TEthnicity)ChanceConfiguration.getM_ethnicity().get(27));
			
			idealBodyTypes = new ArrayList();
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(1));
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(2));
			idealBodyTypes.add((TBodytype)ChanceConfiguration.getM_bodyType().get(4));
			
			idealLookingFor = new ArrayList();
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(6));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(5));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(4));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(3));
			idealLookingFor.add((TLookingfor)ChanceConfiguration.getM_lookingFor().get(2));
		
			userId = um.createUser(
					cal4.getTime()
					, (TEthnicity)ChanceConfiguration.getM_ethnicity().get(26)
					, (TSex)ChanceConfiguration.getM_sex().get(2) //sex
					, (TSex)ChanceConfiguration.getM_sex().get(1) //sex looking for
					, new Integer(20) //height 
					, (TColor)ChanceConfiguration.getM_color().get(5) //eye color
					, (TColor)ChanceConfiguration.getM_color().get(6) //hair color
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //smoke
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //drink
					, (THasChildren)ChanceConfiguration.getM_hasChildren().get(1)
					, (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(1)
					, (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(1)
					, (TBodytype)ChanceConfiguration.getM_bodyType().get(1)
					, "test cacth phrase"
					, "test about me"
					, new Integer(20) // ideal height start
					, new Integer(30) // ideal height end
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //ideal smoke
					, (TChoice)ChanceConfiguration.getM_choice().get(5) //ideal drink
					, (THasChildren)ChanceConfiguration.getM_hasChildren().get(1) //ideal has childern
					, (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(1) //ideal wants children
					, (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(1) //ideal marital status
					, new Integer(1)
					, new Integer(1)
					, (Zipcode)ChanceConfiguration.getM_zipcodeTable().get(94086)					
					, "test4"
					, "test4"
					, "test4@test.com"					
					, idealEthnicities
					, idealBodyTypes
					, idealLookingFor
					, (Zipcode)ChanceConfiguration.getM_zipcodeTable().get(new Integer(94085))
					, new Integer(18)
					, new Integer(65)
					, new Integer(100)
			);
			
			// test base search (initial search, not logged in, no one is found, 
			// non existant gender in list)
			// um.baseSearch(gender, ageStage, ageEnd, zipcode, distance, startIndex, pageSize);						
			PageList pl = um.baseSearch(2, 1, 100, "94085", 100, 0, 100, -1);			
			assertInt(pl.getM_data().size(), 0, "base search failed test--");
			
			// test base search (initial search, not logged in, should find 3 based on 
			// zipcode and distance, 94085)
			// um.baseSearch(gender, ageStage, ageEnd, zipcode, distance, startIndex, pageSize);
			pl = um.baseSearch(1, 1, 100, "94085", 100, 0, 100, -1);
			assertInt(pl.getM_data().size(), 3, "base search test--");
			
			//test that the 3 returned zipcodes are not 96782 (< 100 mile distance test)
			for(int i = 0; i < pl.getM_data().size(); i ++)
			{
				User u = (User)pl.getM_data().get(i);
				assertNotInt(u.getZipcode().getZipcode(), 96782, " < 100 mile distance testzipcode test--");				
			}
			
			// test base search (initial search, not logged in, should find 1 based on 
			// zipcode and distance, 96782)
			// um.baseSearch(gender, ageStage, ageEnd, zipcode, distance, startIndex, pageSize);
			pl = um.baseSearch(1, 1, 100, "96782", 100, 0, 100, -1);
			assertInt(pl.getM_data().size(), 1, "zipcode test--");
			
			//check that the zipcode for the returned item is correct (96782)
			User u = (User)pl.getM_data().get(0);
			assertInt(u.getZipcode().getZipcode(), 96782, "zipcode test--");
			
			// check age (15 yrs) should return none
			pl = um.baseSearch(1, 1, 15, "94085", 100, 0, 100, -1);
			assertInt(pl.getM_data().size(), 0, "age 15 user test--");
			
			// check age (40 yrs should return 1, should ignore 96782 user)
			pl = um.baseSearch(1, 1, 40, "94085", 100, 0, 100, -1);
			assertInt(pl.getM_data().size(), 1, "age 40 user distance 100 test 2--");
			for(int i = 0; i < pl.getM_data().size(); i ++)
			{
				u = (User)pl.getM_data().get(i);
				assertNotInt(u.getZipcode().getZipcode(), 96782, " < 100 mile distance testzipcode test("+i+")--");								
			}
			
			//check age (40 yrs should return 2, should include 96782 user)
			pl = um.baseSearch(1, 1, 40, "94085", 5000, 0, 100, -1);
			assertInt(pl.getM_data().size(), 2, "age 40 user distance 5000 test 2--");
			for(int i = 0; i < pl.getM_data().size(); i ++)
			{
				u = (User)pl.getM_data().get(i);
				assertNotInt(u.getZipcode().getZipcode(), 95134, "age 40 user distance 1000 test 2("+i+")--");				
				assertNotInt(u.getZipcode().getZipcode(), 94086, "age 40 user distance 1000 test 2("+i+")--");				
			}	
			
			//login test
			u = um.login("test1", "test1");
			assertString(u.getUserName(), "test1", "login \"test1\" test--");			
			
			//load user for users test 
			pl = um.loadUsersForUser(u, 0, 1000);
			for(int i = 0; i < pl.getM_data().size(); i ++)
			{
				u = (User)pl.getM_data().get(i);
				assertNotString(u.getUserName(), "test1", "load for user test--("+i+")");
				assertNotString(u.getUserName(), "test2", "load for user test--("+i+")");
			}
			
			System.out.println("tests done!");
		}			
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void assertInt(int param, int known, String note)
	{
		if (param == known)
		{
			System.out.println(note + " test passed. ");
		}
		else
		{
			System.out.println(note + " test failed. ");
		}
	}
	
	public void assertNotInt(int param, int known, String note)
	{
		if (param != known)
		{
			System.out.println(note + " test passed. ");
		}
		else
		{
			System.out.println(note + " test failed. ");
		}
	}
	
	public void assertString(String param, String known, String note)
	{
		if (param.equals(known))
		{
			System.out.println(note + " test passed. ");
		}
		else
		{
			System.out.println(note + " test failed. ");
		}
	}
	
	public void assertNotString(String param, String known, String note)
	{
		if (param.equals(known) == false)
		{
			System.out.println(note + " test passed. ");
		}
		else
		{
			System.out.println(note + " test failed. ");
		}
	}
}
