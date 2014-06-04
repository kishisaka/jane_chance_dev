package us.ttyl.chance.web;

import us.ttyl.chance.common.*;
import us.ttyl.chance.domain.*;
import java.util.*;

public class WebUtils {
	public static String renderMonthList(String selectedValue)
	{
		String[] sequence = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		String returnHTML = "<select name='" + ChanceConfiguration.BIRTHDATE_MONTH + "'>";
		
		for (int i = 0; i < 12; i++)
		{
			if (selectedValue != null && selectedValue.equals(sequence[i]))
			{
				returnHTML += "<option value='" + sequence[i] + "' selected=\"selected\">" + months[i] + "</option>";
			}
			else
			{
				returnHTML += "<option value='" + sequence[i] + "'>"+ months[i] + "</option>";
			}
		}
		
		returnHTML += "</select>";
		return returnHTML;
	}

	public static String renderEthnicityList(String selectedValue)
	{
		String returnHTML = "<select name='" + ChanceConfiguration.ETHNICITY + "'>";
		
		Enumeration keys = ChanceConfiguration.getM_ethnicity().keys();
		while (keys.hasMoreElements())
		{
			Integer key = (Integer)keys.nextElement();
			TEthnicity e = (TEthnicity)ChanceConfiguration.getM_ethnicity().get(key);
			
			
			if (selectedValue != null && selectedValue.equals(e.getEthnicityId().toString()))
			{
				returnHTML += "<option value='" + e.getEthnicityId() + "' selected=\"selected\">" + e.getEthnicityDesc() + "</option>";
			}
			else
			{
				returnHTML += "<option value='" + e.getEthnicityId() + "'>"+ e.getEthnicityDesc() + "</option>";
			}
		}
		
		returnHTML += "</select>";
		return returnHTML;
	}
}
