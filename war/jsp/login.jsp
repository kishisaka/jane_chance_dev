<%@ page import="us.ttyl.chance.common.ChanceConfiguration" %>

<HTML>
	<HEAD>
		<TITLE> New Document </TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">

		<link href="/<%=ChanceConfiguration.CONTEXT_ROOT%>includes/chance.css" rel="stylesheet" type="text/css">
		
		<script>
			function goSearchLogin()
			{
				frmInput.mode.value = "<%=ChanceConfiguration.LOGIN%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>login";
				frmInput.submit();
			}
			
			function registerNewUser()
			{
				frmInput.mode.value = "<%=ChanceConfiguration.REGISTER %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			
			function resetPassword()
			{
				frmInput.mode.value = "<%=ChanceConfiguration.GO_RESET_PASSWORD_PAGE %>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			
			function goSearchFromZipcodeFieldIfReturnPressed(e)
			{
				var unicode=e.keyCode? e.keyCode : e.charCode;
				if (unicode == 13)
				{
					frmInput.mode.value = "<%=ChanceConfiguration.LOGIN%>";
					frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>login";
					frmInput.submit();
				}
			}
		</script>
		
	</HEAD>

	<BODY>
	<FORM name="frmInput" METHOD="POST">
		
		<input type='hidden' name='<%=ChanceConfiguration.MODE%>'>
		<input type='hidden' name='transId' value='<%=request.getParameter("transId")%>'>
		
		<table align=center width=500>
			<tr>
				<td>
					<table width=100% cellpadding=5>
						<tr>
							<jsp:include page="header_help_only.jsp" />								
						</tr>			
					</table>
					
					<table align=center width=100% cellspacing=0 cellpadding=5>
						<tr bgcolor='CBDCF2'>
							<td colspan=2 align=left><b class='loginnewuser'>New user?</b> Please register <a href='#' onClick='registerNewUser()'><b>here</b></a>.</td>
						</tr>
					</table>
					<br>
					<table align=center width=100% cellspacing=0 cellpadding=5>
						<tr bgcolor='CBDCF2'>
							<td colspan=2 align=left><b>Registered users</b>, please enter your username and password below.</td>
						</tr>
						<%
						String message = (String)request.getSession().getAttribute("message");						
						if (message != null)
						{
							out.println("<tr bgcolor='CBDCF2'>");
							out.println("<td colspan=2 align=left><b class='loginnewuser'>"+message+"</b></td>");
							out.println("</tr>");
						}
						request.getSession().setAttribute("message", null);	
						%>
						<tr bgcolor='CBDCF2'>
							<td colspan=2 align=left><br></td>
						</tr>
						<tr bgcolor='CBDCF2'>
							<td width=30% align=right><label>Username</label></td>
							<td><input name="<%=ChanceConfiguration.USER_NAME%>" onkeypress='goSearchFromZipcodeFieldIfReturnPressed(event)' type=text size=30></td>
						</tr>
						<tr bgcolor='CBDCF2'>
							<td width=30% align=right><label>Password</label></td>
							<td><input name="<%=ChanceConfiguration.USER_PASSWORD%>" onkeypress='goSearchFromZipcodeFieldIfReturnPressed(event)' type=password size=30></td>
						</tr>								
						<tr bgcolor='CBDCF2'>							
							<td colspan=2 align=center><img onClick='goSearchLogin()' src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/login_button.gif'></td>							
						</tr>	
						<tr bgcolor='CBDCF2'>							
							<td colspan=2 align=left><a href='#' onClick='resetPassword()'>Forgot your password?<a/></td>
						</tr>					
					</table>
					
					<br>
					
					<jsp:include page="footer.jsp" />
				</td>
			</tr>
		</table>
	</FORM>
	</BODY>
</HTML>

