package us.ttyl.chance.dao.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

import us.ttyl.chance.domain.*;
import us.ttyl.chance.hibernate.*;
import us.ttyl.chance.dao.*;

public class Tester
{	
	private static final Log m_logger = LogFactory.getLog(Tester.class);
	
	public static void main(String args[])
	{
		new Tester();
	}
	
	public Tester()
	{
		try
		{
			m_logger.debug("\n****************** waiting! doing city ********************\n");
			LocationDAO locdao = new LocationDAO();
			List <City> cities = locdao.findCityByProperty("cityDesc", (Object)"Los Angeles");
			for(int i = 0; i < cities.size(); i ++)			
			{				
				System.out.println(cities.get(i).getCityDesc() + ":" + cities.get(i).getState().getStateDesc() + ":" + cities.get(i).getCountry().getCountryCode());				
			}
			m_logger.debug("\n****************** waiting! doing country ********************\n");
			Thread.sleep(5000);			
			List <Country>countries = locdao.findCountryByProperty("countryCode", (Object)"USA");			
			for(int i = 0; i < countries.size(); i ++)			
			{										
				Iterator <State> y = countries.get(i).getStates().iterator();
				while (y.hasNext())					
				{
					System.out.println(y.next().getStateCode());
				}					
			}
			m_logger.debug("\n****************** waiting! doing state ********************\n");
			Thread.sleep(5000);
			List <State>states = locdao.findStateByProperty("stateCode", (Object)"CA");			
			for(int i = 0; i < states.size(); i ++)			
			{		
				Iterator <City> z = states.get(i).getCities().iterator();
				while (z.hasNext())					
				{
					System.out.println(z.next().getCityDesc());
				}
			}	
			
			m_logger.debug("\n****************** waiting! doing state ********************\n");
			Thread.sleep(5000);
			Zipcode zipcode = locdao.getZipcodeById(94085);	
			System.out.println(zipcode.getGeoInfoLat() + ":" + zipcode.getGeoInfoLong() + ":" + zipcode.getZipcode());
//			for(int i = 0; i < zipcode.getCities().size(); i ++)			
//			{		
//				Iterator <City> z = zipcode.getCities().iterator();
//				while (z.hasNext())					
//				{
//					System.out.println(z.next().getCityDesc());
//				}
//			}											
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
