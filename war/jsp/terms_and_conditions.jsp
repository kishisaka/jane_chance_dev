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
                    <tr><td><h4 align="center" style="text-align:center"><span style="color:blue">TERMS OF 
USE AGREEMENT</span></h4>

<p class="MsoNormal" style="line-height:200%"><b>
<span style="font-size:10.0pt;line-height:200%;font-family:Arial;
color:blue">DEFINITIONS</span></b></p>
<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">“You” 
refers to the user of this website and its related services, and as such you 
have gained the right to use this website by respecting the applicable Terms of 
Use described in detail below. </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">
<%=name%> Inc. (“<%=name%>”) is the exclusive owner and operator of
<b><%=name%>.com </b>(the website).&nbsp; </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">As used in 
this Agreement, &quot;we&quot; and &quot;us&quot; means <%=name%> or any successor or assign of 
<%=name%>. </span></p>

<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">&nbsp;</span></b></p>
<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">
“Service(s)” refers to your use of <b><%=name%>.com </b>for any purpose 
whatsoever.</span></p>
<p class="MsoNormal" style="line-height:200%"><b>
<span style="font-size:10.0pt;line-height:200%;font-family:Arial;
color:blue">&nbsp;</span></b></p>
<p class="MsoNormal" style="line-height:200%"><b>
<span style="font-size:10.0pt;line-height:200%;font-family:Arial;
color:blue">OVERVIEW</span></b></p>
<p><span style="font-size:10.0pt">This Terms of Use Agreement (the &quot;Agreement&quot;) 
sets forth the terms and conditions which apply to your use of the <b>
<%=name%>.com</b> website and all services offered by <%=name%>. By 
completing the subscription process you are indicating that you agree to be 
bound by all of the terms in this Agreement. Please print and keep a copy of 
this Agreement for your records.</span></p>

<p><span style="font-size:10.0pt">We reserve the right, at any time, to change 
or modify the terms and conditions applicable to your subscription. Such changes 
will become effective upon notification, which we may effect by sending you 
notice by email and posting a revised Agreement on this website, which you can 
access at any time using the &quot;Terms of Use&quot; link. Your continued use of our 
service after notification of such a change has been sent shall be deemed to 
constitute acceptance by you of any such changes, modifications, additions or 
deletions. You agree to review this Agreement periodically to be aware of any 
such revisions.</span></p>
<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">ELIGIBILITY </span>
</b></p>
<p class="MsoNormal">&nbsp;</p>
<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">You must 
be 18 years of age or older to visit or use this website in any manner. By 
visiting <b><%=name%>.com</b> or accepting these Terms of Use, you represent 
and warrant to <%=name%> that you are 18 years of age or older, and that you 
have the right, authority and capacity to agree to and abide by these Terms of 
Use. You also represent and warrant to <%=name%> that you will use this 
website in a manner consistent with any and all applicable laws and regulations.</span></p>
<h5><span style="color:blue">PRIVACY OF INFORMATION</span></h5>

<h5><span style="color: windowtext; font-weight: normal">“Personal Information” means all information 
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
<p class="MsoNormal"><b>
 
<span style="font-size:10.0pt;font-family:Arial;color:blue">&nbsp;</span></b></p>
<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">USER AGREEMENT

</span></b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">This 
Agreement constitutes your agreement with <%=name%> with respect to your use 
of the Service. You must agree to abide by all of the terms and conditions 
contained in this Agreement in order to become or remain an authorized user of 
the Service. </span></p>

<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">RIGHT TO USE </span>
</b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">Your right 
to use the Service is subject to any limitations, conditions and restrictions 
established by us from time to time, in our sole discretion. We may alter, 
suspend or discontinue any aspect of the Service at any time, including the 
availability of any Service feature, database or content. We may also impose 
limits on certain features and aspects of the Service or restrict your access to 
parts or all of the Service without notice or liability. </span></p>

<p class="MsoNormal"><b>




<span style="font-size:10.0pt;font-family:Arial;color:blue">&nbsp;</span></b></p>

