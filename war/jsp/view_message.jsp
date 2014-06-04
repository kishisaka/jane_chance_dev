<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.common.*" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%			
	User user = PageUtils.getUser(request);		
	TUsermessages selectedMessage = PageUtils.getSelectedMessage(request);								
%>
<HTML>
	<HEAD>
		<TITLE> New Document </TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">

		<link href="/<%=ChanceConfiguration.CONTEXT_ROOT%>includes/chance.css" rel="stylesheet" type="text/css">
		<script language=javascript>
		function listMessages(userId)
		{
			frmInput.mode.value="<%=ChanceConfiguration.LIST_MESSAGES %>";
			frmInput.otheruserid.value=userId;
			frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
			frmInput.submit();
		}
		function returnToSearch()
		{
			frmInput.mode.value="<%=ChanceConfiguration.START_PAGE%>";		
			frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
			frmInput.submit();
		}
		
		function replyMessage()
		{
			frmInput.mode.value="<%=ChanceConfiguration.REPLY_MESSAGE%>";		
			frmInput.otheruserid.value = "<%=selectedMessage.getUserIdFrom().getUserId()%>";
			frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
			frmInput.submit();
		}
		</script>
	</HEAD>

	<BODY>
		<form name=frmInput METHOD="POST">
			<input type='hidden' name='<%=ChanceConfiguration.MODE%>'>
			<input type='hidden' name='otheruserid'>
			<input type='hidden' name='messageid'>
			
		<table align=center>
			<tr>
				<td>
					<jsp:include page="header.jsp" />
					<table width=800 cellpadding=5>															
						<tr>
							<td colspan=3>								
								<a href='#' onClick='returnToSearch()'>Return to Search</a>&nbsp;|&nbsp;
								<a href='#' onClick='listMessages(<%=user.getUserId()%>)'> View messages</a>&nbsp;|&nbsp;
								<a href='#' onClick='replyMessage()'> Reply to message</a>
							</td>
						</tr>
					</table>
			
					<table width=800 cellspacing=0 cellpadding=5 bgcolor='CBDCF2'>	
						<tr>
							<td width=10% align=right>
								<b>Sender:</b> 								
							</td>
							<td><%=selectedMessage.getUserIdFrom().getUserName()%></td>
						</tr>
						<tr>
							<td width=10% align=right>
								<b>Receiver:</b>									
							</td>
							<td><%=user.getUserName()%></td>
						</tr>											
						<tr bgcolor='CBDCF2'>
							<td width=10% align=right><b>Subject:</b></td>
							<td><%=selectedMessage.getMessageSubject()%></td>	
						</tr>			
						<tr bgcolor='CBDCF2'>                                      
							<td width=10% valign=top align=right><b>Message:</b></td>
							<td><%=selectedMessage.getMessageContent()%></td>	
						</tr>						
					</table>
					
					<br>
					
					<jsp:include page="footer.jsp" />
				</td>
			</tr>
		</table>
		</form>
	</BODY>
</HTML>
