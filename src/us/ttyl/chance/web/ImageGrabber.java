package us.ttyl.chance.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;
import java.io.*;

import us.ttyl.chance.common.ChanceConfiguration;

public class ImageGrabber extends GenericServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		returnImage(request, response);		
	}
	
	private void returnImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		byte[] imageBuffer = new byte[4096]; 
		String imageId = request.getParameter("imageId");
		File imageFile = new File(ChanceConfiguration.getM_imageDirectory() + File.separator + imageId + ".jpg");
		OutputStream os = response.getOutputStream();
		FileInputStream fis = new FileInputStream(imageFile);
		
		//get and write file in 4k chunks
		while (fis.available() >= 4096)
		{
			fis.read(imageBuffer);
			os.write(imageBuffer);
		}
		//write rest of file
		int imageBytesLeft = fis.available();
		if (imageBytesLeft > 0)
		{
			byte[] imageBufferLast = new byte[imageBytesLeft];
			fis.read(imageBufferLast);
			os.write(imageBufferLast);
		}
		fis.close();
		os.flush();
		os.close();
	}
}
