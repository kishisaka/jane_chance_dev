package us.ttyl.chance.domain;

public class SimpleUserInfo 
{
	String zipcode;
	Integer distance;
	Integer userId;
	String pictureFilename;
	String catchPhrase;
	String city;
	String state;
	String userName;
	
	public String getPictureFilename() 
	{
		return pictureFilename;
	}

	public void setPictureFilename(String pictureFilename) 
	{
		this.pictureFilename = pictureFilename;
	}

	public String getCatchPhrase() 
	{
		return catchPhrase;
	}

	public void setCatchPhrase(String catchPhrase) 
	{
		this.catchPhrase = catchPhrase;
	}

	public String getCity() 
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) 
	{
		this.state = state;
	}

	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getZipcode() 
	{
		return zipcode;
	}
	
	public void setZipcode(String zipcode) 
	{
		this.zipcode = zipcode;
	}
	
	public Integer getDistance() 
	{
		return distance;
	}
	
	public void setDistance(Integer distance) 
	{
		this.distance = distance;
	}
	
	public Integer getUserId() 
	{
		return userId;
	}
	
	public void setUserId(Integer userId) 	
	{
		this.userId = userId;
	}
	
	public String toString()
	{
		return "userId: " + userId + " | zipcode: " + zipcode + " | distance: " + distance + 
		" | pictureFilename: " + pictureFilename + " | userName:" + userName +
		" | catchPhrase: " + catchPhrase + " | city:" + city + " | state: " + state ;
	}
}
