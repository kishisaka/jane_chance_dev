package us.ttyl.chance.domain;

import java.util.*;
import us.ttyl.chance.domain.*;
import us.ttyl.chance.common.*;

public class User 
{
	private Integer userId;
	private Date birthdate;
	private TEthnicity ethnicity;
	private TSex sex;
	private TSex sexLookingFor;
	private TColor eyeColor;
	private TColor hairColor;
	private TChoice smoke;
	private TChoice drink;
	private THasChildren haveChildern;
	private TWantsChildren wantChildern;
	private TMaritalStatus maritalStatus;
	private TBodytype bodyType;
	private String catchphrase = "";
	private String aboutme= "";
	private Integer height;	
	private Integer idealAgeStart;
	private Integer idealAgeEnd;
	private Integer idealDistance;
	private Zipcode idealZipcode;
	private Integer idealHeightStart;
	private Integer idealHeightEnd;
	private TChoice idealSmokes;
	private TChoice idealDrinks;
	private THasChildren idealHasChildern;
	private TWantsChildren idealWantsChildern;
	private TMaritalStatus idealMaritalStatus;
	private Integer termserviceagreement;
	private Integer profileStatus;
	private Zipcode zipcode;
	private Set pictures;
	private List idealBodyTypes;
	private List idealEthnicities;
	private List idealLookingfor;
	private Set messages;
	private String password = "";
	private String userName = "";
	private String email = "";
	private Integer changePasswordCode;
	
