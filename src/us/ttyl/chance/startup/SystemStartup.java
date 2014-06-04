package us.ttyl.chance.startup;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import us.ttyl.chance.common.ChanceConfiguration;
import us.ttyl.chance.dao.BodytypeDAO;
import us.ttyl.chance.dao.ChoiceDAO;
import us.ttyl.chance.dao.ColorDAO;
import us.ttyl.chance.dao.EthnicityDAO;
import us.ttyl.chance.dao.HasChildrenDAO;
import us.ttyl.chance.dao.LocationDAO;
import us.ttyl.chance.dao.LookingForDAO;
import us.ttyl.chance.dao.MaritalStatusDAO;
import us.ttyl.chance.dao.SexDAO;
import us.ttyl.chance.dao.WantsChildrenDAO;
import us.ttyl.chance.dao.*;
import us.ttyl.chance.domain.Zipcode;
import us.ttyl.chance.domain.GoogleMapMarkers;
import us.ttyl.chance.hibernate.HibernateSessionFactory;

public class SystemStartup 
{
	public static void startSystem(Hashtable params)
	{
		System.out.println("System started!");
		
		//get configuration from web.xml 
		ChanceConfiguration.setM_imageDirectory((String)params.get("image_directory"));
		System.out.println("getM_imageDirectory: " + ChanceConfiguration.getM_imageDirectory());
		
		ChanceConfiguration.setM_hour((String)params.get("hour"));
		System.out.println("getM_hour: " + ChanceConfiguration.getM_hour());
		
		ChanceConfiguration.setM_mailSource((String)params.get("mail_source"));		
		System.out.println("getM_mailSource: " + ChanceConfiguration.getM_mailSource());
		
		ChanceConfiguration.setM_mailUser((String)params.get("mail_user"));		
		System.out.println("getM_mailUser: " + ChanceConfiguration.getM_mailUser());
		
		ChanceConfiguration.setM_mailPassword((String)params.get("mail_password"));		
		System.out.println("getM_mailPassword: " + ChanceConfiguration.getM_mailPassword());
		
		ChanceConfiguration.setM_smtpHost((String)params.get("smtp_host"));
		System.out.println("getM_smtpHost: " + ChanceConfiguration.getM_smtpHost());
		
		ChanceConfiguration.setM_sleepInterval((String)params.get("sleep_interval"));
		System.out.println("getM_sleepInterval: " + ChanceConfiguration.getM_sleepInterval());
		
		ChanceConfiguration.setM_smtpPort((String)params.get("smtp_port"));
		System.out.println("getM_smtpPort: " + ChanceConfiguration.getM_smtpPort());				
		
		Session session = HibernateSessionFactory.getSession();
		try
		{
			BodytypeDAO bodyTypeDao = new BodytypeDAO();
			ChanceConfiguration.setM_bodyType(bodyTypeDao.getAll());
			
			ChoiceDAO choiceDao = new ChoiceDAO();
			ChanceConfiguration.setM_choice(choiceDao.getAll());
			
			ColorDAO colorDao = new ColorDAO();
			ChanceConfiguration.setM_color(colorDao.getAll());
			
			EthnicityDAO ethnicityDao = new EthnicityDAO();
			ChanceConfiguration.setM_ethnicity(ethnicityDao.getAll());
			
			HasChildrenDAO hasChildrenDao = new HasChildrenDAO();
			ChanceConfiguration.setM_hasChildren(hasChildrenDao.getAll());
			
			WantsChildrenDAO wantsChildrenDao = new WantsChildrenDAO();
			ChanceConfiguration.setM_wantsChildren(wantsChildrenDao.getAll());
			
			SexDAO sexDao = new SexDAO();
			ChanceConfiguration.setM_sex(sexDao.getAll());
			
			MaritalStatusDAO maritalStatusDao = new MaritalStatusDAO();
			ChanceConfiguration.setM_maritalStatus(maritalStatusDao.getAll());	
			
			LookingForDAO lookingForDao = new LookingForDAO();
			ChanceConfiguration.setM_lookingFor(lookingForDao.getAll());
			
			LocationDAO ld = new LocationDAO();			
			ChanceConfiguration.setM_zipcodes(ld.getAllZipcodes());
			
			Hashtable ziptable = new Hashtable();
			for(int i = 0; i < ChanceConfiguration.getM_zipcodes().size(); i ++)
			{				
				ziptable.put(((Zipcode)ChanceConfiguration.getM_zipcodes().get(i)).getZipcode(), (Zipcode)ChanceConfiguration.getM_zipcodes().get(i));
			}
			ChanceConfiguration.setM_zipcodeTable(ziptable);
			
			ChanceConfiguration.setM_googleMapMarkers(new GoogleMapMarkersDAO().getAllObjects("from GoogleMapMarkers"));
			
			GoogleMapMarkerUpdater gmmu = new GoogleMapMarkerUpdater();
			gmmu.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			HibernateSessionFactory.closeSession();
		}
	}
}
