<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.common.*" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
	User user = PageUtils.getSelectedProfile(request);											
	TPicture currentUserMainPicture = user.getMainPicture();
	User loggedInUser = PageUtils.getUser(request);
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
			function openImage(url, name, attribute)
			{
				windowHandle=window.open(url, name, attribute);
				windowHandle.focus();	
			}
			
			function listMessages()
			{
				frmInput.mode.value="<%=ChanceConfiguration.LIST_MESSAGES %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
				frmInput.submit();
			}
			
			function logOut()
			{
				frmInput.mode.value="<%=ChanceConfiguration.LOG_OUT%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
			
			function updateProfile()
			{
				frmInput.mode.value="<%=ChanceConfiguration.EDIT_USER %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			
			function sendMessage(receiverId)
			{				
				frmInput.mode.value="<%=ChanceConfiguration.CREATE_MESSAGE %>";
				frmInput.otheruserid.value=receiverId;
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
				frmInput.submit();
			}
			
			function saveProfile(userId)
			{
				frmInput.mode.value="<%=ChanceConfiguration.SAVE_PROFILE %>";		
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>profiles";
				frmInput.userid.value=userId;
				frmInput.submit();	
			}
			
			function returnToSearch()
			{
				frmInput.mode.value="<%=ChanceConfiguration.START_PAGE%>";		
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
		</script>
	</HEAD>

	<BODY>
		<form name=frmInput method=post>
		
			<input type=hidden name='mode'>
			<input type=hidden name='userid'>
			<input type=hidden name='otheruserid'>
			
			<table align=center width=800>
				<tr>
					<td>
						<jsp:include page="header.jsp" />											
						<table width=100% cellspacing=5 cellpadding=5>
							<tr>
								<td>
								<% 
									if (PageUtils.getUser(request) != null)
									{
										if (user.getUserId().intValue() != PageUtils.getUser(request).getUserId().intValue())								
										{
											out.println("<a href='#' onClick='sendMessage("+user.getUserId()+")'>Send "+user.getUserName()+" a message </a>&nbsp|&nbsp");
										}
									
										if (user.getUserId().intValue() == PageUtils.getUser(request).getUserId().intValue())																		
										{
											out.println("<a href='#' onClick='updateProfile()'> Update your profile </a>&nbsp|&nbsp");
										}
										
										if (user.getUserId().intValue() != PageUtils.getUser(request).getUserId().intValue())								
										{
											out.println("<a href='#' onClick='saveProfile("+user.getUserId()+")'>Save profile</a>&nbsp|&nbsp");
										}																				
									}
									out.println("<a href='#' onClick='returnToSearch()'>Return to search</a>");																																		
									out.println("</td>");						
								%>
							</tr>	
						</table>
						
						<table width=100% bgcolor='CBDCF2' cellpadding=5 callpadding=5>
							<tr>
								<td><p class=catchphrase><%=user.getCatchphrase()%></p></td>
							</tr>	
						</table>
						
						<br>
						
						<table width=100% cellspacing='5' cellpadding='5' bgcolor='CBDCF2'>
							<tr>
								<td><b>I am looking for:</b></td>
							</tr>
							<tr>
								<td><b>
									<% 									
										List idealLookingFor = user.getIdealLookingfor();
										for(int i = 0; i < idealLookingFor.size(); i ++)
										{		
											TLookingfor lookingFor = (TLookingfor)idealLookingFor.get(i);							
											out.print(lookingFor.getLookingforDesc() + ", "); 
										}									
									%> 
								</b></td>
							</tr>
						</table>				
						
						<br>
						
						<table width=100% cellspacing=0 cellpading=0 bgcolor='CBDCF2'>
							<tr>
								<td>
									<table width=100% cellspacing='5' cellpadding='5' bgcolor='CBDCF2'>						
										<tr>
											<td>Profile for <b><%=user.getUserName() %></b> </td>
										</tr>
										<tr>										
											<%
												if (currentUserMainPicture != null && currentUserMainPicture.getFilenamePath().startsWith("small_") && currentUserMainPicture.getDeleted().intValue() == 0)
												{
													out.println("<td><image onClick=\"openImage('/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId="+currentUserMainPicture.getFilenamePath().substring(6, currentUserMainPicture.getFilenamePath().length())+"','Image','toolbar=no,resizeable=yes,scrollbars=yes')\" src='/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId="+currentUserMainPicture.getFilenamePath()+"'></td>");
												}
												else
												{
													out.println("<td><image src='/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId=no_picture'></td>");
												}											
											%>
											
										</tr>
										<tr>
											<td>Age: <b><%=user.findAge() %></b></td>
										</tr>									
										<tr>
											<td>I am located in: <br><b><%=user.getZipcode().getCity().getCityDesc() %>, <%=user.getZipcode().getCity().getState().getStateDesc() %> <br>(<%=user.getZipcode().getCity().getCountry().getCountryDesc() %>)</b></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>I am a <b><%=user.getSex().getSexDesc() %></b> seeking a <b><%=user.getSexLookingFor().getSexDesc() %>.</b></td>
										</tr>
										<tr>
											<td>&nbsp;</td>
										</tr>
										<tr>
											<td>Ethnicty: <b><%=user.getEthnicity().getEthnicityDesc()%></b></td>
										</tr>
										<tr>
											<td>Eye color: <b><%=user.getEyeColor().getColorDesc() %></b></td>
										</tr>
										<tr>
											<td>Hair color: <b><%=user.getHairColor().getColorDesc() %></b></td>
										</tr>
										<tr>
											<td>Height: <b>
												<%
													int incomingInches = user.getHeight().intValue();
													if (incomingInches == -1)
													{
														out.println("0'&nbsp 0\"</b>");
													}
													else
													{
														int userFeet = user.getHeight().intValue() / 12;
														int userInches = user.getHeight().intValue() % 12;
														out.println(userFeet + "'&nbsp "+userInches+"\"</b>");
													}
												%>
											</td>	
										</tr>
										<tr>
											<td>Body type: <b><%=user.getBodyType().getBodytypeDesc() %></b></td>
										</tr>
									</table>
								</td>
								<td>
									<table>						
										<tr>
											<td>											
											</td>
											<td>
												<table>
													<tr>
														<td>
															<table align=left>
																<%
																	boolean hasPictures = false;
																	int pictureCounter = 0;
																	Iterator i = user.getPictures().iterator();
																	if (i.hasNext())
																	{
																		out.println("<tr><td colspan=3><b>Pictures</b><br>Click on a thumbnail for a larger view.</tr><tr>");
																		hasPictures = true;
																	}
																	while (i.hasNext())
																	{
																		TPicture currentPicture = (TPicture)i.next();
																		if ((currentPicture.getPictureId().intValue() != currentUserMainPicture.getPictureId().intValue()) && currentPicture.getFilenamePath().startsWith("small_") && currentPicture.getDeleted().intValue() == 0)
																		{																	
																			out.println("	<td><img onClick=\"openImage('/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId="+currentPicture.getFilenamePath().substring(6, currentPicture.getFilenamePath().length())+"','Image','toolbar=no,resizeable=yes,scrollbars=yes')\" src='/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId="+currentPicture.getFilenamePath()+"'></td>");																		
																			pictureCounter ++;
																		}																																
																		if (pictureCounter > 2)
																		{
																			pictureCounter = 0;
																			out.println("</tr><tr>");
																		}
																	}
																	if (hasPictures == true)
																	{
																		out.println("</tr>");
																	}																
																%>																											
															</table>
														</td>													
													</tr>
												</table>
											</td>
										</tr>								
									</table>
								</td>
							</tr>
						</table>				
						
						<table width=100% cellspacing='5' cellpadding='5' bgcolor='CBDCF2'>
							<tr>
								<td>I drink: <b><%=user.getDrink().getChoiceDesc() %></b></td>
							</tr>
							<tr>
								<td>I smoke: <b><%=user.getSmoke().getChoiceDesc() %></b></td>
							</tr>
							<tr>
								<td>I have children: <b><%=user.getHaveChildern().getHasChildrenDesc() %></b></td>
							</tr>
							<tr>
								<td>I want children: <b><%=user.getWantChildern().getWantsChildrenDesc() %></b></td>
							</tr>
							<tr>
								<td>My marital status: <b><%=user.getMaritalStatus().getMaritalStatusDesc() %></b></td>
							</tr>
						</table>
						
						<br>					
						
						<table width=100% cellspacing='5' cellpadding='5' bgcolor='CBDCF2'>
							<tr>
								<td><b>More about me:</b></td>
							</tr>					
							<tr>
								<td><%=user.getAboutme() %></td>
							</tr>	
						</table>
						
						<br>
						
						<table width=100% cellspacing='5' cellpadding='5' bgcolor='CBDCF2'>
							<tr>
								<td><b>My ideal companion:</b></td>
							</tr>
							<tr>
								<%
									if (user.getIdealHeightStart().intValue() == -1) 
									{
										out.println("<td>My ideal companion's height is: <b>between 0' 0\"");
									}
									else
									{
										int feetStart = user.getIdealHeightStart().intValue() / 12;
										int inchesStart = user.getIdealHeightStart().intValue() % 12;
										out.println("<td>My ideal companion's height is: <b>between "+feetStart+"' "+inchesStart+"\"");						
									}	
																			
									if (user.getIdealHeightEnd().intValue() == -1) 
									{
										out.print("and 0' 0\"</b></td>");
									}
									else
									{
										int feetEnd = user.getIdealHeightEnd().intValue() / 12;
										int inchesEnd = user.getIdealHeightEnd().intValue() % 12;
										out.println("and "+feetEnd+"' "+inchesEnd+"\"</b></td>");						
									}																		
								%>								
							</tr>
							<tr>
								<td>My ideal companion smokes: <b><%=user.getIdealSmokes().getChoiceDesc() %></b></td>
							</tr>
							<tr>
								<td>My ideal companion drinks: <b><%=user.getIdealDrinks().getChoiceDesc() %></b></td>
							</tr>
							<tr>
								<td>My ideal companion has children: <b><%=user.getIdealHasChildern().getHasChildrenDesc() %></b></td>
							</tr>
							<tr>
								<td>My ideal companion wants children: <b><%=user.getIdealWantsChildern().getWantsChildrenDesc() %></b></td>
							</tr>
							<tr>
								<td>My ideal companion's marital status is: <b><%=user.getIdealMaritalStatus().getMaritalStatusDesc() %></b></td>
							</tr>
							<tr>
								<td>My ideal conpanions's body type is: <b> 
								<% 
									List idealBodyTypes = user.getIdealBodyTypes();
									for(int j = 0; j < idealBodyTypes.size(); j ++)
									{
										TBodytype bt = (TBodytype)idealBodyTypes.get(j);
										out.println(bt.getBodytypeDesc() + ",");
									}
									
								%>
								</b></td>
							</tr>
							<tr>
								<td>My ideal conpanions's ethnicity is: <b>
								<% 
									Iterator ethnicities = user.getIdealEthnicities().iterator();
									while (ethnicities.hasNext())
									{
										TEthnicity te = (TEthnicity)ethnicities.next();
										out.println(te.getEthnicityDesc() + ",");
									}
								%>
								</b></td>
							</tr>																		
						</table>
						
						<jsp:include page="footer.jsp" />
					</td>
				</tr>
			</table>
		</form>
	</BODY>
<HTML>
