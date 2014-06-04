package us.ttyl.chance.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.ttyl.chance.dao.test.Driver;
import us.ttyl.chance.domain.*;
import us.ttyl.chance.common.*;
import us.ttyl.chance.hibernate.HibernateSessionFactory;
import us.ttyl.chance.startup.SystemStartup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import org.hibernate.*;

public class UserDAO 
{
	private static final Log log = LogFactory.getLog(UserDAO.class);
	
	private static final String INSERT_PROFILE = "insert into t_saved_profile(user_id, saved_user_id) values(?,?)"; 
	
	/**
	 * remove all users by bulk erase (for testing only)
	 */
	public void removeAllUsers()
	{
		Session session = HibernateSessionFactory.getSession();		
		Query query = session.createQuery("delete TUserIdealEthnicity");
		query.executeUpdate();
		query = session.createQuery("delete TUserIdealLookingfor");
		query.executeUpdate();
		query = session.createQuery("delete TIdealBodytype");
		query.executeUpdate();
		query = session.createQuery("delete User u");
		query.executeUpdate();
	}
	
	/**
	 * get user by id
	 * @param id
	 * @return found user
	 * @throws Exception
	 */
	public User getUserByUserId(Integer id) throws Exception
    {
        User instance = (User)HibernateSessionFactory.getSession().get("us.ttyl.chance.domain.User", id);        	
        return instance;
    }	
	
	public User getUserByLoginName(String loginName) throws Exception
    {
		Session session = HibernateSessionFactory.getSession();
		Query query = session.createQuery("from User where userName=?");
		List users = query.setString(0, loginName).list();
        if (users.size() > 0)
        {
        	return (User)users.get(0);
        }
        else
        {
        	return null;
        }
    }
	
	public List findUserByZipcode(int zipcode) throws Exception
    {
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery("from User where zipcode.zipcode=?");
        query.setInteger(0, zipcode);        
        return query.list();
    }	
	
	public User findUserByUserName(String userName) throws Exception
    {
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery("from User where userName=?");
        query.setString(0, userName);
        List <User> users = query.list();
        if (users.size() > 0)
        {
        	return users.get(0);
        }
        else
        {
        	return null;
        }
    }	
	
	/**
	 * get add or update user
	 * @param u
	 * @return Intger userId
	 */
	public Integer addUserOrUpdate(User u) throws Exception
	{
		HibernateSessionFactory.getSession().saveOrUpdate(u);			
		return u.getUserId();
	}	

	/**
	 * login with username and encrypted password
	 * @param userName
	 * @param password
	 * @return User
	 */
	public User login(String userName, String password) 
	{
		String queryString = "from User where userName=? and password=?";
		Query queryObject = HibernateSessionFactory.getSession().createQuery(queryString);
		queryObject.setParameter(0, userName);
		queryObject.setParameter(1, password);
		List <User> users = queryObject.list();
		
		//should only return one user
		for(int i = 0; i < users.size(); i ++)			
		{	
			return users.get(i); 
		}
		
		//no users found
		return null;
	}
	
