package us.ttyl.chance.dao;

import java.util.List;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import us.ttyl.chance.hibernate.HibernateSessionFactory;
import us.ttyl.chance.domain.*;

import java.sql.*;

public class MessageDAO 
{
	public static final String INSERT_MESSAGE = "" +
			" insert into t_usermessages(user_id_from, user_id_to, message_subject, message_content, created_on, deleted) " +
			" values(?,?,?,?,?,?) ";
	
	/**
	 * insert message into db (must use JDBC dur to hibernate bug, throws non 
	 * unique id exception)
	 * @param message
	 * @return id
	 */
	public void addMessage(TUsermessages message)
	{		
		Session session = HibernateSessionFactory.getSession();
		message.setDeleted((byte)0);
		message.setDeletedOn(null);
		message.setCreatedOn(new Date());
		message.setViewedOn(null);
		session.saveOrUpdate(message);	
	}
	
	public void free(ResultSet rs, Statement stmt, Connection conn)
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
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int getNewMessagesCount(User user)
	{
		String queryString = "from TUsermessages where userIdTo = ? and viewedOn is null and deleted = 0";
		Query queryObject = HibernateSessionFactory.getSession().createQuery(queryString);
		queryObject.setParameter(0, user); 
		return queryObject.list().size();		
	}
	
	public void viewMessage(int messageId)
	{
		String queryString = "from TUsermessages where messageId = ?";
		Query queryObject = HibernateSessionFactory.getSession().createQuery(queryString);
		queryObject.setParameter(0, messageId);
		List <TUsermessages> messages = queryObject.list();
		for(int i = 0; i < messages.size(); i ++)			
		{
			messages.get(i).setViewedOn(new Date());
			HibernateSessionFactory.getSession().saveOrUpdate(messages.get(i));
		}
	} 
	
	public List <TUsermessages> getMessagesByUserId(User user)
	{
		String queryString = "from TUsermessages where userIdTo = ? order by createdOn desc";
		Query queryObject = HibernateSessionFactory.getSession().createQuery(queryString);
		queryObject.setParameter(0, user);
		return queryObject.list();    
	}
	
	public void deleteMessage(String messageId)
	{
		String queryString = "from TUsermessages where messageId = ?";
		Query queryObject = HibernateSessionFactory.getSession().createQuery(queryString);
		queryObject.setParameter(0, new Integer(messageId));
		List <TUsermessages> messages = queryObject.list();
		for(int i = 0; i < messages.size(); i ++)			
		{
			messages.get(i).setDeletedOn(new Date());
			messages.get(i).setDeleted((byte)1);
			HibernateSessionFactory.getSession().saveOrUpdate(messages.get(i));
		}
	} 
}