<span style="font-size:10.0pt;font-family:Arial;color:blue"><a name=code>CODE OF CONDUCT</a>
</span></b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">You agree 
to use the Service in accordance with the following Code of Conduct: </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">a.&nbsp; you 
will keep all information provided to you through the Service as private and 
confidential and will not give such information to anyone without the permission 
of the person who provided it to you; </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">b.&nbsp; you 
will not use the Service to engage in any form of harassment or offensive 
behavior, including but not limited to the posting of communications, pictures 
or recordings which contain libelous, slanderous, abusive or defamatory 
statements, or racist, pornographic, obscene, or offensive language; </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">c.&nbsp; you 
will not forward chain letters through the Service; </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">d.&nbsp; you 
will not use the Service to infringe the privacy rights, property rights, or any 
other rights of any person; </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">e.&nbsp; you 
will not post messages, pictures or recordings or use the Service in any way 
which </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">&nbsp;i. 
violates, plagiarizes or infringes upon the rights of any third party, including 
but not limited to any copyright or trade-mark law, privacy or other personal or 
proprietary rights, or </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">&nbsp;ii. is 
fraudulent or otherwise unlawful or violates any law; and </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">f.&nbsp; you 
will not use the Service to distribute, promote or otherwise publish any 
material containing any solicitation for funds, advertising or solicitation for 
goods or services. </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">g.&nbsp; you 
will not use the Service to distribute or up load any virus, trojan horses or do 
anything else that might cause harm to the Service, <%=name%> systems or to 
other members' systems in any way. </span></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">h.&nbsp; you 
will not post or transmit in any manner any contact information including, but 
not limited to, email addresses, &quot;instant messenger&quot; nicknames, telephone 
numbers, postal addresses, URLs, or full names through your publicly posted 
information.&nbsp; </span></p>

<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">MONITORING OF 
INFORMATION </span></b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">We reserve 
the right to monitor all advertisements, public postings and messages to ensure 
that they conform to content guidelines that are monitored by us and subject to 
change from time to time. </span></p>
<p class="MsoNormal"><b><span style="font-size:10.0pt;font-family:Arial">&nbsp;</span></b></p>
<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">REMOVAL OF 
INFORMATION </span></b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">While we 
do not and cannot review every message or other material posted or sent by users 
of the Service, and are not responsible for any content of these messages or 
materials, we reserve the right, but are not obligated, to delete, move, or edit 
messages or materials, including without limitation profiles, public postings 
and messages, that we, in our sole discretion, deem to violate the Code of 
Conduct set out above or any other applicable content guidelines, or to be 
otherwise unacceptable. You shall remain solely responsible for the content of 
profiles, public postings, messages and other materials you may upload to the 
Service or users of the Service. </span></p>

<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">TERMINATION OF 
ACCESS TO SERVICE </span></b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">We may, in 
our sole discretion, terminate or suspend your access to all or part of the 
Service at any time, with or without notice, for any reason, including, without 
limitation, breach of this Agreement. Without limiting the generality of the 
foregoing, any fraudulent, abusive, or otherwise illegal activity, or that may 
otherwise affect the enjoyment of the Service or the Internet by others may be 
grounds for termination of your access to all or part of the Service at our sole 
discretion, and you may be referred to appropriate law enforcement agencies.
</span></p>

<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">PROPRIETARY 
INFORMATION </span></b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">The 
Service contains information which is proprietary to us, our partners, and our 
users. We assert full copyright protection in the Service. Information posted by 
us, our partners or users of the Service may be protected whether or not it is 
identified as proprietary to us or to them. You agree not to modify, copy or 
distribute any such information in any manner whatsoever without having first 
received the express written permission of the owner of such information.</span></p>

<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">NO RESPONSIBILITY
</span></b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">You 
acknowledge that we are not responsible for suspension of the Service, 
regardless of the cause of the interruption or suspension. </span></p>

<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">SECURITY </span></b>
</p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">Your 
account is private and should not be used by anyone other than you. You are 
responsible for all usage or activity on the Service by users using your 
password, including but not limited to use of your password by any third-party.
</span></p>

<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">EXTERNAL LINKS
</span></b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">The 
Service may from time to time contain links to other Internet sites and 
resources (&quot;External Links&quot;). You acknowledge that we are not responsible for, 
and have no liability as a result of the availability of External Links or their 
contents. We suggest that you review the terms of use and privacy statements of 
such External Links prior to using them. </span></p>

<h5><span style="color:blue">MAKING CLAIMS OF COPYRIGHT INFRINGEMENT</span></h5>
<p><span style="font-size:10.0pt">If you believe that any material or content 
distributed by <%=name%> constitutes copyright infringement, please provide 
us with the following information: an electronic or physical signature of the 
person authorized to act on behalf of the owner of the copyright interest; a 
description of the copyrighted work that you claim has been infringed; a 
description of where the material that you claim is infringing is located on our 
website; your address, telephone number and email address; a written statement 
by you that you have a good faith belief that the disputed use is not authorized 
by the copyright owner, its agent, or the law; a statement by you, made under 
penalty of perjury, that the above information in your notice is accurate and 
that you are the copyright owner or authorized to act on the copyright owner's 
behalf.</span></p>
<h5><span style="color:blue">DISCLAIMER OF WARRANTIES AND LIMITATION OF 
LIABILITY</span></h5>
<p><span style="font-size:10.0pt">YOU UNDERSTAND AND AGREE THAT YOU USE <b>
<%=name%>.COM</b> AND ITS RELATED SERVICES AT YOUR OWN RISK. <%=name%>’S 
SERVICES ARE PROVIDED ON AN &quot;AS IS&quot; BASIS WITHOUT REPRESENTATIONS OR WARRANTIES 
OF ANY KIND, WHETHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO WARRANTIES 
OF TITLE, NON-INFRINGEMENT, OR IMPLIED WARRANTIES OF MERCHANTABILITY OR FITNESS 
FOR A PARTICULAR PURPOSE, OTHER THAN THOSE WARRANTIES WHICH ARE INCAPABLE OF 
EXCLUSION UNDER LAW. </span></p>
<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">

