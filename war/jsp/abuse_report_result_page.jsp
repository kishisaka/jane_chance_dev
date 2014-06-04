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
			function closeWindow()
			{
				window.close();
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
							<td colspan=2 align=left><b>Reporting Abuse</b><br>Thank you for submitting an abuse report. All abuse reports are taken extremely seriously and will be investigated and handled quickly in an effort to keep our community safe. Once again thank you very much for taking time to fill out this report. </td>
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
							<td colspan=2 align=center><input type='button' name='Close Abuse Report Page' value='Close Abuse Report Page' onClick='closeWindow()'></td>							
						</tr>											
					</table>					
					<br>
				</td>
			</tr>
		</table>
	</FORM>
	</BODY>
</HTML>

