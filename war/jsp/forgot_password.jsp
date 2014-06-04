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
			function resetPassword()
			{
				frmInput.mode.value="<%=ChanceConfiguration.RESET_PASSWORD %>";
				frmInput.action = "/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput.submit();
			}
			function goMain()
			{
				frmInput.action = "/<%=ChanceConfiguration.CONTEXT_ROOT%>index.jsp";
				frmInput.submit();
			}
			
			function goSearchFromZipcodeFieldIfReturnPressed(e)
			{
				var unicode=e.keyCode? e.keyCode : e.charCode;
				if (unicode == 13)
				{					
					frmInput.mode.value="<%=ChanceConfiguration.RESET_PASSWORD %>";
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
						<table width=100% cellpadding=5>
							<tr>
								<jsp:include page="header_help_only.jsp" />								
							</tr>			
						</table>
						
						<table align=center width=100% cellspacing=0 cellpadding=5>						
							<tr bgcolor='CBDCF2'>
								<td colspan=2 align=left><br></td>
							</tr>
							<tr bgcolor='CBDCF2'>
								<td width=30% align=right><label>Username</label></td>
								<td><input type=text onkeypress='goSearchFromZipcodeFieldIfReturnPressed(event)' size=30 name='<%=ChanceConfiguration.USER_NAME %>'></td>
							</tr>														
							<tr bgcolor='CBDCF2'>							
								<td colspan=2 align=center><img onClick='resetPassword()' src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/reset_password_button.gif'></td>							
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

