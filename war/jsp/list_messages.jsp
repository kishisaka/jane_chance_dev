<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.common.*" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
	User user = PageUtils.getUser(request);			
	List userMessages = PageUtils.getUserMessages(request);								
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
			function showProfile(userId)
			{
				frmInput.mode.value="<%=ChanceConfiguration.SHOW_PROFILE %>";
				frmInput.otheruserid.value=userId;
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}	
			
			function showMessage(messageId)
			{
				frmInput.mode.value="<%=ChanceConfiguration.SHOW_MESSAGE %>";
				frmInput.messageid.value=messageId;
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
				frmInput.submit();
			}	
			
			function deleteMessages()
			{
				frmInput.mode.value="<%=ChanceConfiguration.DELETE_MESSAGES %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
				frmInput.submit();
			}
			
			function returnToSearch()
			{
				frmInput.mode.value="start";		
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
			
			function setChecked(value) 
			{
				var i=0;
				var checkboxes = document.forms["frmInput"].elements["deleteMessageId"];
				if (!checkboxes)
				{
					return;
				}
				var itemCount = checkboxes.length;				
				if(itemCount)
				{
					for(i=0 ; itemCount > 0 ; i++) 
					{
						checkboxes[i].checked = value;
					}
				}
				else
				{
					checkboxes.checked = value;
				}					
			}
			
			function logOut()
			{
				frmInput.mode.value="logout";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
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
						<table width=800 cellpadding=5 align=center>					
							<tr>
								<td colspan=2>								
									<a href='#' onClick='setChecked(1)'>Check all</a>&nbsp;|&nbsp;
									<a href='#' onClick='setChecked(0)'>Clear all</a>&nbsp;|&nbsp;
									<a href='#' onClick='deleteMessages()'>Delete selected messages</a>&nbsp;|&nbsp;
									<a href='#' onClick='returnToSearch()'>Return to Search</a>
								</td>
							</tr>						
						</table>
						<table width=800 align=center>			
							<tr>
								<td>		
									<table width=100% cellspacing=0 cellpadding=5>	
										<tr bgcolor='7FB0F1'>
											<td><b>&nbsp;</b></td>
											<td><b>Sender</b></td>
											<td><b>Subject</b></td>							
											<td><b>Date</b></td>
										</tr>
										
										<%
											for (int i = 0; i < userMessages.size(); i++)
											{
												TUsermessages message = (TUsermessages)userMessages.get(i);
												
												if (message.getDeleted() == 0)
												{
													if (i % 2 == 0)
													{
														out.println("<tr bgcolor='ffffff'>");
													}
													else
													{
														out.println("<tr bgcolor='CBDCF2'>");
													}
													out.println("<td><input name=\"deleteMessageId\" type=checkbox value=\"" + message.getMessageId() + "\"></td>");
													out.println("<td><a href='#' onClick='showProfile(" + message.getUserIdFrom().getUserId()+")'>" + message.getUserIdFrom().getUserName() + "</a></td>");
													if (message.getViewedOn() == null)										
													{
														out.println("<td><b><a href='#' onClick='showMessage(" + message.getMessageId()+")'>" + message.getMessageSubject() + "</a></b></td>");
													}
													else
													{
														out.println("<td><a href='#' onClick='showMessage(" + message.getMessageId()+")'>" + message.getMessageSubject() + "</td>");
													}							
													SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy HH:mm");
													out.println("<td>" + sdf.format(message.getCreatedOn()) + "</td>");
													out.println("</tr>");
												}
											}
										%>
												
									</table>												
									
									<br>
									
									<jsp:include page="footer.jsp" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</BODY>
</HTML>
