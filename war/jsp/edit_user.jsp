<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.common.*" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
	User user = PageUtils.getUser(request);	
	TPicture currentUserMainPicture = user.getMainPicture();
%> 

<HTML>
	<HEAD>
		<TITLE>John Chance Update Profile</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">			
		<link href="/<%=ChanceConfiguration.CONTEXT_ROOT%>includes/chance.css" rel="stylesheet" type="text/css">
		
		<script language='javascript'>	
			function updateUser()
			{
				frmInput.mode.value="<%=ChanceConfiguration.UPDATE_USER%>";				
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
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
			
			function openImage(url, name, attribute)
			{
				windowHandle=window.open(url, name, attribute);
				windowHandle.focus();	
			}
			
			function logOut()
			{
				frmInput.mode.value="<%=ChanceConfiguration.LOG_OUT%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
			
			function setMainImage(imageId)
			{
				frmInput.<%=ChanceConfiguration.IMAGE_ID%>.value=imageId;
				frmInput.mode.value="<%=ChanceConfiguration.SET_MAIN_IMAGE %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			
			function deleteImage(imageId)
			{
				frmInput.<%=ChanceConfiguration.IMAGE_ID%>.value=imageId;
				frmInput.mode.value="<%=ChanceConfiguration.DELETE_IMAGE %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			
			function gotoUploadPicturesPage()
			{
				frmInput.mode.value = '<%=ChanceConfiguration.GOTO_UPLOAD_PICTURES_PAGE %>';
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			
			function listMessages()
			{
				frmInput.mode.value="<%=ChanceConfiguration.LIST_MESSAGES %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>listMessages";
				frmInput.submit();
			}
				
		</script>
	</HEAD>

	<BODY>
		<form name=frmInput METHOD="POST">
		
			<input type='hidden' name='<%=ChanceConfiguration.MODE%>'>
			<input type='hidden' name='<%=ChanceConfiguration.IMAGE_ID%>'>
			
			<table align=center>
				<tr>
					<td>
						<jsp:include page="header.jsp" />
						<table width=800 cellpadding=5>							
							<tr>
								<td colspan=2>
									<a href='#' onClick="returnToSearch()">Return to search</a>  								
								</td>		
						</table>
				
						<table width=800 cellspacing=0 cellpadding=15 bgcolor='CBDCF2'>	
							<tr>
								<td>
									<table>
										<tr>							
											<td align=right valign=top><label>Login name:</label></td>
											<td valign=bottom><%=user.getUserName()%></td>
										</tr>											
										<tr bgcolor='CBDCF2'>
											<td align=right valign=top><label>Email address:</label></td>
											<td valign=bottom><%=user.getEmail()%>	
										</tr>
										<tr bgcolor='CBDCF2'>
											<td align=right valign=top><label>Birthdate:</label></td>
											<td valign=bottom>
											<%=user.getBirthdate()%>
											</td>			
										</tr>
									</table>
								</td>
								<td align=center>
									<%
										if (currentUserMainPicture != null && currentUserMainPicture.getDeleted().intValue() == 0 && currentUserMainPicture.getFilenamePath().startsWith("small_"))
										{
											out.println("<image onClick=\"openImage('/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId="+currentUserMainPicture.getFilenamePath().substring(6, currentUserMainPicture.getFilenamePath().length())+"','Image','toolbar=no,resizeable=yes,scrollbars=yes')\" src='/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId="+currentUserMainPicture.getFilenamePath()+"'><br>");
										}
										else
										{
											out.println("<image src='/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId=no_picture'><br>");
										}											
									%>
								</td>
						</table>	
							
						<table width=800 cellspacing=0 cellpadding=15 bgcolor='CBDCF2'>		
							<tr>
								<%
									int pictureCount = 0;
									boolean hasPictures = false;
									
									Iterator pictures = user.getPictures().iterator();
									if (pictures.hasNext())
									{
										out.println("<tr>");										
										hasPictures = true;
									}
									while (pictures.hasNext())
									{										
										TPicture picture = (TPicture)pictures.next();
										if (picture.getFilenamePath().startsWith("small_") && picture.getDeleted().intValue() == 0)
										{										
											out.println("<td valign=top align=center>");
											out.println("	<table bgcolor='CBDCF2' cellspacing=0 cellpadding=0>");
											out.println("		<tr>");																		
											out.println("			<td align=center>");
											out.println("				<image onClick=\"openImage('/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId="+picture.getFilenamePath().substring(6, currentUserMainPicture.getFilenamePath().length())+"','Image','toolbar=no,resizeable=yes,scrollbars=yes')\" src='/"+ChanceConfiguration.CONTEXT_ROOT+"getImage?imageId="+picture.getFilenamePath()+"'>");
											out.println("			</td>");
											out.println("		</tr>");	
											out.println("		<tr>");
											out.println("			<td>");
											out.println("				<a href='#' onClick='deleteImage("+picture.getPictureId()+")'>Delete picture</a>");
											out.println("			</td>");
											out.println("		</tr>");
											out.println("		<tr>");
											out.println("			<td>");
											out.println("				<a href='#' onClick='setMainImage("+picture.getPictureId()+")'>Set as main</a>");
											out.println("			</td>");
											out.println("		</tr>");					
											out.println("	</table>");
											out.println("</td>");
											pictureCount ++;
											if (pictureCount > 2)
											{
												pictureCount = 0;
												out.println("</tr><tr>");
											}
										}
									}
									if (hasPictures == true)
									{
										out.println("</tr>");
									}
								%>																																									
							</tr>
							<tr>
								<td colspan=6 align=center>
									<img src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/add_another_picture.gif' onClick='gotoUploadPicturesPage()'>
								</td>
							</tr>	
						</table>
						
						<br>
											
						<table bgcolor='CBDCF2' width=800 cellspacing=0 cellpadding=5>						
							<tr>
								<td colspan=2><b>My Profile</b>		
							</tr>
							<tr>							
								<td width=50%>
									<table cellspacing=0 cellpadding=5>
										<tr bgcolor='CBDCF2'>
											<td width=20% align=right><b>Zipcode</b></td>
											<td>
												<input type=text name='<%=ChanceConfiguration.ZIPCODE %>'  value='<%=user.getZipcode().getZipcode()%>'>
											</td>	
										</tr>
										<tr bgcolor='CBDCF2'>
											<td width=20% align=right><b>Ethnicity:</b></td>
											<td>
												<select name='<%=ChanceConfiguration.ETHNICITY%>'> 									
												<% 
												Enumeration ethnicityKeys = ChanceConfiguration.getM_ethnicity().keys();
												while (ethnicityKeys.hasMoreElements())
												{
													Integer ethnicityKey = (Integer)ethnicityKeys.nextElement();
													TEthnicity e = (TEthnicity)ChanceConfiguration.getM_ethnicity().get(ethnicityKey);													 
													if (e.getEthnicityId().intValue() == user.getEthnicity().getEthnicityId().intValue())
													{
														out.println("<option Value='"+e.getEthnicityId()+"' selected >"+e.getEthnicityDesc()+"</option>");
													}
													else	
													{
														out.println("<option Value='"+e.getEthnicityId()+"'>"+e.getEthnicityDesc()+"</option>");
													}
												}
												%>
												</select>
											</td>	
										</tr>								
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>My height</b></td>
											<td>
												<%
													int incomingInches = user.getHeight().intValue();
													if (incomingInches == -1)
													{
														out.println("<input type=text size=2 name='"+ChanceConfiguration.HEIGHT_FEET +"' value='0'>'&nbsp <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_INCHES+"' value='0'>\"");
													}
													else
													{
														int userFeet = user.getHeight().intValue() / 12;
														int userInches = user.getHeight().intValue() % 12;
														out.println("<input type=text size=2 name='"+ChanceConfiguration.HEIGHT_FEET +"' value='"+userFeet+"'>'&nbsp <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_INCHES+"' value='"+userInches+"'>\"");
													}
												%>
											</td>			
										</tr>
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>Eye color</b></td>
											<td>
												<select name='<%=ChanceConfiguration.EYE_COLOR %>'>
													<% 
													Enumeration eyeColorKeys = ChanceConfiguration.getM_color().keys();
													while (eyeColorKeys.hasMoreElements())
													{
														Integer colorKey = (Integer)eyeColorKeys.nextElement();
														TColor color = (TColor)ChanceConfiguration.getM_color().get(colorKey);
														if (color.getColorId().intValue() == user.getEyeColor().getColorId().intValue())
														{
															out.println("<option Value='"+color.getColorId()+"' selected >"+color.getColorDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+color.getColorId()+"'>"+color.getColorDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>			
										</tr>
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>Hair color</b></td>
											<td>
												<select name='<%=ChanceConfiguration.HAIR_COLOR %>'>
													<% 
													Enumeration hairColorKeys = ChanceConfiguration.getM_color().keys();
													while (hairColorKeys.hasMoreElements())
													{
														Integer colorKey = (Integer)hairColorKeys.nextElement();
														TColor color = (TColor)ChanceConfiguration.getM_color().get(colorKey);
														if (color.getColorId().intValue() == user.getHairColor().getColorId().intValue())
														{
															out.println("<option Value='"+color.getColorId()+"' selected >"+color.getColorDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+color.getColorId()+"'>"+color.getColorDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>			
										</tr>
									</table>					
								</td>
								<td width=50%>
									<table cellspacing=0 cellpadding=5>						
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>I smoke</b></td>
											<td>
												<select name='<%=ChanceConfiguration.SMOKE %>'>
													<% 
													Enumeration smokeChoiceKeys = ChanceConfiguration.getM_choice().keys();
													while (smokeChoiceKeys.hasMoreElements())
													{
														Integer choiceKey = (Integer)smokeChoiceKeys.nextElement();
														TChoice choice = (TChoice)ChanceConfiguration.getM_choice().get(choiceKey);
														if (choice.getChoiceId().intValue() == user.getSmoke().getChoiceId().intValue())
														{
															out.println("<option Value='"+choice.getChoiceId()+"' selected >"+choice.getChoiceDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+choice.getChoiceId()+"'>"+choice.getChoiceDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>	
										</tr>	
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>I drink</b></td>
											<td>
												<select name='<%=ChanceConfiguration.DRINK %>'>
													<% 
													Enumeration drinkChoiceKeys = ChanceConfiguration.getM_choice().keys();
													while (drinkChoiceKeys.hasMoreElements())
													{
														Integer choiceKey = (Integer)drinkChoiceKeys.nextElement();
														TChoice choice = (TChoice)ChanceConfiguration.getM_choice().get(choiceKey);
														if (choice.getChoiceId().intValue() == user.getDrink().getChoiceId().intValue())
														{
															out.println("<option Value='"+choice.getChoiceId()+"' selected >"+choice.getChoiceDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+choice.getChoiceId()+"'>"+choice.getChoiceDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>	
										</tr>									
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>I have children</b></td>
											<td>
												<select name='<%=ChanceConfiguration.HAS_CHILDREN %>'>
													<% 
													Enumeration hasChildrenKeys = ChanceConfiguration.getM_hasChildren().keys();
													while (hasChildrenKeys.hasMoreElements())
													{
														Integer hasChildrenKey = (Integer)hasChildrenKeys.nextElement();
														THasChildren hasChildren = (THasChildren)ChanceConfiguration.getM_hasChildren().get(hasChildrenKey);
														if (hasChildren.getHasChildrenId().intValue() == user.getHaveChildern().getHasChildrenId().intValue())
														{
															out.println("<option Value='"+hasChildren.getHasChildrenId()+"' selected >"+hasChildren.getHasChildrenDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+hasChildren.getHasChildrenId()+"'>"+hasChildren.getHasChildrenDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>	
										</tr>
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>I want (more) children</b></td>
											<td>
												<select name=<%=ChanceConfiguration.WANTS_CHILDREN %>>
													<% 
													Enumeration wantsChildrenKeys = ChanceConfiguration.getM_wantsChildren().keys();
													while (wantsChildrenKeys.hasMoreElements())
													{
														Integer wantsChildrenKey = (Integer)wantsChildrenKeys.nextElement();
														TWantsChildren wantsChildren = (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(wantsChildrenKey);
														if (wantsChildren.getWantsChildrenId().intValue() == user.getWantChildern().getWantsChildrenId().intValue())
														{
															out.println("<option Value='"+wantsChildren.getWantsChildrenId()+"' selected >"+wantsChildren.getWantsChildrenDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+wantsChildren.getWantsChildrenId()+"'>"+wantsChildren.getWantsChildrenDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>	
										</tr>
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>My current situation</b></td>
											<td>
												<select name='<%=ChanceConfiguration.MARITAL_STATUS %>'>
													<% 
													Enumeration maritalStatusKeys = ChanceConfiguration.getM_maritalStatus().keys();
													while (maritalStatusKeys.hasMoreElements())
													{
														Integer maritalStatusKey = (Integer)maritalStatusKeys.nextElement();
														TMaritalStatus maritalStatus = (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(maritalStatusKey);
														if (maritalStatus.getMaritalStatusId().intValue() == user.getMaritalStatus().getMaritalStatusId().intValue())
														{
															out.println("<option Value='"+maritalStatus.getMaritalStatusId()+"' selected >"+maritalStatus.getMaritalStatusDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+maritalStatus.getMaritalStatusId()+"'>"+maritalStatus.getMaritalStatusDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>	
										</tr>
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>My bodytype is</b></td>
											<td>
												<select name='<%=ChanceConfiguration.BODY_TYPE %>'>
													<% 
													Enumeration bodyTypeKeys = ChanceConfiguration.getM_bodyType().keys();
													while (bodyTypeKeys.hasMoreElements())
													{
														Integer bodyTypeKey = (Integer)bodyTypeKeys.nextElement();
														TBodytype bodyType = (TBodytype)ChanceConfiguration.getM_bodyType().get(bodyTypeKey);
														if (bodyType.getBodytypeId().intValue() == user.getBodyType().getBodytypeId().intValue())
														{
															out.println("<option Value='"+bodyType.getBodytypeId()+"' selected >"+bodyType.getBodytypeDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+bodyType.getBodytypeId()+"'>"+bodyType.getBodytypeDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>			
										</tr>
									</table>
								</td>
							</tr>
						</table>
						
						<table bgcolor='CBDCF2' width=800 cellspacing=0 cellpadding=15>
							<tr bgcolor='CBDCF2'>
								<td valign=top align=right width=30%><b>Catch phrase</b><br>(Designed to catch peoples' eye during browsing. Make it short and sweet!)</td>
								<td><input type=text size=80 name='<%=ChanceConfiguration.CATCH_PHRASE %>' value='<%=user.getCatchphrase() %>'></td>	
							</tr>			
							<tr bgcolor='CBDCF2'>                                      
								<td valign=top align=right width=30%><b>About me</b> <br>(Don't put any information here that you don't want publicly revealed (real name, email address, telephone number, home address, social securty number, credit card numbers, etc.). Protect your identity and privacy!)</td>
								<td><textarea cols=60 rows=8 name='<%=ChanceConfiguration.ABOUT_ME %>'><%=user.getAboutme() %></textarea></td>	
							</tr>
						</table>
											
						<br>
						<table width=800 cellspacing=0 cellpadding=5>
						
							<tr bgcolor='CBDCF2'>
								<td width=20% align=right><b>I am a </b></td>
								<td>										
									<select name='<%=ChanceConfiguration.SEX%>'>
										<% 
										Enumeration sexKeys = ChanceConfiguration.getM_sex().keys();
										while (sexKeys.hasMoreElements())
										{
											Integer sexKey = (Integer)sexKeys.nextElement();
											TSex sex = (TSex)ChanceConfiguration.getM_sex().get(sexKey);
											if (sex.getSexId().intValue() == user.getSex().getSexId().intValue())
											{
												out.println("<option Value='"+sex.getSexId()+"' selected >"+sex.getSexDesc()+"</option>");
											}
											else	
											{
												out.println("<option Value='"+sex.getSexId()+"'>"+sex.getSexDesc()+"</option>");
											}
										}
										%>												
									</select> 
									<label><b>seeking a</b></label>
									<select name='<%=ChanceConfiguration.SEX_LOOKING_FOR%>'>
										<% 
										Enumeration sexLookingForKeys = ChanceConfiguration.getM_sex().keys();
										while (sexLookingForKeys.hasMoreElements())
										{
											Integer sexKey = (Integer)sexLookingForKeys.nextElement();
											TSex sex = (TSex)ChanceConfiguration.getM_sex().get(sexKey);
											if (sex.getSexId().intValue() == user.getSexLookingFor().getSexId().intValue())
											{
												out.println("<option Value='"+sex.getSexId()+"' selected >"+sex.getSexDesc()+"</option>");
											}
											else	
											{
												out.println("<option Value='"+sex.getSexId()+"'>"+sex.getSexDesc()+"</option>");
											}
										}
										%>
									</select>
								</td>
								<td></td>																						
							</tr>								
							
							
							
							<tr bgcolor='CBDCF2'>							
								<td width=20% valign=top align=right><label><b>I am looking for</b></label></td></td>
								<td>
									<% 
										int lookingForCount = 0;
										int lookingForDividePoint = ChanceConfiguration.getM_lookingFor().size() / 2 + 1;										
										Enumeration lookingForKeys = ChanceConfiguration.getM_lookingFor().keys();
										while (lookingForKeys.hasMoreElements())
										{
											Integer lookingForKey = (Integer)lookingForKeys.nextElement();
											TLookingfor lookingFor = (TLookingfor)ChanceConfiguration.getM_lookingFor().get(lookingForKey);							
											boolean found = false;	
											List lookingForIterator = user.getIdealLookingfor();	
											for(int i = 0; i < lookingForIterator.size(); i ++)
											{		
												TLookingfor userLf = (TLookingfor)lookingForIterator.get(i);	
												if (lookingFor.getLookingforId().intValue() == userLf.getLookingforId().intValue())
												{																				
													out.println("<input name='"+lookingFor.getLookingforDesc()+"' type=checkbox value='"+lookingFor.getLookingforId()+"' checked>"+lookingFor.getLookingforDesc()+"</input><br>");																						
													found = true;
													break;
												}
											}
											if (found == false)
											{
												out.println("<input name='"+lookingFor.getLookingforDesc()+"' type=checkbox value='"+lookingFor.getLookingforId()+"'>"+lookingFor.getLookingforDesc()+"</input><br>");											
											}
											lookingForCount ++;
											if (lookingForCount == lookingForDividePoint)
											{
												out.println("</td><td>");
											}
										}
									%>									
								</td>			
							</tr>											
						</table>
						
						<br>
						
						<table bgcolor='CBDCF2' width=800 cellspacing=0 cellpadding=5>
							<tr>
								<td colspan=2><b>My ideal companion/s'</b>		
							</tr>
							<tr>
								<%
									if (user.getIdealHeightStart().intValue() == -1) 
									{
										out.println("<td colspan=2><b>height</b> is between <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_FEET_START+"' value='0'>' <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_INCHES_START+"' value='0'>\"");
									}
									else
									{
										int feetStart = user.getIdealHeightStart().intValue() / 12;
										int inchesStart = user.getIdealHeightStart().intValue() % 12;
										out.println("<td colspan=2><b>height</b> is between <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_FEET_START+"' value='"+feetStart+"'>' <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_INCHES_START+"' value='"+inchesStart+"'>\"");						
									}	
																			
									if (user.getIdealHeightEnd().intValue() == -1) 
									{
										out.print("and <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_FEET_END+"' value='0'>' <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_INCHES_END+"' value='0'>\"");
									}
									else
									{
										int feetEnd = user.getIdealHeightEnd().intValue() / 12;
										int inchesEnd = user.getIdealHeightEnd().intValue() % 12;
										out.println("and <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_FEET_END+"' value='"+feetEnd+"'>' <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_INCHES_END+"' value='"+inchesEnd+"'>\"");						
									}																		
								%>									
							</tr>
							
							<tr>
								<%
									out.println("<td> is located"); 
									out.println("<select name='"+ChanceConfiguration.DISTANCE+"'>");
									for(int i = 0; i <= 100; i ++)
									{
										if (user.getIdealDistance().intValue() == i)
										{										
											out.println("<option value='"+i+"' selected>"+i+"</option>");
										}
										else
										{
											out.println("<option value='"+i+"'>"+i+"</option>");
										}											
									}
									out.println("</select> miles around <b>zipcode</b> </label>");
									if (user.getIdealZipcode() != null)
									{
										out.println("<input type=text size=7 name='"+ChanceConfiguration.ZIPCODE_LOOKING_FOR+"' value='"+user.getIdealZipcode().getZipcode().intValue()+"'>");
									}
									else
									{
										out.println("<input type=text size=7 name='"+ChanceConfiguration.ZIPCODE_LOOKING_FOR+"' value=''>");
									}																	
									out.println("</td>");									
								%>																								
							</tr>
							
							<tr>
								<td>								
									<label><b>age</b> is between</label>
									<select name='<%=ChanceConfiguration.AGE_START%>'>
										<%
										boolean selected = false;										
										for(int i = 18; i <= 65; i ++)
										{ 
											String ageString = Integer.toString(i);
											if (i == 65)											
											{
												ageString = "65+";
											}
											if (user.getIdealAgeStart() != null && user.getIdealAgeStart().intValue() == i)
											{												
												out.println("<option value="+i+" selected>"+ageString+"</option>");
											}
											else
											{
												out.println("<option value="+i+">"+ageString+"</option>");
											}
										}																		
										%>
									</select> 
									and
									<select name='<%=ChanceConfiguration.AGE_END%>'>									
										<%
										selected = false;
										for(int i = 18; i <= 65; i ++)
										{
											String ageString = Integer.toString(i);
											if (i == 65)											
											{
												ageString = "65+";
											}
											if (user.getIdealAgeEnd() != null && user.getIdealAgeEnd().intValue() == i)
											{																																		
												out.println("<option value="+i+" selected>"+ageString+"</option>");
												selected = true;
											}
											else
											{
												out.println("<option value="+i+">"+ageString+"</option>");
											}																						
										}										
										%>
									</select>
								</td>							
							</tr>
							
							<tr>						
								<td width=50%>
									<table cellspacing=0 cellpadding=5>																					
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>smokes</b></td>
											<td>
												<select name='<%=ChanceConfiguration.SMOKE_LOOKING_FOR %>'>
													<% 
													Enumeration idealSmokeChoiceKeys = ChanceConfiguration.getM_choice().keys();
													while (idealSmokeChoiceKeys.hasMoreElements())
													{
														Integer choiceKey = (Integer)idealSmokeChoiceKeys.nextElement();
														TChoice choice = (TChoice)ChanceConfiguration.getM_choice().get(choiceKey);
														if (choice.getChoiceId().intValue() == user.getIdealSmokes().getChoiceId().intValue())
														{
															out.println("<option Value='"+choice.getChoiceId()+"' selected >"+choice.getChoiceDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+choice.getChoiceId()+"'>"+choice.getChoiceDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>	
										</tr>	
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>drinks</b></td>
											<td>
												<select name='<%=ChanceConfiguration.DRINK_LOOKING_FOR %>'>
													<% 
													Enumeration idealDrinkChoiceKeys = ChanceConfiguration.getM_choice().keys();
													while (idealDrinkChoiceKeys.hasMoreElements())
													{
														Integer choiceKey = (Integer)idealDrinkChoiceKeys.nextElement();
														TChoice choice = (TChoice)ChanceConfiguration.getM_choice().get(choiceKey);
														if (choice.getChoiceId().intValue() == user.getIdealDrinks().getChoiceId().intValue())
														{
															out.println("<option Value='"+choice.getChoiceId()+"' selected >"+choice.getChoiceDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+choice.getChoiceId()+"'>"+choice.getChoiceDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>	
										</tr>									
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>has children</b></td>
											<td>
												<select name='<%=ChanceConfiguration.HAS_CHILDREN_LOOKING_FOR %>'>
													<% 
													Enumeration idealHasChildrenKeys = ChanceConfiguration.getM_hasChildren().keys();
													while (idealHasChildrenKeys.hasMoreElements())
													{
														Integer hasChildrenKey = (Integer)idealHasChildrenKeys.nextElement();
														THasChildren hasChildren = (THasChildren)ChanceConfiguration.getM_hasChildren().get(hasChildrenKey);
														if (hasChildren.getHasChildrenId().intValue() == user.getIdealHasChildern().getHasChildrenId().intValue())
														{
															out.println("<option Value='"+hasChildren.getHasChildrenId()+"' selected >"+hasChildren.getHasChildrenDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+hasChildren.getHasChildrenId()+"'>"+hasChildren.getHasChildrenDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>	
										</tr>									
									</table>					
								</td>
								<td width=50%>
									<table cellspacing=0 cellpadding=5>															
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>wants children</b></td>
											<td>
												<select name='<%=ChanceConfiguration.WANTS_CHILDREN_LOOKING_FOR %>'>
													<% 
													Enumeration idealWantsChildrenKeys = ChanceConfiguration.getM_wantsChildren().keys();
													while (idealWantsChildrenKeys.hasMoreElements())
													{
														Integer wantsChildrenKey = (Integer)idealWantsChildrenKeys.nextElement();
														TWantsChildren wantsChildren = (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(wantsChildrenKey);
														if (wantsChildren.getWantsChildrenId().intValue() == user.getIdealWantsChildern().getWantsChildrenId().intValue())
														{
															out.println("<option Value='"+wantsChildren.getWantsChildrenId()+"' selected >"+wantsChildren.getWantsChildrenDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+wantsChildren.getWantsChildrenId()+"'>"+wantsChildren.getWantsChildrenDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>	
										</tr>
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>situation</b></td>
											<td>
												<select name='<%=ChanceConfiguration.MARITAL_STATUS_LOOKING_FOR %>'>
													<% 
													Enumeration idealMaritalStatusKeys = ChanceConfiguration.getM_maritalStatus().keys();
													while (idealMaritalStatusKeys.hasMoreElements())
													{
														Integer maritalStatusKey = (Integer)idealMaritalStatusKeys.nextElement();
														TMaritalStatus maritalStatus = (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(maritalStatusKey);
														if (maritalStatus.getMaritalStatusId().intValue() == user.getIdealMaritalStatus().getMaritalStatusId().intValue())
														{
															out.println("<option Value='"+maritalStatus.getMaritalStatusId()+"' selected >"+maritalStatus.getMaritalStatusDesc()+"</option>");
														}
														else	
														{
															out.println("<option Value='"+maritalStatus.getMaritalStatusId()+"'>"+maritalStatus.getMaritalStatusDesc()+"</option>");
														}
													}
													%>
												</select>
											</td>	
										</tr>										
									</table>
								</td>							
							</tr>														
							
							<tr>
								<td width=20% align=left><b>ethnicity</b></td>
							</tr>						
							<tr bgcolor='CBDCF2'>							
								<td>
									<table>
									<% 
										int ehtnicityLFCount = 0;
										int ethnicityLFDividePoint = ChanceConfiguration.getM_ethnicity().size() / 2 + 1;										
										Enumeration ethnicityLFKeys = ChanceConfiguration.getM_ethnicity().keys();
										while (ethnicityLFKeys.hasMoreElements())
										{
											Integer ehtnicityLFKey = (Integer)ethnicityLFKeys.nextElement();
											TEthnicity ethnicityLF = (TEthnicity)ChanceConfiguration.getM_ethnicity().get(ehtnicityLFKey);
											List ehtIterator = user.getIdealEthnicities();
											boolean found = false;
											for(int i = 0; i < ehtIterator.size(); i ++)
											{
												TEthnicity userEth = (TEthnicity)ehtIterator.get(i);
												if (userEth.getEthnicityId().intValue() == ethnicityLF.getEthnicityId().intValue())									
												{												
													out.println("<tr><td><input name='"+ethnicityLF.getEthnicityDesc()+"' type=checkbox value='"+ethnicityLF.getEthnicityId()+"' checked>"+ethnicityLF.getEthnicityDesc()+"</input></td></tr>");																						
													found = true;
													break;
												}													
											}
											if (found == false)
											{
												out.println("<tr><td><input name='"+ethnicityLF.getEthnicityDesc()+"' type=checkbox value='"+ethnicityLF.getEthnicityId()+"'>"+ethnicityLF.getEthnicityDesc()+"</input></td></tr>");								
											}
											ehtnicityLFCount ++;
											if (ehtnicityLFCount == ethnicityLFDividePoint)
											{
												out.println("</table></td><td><table>");
											}
										}
									%>	
									</table>
								<td>	
							</tr>
							<tr>
								<td width=20% align=left><b>bodytype</b></td>
							</tr>
							<tr bgcolor='CBDCF2'>							
								<td>
									<table>
										<% 
											int bodyTypeLFCount = 0;
											int bodyTypeLFDividePoint = ChanceConfiguration.getM_bodyType().size() / 2 + 1;										
											Enumeration bodyTypeLFKeys = ChanceConfiguration.getM_bodyType().keys();
											while (bodyTypeLFKeys.hasMoreElements())
											{
												Integer bodyTypeLFKey = (Integer)bodyTypeLFKeys.nextElement();
												TBodytype bodyTypeLF = (TBodytype)ChanceConfiguration.getM_bodyType().get(bodyTypeLFKey);																						
												Iterator userBodyTypes = user.getIdealBodyTypes().iterator();
												boolean found = false;
												while (userBodyTypes.hasNext())
												{	
													TBodytype userbt = (TBodytype)userBodyTypes.next();						
													if (userbt.getBodytypeId().intValue() == bodyTypeLF.getBodytypeId().intValue())					
													{
														out.println("<tr><td><input name='"+bodyTypeLF.getBodytypeDesc()+"' type=checkbox value='"+bodyTypeLF.getBodytypeId()+"' checked>"+bodyTypeLF.getBodytypeDesc()+"</input></td></tr>");																																			
														found = true;
														break;
													}
												}
												if (found == false)
												{
													out.println("<tr><td><input name='"+bodyTypeLF.getBodytypeDesc()+"' type=checkbox value='"+bodyTypeLF.getBodytypeId()+"'>"+bodyTypeLF.getBodytypeDesc()+"</input></td></tr>");															
												}
												bodyTypeLFCount ++;
												if (bodyTypeLFCount == bodyTypeLFDividePoint)
												{
													out.println("</table></td><td><table>");
												}
											}
										%>	
									</table>									
								<td>	
							</tr>
							<tr bgcolor='CBDCF2'>
								<td colspan=3 align=center><img src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/update_profile_button.gif' onClick='updateUser()'></td>
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
