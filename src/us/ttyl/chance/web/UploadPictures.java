package us.ttyl.chance.web;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.util.*;
import org.apache.commons.fileupload.disk.*;

import us.ttyl.chance.common.*;
import us.ttyl.chance.domain.*;
import us.ttyl.chance.dao.*;

public class UploadPictures extends GenericServlet 
{	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			UserManager um = UserManagmentFactory.getUserManager();
			//Check that we have a file upload request
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart == true)
			{
				//Create a factory for disk-based file items
				DiskFileItemFactory factory = new DiskFileItemFactory();			

				//Create a new file upload handler
				ServletFileUpload upload = new ServletFileUpload(factory);

				//Parse the request
				List items = upload.parseRequest(request);
				
				//Process the uploaded items
				Iterator iter = items.iterator();
				while (iter.hasNext()) 
				{
				    FileItem item = (FileItem)iter.next();
				    if (item.isFormField()) 
				    {				    	
				    	// do nothing
				    } 
				    else 
				    {				    
				    	String fieldName = item.getFieldName();
				        String fileName = item.getName();
				        String contentType = item.getContentType();
				        boolean isInMemory = item.isInMemory();
				        long sizeInBytes = item.getSize();		    	
				        System.out.println("fieldName: " + fieldName + "\nfileName: " + fileName + "\ncontentType:" + contentType + "\nisInMemory: " + isInMemory + "\nsizeInBytes: " + sizeInBytes);
				        if (sizeInBytes > 0)
				        {	
				        	String filename = Long.toString(System.currentTimeMillis()) + "_" + PageUtils.getUser(request).getUserId();
				        	FileOutputStream fos = new FileOutputStream(ChanceConfiguration.getM_imageDirectory() + File.separator + filename + ".jpg");				        	
				        	InputStream is = item.getInputStream();				        	
				        	byte[] buffer = new byte[is.available()];
				        	is.read(buffer);
				        	fos.write(buffer);
				        	fos.flush();
				        	fos.close();	
				        	
				        	//generate thumbnail image (10%)
				        	ImageTools it = new ImageTools();				        	
				        	FileInputStream fisSmall = new FileInputStream(ChanceConfiguration.getM_imageDirectory() + File.separator + filename + ".jpg");
				        	FileOutputStream fosSmall = new FileOutputStream(ChanceConfiguration.getM_imageDirectory() + File.separator + "small_" + filename + ".jpg");
				        	it.jpegManipRestrain(fisSmall, fosSmall);
				        	
				        	um.addPicture(PageUtils.getUser(request), filename);
				        	um.addPicture(PageUtils.getUser(request),"small_" + filename);
				        }
				    }
				    PageUtils.setUser(um.findUserById(PageUtils.getUser(request).getUserId()), request);
				}
			}
			PageUtils.forwardToJspPage("/jsp/edit_user.jsp", request.getSession().getServletContext(), request, response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
