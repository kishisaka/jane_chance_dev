<%@ page import="us.ttyl.chance.common.ChanceConfiguration" %>
<%@ page import="us.ttyl.chance.domain.*" %>
<%@ page import="java.util.*" %>

<%
	String mapKey = "";
	String imageTitle = "";
	String hostName = request.getRequestURL().toString().toLowerCase();			
	if (hostName.indexOf("janechance") > -1)
	{
		imageTitle="title5.gif";
		hostName="www.janechance.com";
		mapKey = "<%mapkey_jane%>";
	}
	else
	{
		imageTitle="title4.gif";
		hostName="www.johnchance.com";
		mapKey = "<%mapkey_john%>";
	}		
	
%>


<html>
	<HEAD>
			
		<link href="/<%=ChanceConfiguration.CONTEXT_ROOT%>includes/chance.css" rel="stylesheet" type="text/css">
		
		<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
		<title>Google Maps JavaScript API Example</title>
		
		<script src="http://maps.google.com/maps?file=api&amp;v=2&amp;key=<%=mapKey%>" type="text/javascript"></script>
		<script type="text/javascript">
			//<![CDATA[
			function load() 
			{
				if (GBrowserIsCompatible()) 
				{
					var map = new GMap2(document.getElementById("map"));
					map.addControl(new GSmallMapControl());
					map.addControl(new GMapTypeControl());
					map.setCenter(new GLatLng(37.4419, -122.1419), 3);															
					<%
						List markers = ChanceConfiguration.getM_googleMapMarkers();
						for(int i = 0; i < markers.size(); i ++)
						{
							GoogleMapMarkers marker = (GoogleMapMarkers)markers.get(i);
							out.println("map.addOverlay(createMarker(new GLatLng("+marker.getGeoInfoLat()+","+marker.getGeoInfoLong()+"),"+marker.getNumberOfUsers()+","+marker.getZipcode()+"));");
						}						
					%>
				}
			}		
			// Creates a marker at the given point with the given number label
			function createMarker(point, number, zipcode) 
			{
			  var marker = new GMarker(point);
			  GEvent.addListener(marker, "click", function() 
			  {
				marker.openInfoWindowHtml(number + " registered users at "+zipcode+"<br><a href='/<%=ChanceConfiguration.CONTEXT_ROOT%>search?gender=99&age_start=19&age_end=65&zipcode="+zipcode+"&distance=1&mode=startlooking'>check them out now </a>");
			  });
			  return marker;
			}
			//]]>
			
			
			
			function goLogin()
			{
				frmInput.mode.value="<%=ChanceConfiguration.LOGIN%>";
				frmInput.action="https://<%=hostName%>/<%=ChanceConfiguration.CONTEXT_ROOT%>home";
				frmInput.submit();
			}			
			
			function startLooking()
			{
				frmInput.mode.value="<%=ChanceConfiguration.START_LOOKING%>";
				frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
				frmInput.submit();
			}
			
			function goSearchFromZipcodeFieldIfReturnPressed(e)
			{
				var unicode=e.keyCode? e.keyCode : e.charCode;
				if (unicode == 13)
				{
					frmInput.mode.value="<%=ChanceConfiguration.START_LOOKING%>";
					frmInput.action="/<%=ChanceConfiguration.CONTEXT_ROOT%>search";
					frmInput.submit();
				}
			}
									
		</script>
	</HEAD>
		
	<BODY onload='load()' onunload='GUnload()' bgcolor='D8DAE2'>
		
		<FORM name="frmInput" METHOD="POST" onSubmit=startLooking()>

			<input type='hidden' name='<%=ChanceConfiguration.MODE%>'>

			<table align=center>
				<tr>
					<td> 
						<table cellspacing=0 cellpadding=0 width=730 height=130 border=0 bgcolor='E8E8E8'>
							<tr>
								<td width=500 height=130><img src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/500_150_2.jpg'></td>
								<td width=212 height=130>
									<table cellspacing=0 cellpadding=0 width=212 height=130 border=0>
										<tr height=110>
											<td>
												<img src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/<%=imageTitle%>'>
											</td>
										</tr>
										<tr height=25>
											<td align=center>
												<img onClick='goLogin()' src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/login_button.gif'>
											</td>
										</tr>
									</table>
								</td>
							</tr>	
						</table>
				
						<table width=730 bgcolor=D1E1F7>
							<tr>
								<td colspan=4>		 																					
									<p class='title'>
										Start searching now! Totally free to post a profile and no registration required to browse!
									<p>									
								</td>
							</tr>
							<tr>
								<td>
									<label>Seeking A</label>								 
									<select name='<%=ChanceConfiguration.GENDER%>'>
										<%
											List genderList = ChanceConfiguration.getList(ChanceConfiguration.getM_sex());
											for(int i = 0; i < genderList.size(); i ++)
											{					
												TSex gender = (TSex)genderList.get(i);																													
												if (gender.getSexId().intValue() < 99)
												{
													out.println("<option value="+(gender.getSexId()).toString()+">"+gender.getSexDesc()+"</option>");
												}
												if (gender.getSexId().intValue() == 99)
												{
													out.println("<option value='99'>not specified</option>");
												}
											}
										%>										
									</select>
								</td>
								<td>
									<label>Age</label>
									<select name='<%=ChanceConfiguration.AGE_START%>'>
										<%
										out.println("<option value=18 selected>18</option>");
										for(int i = 19; i < 65; i ++)
										{ 
											out.println("<option value="+i+">"+i+"</option>");
										}
										out.println("<option value=65>65+</option>");
										%>
									</select> 
									To 
									<select name='<%=ChanceConfiguration.AGE_END%>'>									
										<%
										for(int i = 18; i < 65; i ++)
										{																							
											out.println("<option value="+i+">"+i+"</option>");																						
										}
										out.println("<option value=65 selected>65+</option>");										
										%>
									</select>
								</td>
								<td>
									<label>ZipCode</label> 
									<input type=text name='<%=ChanceConfiguration.ZIPCODE%>' onkeypress='goSearchFromZipcodeFieldIfReturnPressed(event)'; size=10>
								</td>
								<td>
									<label>Within</label> 
									<select name='<%=ChanceConfiguration.DISTANCE%>'>
										<option value="10" selected>10</option>										
										<option value="20" >20</option>	
										<option value="30" >30</option>	
										<option value="40" >40</option>	
										<option value="50" >50</option>	
										<option value="60" >60</option>	
										<option value="70" >70</option>	
										<option value="80" >80</option>	
										<option value="90" >90</option>	
										<option value="100" >100</option>										
									</select> miles.
								</td>				
							</tr>
										
							<tr>
								<td colspan=4 align=center>
									<img src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/start_looking_button.gif' onClick='startLooking()'>
								</td>
							</tr>
							<tr>
								<td colspan=4 align=center><br></td>
							</tr>
						</table>
						<table cellspacing=0 cellpadding=0 border=0 width=712>
							<tr>
								<td width=51 height=26 style='bgcolor='97C0F8'><img src='/<%=ChanceConfiguration.CONTEXT_ROOT%>images/left_corner_map2.gif'></td>
								<td width=658 height=26 bgcolor='97C0F8'>Click on a marker to see how many chances are in your area! </td>
								<td width=3 height=26 bgcolor='97C0F8'>&nbsp;</td>
							</tr>
							<tr>
								<td width=3 height=300 bgcolor='97C0F8'>&nbsp;</td>
								<td><div id="map" style="width: 706px; height: 300px"></div></td>
								<td width=3 height=300 bgcolor='97C0F8'>&nbsp;</td>
							</tr>
							<tr>
								<td colspan=3 width=712 height=3 bgcolor='97C0F8' colspan=3></td>
							</tr>
						</table>
						
						<jsp:include page="jsp/footer.jsp" />
					</td>
				</tr>
			</table>
		</form>
	</BODY>
</HTML>
