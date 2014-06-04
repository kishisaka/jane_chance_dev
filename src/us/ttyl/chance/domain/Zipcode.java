package us.ttyl.chance.domain;

import java.util.*;

public class Zipcode 
{
	private Integer zipcode;
	private String geoInfoLat;
	private String geoInfoLong;
	private City city;
	private State state;
	private Country country;
	//private Set cities;
	
	public Zipcode()
	{
	}
	
	public Zipcode(String geoInfoLat, String geoInfoLong, Set cities)
	{
		this.geoInfoLat = geoInfoLat;
		this.geoInfoLong = geoInfoLong;
		//this.cities = cities;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

//	public Set getCities() {
//		return cities;
//	}
	
//	public void setCities(Set cities) {
//		this.cities = cities;
//	}
	
	public String getGeoInfoLat() {
		return geoInfoLat;
	}
	
	public void setGeoInfoLat(String geoInfoLat) {
		this.geoInfoLat = geoInfoLat;
	}
	
	public String getGeoInfoLong() {
		return geoInfoLong;
	}
	
	public void setGeoInfoLong(String geoInfoLong) {
		this.geoInfoLong = geoInfoLong;
	}
	
	public Integer getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
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