	/**
	 * search for others users matching profile of entered user
	 * @param user
	 * @return list of Users 
	 */
	public PageList searchBasedOnUser(User user, int startingRow, int listSize)
	{				
		List <SimpleUserInfo> users = new ArrayList <SimpleUserInfo> ();
		boolean next = false;
		boolean previous = false;
		
		List <Distance> zipcodes = null;
		try
		{
			zipcodes = searchZipcodeDistance(Integer.toString(user.getIdealZipcode().getZipcode()), user.getIdealDistance().intValue());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select distinct" +
				" user.user_id " +
				" ," + generateDistanceSQL(zipcodes)+
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
				" left outer join t_picture picture on user.user_id = picture.user_id and picture.main_page_pic = 1");
		
		if (user.getIdealLookingfor() != null && user.getIdealLookingfor().size() > 0)
		{
			queryString.append(" join t_user_ideal_lookingfor uilf on user.user_id = uilf.user_id ");
		}

		queryString.append(" where user.user_id !=:myUserId ");
		
		if (user.getSexLookingFor().getSexId() != 99)
		{
			queryString.append(" and user.sex_id = " + user.getSexLookingFor().getSexId());
		}
		if (user.getIdealHeightStart() != -1 || user.getIdealHeightEnd() != -1)
		{
			queryString.append(" and ((user.height >= " + user.getIdealHeightStart() + " and user.height <= " + user.getIdealHeightEnd() + ") or (user.height = -1 ))");
		}
		if (user.getIdealSmokes().getChoiceId() != 99)
		{
			queryString.append(" and user.smoke_id = "+user.getIdealSmokes().getChoiceId());
		}		
		if (user.getIdealDrinks().getChoiceId() != 99)
		{
			queryString.append(" and user.drink_id = "+user.getIdealDrinks().getChoiceId());
		}		
		if (user.getIdealHasChildern().getHasChildrenId() != 99)
		{
			queryString.append(" and user.have_children_id = "+user.getIdealHasChildern().getHasChildrenId());
		}
		if (user.getIdealWantsChildern().getWantsChildrenId() != 99)
		{
			queryString.append(" and user.want_more_children_id = "+user.getIdealWantsChildern().getWantsChildrenId());
		}
		if (user.getIdealMaritalStatus().getMaritalStatusId() != 99)
		{
			queryString.append(" and user.marital_status_id = "+user.getIdealMaritalStatus().getMaritalStatusId());
		}
		
		//ethnicity
		List userEthnicityList = user.getIdealEthnicities();		
		boolean ethnicitySQLAppend = true;
		StringBuffer ethnicitySQL = new StringBuffer();		
		if (user.getIdealEthnicities() != null && user.getIdealEthnicities().size() > 0)
		{
			ethnicitySQL.append(" and (");
			for(int i = 0; i < userEthnicityList.size(); i ++)
			{
				TEthnicity ethnicity = (TEthnicity)userEthnicityList.get(i);
				if (ethnicity.getEthnicityId() != 99)
				{
					ethnicitySQL.append(" user.ethnicity_id = "+ethnicity.getEthnicityId() + " or ");
				}
				else
				{
					ethnicitySQLAppend = false;
					break;					
				}
			}
			if (ethnicitySQLAppend == true)
			{
				ethnicitySQL.delete(ethnicitySQL.length() - 3, ethnicitySQL.length());
				ethnicitySQL.append(" ) ");
				queryString.append(ethnicitySQL.toString());
			}
		}			
		
		//body types
		List userIdealBodyTypeList = user.getIdealBodyTypes();
		if (user.getIdealBodyTypes() != null && user.getIdealBodyTypes().size() > 0)
		{
			queryString.append(" and (");
			for(int i = 0 ;i < userIdealBodyTypeList.size(); i ++)
			{
				TBodytype bodyType = (TBodytype)userIdealBodyTypeList.get(i);
				queryString.append("  user.userbodytype_id = "+bodyType.getBodytypeId() + " or ");
			}
			queryString.delete(queryString.length() - 3, queryString.length());
			queryString.append(" ) ");
		}
				
		// looking for
		List userIdealLookingForList = user.getIdealLookingfor();
		if (user.getIdealLookingfor() != null && user.getIdealLookingfor().size() > 0)
		{			
			queryString.append(" and (");
			for(int i = 0; i < userIdealLookingForList.size(); i ++)
			{
				TLookingfor lookingfor = (TLookingfor)userIdealLookingForList.get(i);
				queryString.append(" uilf.ideal_lookingfor_id = " + lookingfor.getLookingforId() + " or ");
			}
			queryString.delete(queryString.length() - 3, queryString.length());
			queryString.append(" ) ");		
		}		
		queryString.append(generateZipcodeLimiter(zipcodes));
		queryString.append(" order by distance, user.zipcode_id asc ");
		
		System.out.println("----------searchBasedOnUser query start----------");
		System.out.println(queryString.toString());
		System.out.println("----------searchBasedOnUser query end----------");
		
		SQLQuery queryObject = HibernateSessionFactory.getSession().createSQLQuery(queryString.toString());
		if (user.getUserId() != null)
		{
			queryObject.setInteger("myUserId", user.getUserId());
		}
		else
		{
			queryObject.setInteger("myUserId", -1);
		}
		queryObject.addEntity("user", SimpleUserInfo.class);
		ScrollableResults sr = queryObject.scroll();
		
		// get last row
		sr.last();
		int maxRow = sr.getRowNumber();
		
		// position cursor at location requested
		sr.setRowNumber(startingRow);
		
		// check for previous
		if (sr.previous() == true)
		{
			previous = true;
		}				
		
		//scroll through the results and put into list
		int current = 0;
		sr.setRowNumber(startingRow);						
		do
		{			
			if (maxRow == -1 || current == listSize)
			{
				break;
			}
			SimpleUserInfo u = (SimpleUserInfo)sr.get()[0];
			users.add(u);
			current ++;
		}
		while (sr.next());		
		
		//go one back
		sr.previous();
		
		// check for next
		if (sr.next() == true)
		{
			next = true;
		}	
		
		return new PageList(users, next, previous, maxRow);
	}
	
