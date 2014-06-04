<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.common.*" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="us.ttyl.chance.web.*" %>
<%@ page import="java.util.*;" %>

<%			
	User user = PageUtils.getUser(request);								
%>
<HTML>
	<HEAD>
		<TITLE>John Chance Registration</TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">

		<link href="/<%=ChanceConfiguration.CONTEXT_ROOT%>includes/chance.css" rel="stylesheet" type="text/css">
		
		<script language='javascript'>
			function submitRegInfo()
			{
				frmInput.mode.value="<%=ChanceConfiguration.REGISTER_USER%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			
			function goMain()
			{
				frmInput.action = "/<%=ChanceConfiguration.CONTEXT_ROOT%>index.jsp";
				frmInput.submit();
			}
		</script>
		
	</HEAD>

	<BODY>
		<form name='frmInput' METHOD='POST'>
			<input type='hidden' name='<%=ChanceConfiguration.MODE%>'>
			<table width=800 align=center>
				<tr>
					<td>
						<jsp:include page="header_help_only.jsp" />	
						<%
							if (PageUtils.getErrorTable(request) != null && PageUtils.getErrorTable(request).size() != 0)
							{
							
					        	out.println("<table width=800 cellpadding=5>");
								out.println("<tr>");
								out.println("	<td><b class='loginnewuser'>Please Fix Items in Red</b></td>");
								out.println("</tr>");			
								out.println("</table>");
							}
						%>
						<table align=center width=800 cellspacing=0 cellpadding=5>
							<tr bgcolor='CBDCF2'>
								<% 
								
									if (PageUtils.getErrorTable(request) != null && PageUtils.getErrorTable(request).get("LOGINNAME") != null)
									{
										out.println("<td width=20% align=right><label><b class='loginnewuser'>Login name</b><br> (Do not use any part of your real name here. Protect your identity and privacy!) </label></td>");
										out.println("<td valign=top><a class='loginnewuser'>Login name is empty or is already being used.</a><br><input type=text size=30 name='"+ChanceConfiguration.LOGIN_NAME+ "' value='" + user.getUserName() + "'></td>");
									}
									else
									{
										out.println("<td width=20% align=right><label><b>Login name</b><br> (Do not use any part of your real name here. Protect your identity and privacy!) </label></td>");
										out.println("<td><input type=text size=30 name='" + ChanceConfiguration.LOGIN_NAME + "' value='" + user.getUserName() + "'></td>");
									}
				
								%>
							</tr>
							<tr bgcolor='CBDCF2'>
								<%
									if (PageUtils.getErrorTable(request) != null && PageUtils.getErrorTable(request).get("PWD") != null)
									{
										out.println("<td width=20% align=right><label><b class='loginnewuser'>Password</b></label></td>");
									}
									else
									{
										out.println("<td width=20% align=right><label><b>Password</b></label></td>");
									}
									
									out.println("<td><input type='password' size=8 name='" + ChanceConfiguration.PASSWORD + "'></td>");
								%>
								
							</tr>	
							<tr bgcolor='CBDCF2'>
								<%
									if (PageUtils.getErrorTable(request) != null && PageUtils.getErrorTable(request).get("PWD") != null)
									{
										out.println("<td width=20% align=right><label><b class='loginnewuser'>Confirm Password</b></label></td>");
									}
									else
									{
										out.println("<td width=20% align=right><label><b>Confirm Password</b></label></td>");
									}
									out.println("<td><input type='password' size=8 name='" + ChanceConfiguration.PASSWORD_CONFIRM + "'></td>");
								%>
								
							</tr>								
							<tr bgcolor='CBDCF2'>
								<%
									if (PageUtils.getErrorTable(request) != null && PageUtils.getErrorTable(request).get("EMAIL") != null)
									{
										out.println("<td width=20% align=right><label><b class='loginnewuser'>Email address</b><br> (This will be used only to email notifications to you. It will not be shown in the public profile.)</label></td>");
									}
									else
									{
										out.println("<td width=20% align=right><label><b>Email address</b><br> (This will be used only to email notifications to you. It will not be shown in the public profile.)</label></td>");
									}
									
									out.println("<td><input type=text size=30 name='" + ChanceConfiguration.EMAIL + "' value='" + user.getEmail() + "'></td>");
								%>
									
							</tr>
							<tr bgcolor='CBDCF2'>
								<%
									if (PageUtils.getErrorTable(request) != null && PageUtils.getErrorTable(request).get("EMAIL") != null)
									{
										out.println("<td width=20% align=right><label><b class='loginnewuser'>Confirm email address</b></label></td>");
									}
									else
									{
										out.println("<td width=20% align=right><label><b>Confirm email address</b></label></td>");
									}
									
									out.println("<td><input type=text size=30 name='" + ChanceConfiguration.EMAIL_CONFIRM + "'></td>");	
								%>
								
							</tr>
							<tr bgcolor='CBDCF2'>
								<%
									if (PageUtils.getErrorTable(request) != null && PageUtils.getErrorTable(request).get("ZIPCODE") != null)
									{
										out.println("<td width=20% align=right><b class='loginnewuser'><label>Zipcode</label></b></td>");
									}
									else
									{
										out.println("<td width=20% align=right><b><label>Zipcode</label></b></td>");
										
									}
									
									String zipCode = (String)request.getSession().getAttribute(ChanceConfiguration.ZIPCODE);
									if (zipCode == null)
									{
										zipCode = "";
									}
									out.println("<td><input type=text size=30 name='" + ChanceConfiguration.ZIPCODE + "' value='" + zipCode + "'></td>");
								%>
								
							</tr>									
							<tr bgcolor='CBDCF2'>
								<%
									if (PageUtils.getErrorTable(request) != null && PageUtils.getErrorTable(request).get("BIRTHDATE") != null)
									{
										out.println("<td width=20% align=right><label><b class='loginnewuser'>Birthdate</b><br>(As Month/Year. This will not be shown in the public profile. Your age will be calculated based on this date and shown in the public profile.)</label></td>");
									}
									else
									{
										out.println("<td width=20% align=right><label><b>Birthdate</b><br>(As Month/Year. This will not be shown in the public profile. Your age will be calculated based on this date and shown in the public profile.)</label></td>");
									}
								%>
								<td>
								<%
									String birthdateMonth = (String)request.getSession().getAttribute(ChanceConfiguration.BIRTHDATE_MONTH);
								    out.println(WebUtils.renderMonthList(birthdateMonth));
								%>
									&nbsp/&nbsp 
									<%
									    String birthdateYear = (String)request.getSession().getAttribute(ChanceConfiguration.BIRTHDATE_YEAR);
									    
									    if (birthdateYear == null)
									    {
									      birthdateYear = "";
									    }
										out.println("<input type='text' size=4 name='" + ChanceConfiguration.BIRTHDATE_YEAR + "' value='" + birthdateYear + "'>");
									%>
								</td>								
							</tr>							
							<tr bgcolor='CBDCF2'>
								<td valign=top align=right width=30%><b>Catch phrase</b><br>(Designed to catch peoples' eye during browsing. Make it short and sweet!)</td>
								<%
									out.println("<td><input type=text size=80 name='" + ChanceConfiguration.CATCH_PHRASE + "' value='" + user.getCatchphrase() + "'></td>");	
								%>
							</tr>			
							<tr bgcolor='CBDCF2'>                                      
								<td valign=top align=right width=30%><b>About me</b> <br>(Don't put any information here that you don't want publicly revealed (real name, email address, telephone number, home address, social securty number, credit card numbers, etc.). Protect your identity and privacy!)</td>
								<%
									out.println("<td><textarea cols=60 rows=8 name='" + ChanceConfiguration.ABOUT_ME + "'>" + user.getAboutme() + "</textarea></td>");	
								%>
							</tr>			
							<tr bgcolor='CBDCF2'>       
							                                   				
								<td align=right>
								<%
									if (PageUtils.getErrorTable(request) != null && PageUtils.getErrorTable(request).get("T&C") != null)
									{
										out.println("<b class='loginnewuser'>*</b>");
									}
								%>
								<input type=checkbox name='<%=ChanceConfiguration.TERMS_AND_CONDITIONS%>'></td>
								<td>I Agree to the <a href='#' onClick="window.open('jsp/terms_and_conditions.jsp','mywindow','width=900,height=200,resizable=yes,scrollbars=yes')" >Terms of service</a> agreement.</td>	
							</tr>
							<tr bgcolor='CBDCF2'>
								<td colspan=2 align=center><img src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/submit_profile_button.gif' onClick='submitRegInfo()'></td>
							</tr>							
																					
						</table>
						<jsp:include page="footer.jsp" />						
						<br>																								
					</td>					
				</tr>
			</table>
			
		</form>
	</BODY>
</HTML>