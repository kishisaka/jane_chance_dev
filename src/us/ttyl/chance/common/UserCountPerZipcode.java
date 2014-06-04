package us.ttyl.chance.common;

public class UserCountPerZipcode 
{
	private String m_zipcode;
	private int m_userCount;
	private String m_lat;
	private String m_long;
	
	public String getM_lat() {
		return m_lat;
	}
	public void setM_lat(String m_lat) {
		this.m_lat = m_lat;
	}
	public String getM_long() {
		return m_long;
	}
	public void setM_long(String m_long) {
		this.m_long = m_long;
	}
	public int getM_userCount() {
		return m_userCount;
	}
	public void setM_userCount(int count) {
		m_userCount = count;
	}
	public String getM_zipcode() {
		return m_zipcode;
	}
	public void setM_zipcode(String m_zipcode) {
		this.m_zipcode = m_zipcode;
	}
	
	
}
