<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.common.*" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="java.util.*;" %>

<% 
	PageList userList = PageUtils.getUserList(request);
	User user = PageUtils.getUser(request);
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
			function next()
			{
				frmInput.mode.value="<%=ChanceConfiguration.FORWARD_PAGE%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>profiles";
				frmInput.submit();
			}
			
			function previous()
			{
				frmInput.mode.value="<%=ChanceConfiguration.PREVIOUS_PAGE%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>profiles";
				frmInput.submit();
			}
			
			function register()
			{
				frmInput.mode.value="<%=ChanceConfiguration.REGISTER%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			
			function logOut()
			{
				frmInput.mode.value="<%=ChanceConfiguration.LOG_OUT%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
			
			function login()
			{
				frmInput.mode.value="<%=ChanceConfiguration.LOGIN%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
			
			function updateProfile()
			{
				frmInput.mode.value="<%=ChanceConfiguration.EDIT_USER %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			
			function showProfile(userId)
			{
				frmInput.mode.value="<%=ChanceConfiguration.SHOW_PROFILE %>";
				frmInput.otheruserid.value=userId;
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			
			function listMessages()
			{
				frmInput.mode.value="<%=ChanceConfiguration.LIST_MESSAGES %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
				frmInput.submit();
			}
			
			function sendMessages(receiverId)
			{
				frmInput.mode.value="<%=ChanceConfiguration.CREATE_MESSAGE %>";
				frmInput.otheruserid.value=receiverId;
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
				frmInput.submit();
			}
			
			function returnToSearch()
			{
				frmInput.mode.value="start";		
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
		</script>

	</HEAD>

	<BODY>
		<form name=frmInput METHOD="POST">
		
			<input type='hidden' name='<%=ChanceConfiguration.MODE%>'>
			<input type='hidden' name='otheruserid'>
			
			<table align=center>
				<tr>
					<td>
						<jsp:include page="header.jsp" />
						<table width=800 cellpadding=5>							
							<%							
							if (user != null)	
							{								
								out.println("<tr>");
								out.println("	<td colspan=2>");
								out.println("		<a href='#'onClick='returnToSearch()'> Return to search </a> ");
								out.println("	</td>");
								out.println("</tr>");	
							}
							%>
						</table>
						
						<table width=800 cellspacing=0 cellpadding=0>																				
							<%
								for(int i = 0; i < userList.getM_data().size(); i ++)
								{									
									User u = (User)userList.getM_data().get(i);
									TPicture currentUserPicture = u.getMainPicture();
									if (i %2 == 0)
									{
										out.println("<tr bgcolor='CBDCF2'>");
									}
									else
									{
										out.println("<tr bgcolor='ffffff'>");
									}		
									if (currentUserPicture != null)
									{									
										out.println("	<td width='23%' align='center'><img onClick='showProfile("+u.getUserId()+")' src='/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId="+currentUserPicture.getFilenamePath()+"'></td>");
									}
									else
									{
										out.println("	<td width='23%' align='center'><img onClick='showProfile("+u.getUserId()+")' src='/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId=no_picture'></td>");
									}
									out.println("		<td width='22%'> ");
									out.println("		<table>");						
									out.println("			<tr>");
									out.println("				<td><a href='#' onClick='showProfile("+u.getUserId()+")'>"+u.getUserName()+"</a></td>");
									out.println("			</tr>	");											
									out.println("			<tr>");
									out.println("				<td>"+u.getZipcode().getCity().getCityDesc()+", "+u.getZipcode().getCity().getState().getStateDesc()+" ("+u.getZipcode().getCity().getCountry().getCountryDesc()+")</td>");
									out.println("			</tr>	");	
									if (user == null)								
									{
										out.println("			<tr>");								
										out.println("				<td><a href='/"+ChanceConfiguration.CONTEXT_ROOT+"registration.html'>Register to send a message</a></td>");
										out.println("			</tr>");
									}
									else
									{
										out.println("<tr>");
										out.println("<td> " + u.findDistance(user) + " miles from your location</td>");
										out.println("</tr>");
										out.println("<tr>");
										out.println("	<td><a href='#' onClick='sendMessages(" + u.getUserId() + ")'>Send a message</a></td>");
										out.println("</tr>");
									}	
									out.println("		</table>");
									out.println("	</td>");
									out.println("	<td width='55%'>");
									out.println("		<table>");
									out.println("			<tr>");
									out.println("				<td>catch phrase</td>");
									out.println("			</tr>");
									out.println("			<tr>");
									out.println("				<td>"+u.getCatchphrase()+"</td>");
									out.println("			</tr>");
									out.println("		</table>");
									out.println("	</td>");
									out.println("</tr>");
								}
							%>
							
						</table>
						<br>
						<table width=800 cellspacing='0' cellpadding='5'>
							<tr bgcolor='CBDCF2'>
								<td>
									&nbsp;	
									<%
										if (userList.isM_hasPrevious())
										{
											out.println("<a href='#' onClick='previous()'> &nbsp<<<<&nbsp </a>");
										}
										out.println((userList.getM_maxSize() + 1) + " row/s returned. ");
										if (userList.isM_hasNext())
										{										
											out.println("<a href='#' onClick='next()'> &nbsp>>>>&nbsp </a>");
										}
									%>																													
								</td>
							</tr>			
						</table>
						<br>
						<table width=800 cellspacing='0' cellpadding='5'>
							<tr>
								<td align=center><a href=''>Terms of service</a>&nbsp;|&nbsp;<a href=''>About us</a>&nbsp;|&nbsp;<a href=''>Your Privacy</a>&nbsp;|&nbsp;<a href=''>Report Abuse</a></td>
							</tr>
							<tr>
								<td align=center>©2006 JohnChance.com</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</BODY>
</HTML>
