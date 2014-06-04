<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="us.ttyl.chance.common.*" %>

<%	
	String errorNotice = request.getParameter(ChanceConfiguration.ERROR_NOTICE);
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
			
	<td width=7% align='left'><a href='#' onClick="handle=window.open('/jsp/help.jsp','mywindow','width=900,height=600,resizable=yes,scrollbars=yes');handle.focus()" >Help</a></td>					
</tr>