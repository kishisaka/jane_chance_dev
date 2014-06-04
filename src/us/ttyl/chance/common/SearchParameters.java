package us.ttyl.chance.common;

public class SearchParameters 
{
	private String m_zipcode = null;
	private int m_gender = 0;
	private int m_startAge = 0;
	private int m_endAge = 0;
	private int m_distance = 0;
	
	public SearchParameters(String zipcode, int gender, int startAge, int endAge, int distance)
	{
		m_zipcode = zipcode;
		m_gender = gender;
		m_startAge = startAge;
		m_endAge = endAge;
		m_distance = distance;
	}
	
	public int getM_distance() 
	{
		return m_distance;
	}
	
	public int getM_endAge() 
	{
		return m_endAge;
	}
	
	public int getM_gender() 
	{
		return m_gender;
	}
	
	public int getM_startAge() 
	{
		return m_startAge;
	}
	
	public String getM_zipcode() 
	{
		return m_zipcode;
	}
}
