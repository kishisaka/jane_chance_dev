package us.ttyl.chance.common;

import java.util.*;
import java.io.*;
import us.ttyl.chance.domain.*;
import us.ttyl.chance.hibernate.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ZipcodeSeeder 
{
	HashMap m_geoDataCity = new HashMap();
	List m_geoDataZipcode = new ArrayList();
	HashMap m_countries = new HashMap();
	HashMap m_states = new HashMap();
	HashMap m_cities = new HashMap();
	
	public static void main(String args[])
	{
		new ZipcodeSeeder();		
	}
	
	public ZipcodeSeeder()
	{
		prepareData();
		seedCity();
		seedZipcode();
	}
	 
	public void prepareData()
	{
		try
		{
			// load data from zipcode file, create list of city and zipcodes
			// use city1 and state as key for city to ensure duplicates are not removed
			
			File file = new File("f:/dev/jane_chance_web/zipcodes/us_zips.csv");
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while(br.ready())
			{
				String dataLine = br.readLine();
				StringTokenizer st = new StringTokenizer(dataLine, ",");
												
				String zipcode = st.nextToken();
				String state = st.nextToken();
				String city1 = st.nextToken();
				String city2 = st.nextToken();
				String unknown1 = st.nextToken();
				String unknown2 = st.nextToken();
				String geoCodeLat = st.nextToken();
				String geoCodeLong = st.nextToken();
				
				GeoData geoData = new GeoData();
				geoData.setCity(city1);
				geoData.setGeoCodeLat(geoCodeLat);
				geoData.setGeoCodeLong(geoCodeLong);
				geoData.setState(state);
				geoData.setZipcode(zipcode);
				
				m_geoDataCity.put(city1+":"+state, geoData);
				
				m_geoDataZipcode.add(geoData);
			}
			
			//load countries from database
			Session session = HibernateSessionFactory.getSession();
			Query query = session.createQuery("from Country");
			List countries = query.list();
			for (int i = 0; i < countries.size(); i ++)
			{ 
				m_countries.put(((Country)countries.get(i)).getCountryCode(), (Country)countries.get(i));
			}
			
			//load states from database			
			query = session.createQuery("from State");
			List states = query.list();
			for (int i = 0; i < states.size(); i ++)
			{ 
				m_states.put(((State)states.get(i)).getStateCode(), (State)states.get(i));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void seedCity()	
	{
		// for every city in the city map, save it with cooresponding country and state. 
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try
		{
			Iterator i = m_geoDataCity.keySet().iterator();
			while (i.hasNext())
			{
				String key = (String)i.next();
				GeoData data = (GeoData)m_geoDataCity.get(key);
				
				City city = new City();
				city.setCityDesc(data.getCity());
				city.setCountry((Country)m_countries.get("USA"));
				city.setState((State)m_states.get(data.getState()));
				session.saveOrUpdate(city);
				
				//load citites into hashtable for seeZipcode() to use 
				m_cities.put(city.getCityDesc()+":"+city.getState().getStateCode(), city);
			}	
			transaction.commit();			
					
		}
		catch (Exception e)
		{
			e.printStackTrace();
			transaction.rollback();			
		}	
		finally
		{
			HibernateSessionFactory.closeSession();
		}
	}
	
	public void seedZipcode()
	{
		//for every zipcode in zipcode list , save it with cooresponding city and state.
		Transaction transaction = null;
		Session session = HibernateSessionFactory.getSession();							
		try
		{			
			transaction = session.beginTransaction();
			for(int i = 0; i < m_geoDataZipcode.size(); i ++)
			{
				GeoData dataZipcode = (GeoData)m_geoDataZipcode.get(i);
				String key = dataZipcode.getCity()+":"+dataZipcode.getState();
				City city = (City)m_cities.get(key);
				System.out.println(city.getCityId() + ":" + city.getCityDesc() + ":" + city.getState().getStateCode());
				
				Zipcode zipcode = new Zipcode();				
				zipcode.setCity(city);
				zipcode.setGeoInfoLat(dataZipcode.getGeoCodeLat());
				zipcode.setGeoInfoLong(dataZipcode.getGeoCodeLong());
				zipcode.setZipcode(new Integer(dataZipcode.getZipcode()));
				zipcode.setCountry((Country)m_countries.get("USA"));
				zipcode.setState((State)m_states.get(dataZipcode.getState()));
				session.saveOrUpdate(zipcode);				
			}	
			transaction.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			transaction.rollback();			
		}
		finally
		{
			HibernateSessionFactory.closeSession();
		}
	}
}

class GeoData
{
	String zipcode;
	String state;
	String city;
	String geoCodeLat;
	String geoCodeLong;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getGeoCodeLat() {
		return geoCodeLat;
	}
	public void setGeoCodeLat(String geoCodeLat) {
		this.geoCodeLat = geoCodeLat;
	}
	public String getGeoCodeLong() {
		return geoCodeLong;
	}
	public void setGeoCodeLong(String geoCodeLong) {
		this.geoCodeLong = geoCodeLong;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}	
}
