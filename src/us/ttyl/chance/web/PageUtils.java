package us.ttyl.chance.web;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.ttyl.chance.domain.*;
import us.ttyl.chance.common.*;

public class PageUtils 
{
	/**
	 * soft forward to a page
	 * @param address
	 * @param ctx
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void forwardToJspPage( String address, ServletContext ctx, HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{		
		RequestDispatcher dispatcher = ctx.getRequestDispatcher( response.encodeURL(address) );
		dispatcher.forward( request, response );	
	}
	
	/**
	 * saves the advanced search criteria for forward and backward searches
	 * @param user
	 * @param request
	 */
	public static void setAdvancedSearchUser(User user, HttpServletRequest request)
	{
		request.getSession().setAttribute("adv_search_user", user);
	}
	
	/**
	 * returns the advanced search criteria for forward and backward searches
	 * @param request
	 * @return
	 */
	public static User getAdvancedSearchUser(HttpServletRequest request)
	{
		return (User)request.getSession().getAttribute("adv_search_user");
	}
	
	/**
	 * soft forward to a page, use 303 instead of 302
	 * @param address
	 * @param ctx
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void redirectToJspPage( String address, ServletContext ctx, HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		String url = request.getContextPath() + response.encodeRedirectURL(address);
		RequestDispatcher dispatcher = ctx.getRequestDispatcher( address );
		response.setStatus(HttpServletResponse.SC_SEE_OTHER);  // Use a 303 redirect instead of 302
		response.setHeader("Location", url);
		dispatcher.forward( request, response );
	}
	
	/**
	 * get the curretnly selected user
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static User getSelectedProfile(HttpServletRequest request)  throws ServletException, IOException
	{
		return (User)request.getSession().getAttribute("selected_user");
	}
	
	/**
	 * set the currently selected user
	 * @param user
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void setSelectedProfile(User user, HttpServletRequest request)  throws ServletException, IOException
	{
		request.getSession().setAttribute("selected_user", user);
	}
	
	/**
	 * set current logged in user
	 * @param user
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void setUser(User user, HttpServletRequest request)  throws ServletException, IOException
	{
		request.getSession().setAttribute("user", user);
	}
	
	/**
	 * get currently logged in user
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static User getUser(HttpServletRequest request)  throws ServletException, IOException
	{
		return (User)request.getSession().getAttribute("user");
	}
	
	/**
	 * set the current record index pointer 
	 * @param pageIndex
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void setRecordIndex(int pageIndex, HttpServletRequest request)  throws ServletException, IOException
	{		
		request.getSession().setAttribute("recordIndex", new Integer(pageIndex));
	}
	
	/**
	 * get the current recoprd index pointer
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static int getRecordIndex(HttpServletRequest request)  throws ServletException, IOException
	{
		return ((Integer)request.getSession().getAttribute("recordIndex")).intValue();
	}
	
	/**
	 * set the user list 
	 * @param users
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void setUserList(PageList users, HttpServletRequest request)  throws ServletException, IOException
	{		
		request.getSession().setAttribute("userList", users);
	}
	
	/**
	 * get the user list 
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static PageList getUserList(HttpServletRequest request)  throws ServletException, IOException
	{
		return (PageList)request.getSession().getAttribute("userList");
	}
	
	/**
	 * set finder mode
	 * potential finder modes: ("user", "search")
	 * 
	 * @param finderMode
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */ 
	public static void setMode(String finderMode, HttpServletRequest request)  throws ServletException, IOException
	{		
		request.getSession().setAttribute("finderMode", finderMode);
	}
	
	/**
	 * set finder mode
	 * potential finder modes: ("user", "search")
	 * 
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String getMode(HttpServletRequest request)  throws ServletException, IOException
	{
		return (String)request.getSession().getAttribute("finderMode");
	}
	
	/**
	 * get search parameters for main screen
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static SearchParameters getSearchParameters(HttpServletRequest request)  throws ServletException, IOException
	{
		return (SearchParameters)request.getSession().getAttribute("searchParams");
	}
	
	/**
	 * set search parameters for main screen 
	 * @param searchParameters
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void setSearchParameters(SearchParameters searchParameters, HttpServletRequest request)  throws ServletException, IOException
	{		
		request.getSession().setAttribute("searchParams", searchParameters);
	}
	
	/**
	 * set usermessages parameter for view message screen
	 * @param userMessages
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void setUserMessages(List <TUsermessages> userMessages,  HttpServletRequest request) throws ServletException, IOException
	{
		request.getSession().setAttribute("userMessages", userMessages);
	}
	
	/**
	 * get usermessages for view messages screen
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static List <TUsermessages> getUserMessages(HttpServletRequest request) throws ServletException, IOException
	{
		return (List <TUsermessages>)request.getSession().getAttribute("userMessages");
	}
	
	public static int getNewUserMessageCount(HttpServletRequest request) throws ServletException, IOException
	{
		return ((Integer)request.getSession().getAttribute("userMessageCount")).intValue();	
	}
	
	public static void setNewUserMessageCount(HttpServletRequest request, int userMessageCount) throws ServletException, IOException
	{
		request.getSession().setAttribute("userMessageCount", new Integer(userMessageCount));	
	}
	
	/**
	 * set usermessages parameter for view message screen
	 * @param userMessages
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void setSelectedMessage(TUsermessages  selectedMessage,  HttpServletRequest request) throws ServletException, IOException
	{
		request.getSession().setAttribute("selectedmessage", selectedMessage);
	}
	
	/**
	 * get usermessages for view messages screen
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static TUsermessages getSelectedMessage(HttpServletRequest request) throws ServletException, IOException
	{
		return (TUsermessages)request.getSession().getAttribute("selectedmessage");
	}
	/**
	 * get return to page
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public static String getReturnToPage(HttpServletRequest request)  throws ServletException, IOException
	{
		return (String)request.getSession().getAttribute("returntopage");
	}
	
	public static void setReturnToPage(String returnToPage, HttpServletRequest request)  throws ServletException, IOException
	{
		request.getSession().setAttribute("returntopage", returnToPage);
	}
	
	public static HashMap getErrorTable(HttpServletRequest request)  throws ServletException, IOException
	{
		return (HashMap)request.getSession().getAttribute("errortable");
	}
	
	public static void setErrorTable(HashMap errorTable, HttpServletRequest request)  throws ServletException, IOException
	{
		request.getSession().setAttribute("errortable", errorTable);
	}
}
