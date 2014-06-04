package us.ttyl.chance.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;

import java.util.*;

import us.ttyl.chance.domain.*;
import us.ttyl.chance.hibernate.HibernateSessionFactory;

public class BodytypeDAO extends CommonDAO
{	
	private static final Log log = LogFactory.getLog(BodytypeDAO.class);
	
	public static void main(String args[])
	{
		new BodytypeDAO();
	}
	
	public BodytypeDAO()
	{
		getAll();
	}
	
	public Hashtable getAll()
    {
		try
		{
			//get items
			String query = "from TBodytype model";
			Hashtable bodyTypes = new Hashtable();
			List list = getAllObjects(query);
			
			//fill hashtable
			for(int i = 0; i < list.size(); i ++)
			{
				TBodytype bt = (TBodytype)list.get(i); 
				bodyTypes.put(bt.getBodytypeId(), bt);
			}
			
			//test hashtable
			Enumeration e = bodyTypes.keys();
			while (e.hasMoreElements())
			{
				TBodytype bt = (TBodytype)bodyTypes.get(e.nextElement());
				System.out.println(bt.getBodytypeId() + ":" + bt.getBodytypeDesc());
			}
			return bodyTypes;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
    }	
}
