package us.ttyl.chance.common;

public class PasswordInfo 
{
	private String m_newPassword;
	private String m_userEmail;
	
	public PasswordInfo(String email, String password)
	{
		m_newPassword = password;
		m_userEmail = email;
	}
	
	public String getM_newPassword() 
	{
		return m_newPassword;
	}
	
	public String getM_userEmail() 
	{
		return m_userEmail;
	}
	
}
