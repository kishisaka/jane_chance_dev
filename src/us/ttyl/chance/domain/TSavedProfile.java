package us.ttyl.chance.domain;

public class TSavedProfile implements java.io.Serializable 
{
	private Integer profileId;
	private User user;
	private User savedUser;
	
	public TSavedProfile()
	{
	}

	public Integer getProfileId() {
		return profileId;
	}

	public void setProfileId(Integer profileId) {
		this.profileId = profileId;
	}

	public User getSavedUser() {
		return savedUser;
	}

	public void setSavedUser(User savedUser) {
		this.savedUser = savedUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
