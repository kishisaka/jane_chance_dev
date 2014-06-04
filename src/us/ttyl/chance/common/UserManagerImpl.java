package us.ttyl.chance.common;

import java.util.*;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

import us.ttyl.chance.dao.MessageDAO;
import us.ttyl.chance.dao.PictureDAO;
import us.ttyl.chance.dao.LocationDAO;
import us.ttyl.chance.dao.UserDAO;
import us.ttyl.chance.domain.*;
import us.ttyl.chance.hibernate.HibernateSessionFactory;

public class UserManagerImpl implements UserManager
{	
	public int getNewMessageCount(User user) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		try
		{		
			MessageDAO dao = new MessageDAO();
			return dao.getNewMessagesCount(user);		
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public void removeAllUsers() throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try
		{		
			UserDAO dao = new UserDAO();
			dao.removeAllUsers();			
			transaction.commit();			
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
		
	}
	
	public void saveUser(User user) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try
		{			
			UserDAO dao = new UserDAO();
			dao.addUserOrUpdate(user);
			transaction.commit();			
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public Integer createUser(
			Date birthdate
			, TEthnicity ethnicity
			, TSex sex
			, TSex sexLookingFor
			, Integer height
			, TColor eyeColor
			, TColor hairColor	
			, TChoice smoke
			, TChoice drink
			, THasChildren haveChildren
			, TWantsChildren wantsMoreChildren
			, TMaritalStatus maritalStatus
			, TBodytype userBodyType
			, String catchphrase
			, String aboutme
			, Integer idealHeightStart
			, Integer idealHeightEnd
			, TChoice idealSmokes
			, TChoice idealDrinks
			, THasChildren idealHasChildren
			, TWantsChildren idealWantsChildren
			, TMaritalStatus idealMaritalStatus
			, Integer termserviceagreement			
			, Integer profileStatus
			, Zipcode zipcode
			, String password
			, String username
			, String email
			, List idealEthnicities
			, List idealBodyTypes			
			, List idealLookingfor
			, Zipcode idealZipcode
			, Integer idealAgeStart
			, Integer idealAgeEnd
			, Integer idealDistance
		) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();		
		Transaction transaction = session.beginTransaction();
		User user = new User();
		try
		{
			user.setEthnicity(ethnicity);
			user.setBirthdate(birthdate);
			user.setSex(sex);
			user.setSexLookingFor(sexLookingFor);
			user.setEyeColor(eyeColor);
			user.setHairColor(hairColor);
			user.setHeight(height);
			user.setSmoke(smoke);
			user.setDrink(drink);
			user.setHaveChildern(haveChildren);
			user.setWantChildern(wantsMoreChildren);
			user.setMaritalStatus(maritalStatus);
			user.setBodyType(userBodyType);
			user.setCatchphrase(catchphrase);
			user.setAboutme(aboutme);
			user.setIdealHeightStart(idealHeightStart);
			user.setIdealHeightEnd(idealHeightEnd);
			user.setIdealSmokes(idealSmokes);
			user.setIdealDrinks(idealDrinks);
			user.setIdealHasChildern(idealHasChildren);
			user.setIdealWantsChildern(idealWantsChildren);
			user.setIdealMaritalStatus(idealMaritalStatus);
			user.setTermserviceagreement(termserviceagreement);
			user.setProfileStatus(profileStatus);
			user.setZipcode(zipcode);
			user.setPassword(PasswordEncryptor.md5(password));
			user.setUserName(username);
			user.setEmail(email);
			user.setIdealAgeStart(idealAgeStart);
			user.setIdealAgeEnd(idealAgeEnd);
			user.setIdealDistance(idealDistance);
			user.setIdealZipcode(idealZipcode);
			user.setIdealEthnicities(idealEthnicities);
			user.setIdealBodyTypes(idealBodyTypes);
			user.setIdealLookingfor(idealLookingfor);			
			
			UserDAO dao = new UserDAO();
			dao.addUserOrUpdate(user);							
			
			transaction.commit();
			return user.getUserId();
		}
		catch (Exception e)
		{			
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
				
	}
	
	public User findUserById(int id) throws Exception
	{
		User user = null;
		Session session = HibernateSessionFactory.getSession();	
		try
		{
			UserDAO dao = new UserDAO();
			user = dao.getUserByUserId(id);		
			return user;
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public void updateUser(User user) throws Exception
	{
		boolean okay = false;
		Session session = HibernateSessionFactory.getSession();		
		Transaction transaction = session.beginTransaction();
		try
		{
			UserDAO dao = new UserDAO();
			dao.addUserOrUpdate(user);						
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public void addUserMessage(
			TUsermessages message
		) throws Exception
	{		
		Session session = HibernateSessionFactory.getSession();		
		Transaction transaction = session.beginTransaction();
		try
		{
			MessageDAO dao = new MessageDAO();			
			dao.addMessage(message);			
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}		
	}
	
	
	public Integer addPicture(
			User user
			, String path
		) throws Exception
	{		
		Session session = HibernateSessionFactory.getSession();		
		Transaction transaction = session.beginTransaction();
		try
		{
			PictureDAO dao = new PictureDAO();		
			TPicture picture = new TPicture();
			picture.setFilenamePath(path);	
			picture.setUser(user);
			picture.setMainPagePic(0);
			picture.setDeleted(0);
			picture = dao.savePicture(picture);			
			transaction.commit();
			return picture.getPictureId();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}		
	}
	
	public void setMainPicture(
			User user
			, int pictureId
		) throws Exception
	{		
		Session session = HibernateSessionFactory.getSession();		
		Transaction transaction = session.beginTransaction();
		try
		{
			//update pictures
			Iterator pictures = user.getPictures().iterator();						
			while (pictures.hasNext())
			{	
				TPicture picture = (TPicture)pictures.next();				
				if (picture.getPictureId() == pictureId)
				{
					picture.setMainPagePic(1);												
				}	
				else
				{
					picture.setMainPagePic(0);
				}
				PictureDAO pictureDao = new PictureDAO();
				pictureDao.savePicture(picture);				
			}		
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}		
	}
	
	public void deleteMessages(String[] messageIds) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();		
		Transaction transaction = session.beginTransaction();
		try
		{
			MessageDAO dao = new MessageDAO();
			for(int i = 0; i < messageIds.length; i ++)
			{
				dao.deleteMessage(messageIds[i]);
			}
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}	
	}
	
	public void viewMessage(int messageId) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();		
		Transaction transaction = session.beginTransaction();
		try
		{
			MessageDAO dao = new MessageDAO();
			dao.viewMessage(messageId);
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}	
	}
	
	public List <TUsermessages> getUserMessages(User user) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();	
		try
		{			
			MessageDAO dao = new MessageDAO();
			return dao.getMessagesByUserId(user);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	/*
	public List getUserMessages(User user) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();	
		try
		{			
			MessageDAO dao = new MessageDAO();
			return dao.getMessagesByUserId(user);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	*/
	
	public User login(String userName, String password) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		try
		{
			UserDAO dao = new UserDAO();
			return dao.login(userName, PasswordEncryptor.md5(password));
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public PageList baseSearch(int gender, int ageStart, int ageEnd, String zipcode, int distance,  
			int startIndex, int pageSize, int userId) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		List <Distance> zipcodes = null;
		try
		{
			UserDAO dao = new UserDAO();
			zipcodes = dao.searchZipcodeDistance(zipcode, distance);							
			return dao.searchUserGeneral(zipcodes, ageStart, ageEnd, gender, startIndex, pageSize, userId);
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public Zipcode findZipcode(String zipcode)
	{
		Session session = HibernateSessionFactory.getSession();	
		try
		{		
			UserDAO dao = new UserDAO();
			return (Zipcode) dao.searchForZipcode(zipcode).get(0);		
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public PageList loadUsersForUser(User user, int startingRow, int listSize) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();	
		try
		{		
			UserDAO dao = new UserDAO();
			return  dao.searchBasedOnUser(user, startingRow, listSize);		
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public PageList findProfiles(User user, int startingRow, int listSize) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();	
		try
		{		
			UserDAO dao = new UserDAO();
			return  dao.findProfiles(user.getUserId(), startingRow, listSize);		
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public void saveProfile(User user, User targetUser) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try
		{				
			UserDAO dao = new UserDAO();
			if (dao.testForExistingProfiles(user, targetUser).size() == 0)
			{
				dao.addProfile(user, targetUser);
			}
			transaction.commit();		
		}
		catch (Exception e)
		{			
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public void deletePicture(Integer pictureId) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try
		{		
			PictureDAO pictureDao = new PictureDAO();
			List pictures = pictureDao.getPicturesByPictureId(pictureId);
			if (pictures.size() > 0)
			{
				TPicture picture = (TPicture)pictures.get(0);
				picture.setDeleted(1);
				pictureDao.savePicture(picture);
			}	
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public boolean checkLoginNameAlreadyUsed(String loginName) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		try
		{		
			UserDAO userDao = new UserDAO();
			return userDao.checkLoginNameAlreadyUsed(loginName);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public PasswordInfo resetUserPassword(String loginName) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try
		{		
			UserDAO dao = new UserDAO();
			User user = dao.getUserByLoginName(loginName);
			String newPassword = dao.resetUserPassword(user);			
			transaction.commit();
			PasswordInfo pi = new PasswordInfo(user.getEmail(), newPassword);
			return pi;
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public PasswordInfo resetUserPassword(User user, String password) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try
		{		
			UserDAO dao = new UserDAO();
			String newPassword = dao.resetUserPassword(user);			
			transaction.commit();
			PasswordInfo pi = new PasswordInfo(user.getEmail(), newPassword);
			return pi;
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
	public void changeUserPassword(User user, String password) throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try
		{		
			UserDAO dao = new UserDAO();
			dao.changeUserPassword(user, password);			
			transaction.commit();
		}
		catch (Exception e)
		{
			transaction.rollback();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();	
		}
	}
	
/*	
	public List getUserCounts() throws Exception
	{
		Session session = HibernateSessionFactory.getSession();
		List userCounts = new ArrayList();
		try
		{		
			LocationDAO locationDao = new LocationDAO();
			UserDAO userDao = new UserDAO();
			List zipcodes = locationDao.getAllZipcodes();
			for(int i = 0; i < zipcodes.size(); i ++)
			{
				Zipcode zipcode = (Zipcode)zipcodes.get(i);
				UserCountPerZipcode userCount = new UserCountPerZipcode();
				userCount.setM_lat(zipcode.getGeoInfoLat());
				userCount.setM_long(zipcode.getGeoInfoLong());
				userCount.setM_zipcode(zipcode.getZipcode().toString());
				userCount.setM_userCount(userDao.findUserByZipcode(zipcode.getZipcode().intValue()).size());
				System.out.println(userCount.getM_lat() + ":" + userCount.getM_long() + ":" + userCount.getM_userCount() + ":" + userCount.getM_zipcode());
				if (userCount.getM_userCount() > 0)
				{
					userCounts.add(userCount);
				}
			}	
			return userCounts;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally
		{
			HibernateSessionFactory.closeSession();			
		} 
	}
	*/
}
