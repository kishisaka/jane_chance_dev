package us.ttyl.chance.dao;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.ttyl.chance.domain.*;

public class WantsChildrenDAO extends CommonDAO
{	
	private static final Log log = LogFactory.getLog(WantsChildrenDAO.class);
	
	public static void main(String args[])
	{
		new WantsChildrenDAO();
	}
	
	public WantsChildrenDAO()
	{
		getAll();
	}
	
	public Hashtable getAll()
    {
		try
		{
			//get items
			String query = "from TWantsChildren model";
			Hashtable bodyTypes = new Hashtable();
			List list = getAllObjects(query);
			
			//fill hashtable
			for(int i = 0; i < list.size(); i ++)
			{
				TWantsChildren bt = (TWantsChildren)list.get(i); 
				bodyTypes.put(bt.getWantsChildrenId(), bt);
			}
			
			//test hashtable
			Enumeration e = bodyTypes.keys();
			while (e.hasMoreElements())
			{
				TWantsChildren bt = (TWantsChildren)bodyTypes.get(e.nextElement());
				System.out.println(bt.getWantsChildrenId() + ":" + bt.getWantsChildrenDesc());
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



