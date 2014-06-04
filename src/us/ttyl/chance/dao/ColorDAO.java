package us.ttyl.chance.dao;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.ttyl.chance.domain.*;

public class ColorDAO extends CommonDAO
{	
	private static final Log log = LogFactory.getLog(ColorDAO.class);
	
	public static void main(String args[])
	{
		new ColorDAO();
	}
	
	public ColorDAO()
	{
		getAll();
	}
	
	public Hashtable getAll()
    {
		try
		{
			//get items
			String query = "from TColor model";
			Hashtable bodyTypes = new Hashtable();
			List list = getAllObjects(query);
			
			//fill hashtable
			for(int i = 0; i < list.size(); i ++)
			{
				TColor bt = (TColor)list.get(i); 
				bodyTypes.put(bt.getColorId(), bt);
			}
			
			//test hashtable
			Enumeration e = bodyTypes.keys();
			while (e.hasMoreElements())
			{
				TColor bt = (TColor)bodyTypes.get(e.nextElement());
				System.out.println(bt.getColorId() + ":" + bt.getColorDesc());
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
