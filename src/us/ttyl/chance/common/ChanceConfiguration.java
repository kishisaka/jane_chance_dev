package us.ttyl.chance.common;

import java.util.*;

public abstract class ChanceConfiguration 
{
	//test udpate 
	
	public static String START_PAGE = "start";
	public static String ADVANCED_SEARCH = "advanced_search";
	public static String ADVANCED_SEARCH_FIND_USERS = "advanced_search_find_users";
	public static String FORWARD_PAGE = "forward";
	public static String PREVIOUS_PAGE = "previous";
	public static String START_LOOKING = "startlooking";
	public static String LOGIN = "login";
	public static String REGISTER = "register";
	public static String REGISTER_USER = "register_user";
	public static String EDIT_USER = "edit_user";
	public static String UPDATE_USER = "update_user";
	public static String LOG_OUT = "logout";
	public static String SHOW_PROFILE = "showprofile";
	public static String LIST_MESSAGES = "list_messages";
	public static String DELETE_MESSAGES = "delete_messages";
	public static String SHOW_MESSAGE = "show_message";
	public static String CREATE_MESSAGE = "create_message";
	public static String SEND_MESSAGE = "send_message";
	public static String REPLY_MESSAGE = "reply_message";
	public static String UPLOAD_PICTURES = "upload_pictures";
	public static String GOTO_UPLOAD_PICTURES_PAGE = "goto_upload_pictures_page";
	public static String SET_MAIN_IMAGE = "set_main_image";	
	public static String DELETE_IMAGE = "delete_image";	
	public static String VIEW_PROFILES = "view_profiles";
	public static String SAVE_PROFILE = "save_profiles";
	public static String RESET_PASSWORD = "resetpassword";
	public static String UPDATE_PASSWORD = "updatepassword";
	public static String GO_RESET_PASSWORD_PAGE = "goresetpasswordpage";
	public static String ABUSE_SEND_PAGE = "send_abuse_report";
	
	//fields parameter names
	public static String MODE = "mode";
	public static String USER_NAME = "username";
	public static String USER_PASSWORD = "userpassword";
	public static String GENDER = "gender";
	public static String AGE_START = "age_start";
	public static String AGE_END = "age_end";
	public static String ZIPCODE = "zipcode";
	public static String DISTANCE = "distance";
	
	public static String ERROR_NOTICE = "errornotice";
	
	public static String LOGIN_NAME = "loginname";
	public static String PASSWORD = "password";
	public static String PASSWORD_CONFIRM = "passwordconfirm";
	public static String EMAIL = "email";
	public static String EMAIL_CONFIRM = "emailconfirm";
	public static String BIRTHDATE_MONTH = "birthdatemonth";
	public static String BIRTHDATE_YEAR	= "birthdateyear";
	public static String ETHNICITY = "ehtnicity";
	public static String CATCH_PHRASE = "catchphrase";
	public static String ABOUT_ME = "aboutme";
	public static String TERMS_AND_CONDITIONS = "termsandconditions";
	
	public static String SEX = "sex";
	public static String SEX_LOOKING_FOR = "sexlookingfor";
	public static String HEIGHT_FEET = "heightfeet";
	public static String HEIGHT_INCHES = "heightinches";
	public static String HEIGHT_LOOKING_FOR_FEET_START = "heightfeetlookingforstart";
	public static String HEIGHT_LOOKING_FOR_FEET_END = "heightfeetlookingforend";
	public static String HEIGHT_LOOKING_FOR_INCHES_START = "heightincheslookingforstart";
	public static String HEIGHT_LOOKING_FOR_INCHES_END = "heightincheslookingforend";
	public static String EYE_COLOR = "eyecolor";
	public static String HAIR_COLOR = "haircolor";
	public static String SMOKE = "smoke";
	public static String SMOKE_LOOKING_FOR = "smokelookingfor";
	public static String DRINK = "drink";
	public static String DRINK_LOOKING_FOR = "drinklookingfor";
	public static String HAS_CHILDREN = "haschildren";
	public static String HAS_CHILDREN_LOOKING_FOR = "haschildrenlookingfor";
	public static String WANTS_CHILDREN = "wantschildren";
	public static String WANTS_CHILDREN_LOOKING_FOR = "wantschildrenlookingfor";	
	public static String MARITAL_STATUS = "maritalstatus";
	public static String MARITAL_STATUS_LOOKING_FOR = "maritalstatuslookingfor";
	public static String BODY_TYPE = "bodytype";
	public static String BODY_TYPE_LOOKING_FOR = "bodytypelookingfor";
	public static String IMAGE_ID = "imageid";	
	public static String ABUSER_USER_NAME = "abuserusername";
	public static String ABUSER_DESCRIPTION = "abuserdesciption";
	
	public static String ZIPCODE_LOOKING_FOR = "zipcodelookingfor";
	public static String DISTANCE_LOOKING_FOR = "distancelookingfor";
	
	//load attributes from db
	private static Hashtable m_bodyType = null;
	private static Hashtable m_choice = null;
	private static Hashtable m_color = null;
	private static Hashtable m_ethnicity = null;
	private static Hashtable m_hasChildren = null;
	private static Hashtable m_lookingFor = null;
	private static Hashtable m_maritalStatus = null;
	private static Hashtable m_sex = null;
	private static Hashtable m_wantsChildren = null;	
	private static Hashtable m_zipcodeTable = null;
	private static List m_zipcodes = null;	
	private static List m_googleMapMarkers = null;
	
