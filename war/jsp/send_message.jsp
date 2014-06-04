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
		function listMessages()
		{
			frmInput.mode.value="<%=ChanceConfiguration.LIST_MESSAGES %>";
			frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
			frmInput.submit();
		}
		
		function sendMessage()
		{
			frmInput.mode.value="<%=ChanceConfiguration.SEND_MESSAGE%>";
			frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
			frmInput.submit();
		}
		
		function returnToSearch()
		{
			frmInput.mode.value="<%=ChanceConfiguration.START_PAGE%>";		
			frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
			frmInput.submit();
		}
		
		function logOut()
		{
			frmInput.mode.value="<%=ChanceConfiguration.LOG_OUT%>";
			frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
			frmInput.submit();
		}

		</script>
	</HEAD>

	<BODY>
		<form name=frmInput METHOD="POST">
			<input type='hidden' name='<%=ChanceConfiguration.MODE%>'>
			<table align=center>
				<tr>
					<td>
						<jsp:include page="header.jsp" />	
						<table width=800 cellpadding=5>
							<tr>
								<td colspan=2>								
									<a href='#' onClick='returnToSearch()'>Return to Search</a>&nbsp;|&nbsp;
									<a href='#' onClick='listMessages()'> View messages</a>
								</td>
							</tr>
						</table>
				
						<table width=800 cellspacing=0 cellpadding=5 bgcolor='CBDCF2'>	
							<tr>
								<td width=10% align=right>
									<b>Sender:</b> 								
								</td>
								<td><%=user.getUserName()%></td>
							</tr>
							<tr>
								<td width=10% align=right>
									<b>Receiver:</b>									
								</td>
								<td><%=selectedMessage.getUserIdTo().getUserName()%></td>
							</tr>											
							<tr bgcolor='CBDCF2'>
								<td width=10% align=right><b>Subject:</b></td>
								<td><textarea name="messageSubject" cols=60 rows=2></textarea></td>	
							</tr>			
							<tr bgcolor='CBDCF2'>                                      
								<td width=10% valign=top align=right><b>Message:</b></td>
								<td><textarea name="messageContent" cols=60 rows=8></textarea></td>	
							</tr>
							<tr bgcolor='CBDCF2'>                                      
								<td colspan=2 align=center><img src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/send_message_button.gif' onClick='sendMessage()'></td>	
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
