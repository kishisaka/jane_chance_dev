<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.common.*" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
	User user = PageUtils.getUser(request);	
	if (PageUtils.getMode(request).equals("advSearch"))
	{
		user = PageUtils.getAdvancedSearchUser(request);
	}
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
			
			function searchForUsers()
			{
				frmInput.mode.value="<%=ChanceConfiguration.ADVANCED_SEARCH_FIND_USERS%>";				
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
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
																		
						<br>
						
						<table width=800 cellspacing=0 cellpadding=5>						
							<tr bgcolor='CBDCF2'>	
								<td width=20% valign=top align=right><label><b>I am seeking a</b></label></td>						
								<td>										
									<select name='<%=ChanceConfiguration.SEX_LOOKING_FOR%>'>
										<% 
										Enumeration sexLookingForKeys = ChanceConfiguration.getM_sex().keys();
										while (sexLookingForKeys.hasMoreElements())
										{											
											Integer sexKey = (Integer)sexLookingForKeys.nextElement();
											TSex sex = (TSex)ChanceConfiguration.getM_sex().get(sexKey);
											if (user != null && sex.getSexId().intValue() == user.getSexLookingFor().getSexId().intValue())
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
							</tr>								
						</table>
						
						<table width=800 cellspacing=0 cellpadding=5>	
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
											if (lookingForKey.intValue() != 99)
											{
												TLookingfor lookingFor = (TLookingfor)ChanceConfiguration.getM_lookingFor().get(lookingForKey);							
												boolean found = false;
												List lookingForIterator = new ArrayList();
												if (user != null)
												{	
													lookingForIterator = user.getIdealLookingfor();
												}	
												for(int i = 0; i < lookingForIterator.size(); i ++)
												{		
													TLookingfor userLf = (TLookingfor)lookingForIterator.get(i);	
													if (user != null && lookingFor.getLookingforId().intValue() == userLf.getLookingforId().intValue())
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
										}
									%>									
								</td>			
							</tr>											
						</table>
						
						<br>
						
						<table bgcolor='CBDCF2' width=800 cellspacing=0 cellpadding=5>
							<tr>
								<td colspan=2><b>My ideal companion/s</b>		
							</tr>
							
							<tr>
								<%
									out.println("<td><label>is located"); 																										
									out.println("<select name='"+ChanceConfiguration.DISTANCE+"'>");
									for(int i = 0; i <= 100; i ++)
									{
										if (user != null && user.getIdealDistance().intValue() == i)
										{										
											out.println("<option value='"+i+"' selected>"+i+"</option>");
										}
										else
										{
											out.println("<option value='"+i+"'>"+i+"</option>");
										}	
									}									
									out.println("</select> miles around <b>zipcode</b> </label>");
									if (user != null && user.getIdealZipcode() != null)
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
											if (user != null && user.getIdealAgeStart() != null && user.getIdealAgeStart().intValue() == i)
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
											if (user != null && user.getIdealAgeEnd() != null && user.getIdealAgeEnd().intValue() == i)
											{																																		
												out.println("<option value="+i+" selected>"+ageString+"</option>");
												selected = true;
											}
											else
											{
												if (i == 65 && selected == false)
												{
													out.println("<option value="+i+" selected>"+ageString+"</option>");
												}
												else
												{
													out.println("<option value="+i+">"+ageString+"</option>");
												}
											}																						
										}										
										%>
									</select>
								</td>							
							</tr>
							<tr>
								<%	
																	
									if (user == null || user.getIdealHeightStart().intValue() == -1) 
									{
										out.println("<td colspan=2><b>height</b> is between <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_FEET_START+"' value='0'>' <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_INCHES_START+"' value='0'>\"");
									}
									else
									{
										int feetStart = user.getIdealHeightStart().intValue() / 12;
										int inchesStart = user.getIdealHeightStart().intValue() % 12;
										out.println("<td colspan=2><b>height</b> is between <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_FEET_START+"' value='"+feetStart+"'>' <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_INCHES_START+"' value='"+inchesStart+"'>\"");						
									}	
									
																			
									if (user == null || user.getIdealHeightEnd().intValue() == -1) 
									{
										out.print("and <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_FEET_END+"' value='10'>' <input type=text size=2 name='"+ChanceConfiguration.HEIGHT_LOOKING_FOR_INCHES_END+"' value='0'>\"");
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
								<td width=50%>
									<table cellspacing=0 cellpadding=5>																					
										<tr bgcolor='CBDCF2'>
											<td width=40% align=right><b>smokes</b></td>
											<td>
												<select name='<%=ChanceConfiguration.SMOKE_LOOKING_FOR %>'>
													<% 
													Enumeration idealSmokeChoiceKeys = ChanceConfiguration.getM_choice().keys();
													
													//take care of default choice
													TChoice defaultChoice = (TChoice)ChanceConfiguration.getM_choice().get(new Integer(99));
													if (user != null && user.getIdealSmokes() != null  && defaultChoice.getChoiceId().intValue() == user.getIdealSmokes().getChoiceId().intValue())
													{
														out.println("<option Value='"+defaultChoice.getChoiceId()+"' selected >"+defaultChoice.getChoiceDesc()+"</option>");
													}
													else	
													{
														out.println("<option Value='"+defaultChoice.getChoiceId()+"'>"+defaultChoice.getChoiceDesc()+"</option>");
													}
													
													//rest of choices
													while (idealSmokeChoiceKeys.hasMoreElements())
													{
														Integer choiceKey = (Integer)idealSmokeChoiceKeys.nextElement();
														TChoice choice = (TChoice)ChanceConfiguration.getM_choice().get(choiceKey);
														if (choiceKey.intValue() != 99)
														{
															if (user != null && user.getIdealSmokes() != null && choice.getChoiceId().intValue() == user.getIdealSmokes().getChoiceId().intValue())
															{
																out.println("<option Value='"+choice.getChoiceId()+"' selected >"+choice.getChoiceDesc()+"</option>");
															}
															else	
															{
																out.println("<option Value='"+choice.getChoiceId()+"'>"+choice.getChoiceDesc()+"</option>");
															}
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
													
													//take care of default choice
													if (user != null && user.getIdealDrinks() != null && defaultChoice.getChoiceId().intValue() == user.getIdealDrinks().getChoiceId().intValue())
													{
														out.println("<option Value='"+defaultChoice.getChoiceId()+"' selected >"+defaultChoice.getChoiceDesc()+"</option>");
													}
													else	
													{
														out.println("<option Value='"+defaultChoice.getChoiceId()+"'>"+defaultChoice.getChoiceDesc()+"</option>");
													}
													
													//rest of choices
													while (idealDrinkChoiceKeys.hasMoreElements())
													{
														Integer choiceKey = (Integer)idealDrinkChoiceKeys.nextElement();
														TChoice choice = (TChoice)ChanceConfiguration.getM_choice().get(choiceKey);
														if (choiceKey.intValue() != 99)
														{
															if (user != null && user.getIdealDrinks() != null && choice.getChoiceId().intValue() == user.getIdealDrinks().getChoiceId().intValue())
															{
																out.println("<option Value='"+choice.getChoiceId()+"' selected >"+choice.getChoiceDesc()+"</option>");
															}
															else	
															{
																out.println("<option Value='"+choice.getChoiceId()+"'>"+choice.getChoiceDesc()+"</option>");
															}
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
													
													//take care of default choice
													THasChildren defaultChoiceHasChildren = (THasChildren)ChanceConfiguration.getM_hasChildren().get(new Integer(99));
													if (user != null && user.getIdealHasChildern() != null && defaultChoiceHasChildren.getHasChildrenId().intValue() == user.getIdealHasChildern().getHasChildrenId().intValue())
													{
														out.println("<option Value='"+defaultChoiceHasChildren.getHasChildrenId()+"' selected >"+defaultChoiceHasChildren.getHasChildrenDesc()+"</option>");
													}
													else
													{
														out.println("<option Value='"+defaultChoiceHasChildren.getHasChildrenId()+"'>"+defaultChoiceHasChildren.getHasChildrenDesc()+"</option>");
													}
												
													while (idealHasChildrenKeys.hasMoreElements())
													{														
														Integer hasChildrenKey = (Integer)idealHasChildrenKeys.nextElement();
														THasChildren hasChildren = (THasChildren)ChanceConfiguration.getM_hasChildren().get(hasChildrenKey);
														if (hasChildrenKey.intValue() != 99)
														{
														
															if (user != null && user.getIdealHasChildern() != null && hasChildren.getHasChildrenId().intValue() == user.getIdealHasChildern().getHasChildrenId().intValue())
															{
																out.println("<option Value='"+hasChildren.getHasChildrenId()+"' selected >"+hasChildren.getHasChildrenDesc()+"</option>");
															}
															else	
															{
																out.println("<option Value='"+hasChildren.getHasChildrenId()+"'>"+hasChildren.getHasChildrenDesc()+"</option>");
															}
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
													
													//take care of default choice
													TWantsChildren defaultChoiceWantsChildren = (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(new Integer(99));
													if (user != null && user.getIdealWantsChildern() != null && defaultChoiceWantsChildren.getWantsChildrenId().intValue() == user.getIdealWantsChildern().getWantsChildrenId().intValue())
													{
														out.println("<option Value='"+defaultChoiceWantsChildren.getWantsChildrenId()+"' selected >"+defaultChoiceWantsChildren.getWantsChildrenDesc()+"</option>");
													}
													else
													{
														out.println("<option Value='"+defaultChoiceWantsChildren.getWantsChildrenId()+"'>"+defaultChoiceWantsChildren.getWantsChildrenDesc()+"</option>");
													}
													
													while (idealWantsChildrenKeys.hasMoreElements())
													{
														Integer wantsChildrenKey = (Integer)idealWantsChildrenKeys.nextElement();
														TWantsChildren wantsChildren = (TWantsChildren)ChanceConfiguration.getM_wantsChildren().get(wantsChildrenKey);
														if (wantsChildrenKey.intValue() != 99)
														{
															if (user != null && user.getIdealWantsChildern() != null && wantsChildren.getWantsChildrenId().intValue() == user.getIdealWantsChildern().getWantsChildrenId().intValue())
															{
																out.println("<option Value='"+wantsChildren.getWantsChildrenId()+"' selected >"+wantsChildren.getWantsChildrenDesc()+"</option>");
															}
															else	
															{
																out.println("<option Value='"+wantsChildren.getWantsChildrenId()+"'>"+wantsChildren.getWantsChildrenDesc()+"</option>");
															}
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
													
													//take care of default choice
													TMaritalStatus defaultChoiceMaritalStatus = (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(new Integer(99));
													if (user != null && user.getMaritalStatus() != null && defaultChoiceMaritalStatus.getMaritalStatusId().intValue() == user.getMaritalStatus().getMaritalStatusId().intValue())
													{
														out.println("<option Value='"+defaultChoiceMaritalStatus.getMaritalStatusId()+"' selected >"+defaultChoiceMaritalStatus.getMaritalStatusDesc()+"</option>");
													}
													else
													{
														out.println("<option Value='"+defaultChoiceMaritalStatus.getMaritalStatusId()+"'>"+defaultChoiceMaritalStatus.getMaritalStatusDesc()+"</option>");
													}
													
													while (idealMaritalStatusKeys.hasMoreElements())
													{
														Integer maritalStatusKey = (Integer)idealMaritalStatusKeys.nextElement();
														TMaritalStatus maritalStatus = (TMaritalStatus)ChanceConfiguration.getM_maritalStatus().get(maritalStatusKey);
														if (maritalStatusKey.intValue() != 99)
														{
															if (user != null && user.getMaritalStatus() != null && maritalStatus.getMaritalStatusId().intValue() == user.getIdealMaritalStatus().getMaritalStatusId().intValue())
															{
																out.println("<option Value='"+maritalStatus.getMaritalStatusId()+"' selected >"+maritalStatus.getMaritalStatusDesc()+"</option>");
															}
															else	
															{
																out.println("<option Value='"+maritalStatus.getMaritalStatusId()+"'>"+maritalStatus.getMaritalStatusDesc()+"</option>");
															}
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
											if (ehtnicityLFKey.intValue() != 99)
											{
												TEthnicity ethnicityLF = (TEthnicity)ChanceConfiguration.getM_ethnicity().get(ehtnicityLFKey);
												List ehtIterator = new ArrayList();
												if (user != null)
												{
													ehtIterator = user.getIdealEthnicities();
												}
												boolean found = false;
												for(int i = 0; i < ehtIterator.size(); i ++)
												{
													TEthnicity userEth = (TEthnicity)ehtIterator.get(i);
													if (user != null && userEth.getEthnicityId().intValue() == ethnicityLF.getEthnicityId().intValue())									
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
												if (bodyTypeLFKey.intValue() != 99)
												{
													TBodytype bodyTypeLF = (TBodytype)ChanceConfiguration.getM_bodyType().get(bodyTypeLFKey);
													boolean found = false;
													if (user != null)
													{
														Iterator userBodyTypes = user.getIdealBodyTypes().iterator();																																																										
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
											}
										%>	
									</table>									
								<td>	
							</tr>
							<tr bgcolor='CBDCF2'>
								<td colspan=3 align=center><input type='button' name='Search Now! ' value='Search Now!' onClick='searchForUsers()'></td>
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