	/**
	 * return true if same looking for is found for both user and foundUser 
	 * else return false
	 * @param user
	 * @param foundUser
	 * @return boolean found
	 */
	public boolean checkUserLookingFor(User user, User foundUser)	
	{
		Iterator lookingForFoundUserList = user.getIdealLookingfor().iterator();
		while (lookingForFoundUserList.hasNext())
		{
			TLookingfor lookingforFoundUser = (TLookingfor)lookingForFoundUserList.next();			
			Iterator lookingForUserList = foundUser.getIdealLookingfor().iterator();
			while (lookingForUserList.hasNext())
			{
				TLookingfor lookingforUser = (TLookingfor)lookingForUserList.next();			
				if (lookingforFoundUser.getLookingforId().intValue() == lookingforUser.getLookingforId().intValue())
				{
					return true;
				}
			}			
		}
		return false;
	}
	
	/**
	 * get zipcode list for specified zipcode
	 * @param zipcode
	 * @return list of zipcodes
	 */
	public List <Zipcode> searchForZipcode(String zipcode)
	{
		Query queryObject = HibernateSessionFactory.getSession().createQuery("from Zipcode where zipcode=?");
		queryObject.setInteger(0, Integer.parseInt(zipcode));
		return queryObject.list();
	}
	
	/**
	 * return a list of zipcodes (in the form of Distance objects) that 
	 * originate from a specifed zipcode and extend outwards 
	 * @param zipcode
	 * @param distance
	 * @return list of Distance objects containing zipcodes 
	 */
	public List <Distance> searchZipcodeDistance(String zipcodeStr, int distance) throws Exception
	{	
		int zipcodeInt = -1;
		try
		{
			zipcodeInt = Integer.parseInt(zipcodeStr);
		}
		catch (Exception e)
		{
			zipcodeInt = 94085;
		}
					
		LocationDAO ld = new LocationDAO();
		Zipcode srcZipcode = ld.getZipcodeById(zipcodeInt);
		return ChanceUtils.getDistancesForSrcZipcode(srcZipcode, distance);
	}
	