<%=name%> PROVIDES ON-LINE PERSONAL DATING SERVICES AND TOOLS TO SINGLES AND 
OTHER SIMILARLY INTERESTED INDIVIDUALS, AS WELL AS FORUMS FOR DISCUSSION AND 
COMMENTARIES TO SUBSCRIBERS THROUGH THE <b><%=name%>.COM</b> WEBSITE AND 
OTHER MEANS OF DISTRIBUTION. <%=name%> DOES NOT GUARANTEE THE ACCURACY, 
COMPLETENESS, OR TIMELINESS OF, OR OTHERWISE ENDORSE ANY INFORMATION CONTAINED 
ON <b><%=name%>.COM</b>. DUE TO THE NUMBER OF SOURCES FROM WHICH CONTENT 
DISTRIBUTED BY <%=name%> IS OBTAINED, THERE MAYBE DELAYS, OMISSIONS OR 
INACCURACIES IN SUCH CONTENT. YOU ACKNOWLEDGE THAT USE OF THE SERVICE IS AT YOUR 
OWN RISK. WE DO NOT REPRESENT OR ENDORSE THE ACCURACY OR RELIABILITY OF ANY 
MEMBER PROFILE, ADVICE, OPINION, STATEMENT OR OTHER INFORMATION DISPLAYED, 
UPLOADED OR DISTRIBUTED THROUGH THE SERVICE BY <%=name%>, OUR PARTNERS OR ANY 
USER OF THE SERVICE OR ANY OTHER PERSON OR ENTITY. YOU ACKNOWLEDGE THAT ANY 
RELIANCE UPON ANY SUCH OPINION, MEMBER PROFILE, ADVICE, STATEMENT OR INFORMATION 
SHALL BE AT YOUR SOLE RISK. YOUR CONTINUED USE OF THE SERVICE NOW, OR FOLLOWING 
THE POSTING OF NOTICE OF ANY CHANGES IN THIS AGREEMENT, WILL CONSTITUTE A 
BINDING ACCEPTANCE BY YOU OF THIS AGREEMENT, OR ANY SUBSEQUENT MODIFICATIONS.
</span></p>
<p><span style="font-size:10.0pt">YOU HEREBY ACKNOWLEDGE AND AGREE THAT UNDER NO 
CIRCUMSTANCES WILL <%=name%>, ITS OFFICERS, DIRECTORS, EMPLOYEES, AGENTS AND 
THIRD PARTY CONTENT PROVIDERS OR LICENSORS BE LIABLE TO YOU OR ANY THIRD PARTY 
FOR ANY LOSS WHATSOEVER CAUSED BY YOUR USE OR RELIANCE ON INFORMATION OBTAINED 
THROUGH THE CONTENT DISTRIBUTED BY <%=name%> AS WELL AS ANY DIRECT, INDIRECT, 
INCIDENTAL, SPECIAL, PUNITIVE OR CONSEQUENTIAL DAMAGES OR INJURY ARISING OUT OF 
THE USE OR INABILITY TO USE THE <%=name%> SERVICE OR OUT OF THE BREACH OF ANY 
WARRANTY, OR CAUSED BY ANY FAILURE OF PERFORMANCE, ERROR, OMISSION, 
INTERRUPTION, DELETION, DEFECT, DELAY IN OPERATION OR TRANSMISSION, COMPUTER 
VIRUS, COMMUNICATION LINE FAILURE, THEFT OR DESTRUCTION OR UNAUTHORIZED ACCESS 
TO AND/OR ALTERATION OF THE <b><%=name%>.COM</b> WEBSITE, NO MATTER WHETHER 
SUCH CLAIMS ARE BASED IN CONTRACT, TORT, NEGLIGENCE, STRICT LIABILITY OR ANY 
OTHER CAUSE OF ACTION, AND REGARDLESS OF WHETHER <%=name%> HAS BEEN ADVISED 
OF THE POSSIBILITY OF SUCH DAMAGES. </span></p>
<p><span style="font-size:10.0pt">SOME JURISDICTIONS LIMIT THE AVAILABILITY OF 
SUCH LIMITATION OF LIABILITY, IN WHICH CASE THE PROVISIONS OF THIS SECTION MAY 
NOT APPLY TO YOU. NOTWITHSTANDING THE FOREGOING, IN NO EVENT SHALL OUR LIABILITY 
EXCEED THE SUM OF $100.00 U.S. DOLLARS. </span></p>
<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">INDEMNITY </span>

