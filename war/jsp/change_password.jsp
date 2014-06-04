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
			function changePassword()
			{
				frmInput.mode.value="<%=ChanceConfiguration.UPDATE_PASSWORD%>";
				frmInput.action = "/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			function goMain()
			{
				frmInput.action = "/<%=ChanceConfiguration.CONTEXT_ROOT%>/index.jsp";
				frmInput.submit();
			}
			
			function goSearchFromZipcodeFieldIfReturnPressed(e)
			{
				var unicode=e.keyCode? e.keyCode : e.charCode;
				if (unicode == 13)
				{					
					frmInput.mode.value="<%=ChanceConfiguration.UPDATE_PASSWORD%>";
					frmInput.action = "/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
					frmInput.submit();
				}
			}
		</script>
		
	</HEAD>

	<BODY>
		<form name=frmInput METHOD="POST">
		
			<input type='hidden' name='<%=ChanceConfiguration.MODE%>'>
			
			<table align=center width=500>
				<tr>
					<td>
						<table cellpadding=5>
							<jsp:include page="header_help_only.jsp" />	
							<tr>
								<td colspan=3>								
									<a href='#' onClick='goMain()'>Return to main page</a>
								</td>
							</tr>		
						</table>
						
						<table align=center width=100% cellspacing=0 cellpadding=5>						
							<tr bgcolor='CBDCF2'>
								<td colspan=2 align=left><br></td>
							</tr>
							<tr bgcolor='CBDCF2'>
								<td width=30% align=right><label>New Password</label></td>
								<td><input type=text size=30 name='<%=ChanceConfiguration.PASSWORD%>'></td>
							</tr>	
							
							<tr bgcolor='CBDCF2'>
								<td width=30% align=right><label>Retype New Password</label></td>
								<td><input type=text size=30 onkeypress='goSearchFromZipcodeFieldIfReturnPressed(event)' name='<%=ChanceConfiguration.PASSWORD_CONFIRM%>'></td>
							</tr>	
																				
							<tr bgcolor='CBDCF2'>							
								<td colspan=2 align=center><img onClick='changePassword()' src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/reset_password_button.gif'></td>							
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