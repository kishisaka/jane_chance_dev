package us.ttyl.chance.common;

public abstract class UserManagmentFactory 
{
	public static UserManager getUserManager()
	{
		return new UserManagerImpl();
	}
}
