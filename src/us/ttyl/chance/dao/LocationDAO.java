package us.ttyl.chance.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.Session;

import java.util.*;

import us.ttyl.chance.domain.*;
import us.ttyl.chance.hibernate.HibernateSessionFactory;

public class LocationDAO 
{
	private static final Log log = LogFactory.getLog(LocationDAO.class);
	
	public City getCityById(Integer id) throws Exception
    {
        log.debug("getting City instance with id: " + id);
    	City instance = (City)HibernateSessionFactory.getSession().get("us.ttyl.chance.domain.City", id);        	
        return instance;
    }
	
	public Country getCountryById(Integer id) throws Exception 
    {
		log.debug("getting Country instance with id: " + id);
		Country instance = (Country)HibernateSessionFactory.getSession().get("us.ttyl.chance.domain.Country", id);        	
	    return instance;
    }
	
	public State getStateById(Integer id) throws Exception
    {
        log.debug("getting State instance with id: " + id);
        State instance = (State)HibernateSessionFactory.getSession().get("us.ttyl.chance.domain.State", id);        	
        return instance;
    }	
	
	public Zipcode getZipcodeById(Integer id) throws Exception
    {
        log.debug("getting Zipcode instance with id: " + id);
        Zipcode instance = (Zipcode)HibernateSessionFactory.getSession().get("us.ttyl.chance.domain.Zipcode", new Integer(id));        	
        return instance;
    }	
	
	public List getAllZipcodes() throws Exception
	{
		log.debug("getting all zipcode instances");
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery("from Zipcode");
        return query.list();
	}
	
	private List findByProperty(String sql, String propertyName, Object value) throws Exception
    {    	
    	String queryString = sql + propertyName + "=?";
    	Query queryObject = HibernateSessionFactory.getSession().createQuery(queryString);
    	queryObject.setParameter(0, value);
    	return queryObject.list();    	
	}	
	
	public List findStateByProperty(String propertyName, Object value) throws Exception
    {
		return findByProperty("from State as model where model.", propertyName, value);
	}
	
	public List findCountryByProperty(String propertyName, Object value) throws Exception
    {
		return findByProperty("from Country as model where model.", propertyName, value);
	}
	
	public List findCityByProperty(String propertyName, Object value) throws Exception
    {
		return findByProperty("from City as model where model.", propertyName, value);
	}
	
	public List findZipcodeByProperty(String propertyName, Object value) throws Exception
    {
		return findByProperty("from Zipcode as model where model.", propertyName, value);
	}
}