</b></p>
<p><span style="font-size:10.0pt">You hereby agree to indemnify, defend and hold 
harmless <%=name%> and all officers, directors, owners, agents, information 
providers, affiliates, licensors and licensees (collectively, the &quot;Indemnified 
Parties&quot;) from and against any and all liability and costs, including, without 
limitation, reasonable attorneys' fees, incurred by the Indemnified Parties in 
connection with any claim arising out of any breach by you of this Agreement or 
the foregoing representations, warranties and covenants. You shall cooperate as 
fully as reasonably required in the defense of any such claim. <%=name%> 
reserves the right, at its own expense, to assume the exclusive defense and 
control of any matter subject to indemnification by you.</span></p>
<h5><span style="color:blue">MISCELLANEOUS</span></h5>
<p><span style="font-size:10.0pt">This Agreement represents the entire agreement 
between you and <%=name%> regarding the use of our services and supersedes 
any other agreement or understanding on the subject matter. This Agreement, your 
rights and obligations, and all actions contemplated by this Agreement shall be 
governed by the laws of the Province of </span>
<span style="font-size:
  10.0pt">British Columbia</span><span style="font-size:10.0pt">, Canada. As a 
condition of using <b><%=name%>.com’s</b> services, each user agrees that any 
and all disputes and causes of action arising out of or connected with <b>
<%=name%>.com</b>, shall be resolved through arbitration, with such 
arbitration to be held in Vancouver</span><span style="font-size:
 10.0pt">, </span><span style="font-size:10.0pt">British Columbia, </span>

<span style="font-size:
  10.0pt">Canada</span><span style="font-size:
10.0pt">. </span></p>
<p><span style="font-size:10.0pt">Additionally, except where prohibited by law, 
as a condition of using <b><%=name%>.com</b> services, you agree that any and 
all disputes and causes of action arising out of or connected to our services 
shall be resolved individually, without resort to any form of class action.</span>
<span style="font-size:10.0pt">You also agree that regardless of any statute or 
law to the contrary, any claim or cause of action arising from or related to the 
use of <b><%=name%>.com</b> must be filed within one (1) year after such 
claim or cause of action arose or be forever barred. The failure of either party 
to exercise in any respect any right provided for herein shall not be deemed a 
waiver of any further rights hereunder. </span></p>
<p><span style="font-size:10.0pt">If any provision of this Agreement is found to 
be unenforceable or invalid, that provision shall be limited or eliminated to 
the minimum extent necessary so that this Agreement shall otherwise remain in 
full force and effect and be enforceable. This Agreement is not assignable, 
transferable or sublicensable without the prior written consent of <%=name%>. 
<%=name%> may assign this Agreement in whole or in part. No agency, 
partnership, joint venture, or employment is created as a result of this 
Agreement. Headings are for convenience only and have no legal or contractual 
effect. All notices under this Agreement shall be in writing and shall be deemed 
to have been duly given when receipt is electronically confirmed, if transmitted 
by facsimile or email or upon receipt, if sent by certified or registered mail, 
return receipt requested. </span></p>
<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">MODIFICATIONS </span>

</b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">We may 
modify this Agreement from time to time. Notification of changes in this 
Agreement will be posted on the Service or sent via electronic mail, as we may 
determine in our sole discretion. If you do not agree to any modifications, you 
should terminate your use of the Service. Your continued use of the Service now, 
or following the posting of notice of any changes in this Agreement, will 
constitute a binding acceptance by you of this Agreement, or any subsequent 
modifications. </span></p>

<p class="MsoNormal"><b>
<span style="font-size:10.0pt;font-family:Arial;color:blue">DISCLOSURE AND OTHER 
COMMUNICATION </span></b></p>

<p class="MsoNormal"><span style="font-size:10.0pt;font-family:Arial">We reserve 
the right to send electronic mail to you, for the purpose of informing you of 
changes or additions to the Service, or of any <%=name%> related products and 
services. We reserve the right to disclose information about your usage of the 
Service and demographics in forms that do not reveal your personal identity.
</span></p>

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