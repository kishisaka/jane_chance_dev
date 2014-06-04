<%@ page import="us.ttyl.chance.web.PageUtils" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="us.ttyl.chance.common.*" %>

<link href="/<%=ChanceConfiguration.CONTEXT_ROOT%>includes/chance.css" rel="stylesheet" type="text/css">

<table width=100% cellpadding=5>
	<tr>
		<jsp:include page="header_help_only.jsp" />								
	</tr>			
</table>
<%
	String hostName = request.getRequestURL().toString().toLowerCase();			
	String name = "John Chance";
	if (hostName.indexOf("janechance") > -1)
	{
		name = "Jane Chance";
	}
%>

<table BORDER="0" CELLPADDING="0" CELLSPACING="0" width="725" height="320" align="center"   >
<tr>
<td>
<TABLE cellSpacing=0 cellPadding=0 width="725" height="320" 
            border=0>

<tr>
<td align=center>




              
                   <table class="tsbheader">  
                    <tr><td>

<h5><span style="color:blue">Help</span></h5>

<p><span style="font-size:10.0pt">
Generally it is not a good idea to use the back button on your browser. 
Use the links and buttons that are provided to navigate the system. </span><p>

<p><span style="font-size:10.0pt">
Using the return key is not reccomended. The tabbing indexes and return key interception
have not been implemented yet.<br><br>
* The return key interception code has beed added. Please use the return key to submit pages and let us know of any issues that arise from using the return key Thank you.</span><p>

<p><span style="font-size:10.0pt;font-family:Arial">
© <%=name%>.com, 2006-2008 </span></p>


</td>
</tr>
</table>      
<br>
<P>              
      </td>     
          </tr>   
  </table>
<body>

</html>