	/**
	 * return a list of users based on a set of specified critera 
	 * @param zipcodes
	 * @param ageStart
	 * @param ageEnd
	 * @param sex
	 * @return list of Users
	 */
	public PageList searchUserGeneral(List<Distance> zipcodes, int ageStart, int ageEnd, 
			int sex, int startingRow, int listSize, int userId)
	{	
		List <SimpleUserInfo> simpleUsers = new ArrayList <SimpleUserInfo>();
		boolean next = false;
		boolean previous = false;
		
		Calendar startAge = Calendar.getInstance();
		if (ageStart != 18)
		{
			startAge.add(Calendar.YEAR, -(ageStart));
		}
		
		Calendar endAge = Calendar.getInstance();
		if (ageEnd == 65)
		{
			endAge.add(Calendar.YEAR, -(1000));
		}
		else
		{	
			endAge.add(Calendar.YEAR, -(ageEnd));	
		}			
		
		System.out.println(startAge.getTime().toString() + ":" + endAge.getTime().toString());
		
		StringBuffer zipcodeList = new StringBuffer();
		if (zipcodes != null)
		{
			for(int i = 0; i < zipcodes.size(); i ++)
			{
				zipcodeList.append(zipcodes.get(i).getZipcodeIdTarget().getZipcode() + ",");
			}
		}		
		StringBuffer queryString = new StringBuffer();
		queryString.append(" select " +
				" user.user_id " +
				" ," + generateDistanceSQL(zipcodes)+
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
				" left outer join t_picture picture on user.user_id = picture.user_id and picture.main_page_pic = 1");				
		queryString.append(" where user.birthdate between ? and ? ");		
		if (zipcodeList.length() > 0)
		{
			zipcodeList.delete(zipcodeList.length() - 1 , zipcodeList.length());			
			queryString.append(" and user.zipcode_id in(" + zipcodeList.toString() + ") ");			
		}		
		
		if (sex < 99)
		{
			queryString.append(" and user.sex_id =:sexId ");
		}
		
		if (userId != -1)
		{
			queryString.append(" and user.user_id <>:userId ");
		}
		queryString.append(generateZipcodeLimiter(zipcodes));
		queryString.append(" order by distance, user.zipcode_id asc ");
		
		System.out.println("----------searchUserGeneral query start----------");
		System.out.println(queryString.toString());
		System.out.println("----------searchUserGeneral query end----------");
		
		SQLQuery queryObject = HibernateSessionFactory.getSession().createSQLQuery(queryString.toString());		
		queryObject.setParameter(0, endAge.getTime());
		queryObject.setParameter(1, startAge.getTime());
		if (sex < 99)
		{
			queryObject.setParameter("sexId", sex);
		}
		if (userId != -1)
		{
			queryObject.setParameter("userId", sex);
		}
		queryObject.addEntity("user", SimpleUserInfo.class);
		ScrollableResults sr = queryObject.scroll();
		
		//get last row;
		sr.last();
		int maxRow = sr.getRowNumber();				
		
		if (maxRow > -1)
		{
			// position cursor at location requested
			sr.setRowNumber(startingRow);		
			
			// check for previous
			if (sr.previous() == true)
			{
				previous = true;
			}		
			
			//scroll through the results and put into list
			int current = 0;
			sr.setRowNumber(startingRow);						
			do
			{
				if (current == listSize)
				{
					sr.previous();
					break;
				}
				SimpleUserInfo u = (SimpleUserInfo)sr.get()[0];
				simpleUsers.add(u);
				current ++;
			}
			while (sr.next());		
			
			// check for next
			if (sr.next() == true)
			{
				next = true;
			}	
		}
		else
		{
			previous = false;
			next = false;			
		}
		
		return new PageList(simpleUsers, next, previous, maxRow);
	}
	
	public String generateZipcodeLimiter(List <Distance> distanceList)
	{
		StringBuffer query = new StringBuffer();
		query.append(" and user.zipcode_id in ( ");
		for(int i = 0; i < distanceList.size(); i ++)
		{
			query.append(distanceList.get(i).getZipcodeIdTarget().getZipcode().intValue() + ",");
		}
		query.delete(query.length() - 1, query.length());
		query.append(" ) " );
		return query.toString();
	}
	
	public String generateDistanceSQL (List <Distance> distanceList)
	{				
		StringBuffer query = new StringBuffer();
		query.append(" case user.zipcode_id ");
		for(int i = 0; i < distanceList.size(); i ++)
		{
			query.append(" when " + distanceList.get(i).getZipcodeIdTarget().getZipcode().intValue() + " then " + distanceList.get(i).getDistanceAmt().intValue());
		}
		query.append(" else 10000 end distance" );
		return query.toString();
	}
	