	public Set getMessages() {
		return messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

	public List getIdealBodyTypes() {
		return idealBodyTypes;
	}

	public void setIdealBodyTypes(List idealBodyTypes) {
		this.idealBodyTypes = idealBodyTypes;
	}

	public List getIdealEthnicities() {
		return idealEthnicities;
	}

	public void setIdealEthnicities(List idealEthnicities) {
		this.idealEthnicities = idealEthnicities;
	}

	public List getIdealLookingfor() {
		return idealLookingfor;
	}

	public void setIdealLookingfor(List idealLookingfor) {
		this.idealLookingfor = idealLookingfor;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User()
	{		
	}

	public Set getPictures() {
		return pictures;
	}

	public void setPictures(Set pictures) {
		this.pictures = pictures;
	}

	public String getAboutme() {
		return aboutme;
	}

	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public TBodytype getBodyType() {
		return bodyType;
	}

	public void setBodyType(TBodytype bodyType) {
		this.bodyType = bodyType;
	}

	public String getCatchphrase() {
		return catchphrase;
	}

	public void setCatchphrase(String catchphrase) {
		this.catchphrase = catchphrase;
	}

	public TChoice getDrink() {
		return drink;
	}

	public void setDrink(TChoice drink) {
		this.drink = drink;
	}

	public TEthnicity getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(TEthnicity ethnicity) {
		this.ethnicity = ethnicity;
	}

	public TColor getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(TColor eyeColor) {
		this.eyeColor = eyeColor;
	}

	public TColor getHairColor() {
		return hairColor;
	}

	public void setHairColor(TColor hairColor) {
		this.hairColor = hairColor;
	}

	public THasChildren getHaveChildern() {
		return haveChildern;
	}

	public void setHaveChildern(THasChildren haveChildern) {
		this.haveChildern = haveChildern;
	}

	public TChoice getIdealDrinks() {
		return idealDrinks;
	}

	public void setIdealDrinks(TChoice idealDrinks) {
		this.idealDrinks = idealDrinks;
	}

	public THasChildren getIdealHasChildern() {
		return idealHasChildern;
	}

	public void setIdealHasChildern(THasChildren idealHasChildern) {
		this.idealHasChildern = idealHasChildern;
	}

	public Integer getIdealHeightEnd() {
		return idealHeightEnd;
	}

	public void setIdealHeightEnd(Integer idealHeightEnd) {
		this.idealHeightEnd = idealHeightEnd;
	}

	public Integer getIdealHeightStart() {
		return idealHeightStart;
	}

	public void setIdealHeightStart(Integer idealHeightStart) {
		this.idealHeightStart = idealHeightStart;
	}

	public TMaritalStatus getIdealMaritalStatus() {
		return idealMaritalStatus;
	}

	public void setIdealMaritalStatus(TMaritalStatus idealMaritalStatus) {
		this.idealMaritalStatus = idealMaritalStatus;
	}

	public TChoice getIdealSmokes() {
		return idealSmokes;
	}

	public void setIdealSmokes(TChoice idealSmokes) {
		this.idealSmokes = idealSmokes;
	}

	public TWantsChildren getIdealWantsChildern() {
		return idealWantsChildern;
	}

	public void setIdealWantsChildern(TWantsChildren idealWantsChildern) {
		this.idealWantsChildern = idealWantsChildern;
	}

	public TMaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(TMaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getProfileStatus() {
		return profileStatus;
	}

	public void setProfileStatus(Integer profileStatus) {
		this.profileStatus = profileStatus;
	}

	public TSex getSex() {
		return sex;
	}

	public void setSex(TSex sex) {
		this.sex = sex;
	}

	public TSex getSexLookingFor() {
		return sexLookingFor;
	}

	public void setSexLookingFor(TSex sexLookingFor) {
		this.sexLookingFor = sexLookingFor;
	}

	public TChoice getSmoke() {
		return smoke;
	}

	public void setSmoke(TChoice smoke) {
		this.smoke = smoke;
	}

	public Integer getTermserviceagreement() {
		return termserviceagreement;
	}

	public void setTermserviceagreement(Integer termserviceagreement) {
		this.termserviceagreement = termserviceagreement;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public TWantsChildren getWantChildern() {
		return wantChildern;
	}

	public void setWantChildern(TWantsChildren wantChildern) {
		this.wantChildern = wantChildern;
	}

	public Zipcode getZipcode() {
		return zipcode;
	}

	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int findAge()
	{
		Calendar birthdateCal = Calendar.getInstance();
		birthdateCal.setTime(birthdate);
		Calendar currentDate = Calendar.getInstance();
		return (currentDate.get(Calendar.YEAR) - birthdateCal.get(Calendar.YEAR));	
	}
	
	public int findDistance(User targetUser)
	{
		double uLat = Double.parseDouble(zipcode.getGeoInfoLat());
		double uLong = Double.parseDouble(zipcode.getGeoInfoLong());
		double targetLat = Double.parseDouble(targetUser.getZipcode().getGeoInfoLat());
		double targetLong = Double.parseDouble(targetUser.getZipcode().getGeoInfoLong());
		return (int)ChanceUtils.calculateDistance(uLat, uLong, targetLat, targetLong, "mi");		
	}
	
	/**
	 * get main picture for set. 
	 * @return TPicture
	 */
	public TPicture getMainPicture()
	{
		TPicture currentPicture = null;
		if (pictures != null && pictures.size() > 0)
		{
			Iterator <TPicture> i = pictures.iterator();
			while (i.hasNext())
			{
				currentPicture = i.next(); 
				if (currentPicture.getMainPagePic() == 1 && currentPicture.getFilenamePath().startsWith("small_"))
				{
					break;
				}
			}
			return currentPicture; 
		}
		else
		{
			return null;
		}
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getChangePasswordCode() {
		return changePasswordCode;
	}

	public void setChangePasswordCode(Integer changePasswordCode) {
		this.changePasswordCode = changePasswordCode;
	}

	public Integer getIdealAgeStart() {
		return idealAgeStart;
	}

	public void setIdealAgeStart(Integer idealAgeStart) {
		this.idealAgeStart = idealAgeStart;
	}

	public Integer getIdealAgeEnd() {
		return idealAgeEnd;
	}

	public void setIdealAgeEnd(Integer idealAgeEnd) {
		this.idealAgeEnd = idealAgeEnd;
	}

	public Integer getIdealDistance() {
		return idealDistance;
	}

	public void setIdealDistance(Integer idealDistance) {
		this.idealDistance = idealDistance;
	}

	public Zipcode getIdealZipcode() {
		return idealZipcode;
	}

	public void setIdealZipcode(Zipcode idealZipcode) {
		this.idealZipcode = idealZipcode;
	}
}
