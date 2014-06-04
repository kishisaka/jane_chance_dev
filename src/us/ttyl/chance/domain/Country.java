package us.ttyl.chance.domain;

import java.util.*;

public class Country 
{
	private Integer countryId = null;
    private Set states = new HashSet(0);
    private String countryCode = "";
    private String countryDesc = "";
    
    /** default constructor */
    public Country() 
    {
    }

    /** full constructor */
    public Country(String countryCode, String countryDesc, Set states) 
    {
        this.countryCode = countryCode;
        this.countryDesc = countryDesc;       
        this.states = states;
    }

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryDesc() {
		return countryDesc;
	}

	public void setCountryDesc(String countryDesc) {
		this.countryDesc = countryDesc;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Set getStates() {
		return states;
	}

	public void setStates(Set states) {
		this.states = states;
	}
    
    
}
