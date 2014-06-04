package us.ttyl.chance.dao;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.ttyl.chance.domain.*;

public class ChoiceDAO extends CommonDAO
{	
	private static final Log log = LogFactory.getLog(ChoiceDAO.class);
	
	public static void main(String args[])
	{
		new ChoiceDAO();
	}
	
	public ChoiceDAO()
	{
		getAll();
	}
	
	public Hashtable getAll()
    {
		try
		{
			//get items
			String query = "from TChoice model";
			Hashtable bodyTypes = new Hashtable();
			List list = getAllObjects(query);
			
			//fill hashtable
			for(int i = 0; i < list.size(); i ++)
			{
				TChoice bt = (TChoice)list.get(i); 
				bodyTypes.put(bt.getChoiceId(), bt);
			}
			
			//test hashtable
			Enumeration e = bodyTypes.keys();
			while (e.hasMoreElements())
			{
				TChoice bt = (TChoice)bodyTypes.get(e.nextElement());
				System.out.println(bt.getChoiceId() + ":" + bt.getChoiceDesc());
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
