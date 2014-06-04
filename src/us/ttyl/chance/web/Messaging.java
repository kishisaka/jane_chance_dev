package us.ttyl.chance.web;


import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import us.ttyl.chance.common.*;
import us.ttyl.chance.domain.TUsermessages;
import us.ttyl.chance.domain.User;

public class Messaging extends GenericServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//get command mode
		String mode = request.getParameter("mode");
	
		//process command mode
		if(mode != null && (mode.equals("") == false))
		{						
			if (mode.equals(ChanceConfiguration.LIST_MESSAGES))
			{
				listMessages(request, response);
			}
			if (mode.equals(ChanceConfiguration.DELETE_MESSAGES))
			{
				deleteMessages(request, response);
			}
			if (mode.equals(ChanceConfiguration.SHOW_MESSAGE))
			{
				showMessage(request, response);
			}
			if (mode.equals(ChanceConfiguration.CREATE_MESSAGE))
			{
				createMessage(request, response);
			}
			if (mode.equals(ChanceConfiguration.SEND_MESSAGE))
			{
				sendMessage(request, response);
			}
			if (mode.equals(ChanceConfiguration.REPLY_MESSAGE))
			{
				replyMessage(request, response);
			}
		}
	}

    private void listMessages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	User user = PageUtils.getUser(request);
    	List <TUsermessages> userMessages = null;
    	
    	try
    	{
    		userMessages = UserManagmentFactory.getUserManager().getUserMessages(user);
    	}
    	catch (Exception e)		
		{
			e.printStackTrace();
		}
		PageUtils.setUserMessages(userMessages, request);
		PageUtils.forwardToJspPage("/jsp/list_messages.jsp", request.getSession().getServletContext(), request, response);

	}
    
    private void deleteMessages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	String[] messagesToDelete = request.getParameterValues("deleteMessageId");
    	if (messagesToDelete != null && messagesToDelete.length > 0)
    	{
    		try
    		{
    			UserManagmentFactory.getUserManager().deleteMessages(messagesToDelete);
    			User user = UserManagmentFactory.getUserManager().findUserById(PageUtils.getUser(request).getUserId());
    	    	PageUtils.setUser(user, request);
    		}
    		catch (Exception e)		
    		{
    			e.printStackTrace();
    		}
    	}    	
    	listMessages(request, response);
	}
    
    private void showMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	int messageId = Integer.parseInt(request.getParameter("messageid"));    	
    	List <TUsermessages> userMessages = PageUtils.getUserMessages(request);    	
    	for (int i = 0; i < userMessages.size(); i++)
    	{
    		TUsermessages userMessage = userMessages.get(i);
    		if (userMessage.getMessageId().intValue() == messageId)
    		{
    			PageUtils.setSelectedMessage(userMessage, request);
    			try
    			{
    				UserManagmentFactory.getUserManager().viewMessage(messageId);
    				userMessage.setViewedOn(new Date());
    			}
    			catch (Exception e)
    			{
    				e.printStackTrace();
    			}
    			break;
    		}    		
    	}
    	PageUtils.forwardToJspPage("/jsp/view_message.jsp", request.getSession().getServletContext(), request, response);
	}
    
    private void createMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	PageUtils.setReturnToPage("search", request);
    	editMessage(request, response);
	}
    
    private void sendMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	TUsermessages message = PageUtils.getSelectedMessage(request);    	
    	message.setMessageSubject(request.getParameter("messageSubject"));
    	message.setMessageContent(request.getParameter("messageContent"));
    	if (message.getMessageSubject() == null || message.getMessageSubject().length() ==  0)
    	{
    		message.setMessageSubject("[empty subject]");
    	}
    	if (message.getMessageContent() == null || message.getMessageContent().length() == 0)
    	{
    		message.setMessageContent("[empty message body]");
    	}
    	try
		{    		
    		UserManagmentFactory.getUserManager().addUserMessage(message);
		}
		catch (Exception e)		
		{
			e.printStackTrace();
		}
			
		String returnToPage = PageUtils.getReturnToPage(request);
    	
    	if (returnToPage.equals("list_messages"))
    	{
    		PageUtils.forwardToJspPage("/jsp/list_messages.jsp", request.getSession().getServletContext(), request, response);
    	}
		else 
		{
			PageUtils.forwardToJspPage("/jsp/search.jsp", request.getSession().getServletContext(), request, response);
		}
	}
    
    private void editMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	User sender = PageUtils.getUser(request);
    	User receiver = null;
    	try
		{
    		receiver = UserManagmentFactory.getUserManager().findUserById(new Integer(request.getParameter("otheruserid")));

		}
		catch (Exception e)		
		{
			e.printStackTrace();
		}

    	TUsermessages message = new TUsermessages();
    	message.setUserIdFrom(sender);
    	message.setUserIdTo(receiver);
    	
    	PageUtils.setSelectedMessage(message, request);	
    	PageUtils.forwardToJspPage("/jsp/send_message.jsp", request.getSession().getServletContext(), request, response);

	}
    private void replyMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	PageUtils.setReturnToPage("list_messages", request);
    	editMessage(request, response);
	}
}