	/**
	 * return a list of users based on a set of specified critera 
	 * @param user
	 * @return list of Users
	 */
	public PageList findProfiles(Integer userId, int startingRow, int listSize)
	{	
		List users = new ArrayList();
		boolean next = false;
		boolean previous = false;
		
		Query queryObject = HibernateSessionFactory.getSession().createQuery("from TSavedProfile where user.userId=? ");	
		queryObject.setParameter(0, userId);
		
		ScrollableResults sr = queryObject.scroll();
		
		//get last row;
		sr.last();
		int maxRow = sr.getRowNumber();				
		
		if (maxRow > -1)
		{
			// position cursor at location requested
			sr.setRowNumber(startingRow);		
			
			// check for previous
			if (sr.previous() == true)
			{
				previous = true;
			}		
			
			//scroll through the results and put into list
			int current = 0;
			sr.setRowNumber(startingRow);						
			do
			{
				if (current == listSize)
				{
					sr.previous();
					break;	
				}
				User u = ((TSavedProfile)sr.get()[0]).getSavedUser();
				users.add(u);
				current ++;
			}
			while (sr.next());		
			
			// check for next
			if (sr.next() == true)
			{
				next = true;
			}	
		}
		else
		{
			previous = false;
			next = false;			
		}
		
		return new PageList(users, next, previous, maxRow);
	}
	
	public List testForExistingProfiles(User user, User targetUser)
	{
		 Query query = HibernateSessionFactory.getSession().createQuery("from TSavedProfile where user=? and savedUser=?");
		 query.setParameter(0, user);
		 query.setParameter(1, targetUser);
		 return query.list();
	}
	
	public void addProfile(User user, User targetUser) throws Exception	
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		Session session = HibernateSessionFactory.getSession();	
		conn = session.connection();
		try
		{
			stmt = conn.prepareStatement(INSERT_PROFILE);
			stmt.setInt(1, user.getUserId().intValue());
			stmt.setInt(2, targetUser.getUserId().intValue());			
			stmt.execute();
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			free(null, stmt, conn, session);
		}
	}	
	
	public void deleteProfile(TSavedProfile profile)	
	{
		HibernateSessionFactory.getSession().delete(profile);	
	}
	
	public boolean checkLoginNameAlreadyUsed(String loginName)
	{
		Session session = HibernateSessionFactory.getSession();
		
		Query query = session.createQuery("from User where userName=?");
		query.setString(0, loginName);
		if (query.list().size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}				
	}
	
	/**
	 * returns reset password to server, updated new password into user, set changePasswordCode to 1 to indicate a force change password upon login. 
	 * @param loginName
	 * @return password
	 */
	public String resetUserPassword(User user)
	{
		Session session = HibernateSessionFactory.getSession();
		String newPassword = Long.toString((long)(Math.random() * 100000));
		user.setPassword(PasswordEncryptor.md5(newPassword));
		user.setChangePasswordCode(1);
		session.saveOrUpdate(user);	
		return newPassword;
	}	
	
	public void changeUserPassword(User user, String password)
	{
		Session session = HibernateSessionFactory.getSession();
		user.setPassword(PasswordEncryptor.md5(password));
		user.setChangePasswordCode(0);
		session.saveOrUpdate(user);	
	}	
	
	
	public void free(ResultSet rs, Statement stmt, Connection conn, Session session)
	{
		try
		{
			if (rs != null)
			{
				rs.close();
			}
			if (stmt != null)
			{
				stmt.close();
			}
			if (conn != null)
			{
				conn.close();
			}			
			if (session != null)
			{
				session.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void testGetUsers()
	{
		try
		{
			Session session = HibernateSessionFactory.getSession();
			List <Distance> zipcodes = searchZipcodeDistance("94085", 20);
			StringBuffer queryString = new StringBuffer();
			queryString.append(" select " +
					" user.user_id " +
					" ," + generateDistanceSQL(zipcodes)+
					" ,user.zipcode_id " +
					" ,picture.filename_path picture_filename" +
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
	
	public static void main(String args[])
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
		UserDAO ud = new UserDAO();
		ud.testGetUsers();	
	}
}
