<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="us.ttyl.chance.common.*" %>

<%
	User user = PageUtils.getUser(request);		
	String errorNotice = request.getParameter(ChanceConfiguration.ERROR_NOTICE);
%>
<table width=800 cellpadding=5>
	<%
		if (errorNotice != null && errorNotice.length() > 0)
		{		
			out.println("<tr><td><b class='loginnewuser'>" + errorNotice + "</b></td></tr>");		
		}
	 %>	
	<tr>	
		<% 
			String hostName = request.getRequestURL().toString().toLowerCase();			
			if (hostName.indexOf("janechance") > -1)
			{
				out.println("<td width=86&#037;><img src='/"+ChanceConfiguration.CONTEXT_ROOT+"images/logo2.gif'></td>");
			}
			else
			{
				out.println("<td width=86&#037;><img src='/"+ChanceConfiguration.CONTEXT_ROOT+"images/logo1.gif'></td>");
			}
		%>				
		
		<%
		if (user != null)
		{
			out.println("<td width='20%' align='right'>Hi "+user.getUserName()+"</td>");
			out.println("<td width='7%' align='right'><a href='#' onClick='logOut()'>Logout</a>&nbsp|&nbsp</td>");
		}
		%>
		<td width=7% align='left'><a href='#'onClick="handle=window.open('/jsp/help.jsp','mywindow','width=900,height=600,resizable=yes,scrollbars=yes');handle.focus()" >Help</a></td>	
	</tr>
	<%
	if (user == null)
	{
		out.println("<tr><td colspan=2>You are not signed in. <a href='#' onClick='register()'>Register</a> to post a profile or <a href='#' onClick='login()'>Login</a>.</td></tr>");					
	}
	else	
	{
		if (PageUtils.getNewUserMessageCount(request) > 0)
		{
			out.println("<tr><td><b><a href='#' onClick='listMessages()'>You have new messages.</a></b></td></tr>");
		}
	}
	%>
</table>