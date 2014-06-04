package us.ttyl.chance.dao.test;

import us.ttyl.chance.common.*;
import java.util.Date;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Set;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import us.ttyl.chance.common.ChanceConfiguration;
import us.ttyl.chance.common.UserAttributes;
import us.ttyl.chance.common.UserManager;
import us.ttyl.chance.common.UserManagerImpl;
import us.ttyl.chance.dao.BodytypeDAO;
import us.ttyl.chance.dao.ChoiceDAO;
import us.ttyl.chance.dao.ColorDAO;
import us.ttyl.chance.dao.EthnicityDAO;
import us.ttyl.chance.dao.HasChildrenDAO;
import us.ttyl.chance.dao.LocationDAO;
import us.ttyl.chance.dao.LookingForDAO;
import us.ttyl.chance.dao.MaritalStatusDAO;
import us.ttyl.chance.dao.PictureDAO;
import us.ttyl.chance.dao.SexDAO;
import us.ttyl.chance.dao.WantsChildrenDAO;
import us.ttyl.chance.domain.*;
import us.ttyl.chance.hibernate.HibernateSessionFactory;
import us.ttyl.chance.startup.SystemStartup;

public class Driver
{
	public static void main(String args[])
	{	
		Hashtable params = new Hashtable();
		params.put("image_directory", "F:/dev/jane_chance_web/image_dir");
		params.put("smtp_host", "smpp.comcast.net");
		params.put("source", "admin@jancechance.com");
		SystemStartup.startSystem(params);
		new Driver();
	}
	
	public Driver()
	{
		try
		{
			UserManager um = new UserManagerImpl();
			//check age (40 yrs should return 2, should include 96782 user)
			PageList pl = um.baseSearch(1, 1, 40, "94085", 100, 0, 100, -1);
			for(int i = 0; i < pl.getM_data().size(); i ++)
			{
				User u = (User)pl.getM_data().get(i);
				System.out.println(u.getUserName() + ":" + u.getZipcode().getZipcode());
			}	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
