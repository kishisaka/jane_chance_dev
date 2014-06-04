package us.ttyl.chance.dao;

import java.util.List;

import org.hibernate.Query;

import us.ttyl.chance.domain.*;
import us.ttyl.chance.hibernate.HibernateSessionFactory;

public class PictureDAO 
{
	/**
	 * get all pictures
	 * @param pictureId
	 * @return List of TPictures
	 */
	public List getPicturesByPictureId(Integer pictureId)
	{
		String queryString = "from TPicture where pictureId = ? and deleted=0";
		Query queryObject = HibernateSessionFactory.getSession().createQuery(queryString);
		queryObject.setParameter(0, pictureId);		
		return queryObject.list();    
	}
	
	/**
	 * save or update picture
	 * @param picture
	 * @return updated TPictures
	 */
	public TPicture savePicture(TPicture picture)
	{						
		HibernateSessionFactory.getSession().saveOrUpdate(picture);
		return picture;
	}
}
