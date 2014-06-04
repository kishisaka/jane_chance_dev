package us.ttyl.chance.dao;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.ttyl.chance.domain.*;

public class LookingForDAO extends CommonDAO
{	
	private static final Log log = LogFactory.getLog(LookingForDAO.class);
	
	public static void main(String args[])
	{
		new LookingForDAO();
	}
	
	public LookingForDAO()
	{
		getAll();
	}
	
	public Hashtable getAll()
    {
		try
		{
			//get items
			String query = "from TLookingfor model";
			Hashtable bodyTypes = new Hashtable();
			List list = getAllObjects(query);
			
			//fill hashtable
			for(int i = 0; i < list.size(); i ++)
			{
				TLookingfor bt = (TLookingfor)list.get(i); 
				bodyTypes.put(bt.getLookingforId(), bt);
			}
			
			//test hashtable
			Enumeration e = bodyTypes.keys();
			while (e.hasMoreElements())
			{
				TLookingfor bt = (TLookingfor)bodyTypes.get(e.nextElement());
				System.out.println(bt.getLookingforId() + ":" + bt.getLookingforDesc());
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


