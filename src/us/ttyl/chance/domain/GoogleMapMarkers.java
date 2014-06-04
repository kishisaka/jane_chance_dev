package us.ttyl.chance.domain;

public class GoogleMapMarkers 
{
	Integer markerId;
	String geoInfoLat;
	String getInfoLong;
	int numberOfUsers;
	String zipcode;
	
	public String getGeoInfoLat() 
	{		
		return geoInfoLat;
	}
	
	public void setGeoInfoLat(String m_lat) 
	{
		this.geoInfoLat = m_lat;
	}
	
	public String getGeoInfoLong() 
	{
		return getInfoLong;
	}
	
	public void setGeoInfoLong(String m_long) 
	{
		this.getInfoLong = m_long;
	}
	
	public int getNumberOfUsers() 
	{
		return numberOfUsers;
	}
	
	public void setNumberOfUsers(int ofUsers) 
	{
		numberOfUsers = ofUsers;
	}
	
	public String getZipcode() 
	{
		return zipcode;
	}
	
	public void setZipcode(String m_zipcode) 
	{
		this.zipcode = m_zipcode;
	}	
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(markerId);
		sb.append(":" + geoInfoLat);
		sb.append(":" + getInfoLong);
		sb.append(":" + numberOfUsers);
		sb.append(":" + zipcode);
		return sb.toString();
	}

	public Integer getMarkerId() {
		return markerId;
	}

	public void setMarkerId(Integer markerId) {
		this.markerId = markerId;
	}
}
