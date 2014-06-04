package us.ttyl.chance.common;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageTools 
{
	private int m_imageSizeX;
	private int m_imageSizeY;
	
	private double m_imagePercentX = 0;
	private double m_imagePercentY = 0;
	
	private int m_canvasSizeX = 200;
	private int m_canvasSizeY = 200;
	
	public ImageTools(InputStream inFs, OutputStream outFs)
	{	
		JPEGManip(inFs, outFs);	
	}
	
	public ImageTools()
	{	
	}
	
	public ImageTools(String inFile, String outFile)
	{
		try
		{
			long startTime = System.currentTimeMillis();
			System.out.println("processing picture "+ inFile +" to "+ outFile);
			FileInputStream inFs = new FileInputStream(inFile);
			FileOutputStream outFs = new FileOutputStream(outFile);
			JPEGManip(inFs, outFs);
			System.out.println("done in " + (System.currentTimeMillis() - startTime) + " ms.");
		}
		catch (Exception e)
		{
			e.printStackTrace();	
		}
	}
	
	public ImageTools(String inFile, String outFile, boolean restrainXY)
	{
		try
		{
			long startTime = System.currentTimeMillis();
			System.out.println("processing picture "+ inFile +" to "+ outFile);
			FileInputStream inFs = new FileInputStream(inFile);
			FileOutputStream outFs = new FileOutputStream(outFile);
			jpegManipRestrain(inFs, outFs);
			System.out.println("done in " + (System.currentTimeMillis() - startTime) + " ms.");
		}
		catch (Exception e)
		{
			e.printStackTrace();	
		}
	}
	
	public static void main(String args[])
	{
		new ImageTools(args[0], args[1]);
	}
	
	public void JPEGManip(InputStream inFs, OutputStream outFs)
	{
		try
		{
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(inFs);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outFs);			
			
			//decode jpeg from stream into a buffered image
			BufferedImage bi = decoder.decodeAsBufferedImage();
			m_imageSizeX = bi.getWidth();
			m_imageSizeY = bi.getHeight();	
			if (reSize() == true)
			{
				Graphics2D g = bi.createGraphics();
					
				//create the transform
				AffineTransform at = new AffineTransform();			
				at.scale(m_imagePercentX, m_imagePercentY);
	
				//apply the transform
				g.drawImage(bi, at, null);
				
				//draw transformed image to orignial buffer and cut resized image from full size buffer. 
				encoder.encode(bi.getSubimage(0, 0, (int)(m_imageSizeX * m_imagePercentX), (int)(m_imageSizeY * m_imagePercentY)));			
			}
			else
			{				
				//no resize necessary, write directly back to stream
				encoder.encode(bi);
				outFs.flush();
				outFs.close();	
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void jpegManipRestrain(InputStream inFs, OutputStream outFs)
	{
		int maxSizeX = 150;
		int maxSizeY = 100;
		
		double scalingRatio = 0;
		
		try
		{
			Image scancard = null;
		
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(inFs);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outFs);			
					
			//decode jpeg from stream into a buffered image
			BufferedImage bi = decoder.decodeAsBufferedImage();
			m_imageSizeX = bi.getWidth();
			m_imageSizeY = bi.getHeight();
			
			
			//find scaling ratio
			if (((double)maxSizeX/(double)m_imageSizeX) < ((double)maxSizeY/(double)m_imageSizeY))
			{
				scalingRatio = (double)maxSizeX/(double)m_imageSizeX;
			}
			else
			{
				scalingRatio = (double)maxSizeY/(double)m_imageSizeY;
			}
			
			//set scaling 
			m_imagePercentX	= scalingRatio;
			m_imagePercentY = scalingRatio;			
			
			Graphics2D g = bi.createGraphics();
				
			//create the transform
			AffineTransform at = new AffineTransform();			
			at.scale(m_imagePercentX, m_imagePercentY);

			//apply the transform
			g.drawImage(bi, at, null);
		
			//draw transformed image to orignial buffer and cut resized image from full size buffer. 
			encoder.encode(bi.getSubimage(0, 0, (int)(m_imageSizeX * m_imagePercentX), (int)(m_imageSizeY * m_imagePercentY)));						
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private boolean reSize()
	{
		m_imagePercentX = (double)m_canvasSizeX / (double)m_imageSizeX;
		m_imagePercentY = (double)m_canvasSizeY / (double)m_imageSizeY;
		if (m_imagePercentX >= 1 || m_imagePercentY >= 1)
		{
			return false;			
		}
		else
		{
			return true;
		}
	}
}
