package us.ttyl.chance.domain;

import java.util.HashSet;
import java.util.Set;

public class State 
{
	private Integer stateId;
	private Country country;
	private Set cities = new HashSet();
	private	String stateCode = "";
	private String stateDesc = "";
	
	 /** default constructor */
    public State() 
    {
    }

    /** full constructor */
    public State(String stateCode, String stateDesc, Country country, Set cities) 
    {
        this.stateCode = stateCode;
        this.stateDesc = stateDesc;
        this.country = country;
        this.cities = cities;
    }
    
    public Set getCities()
    {
    	return this.cities;
    }
    
    public void setCities(Set cities)
    {
    	this.cities = cities;
    }
    
	public Integer getStateId() {
		return stateId;
	}
	
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}


	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}
    
}
