<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.common.*" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>

<%
	User user = PageUtils.getUser(request);	
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
			function uploadPictures()
			{
				frmInput.mode.value="<%=ChanceConfiguration.UPLOAD_PICTURES %>";
				frmInput.action='/<%=ChanceConfiguration.CONTEXT_ROOT%>uploadPictures';
				frmInput.submit();
			}
			
			function logOut()
			{
				frmInput2.mode.value="<%=ChanceConfiguration.LOG_OUT%>";
				frmInput2.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput2.submit();
			}
			
			function updateProfile()
			{
				frmInput2.mode.value="<%=ChanceConfiguration.EDIT_USER %>";
				frmInput2.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>register";
				frmInput2.submit();
			}
		</script>
		
	</HEAD>
	
	<BODY>
		<form name=frmInput2 METHOD="POST">
			<input type='hidden' name='<%=ChanceConfiguration.MODE%>'>
		</form>
		
		<form name=frmInput METHOD="POST" ENCTYPE="multipart/form-data">
		
			<input type='hidden' name='<%=ChanceConfiguration.MODE%>'>
		
			<table align=center width=800>
				<tr>
					<td>
						<jsp:include page="header.jsp" />
						<table width=100% cellpadding=5>							
							<tr>
								<td colspan=2>
									<a href='#' onClick='updateProfile()'>Return to profile </a> 															
								</td>
							</tr>		
						</table>
						
						<table align=center width=100% cellspacing=0 cellpadding=5>
							<tr bgcolor='CBDCF2'>
								<td align=right> Upload picture 1</td>
								<td> <input type=file name='upload1' size=30></td>
							</tr>							
							<tr bgcolor='CBDCF2'>
								<td align=right> Upload picture 2</td>
								<td> <input type=file name='upload2' size=30></td>
							</tr>	
							<tr bgcolor='CBDCF2'>
								<td align=right> Upload picture 3</td>
								<td> <input type=file name='upload3' size=30></td>
							</tr>
							<tr bgcolor='CBDCF2'>
								<td align=right> Upload picture 4</td>
								<td> <input type=file name='upload4' size=30></td>
							</tr>
							<tr bgcolor='CBDCF2'>
								<td align=right> Upload picture 5</td>
								<td> <input type=file name='upload5' size=30></td>
							</tr>
							<tr bgcolor='CBDCF2'>
								<td align=right> Upload picture 6</td>
								<td> <input type=file name='upload6' size=30></td>
							</tr>
							<tr bgcolor='CBDCF2'>
								<td colspan=2 align=center><img onClick='uploadPictures()' src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/upload_button.gif'></td>
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

