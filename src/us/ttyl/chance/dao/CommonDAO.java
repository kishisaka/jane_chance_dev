package us.ttyl.chance.dao;

import java.util.Hashtable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import us.ttyl.chance.hibernate.HibernateSessionFactory;

public abstract class CommonDAO 
{
	public List getAllObjects(String queryStr) throws Exception 
    {	
		Session session = HibernateSessionFactory.getSession();
		try
		{
			Query query = session.createQuery(queryStr);
			return query.list();
		}
		catch (Exception e)
		{	
			throw e;
		}
		finally
		{
			session.close();
		}
    }
	
	public abstract Hashtable getAll();
}
