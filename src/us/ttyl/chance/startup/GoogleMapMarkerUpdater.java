package us.ttyl.chance.startup;

import java.util.Calendar;

import us.ttyl.chance.common.ChanceConfiguration;
import us.ttyl.chance.dao.GoogleMapMarkersDAO;

/**
 * update the google map markers 
 * @author test
 *
 */
public class GoogleMapMarkerUpdater extends Thread 
{
	/**
	 * check time at every specified sleep interval and on a specified hour of the day, 
	 * update the google map markers.
	 */
	public void run()
	{	
		//update markers on startup! 
		updateMarkers();
		
		//update markers at the specified time interval!
		while (true)
		{
			try
			{										
				Calendar cal = Calendar.getInstance();
				if (cal.get(Calendar.HOUR_OF_DAY) == ChanceConfiguration.getM_hour())
				{						
					updateMarkers();
				}
				sleep(ChanceConfiguration.getM_sleepInterval());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}			
		}
	}
	
	public void updateMarkers()
	{
		try
		{
			//populate maker table
			new GoogleMapMarkersDAO().updateMarkerList();
			
			//get markers and put into marker map list.
			ChanceConfiguration.setM_googleMapMarkers(new GoogleMapMarkersDAO().getAllObjects("from GoogleMapMarkers"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
