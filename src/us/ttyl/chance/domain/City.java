package us.ttyl.chance.domain;

public class City 
{
	private Integer cityId = null;
	private State state = null;
	private Country country = null;
	private String cityDesc = "";
	
	public City()
	{		
	}
	
	public City(State state, Country country, String cityDesc)
	{
		this.state = state;
		this.country = country;
		this.cityDesc = cityDesc;
	}
	
	public String getCityDesc()	{
		return cityDesc;
	}
	
	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}
	
	public Integer getCityId() {
		return cityId;
	}
	
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public Country getCountry() {
		return country;
	}
	
	public void setCountry(Country country) {
		this.country = country;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}

}
