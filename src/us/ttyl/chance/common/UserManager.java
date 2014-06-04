package us.ttyl.chance.common;

import java.util.*;

import org.hibernate.ScrollableResults;

import us.ttyl.chance.domain.TBodytype;
import us.ttyl.chance.domain.TChoice;
import us.ttyl.chance.domain.TColor;
import us.ttyl.chance.domain.TEthnicity;
import us.ttyl.chance.domain.THasChildren;
import us.ttyl.chance.domain.TMaritalStatus;
import us.ttyl.chance.domain.TSex;
import us.ttyl.chance.domain.TUsermessages;
import us.ttyl.chance.domain.TWantsChildren;
import us.ttyl.chance.domain.User;
import us.ttyl.chance.domain.Zipcode;

public interface UserManager 
{
	public Integer createUser(
			Date birthdate
			, TEthnicity ethnicity
			, TSex sex
			, TSex sexLookingFor
			, Integer height
			, TColor eyeColor
			, TColor hairColor	
			, TChoice smoke
			, TChoice drink
			, THasChildren haveChildren
			, TWantsChildren wantsMoreChildren
			, TMaritalStatus maritalStatus
			, TBodytype userBodyType
			, String catchphrase
			, String aboutme
			, Integer idealHeightStart
			, Integer idealHeightEnd
			, TChoice idealSmokes
			, TChoice idealDrinks
			, THasChildren idealHasChildren
			, TWantsChildren idealWantsChildren
			, TMaritalStatus idealMaritalStatus
			, Integer termserviceagreement			
			, Integer profileStatus
			, Zipcode zipcode
			, String password
			, String username
			, String email
			, List idealEthnicities
			, List idealBodyTypes			
			, List idealLookingfor
			, Zipcode idealZipcode
			, Integer idealAgeStart
			, Integer idealAgeEnd
			, Integer idealDistance
		) throws Exception;
	
	public void saveUser(User user) throws Exception;
	
	public User findUserById(int id) throws Exception;
	
	public PasswordInfo resetUserPassword(String loginName) throws Exception;
	
	public void setMainPicture(User user, int pictureId) throws Exception;
	
	public void updateUser(User user) throws Exception;
	
	public void deleteMessages(String[] messageIds) throws Exception;	
	
	public void addUserMessage(TUsermessages message) throws Exception;
	
	public List <TUsermessages> getUserMessages(User user) throws Exception;
	
	public int getNewMessageCount(User user) throws Exception;	
	 
	public Integer addPicture(User user, String path) throws Exception;
	
	public User login(String userName, String password) throws Exception;
	
	public PageList baseSearch(int gender, int ageStage, int ageEnd, String zipcode, 
			int distance, int startIndex, int pageSize, int userId) throws Exception;
	
	public PageList loadUsersForUser(User user, int startIndex, int pageSize) throws Exception;
	
	public Zipcode findZipcode(String zipcode);
	
	public void deletePicture(Integer pictureId) throws Exception;
	
	public PageList findProfiles(User user, int startingRow, int listSize) throws Exception;
	
	public void saveProfile(User user, User targetUser) throws Exception;
	
	public boolean checkLoginNameAlreadyUsed(String loginName) throws Exception;
	
	public void changeUserPassword(User user, String password) throws Exception;
	
	public void removeAllUsers() throws Exception;
	
	public void viewMessage(int messageId) throws Exception;
}
