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

<h5><span style="color:blue">Mission</span></h5>

<p><span style="font-size:10.0pt"><%=name%> was started by a Java 
Evangelist that wants to help people find their soul mates.
 It is also a technological showcase to show prospective employers 
 what this Java Evangelist can do. Please email any problems and improvement
 suggestions to admin@jancechance.com. Please include as much information as 
 you can in probelm reports (including any stack traces or error messages that you see in the browser window 
 as well as the time of the day that the problem occured will be very helpful in diagnosing the problem). 
 Thank you. </span><p>

<h5><span style="color:blue">Site Information</span></h5>

<p><span style="font-size:10.0pt"><%=name%> is 100% free to use. You must register 
in order to contact others however browsing user profiles can be done without registering. 
Your email address is only used to email a reset password to you when you 
choose to reset it. It will not be passed to any third parties unless required by law. </span></p>

<h5><span style="color:blue">Tehcnical</span></h5>

<p><span style="font-size:10.0pt">The site runs on Apache Tomcat and a MySQL database backend. 
A homegrown MVC framework comprised of servlets and JSPs was created to handle request and response dispatching. 
A Hibernate persistance layer is employed to persist user data. Stored user images are 
held on disk in the file system and are retrieved by an image engine subsystem. The image 
engine subsystem uses the Java 2D framework to proportionaly scale images to 10% for use 
as thumbnails on profile pages. </span></p>

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