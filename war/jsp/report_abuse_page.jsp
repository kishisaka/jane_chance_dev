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
			function sendAbuseReport()
			{
				frmInput.mode.value = "<%=ChanceConfiguration.ABUSE_SEND_PAGE%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>abuse";
				frmInput.submit();
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
					
					<br>
					<table align=center width=100% cellspacing=0 cellpadding=5>
						<tr bgcolor='CBDCF2'>
							<td colspan=2 align=left><b>Reporting Abuse</b><br>Thank you for submitting an abuse report. Please enter detail regarding the type of abuse abuse and the user name or names that are connected with the abuse in the fields below. All abuse reports are taken extremely seriously and will be investigated and handled quickly in an effort to keep our community safe. Once again thank you very much for taking time to fill out this report. </td>
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
							<td width=30% align=right><label>Username/s involed in the abuse</label></td>
							<td><textarea cols=60 rows=2 name="<%=ChanceConfiguration.ABUSER_USER_NAME%>"></textarea></td>
						</tr>
						<tr bgcolor='CBDCF2'>
							<td width=30% align=right><label>Details of the abuse</label></td>
							<td><textarea cols=60 rows=8 name="<%=ChanceConfiguration.ABUSER_DESCRIPTION%>"></textarea></td>
						</tr>								
						<tr bgcolor='CBDCF2'>							
							<td colspan=2 align=center><input type='button' name='Submit Abuse Report' value='Submit Abuse Report' onClick='sendAbuseReport()'></td>							
						</tr>											
					</table>					
					<br>
				</td>
			</tr>
		</table>
	</FORM>
	</BODY>
</HTML>

