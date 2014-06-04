package us.ttyl.chance.dao;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.ttyl.chance.domain.*;

public class EthnicityDAO extends CommonDAO
{	
	private static final Log log = LogFactory.getLog(EthnicityDAO.class);
	
	public static void main(String args[])
	{
		new EthnicityDAO();
	}
	
	public EthnicityDAO()
	{
		getAll();
	}
	
	public Hashtable getAll()
    {
		try
		{
			//get items
			String query = "from TEthnicity model";
			Hashtable bodyTypes = new Hashtable();
			List list = getAllObjects(query);
			
			//fill hashtable
			for(int i = 0; i < list.size(); i ++)
			{
				TEthnicity bt = (TEthnicity)list.get(i); 
				bodyTypes.put(bt.getEthnicityId(), bt);
			}
			
			//test hashtable
			Enumeration e = bodyTypes.keys();
			while (e.hasMoreElements())
			{
				TEthnicity bt = (TEthnicity)bodyTypes.get(e.nextElement());
				System.out.println(bt.getEthnicityId() + ":" + bt.getEthnicityDesc());
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