	//mail configuration
	private static String m_smtpHost = "";
	private static String m_mailSource = "";	
	private static String m_mailUser = "";
	private static String m_mailPassword = "";
	private static String m_smtpPort = "";
	
	//google map time configuration
	private static int m_hour = 5;		

	private static int m_sleepInterval = 1000*60*60;
	
	//set the root context here
	public static String CONTEXT_ROOT= "";  
	
	//user list state count	
	private static List m_userCount = null;
	
	//image directory read from web.xml
	private static String m_imageDirectory = "";
	
	//defualt attribute id
	public static int DEFAULT_ATTR_ID = 99;
	
	//date format Strings
	public static String BIRTHDATE_FORMAT= "MMMMM/YYYY";
	
	//search page size
	private static int m_pageSize = 5;
		
	public static String getM_smtpPort() 
	{
		return m_smtpPort;
	}

	public static void setM_smtpPort(String port) 
	{
		m_smtpPort = port;
	}
	
	public static int getM_pageSize() 
	{
		return m_pageSize;
	}

	public static void setM_pageSize(int size) 
	{
		m_pageSize = size;
	}

	public static Hashtable getM_bodyType() 
	{
		return m_bodyType;
	}

	public static Hashtable getM_choice() 
	{
		return m_choice;
	}

	public static Hashtable getM_color() 
	{		
		return m_color;
	}

	public static Hashtable getM_ethnicity() 
	{
		return m_ethnicity;
	}

	public static Hashtable getM_hasChildren() 
	{
		return m_hasChildren;
	}

	public static Hashtable getM_lookingFor() 
	{
		return m_lookingFor;
	}

	public static Hashtable getM_maritalStatus() 
	{
		return m_maritalStatus;
	}

	public static Hashtable getM_sex() 
	{
		return m_sex;
	}

	public static Hashtable getM_wantsChildren() 
	{
		return m_wantsChildren;
	}

	public static void setM_bodyType(Hashtable type) 
	{
		m_bodyType = type;
	}

	public static void setM_choice(Hashtable m_choice) 
	{
		ChanceConfiguration.m_choice = m_choice;
	}

	public static void setM_color(Hashtable m_color) 
	{
		ChanceConfiguration.m_color = m_color;
	}

	public static void setM_ethnicity(Hashtable m_ethnicity)
	{
		ChanceConfiguration.m_ethnicity = m_ethnicity;
	}

	public static void setM_hasChildren(Hashtable children) 
	{
		m_hasChildren = children;
	}

	public static void setM_lookingFor(Hashtable for1) 
	{
		m_lookingFor = for1;
	}

	public static void setM_maritalStatus(Hashtable status) 
	{
		m_maritalStatus = status;
	}

	public static void setM_sex(Hashtable sex) 
	{
		m_sex = sex;
	}
	public static void setM_wantsChildren(Hashtable children) 
	
	{
		m_wantsChildren = children;
	}
	
	public static String getM_imageDirectory() 
	{
		return m_imageDirectory;
	}

	public static void setM_imageDirectory(String directory) 
	{
		m_imageDirectory = directory;
	}

	public static List getList(Hashtable table)
	{
		ArrayList list = new ArrayList();
		Enumeration e = table.keys();
		while (e.hasMoreElements())
		{			 
			list.add(table.get(e.nextElement()));
		}		
		return list;
	}

	public static List getM_userCount() 
	{
		return m_userCount;
	}

	public static void setM_userCount(List count) 
	{
		m_userCount = count;
	}

	public static List getM_zipcodes() 
	{
		return m_zipcodes;
	}

	public static void setM_zipcodes(List zipcodes) 
	{
		m_zipcodes = zipcodes;
	}

	public static Hashtable getM_zipcodeTable() 
	{
		return m_zipcodeTable;
	}

	public static void setM_zipcodeTable(Hashtable table) 
	{
		m_zipcodeTable = table;
	}

	public static List getM_googleMapMarkers() {
		return m_googleMapMarkers;
	}

	public static void setM_googleMapMarkers(List mapMarkers) {
		m_googleMapMarkers = mapMarkers;
	}

	public static String getM_smtpHost() {
		return m_smtpHost;
	}

	public static void setM_smtpHost(String host) {
		m_smtpHost = host;
	}
	
	public static String getM_mailUser() {
		return m_mailUser;
	}

	public static void setM_mailUser(String mailUser) {
		m_mailUser = mailUser;
	}
	
	public static String getM_mailPassword() {
		return m_mailPassword;
	}

	public static void setM_mailPassword(String mailPasword) {
		m_mailPassword = mailPasword;
	}

	public static String getM_mailSource() {
		return m_mailSource;
	}

	public static void setM_mailSource(String mailSource) {
		m_mailSource = mailSource;
	}

	public static int getM_hour() {
		return m_hour;
	}

	public static void setM_hour(String m_hour) {
		ChanceConfiguration.m_hour = Integer.parseInt(m_hour);
	}

	public static int getM_sleepInterval() {
		return m_sleepInterval;
	}

	public static void setM_sleepInterval(String interval) {
		m_sleepInterval = Integer.parseInt(interval);
	}
}
