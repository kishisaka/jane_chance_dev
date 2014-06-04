package us.ttyl.chance.dao;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.ttyl.chance.domain.*;

public class SexDAO extends CommonDAO
{	
	private static final Log log = LogFactory.getLog(SexDAO.class);
	
	public static void main(String args[])
	{
		new SexDAO();
	}
	
	public SexDAO()
	{
		getAll();
	}
	
	public Hashtable getAll()
    {
		try
		{
			//get items
			String query = "from TSex model";
			Hashtable items = new Hashtable();
			List list = getAllObjects(query);
			
			//fill hashtable
			for(int i = 0; i < list.size(); i ++)
			{
				TSex bt = (TSex)list.get(i); 
				items.put(bt.getSexId(), bt);
			}
			
			//test hashtable
			Enumeration e = items.keys();
			while (e.hasMoreElements())
			{
				TSex bt = (TSex)items.get(e.nextElement());
				System.out.println(bt.getSexId() + ":" + bt.getSexDesc());
			}
			return items;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
    }	
}


