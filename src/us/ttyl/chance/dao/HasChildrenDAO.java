package us.ttyl.chance.dao;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.ttyl.chance.domain.*;

public class HasChildrenDAO extends CommonDAO
{	
	private static final Log log = LogFactory.getLog(HasChildrenDAO.class);
	
	public static void main(String args[])
	{
		new HasChildrenDAO();
	}
	
	public HasChildrenDAO()
	{
		getAll();
	}
	
	public Hashtable getAll()
    {
		try
		{
			//get items
			String query = "from THasChildren model";
			Hashtable bodyTypes = new Hashtable();
			List list = getAllObjects(query);
			
			//fill hashtable
			for(int i = 0; i < list.size(); i ++)
			{
				THasChildren bt = (THasChildren)list.get(i); 
				bodyTypes.put(bt.getHasChildrenId(), bt);
			}
			
			//test hashtable
			Enumeration e = bodyTypes.keys();
			while (e.hasMoreElements())
			{
				THasChildren bt = (THasChildren)bodyTypes.get(e.nextElement());
				System.out.println(bt.getHasChildrenId() + ":" + bt.getHasChildrenDesc());
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

