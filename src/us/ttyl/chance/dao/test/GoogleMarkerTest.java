package us.ttyl.chance.dao.test;

import java.util.*;
import us.ttyl.chance.dao.*;
import us.ttyl.chance.domain.*;

public class GoogleMarkerTest 
{
	public GoogleMarkerTest()
	{
		GoogleMapMarkersDAO dao = new GoogleMapMarkersDAO();
		dao.updateMarkerList();
	}
	
	public static void main(String args[])
	{
		new GoogleMarkerTest();
	}
}
