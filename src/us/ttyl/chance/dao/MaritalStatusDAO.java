package us.ttyl.chance.dao;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.ttyl.chance.domain.*;

public class MaritalStatusDAO extends CommonDAO
{	
	private static final Log log = LogFactory.getLog(MaritalStatusDAO.class);
	
	public static void main(String args[])
	{
		new MaritalStatusDAO();
	}
	
	public MaritalStatusDAO()
	{
		getAll();
	}
	
	public Hashtable getAll()
    {
		try
		{
			//get items
			String query = "from TMaritalStatus model";
			Hashtable bodyTypes = new Hashtable();
			List list = getAllObjects(query);
			
			//fill hashtable
			for(int i = 0; i < list.size(); i ++)
			{
				TMaritalStatus bt = (TMaritalStatus)list.get(i); 
				bodyTypes.put(bt.getMaritalStatusId(), bt);
			}
			
			//test hashtable
			Enumeration e = bodyTypes.keys();
			while (e.hasMoreElements())
			{
				TMaritalStatus bt = (TMaritalStatus)bodyTypes.get(e.nextElement());
				System.out.println(bt.getMaritalStatusId() + ":" + bt.getMaritalStatusDesc());
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


