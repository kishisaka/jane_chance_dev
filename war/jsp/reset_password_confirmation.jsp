<%@ page import="us.ttyl.chance.common.ChanceConfiguration" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="java.util.*" %>

<HTML>
	<HEAD>
		<TITLE> New Document </TITLE>
		<META NAME="Generator" CONTENT="EditPlus">
		<META NAME="Author" CONTENT="">
		<META NAME="Keywords" CONTENT="">
		<META NAME="Description" CONTENT="">

		<link href="/<%=ChanceConfiguration.CONTEXT_ROOT%>includes/chance.css" rel="stylesheet" type="text/css">
		
		<script>
			function goMain()
			{
				frmInput.action = "/<%=ChanceConfiguration.CONTEXT_ROOT%>index.jsp";
				frmInput.submit();
			}
		</script>
		
	</HEAD>

	<BODY>
		<form name=frmInput>
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
								<td colspan=2 align=left>An email has been sent to the email address that is tied to your account. Please follow the instructions in the email to reset your password.</td>
							</tr>						
							<tr bgcolor='CBDCF2'>
								<td colspan=2 align=left><br><center><img onclick='goMain()' src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/back_to_main.gif'></center></td>
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

