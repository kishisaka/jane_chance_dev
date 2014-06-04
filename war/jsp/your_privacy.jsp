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

<h5><span style="color:blue">PRIVACY OF INFORMATION</span></h5>

<h5><span style="color: windowtext; font-weight: normal"> “Personal Information” means all information 
about an identifiable individual. <%=name%> recognizes the importance of 
privacy and recognizes the sensitivity of personal information received by us in 
the course of offering our service.</span></h5>
<h5><span style="color: windowtext; font-weight: normal">We recognize our 
professional obligation to maintain the confidentiality of our clients’ 
information, and recognize our obligations concerning the personal information 
of all individuals that we collect, use or disclose in our practice. This policy 
has been developed with those obligations in mind.</span></h5>
<p><span style="font-size:10.0pt"><%=name%> collects personal information from you in order to create your online dating profile.  <%=name%> will not use or disclose your personal information for purposes other than those described in this policy.</span></p>
<p><span style="font-size:10.0pt"><%=name%> may also use third-party companies to provide various services (including but not limited to instant messaging and advertising) when you use this website. If you use those services, the internet protocol address (IP address) of the computer you are using to access the website may be disclosed to those third-party companies, and we may use attributes (including but not limited to age and gender) that you provide in your public profile to display relevant ads to you. <%=name%> is not responsible for the privacy practices of these third-party companies or any other external websites linked to this site. We encourage you to read the privacy statements of these other websites when you leave our website.</span></p>
<p><span style="font-size:10.0pt">If we change our privacy policy, we will 
notify you.</span></p>
<p><span style="font-size:10.0pt">If you have any questions with respect to our 
policies concerning the handling of your personal information, or if you wish to 
request access to, or correction of, your personal information under our care 
and control, please contact our Privacy Officer at: adminstrator *at*  <%=name%>.com</span>
</span></p>
<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">By issuing 
this website you acknowledge that </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">(a) we 
cannot ensure the security or privacy of information you provide through the 
Internet and your email messages, and you release us from any and all liability 
in connection with the use of such information by other parties; </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">(b) we are 
not responsible for, and cannot control, the use by others of any information 
which you provide to them and you should use caution in selecting the personal 
information you provide to others through the Service; and </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">(c) we 
cannot assume any responsibility for the content of messages sent by other users 
of the Service, and you release us from any and all liability in connection with 
the contents of any communications you may receive from other users. We cannot 
guarantee, and assume no responsibility for verifying, the accuracy of the 
information provided by other users of the Service. </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">You may 
not use the Service for any unlawful purpose.  If you use this service unlawfully we will pass along all information to law enforcement. We may refuse to grant you an ID 
or nickname that impersonates someone else, is protected by trade-mark or 
proprietary law, or is vulgar or otherwise offensive, as determined by us in our 
sole discretion. </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">We do not 
share any personal information with third parties, and we do not send 
advertising to our members. </span></p>


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