package us.ttyl.chance.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import us.ttyl.chance.domain.Zipcode;
import us.ttyl.chance.domain.Distance;

public class ChanceUtils 
{
	/**
	 * return distance betwee two points specified by lat and long coords. 
	 * @param geoLatSrc
	 * @param geoLongSrc
	 * @param geoLatDest
	 * @param geoLongDest
	 * @param type (km or mi)
	 * @return
	 */
	public static double calculateDistance(double geoLatSrc, double geoLongSrc, double geoLatDest, double geoLongDest, String type) 
	{
		double radCalc = Math.PI / 180;
		double latRad1 = geoLatSrc * (radCalc);
		double lonRad1 = geoLongSrc * (radCalc);
		double latRad2 = geoLatDest * (radCalc);
		double lonRad2 = geoLongDest * (radCalc);

		double lonRadDif = Math.abs(lonRad1 - lonRad2);
		double X = Math.sin(latRad1) * Math.sin(latRad2) + Math.cos(latRad1) * Math.cos(latRad2) * Math.cos(lonRadDif);		
		double radDist = Math.atan(-X / Math.sqrt(-X * X + 1)) + 2 * Math.atan(1);
		if (type.equals("km"))
		{
			return (radDist * 3958.754) * 1.609344;
		}
		else
		{
			return radDist * 3958.754;
		}		
	}
	
	/**
	 * given a zipcode and a distance radius,  find all zipcodes that within the specifed distance radius, 
	 * return a list of zipcodes in that radius
	 * @param startCode
	 * @param distanceRadius
	 * @return list of zipcodes within radius specified
	 */
	public static List <Distance> getDistancesForSrcZipcode(Zipcode startCode, int distanceRadius)
	{	
		List <Distance> zipcodeList = new ArrayList <Distance> ();		
		try
		{			
			for(int i = 0; i < ChanceConfiguration.getM_zipcodes().size(); i++)
			{				
				Zipcode destZipcode = (Zipcode)(ChanceConfiguration.getM_zipcodes().get(i));
				double srcLat = Double.parseDouble(startCode.getGeoInfoLat());
				double srcLong = Double.parseDouble(startCode.getGeoInfoLong());
				double destLat = Double.parseDouble(destZipcode.getGeoInfoLat());
				double destLong = Double.parseDouble(destZipcode.getGeoInfoLong());
				int distance = (int)ChanceUtils.calculateDistance(srcLat, srcLong, destLat, destLong, "mi");
				if (distance <= distanceRadius)
				{
					Distance d = new Distance();
					d.setDistanceId(i);
					d.setDistanceAmt(distance);
					d.setZipcodeIdOrigin(startCode);
					d.setZipcodeIdTarget(destZipcode);
					zipcodeList.add(d);
				}				
			}	
			zipcodeList = sortDistances(zipcodeList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return zipcodeList;
	}	
	
	private static List <Distance> sortDistances(List <Distance> distanceList)
	{
		Distance[] distances = distanceList.toArray(new Distance[]{});
		Arrays.sort(distances, new DistanceSorter());
		List <Distance> distanceListSorted = new ArrayList();
		for(int i = 0; i < distances.length; i ++)
		{
			distanceListSorted.add(distances[i]);
		}
		return distanceListSorted;
	}
}

class DistanceSorter implements Comparator
{
	public int compare(Object o1, Object o2) 
	{
		Distance d1 = (Distance)o1;
		Distance d2 = (Distance)o2;
		if (d1.getDistanceAmt().intValue() > d2.getDistanceAmt().intValue())
		{
			return 1;			
		}
		else
		{
			if (d1.getDistanceAmt().intValue() < d2.getDistanceAmt().intValue())
			{
				return -1;
			}
		}
		return 0;
	}
}
