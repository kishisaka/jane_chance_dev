<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.common.*" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="java.util.*;" %>

<% 
	PageList userList = PageUtils.getUserList(request);
	User user = PageUtils.getUser(request);
	SearchParameters sp = PageUtils.getSearchParameters(request);
	
	String hostName = request.getRequestURL().toString().toLowerCase();
	if (hostName.indexOf("janechance") > -1)
	{
		hostName="www.janechance.com";
	}
	else
	{
		hostName="www.johnchance.com";
	}		
	
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
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
			
			function previous()
			{
				frmInput.mode.value="<%=ChanceConfiguration.PREVIOUS_PAGE%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
			
			function startLooking()
			{
				frmInput.mode.value="<%=ChanceConfiguration.START_LOOKING%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
			
			function register()
			{
				frmInput.mode.value="<%=ChanceConfiguration.REGISTER%>";
				frmInput.action="https://<%=hostName%>/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
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
				frmInput.action="https://<%=hostName%>/<%=ChanceConfiguration.CONTEXT_ROOT%>home";
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
			
			function viewProfiles()
			{
				frmInput.mode.value="<%=ChanceConfiguration.VIEW_PROFILES %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>profiles";
				frmInput.submit();
			}
			
			function goSearchFromZipcodeFieldIfReturnPressed(e)
			{
				var unicode=e.keyCode? e.keyCode : e.charCode;
				if (unicode == 13)
				{
					frmInput.mode.value="<%=ChanceConfiguration.START_LOOKING%>";
					frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
					frmInput.submit();
				}
			}
			
			function startAdvancedSearch()
			{
				frmInput.mode.value = "<%=ChanceConfiguration.ADVANCED_SEARCH%>";
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
							<tr>
								<%
								if (user != null)
								{
									out.println("<tr>");
									out.println("	<td colspan=2>");
									out.println("		<a href='#' onClick='updateProfile()'> Update your profile </a> ");
									out.println("		&nbsp;|&nbsp; ");
									out.println("		<a href='#' onClick='viewProfiles()'> View saved profiles </a> ");
									out.println("		&nbsp;|&nbsp; ");
									out.println("		<a href='#' onClick='showProfile("+user.getUserId()+")'> View your public profile</a>");
									out.println("		&nbsp;|&nbsp;");
									out.println("		<a href='#' onClick='listMessages()'> View messages</a>");
									out.println("	</td>");
									out.println("</tr>");	
								}
								%>
						</table>
				
						<table width=800 cellspacing=0 cellpadding=5>
							<tr bgcolor='CBDCF2'>
								<td>
									<label>Seeking A</label>									 
									<select name='<%=ChanceConfiguration.GENDER%>'>
									<%											
										if (sp != null && sp.getM_gender() == 1)
										{																																								
											out.println("<option value='1' selected>male</option>");
										}
										else
										{
											out.println("<option value='1'>male</option>");
										}											
										if (sp != null && sp.getM_gender() == 2)
										{																																								
											out.println("<option value='2' selected>female</option>");
										}
										else
										{
											out.println("<option value='2'>female</option>");
										}										
										if (sp != null && sp.getM_gender() == 99)
										{
											out.println("<option value='99' selected>not specified</option>");
										}
										else
										{
											out.println("<option value='99'>not specified</option>");
										}	
									%>										
									</select>
								</td>
								<td>
									<label>Age</label>
									<select name='<%=ChanceConfiguration.AGE_START%>'>
										<%
										for(int i = 18; i < 65; i ++)
										{ 
											if (sp != null && sp.getM_startAge() == i)
											{
												out.println("<option value="+i+" selected>"+i+"</option>");
											}
											else	
											{
												out.println("<option value="+i+">"+i+"</option>");
											}
										}
										if (sp != null && sp.getM_startAge() == 65)
										{
											out.println("<option value=65 selected>65+</option>");
										}
										else
										{
											out.println("<option value=65>65+</option>");
										}
										%>
									</select> 
									To 
									<select name='<%=ChanceConfiguration.AGE_END%>'>									
										<%
										boolean ageEndSelected = false;
										for(int i = 18; i < 65; i ++)
										{ 	
											if (sp != null && sp.getM_endAge() == i)
											{							
												ageEndSelected = true;	
												out.println("<option value="+i+" selected>"+i+"</option>");											
											}
											else	
											{
												out.println("<option value="+i+">"+i+"</option>");
											}
										}
										if (ageEndSelected == false)
										{
											out.println("<option value=65 selected>65+</option>");
										}
										else
										{
											out.println("<option value=65 >65+</option>");
										}
										%>
									</select>
								</td>
								<td>
									<label>ZipCode</label> 
									<%
									String zipcode = "";
									if (sp != null)									
									{
										zipcode = sp.getM_zipcode();
									}
									out.println("<input type=text name='"+ChanceConfiguration.ZIPCODE+"' onkeypress='goSearchFromZipcodeFieldIfReturnPressed(event)' value='"+zipcode+"' size=10>");
									%>
								</td>
								<td>
									<label>Within</label> 
									<select name='<%=ChanceConfiguration.DISTANCE%>'>
									<%
										for(int i = 10; i < 110; i = i + 10)
										{
											if (sp != null && sp.getM_distance() == i)
											{
												out.println("<option value="+i+" selected>"+i+"</option>");
											}
											else
											{
												out.println("<option value="+i+">"+i+"</option>");
											}
										}																			
									%>
									</select> miles.
								</td>				
								<td>
									<img src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/micro.gif' title='Basic Search, enter search criteria on the top field and hit the magnifiying glass to do a basic search. ' alt='Basic Search' onClick='startLooking()'>
								</td>
								<td>
									<img src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/magnifier.gif' title='Advanced Search, hit the microscope to proceed to the next menu, enter search criteria and hit start search button to do an advanced search. ' alt='Advanced Search' onClick='startAdvancedSearch()'>
								</td>
							</tr>
						</table>
						
						<br>
						
						<table width=800 cellspacing=0 cellpadding=0>																				
							<%
								for(int i = 0; i < userList.getM_data().size(); i ++)
								{									
									SimpleUserInfo u = (SimpleUserInfo)userList.getM_data().get(i);
									
									int distance = -1;
									if (u != null && u.getDistance() != null)
									{
										distance = u.getDistance().intValue();
									}
																		
									if (i%2 == 0)
									{
										out.println("<tr bgcolor='CBDCF2'>");
									}
									else
									{
										out.println("<tr bgcolor='ffffff'>");
									}		
									if (u.getPictureFilename() != null)
									{									
										out.println("	<td width='23%' align='center'><img onClick='showProfile("+u.getUserId()+")' src='/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId="+u.getPictureFilename()+"'></td>");
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
									if (user == null)								
									{										
										out.println("<tr><td>Register to find out how far away this person is from you. </td></tr>");										
										out.println("</tr>");
										out.println("<tr><br></tr>");
										out.println("<tr>");								
										out.println(" 	<td><a href='#' onClick='register()'>Register to send a message</a></td>");
										out.println("</tr>");
									}
									else
									{
										out.println("<tr><td>"+u.getCity()+", "+u.getState()+" ("+u.getZipcode()+")</td></tr>");	
										out.println("<tr>");
										if (PageUtils.getMode(request).equals("user"))
										{										
											out.println("<td> " + distance + " miles from your current location</td>");
										}
										else
										{
											out.println("<td> " + distance + " miles from your search location</td>");
										}	
										out.println("</tr>");
										out.println("<tr>");
										out.println("	<td><a href='#' onClick='sendMessages(" + u.getUserId() + ")'>Send a message</a></td>");
										out.println("</tr>");
										out.println("<tr>");
										out.println("	<td><br></td>");
										out.println("</tr>");
									}	
									
									out.println("	</table>");
									out.println("	</td>");
									out.println("	<td width='55%'>");
									out.println("		<table>");
									out.println("			<tr>");
									out.println("				<td>"+u.getCatchPhrase()+"</td>");
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
						<jsp:include page="footer.jsp" />
					</td>
				</tr>
			</table>
		</form>
	</BODY>
</HTML>
