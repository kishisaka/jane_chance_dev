package us.ttyl.chance.dao;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.*;
import java.math.*;

import org.hibernate.SQLQuery;
import org.hibernate.*;

import us.ttyl.chance.domain.*;
import us.ttyl.chance.hibernate.HibernateSessionFactory;
import us.ttyl.chance.common.*;

public class GoogleMapMarkersDAO extends CommonDAO
{	
	private final String truncateMapMakers = "delete from GoogleMapMarkers";
	
	private final String userCount = ""
		+" select "
		+" users.zipcode, users.number_of_users, zipcode.geo_info_lat, zipcode.geo_info_long "
		+" from "
		+" ( "
		+" select " 
		+" u.zipcode_id zipcode, count(0) number_of_users " 
		+" from "
		+" t_user u group by u.zipcode_id " 
		+" order by "
		+" number_of_users desc limit ? "
		+" ) users "
		+" , t_zipcode zipcode "
		+" where "
		+" zipcode.zipcode_id = users.zipcode ";
	
	public Hashtable getAll()
	{
		return null;
	}
	
	public void updateMarkerList()
	{		
		List markers = new ArrayList();
		Session session = HibernateSessionFactory.getSession();
		Transaction transaction = null;
		try
		{				
			transaction = session.beginTransaction();
			
			//TODO remove existing record first!!!
			Query queryTrunc = session.createQuery(truncateMapMakers);
			queryTrunc.executeUpdate();			
			
			SQLQuery query = session.createSQLQuery(userCount);	
			query.setInteger(0, 5);
			List objects = query.list();			
			for(int i = 0; i < objects.size(); i ++)
			{
				Object[] elements = (Object[])objects.get(i);
				GoogleMapMarkers marker = new GoogleMapMarkers();
				marker.setZipcode(((Integer)elements[0]).toString());
				marker.setNumberOfUsers(((BigInteger)elements[1]).intValue());
				marker.setGeoInfoLat((String)elements[2]);
				marker.setGeoInfoLong((String)elements[3]);
				
				transaction = session.beginTransaction();
				session.saveOrUpdate(marker);
				
				markers.add(marker);
			}								
			
			transaction.commit();
			
			//update system google marker list
			ChanceConfiguration.setM_googleMapMarkers(markers);
		}
		catch (Exception e)
		{	
			e.printStackTrace();
			transaction.rollback();
		}
		finally
		{
			session.close();
		}
	}
}
