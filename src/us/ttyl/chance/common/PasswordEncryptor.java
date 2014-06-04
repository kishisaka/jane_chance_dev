package us.ttyl.chance.common;

import java.util.*;
import java.io.*;
import java.security.*;

public class PasswordEncryptor
{
	private static String getString( byte[] bytes )
	{
		StringBuffer sb = new StringBuffer();
	    for( int i=0; i<bytes.length; i++ )
	    {
	    	byte b = bytes[ i ];
	    	sb.append( ( int )( 0x00FF & b ) );
	    	if( i+1 <bytes.length )
	    	{
	    		sb.append( "-" );
	    	}
	    }
	    return sb.toString();	  
	}
	
	private static byte[] getBytes( String str )
	{
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    StringTokenizer st = new StringTokenizer( str, "-", false );
	    while( st.hasMoreTokens() )
	    {
	    	int i = Integer.parseInt( st.nextToken() );
	    	bos.write( ( byte )i );
	    }
	    return bos.toByteArray();
	}
	
	public static String md5( String source )
	{
		try
	    {
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			byte[] bytes = md.digest( source.getBytes() );
			return getString( bytes );
	    }
	    catch( Exception e )
	    {
	    	e.printStackTrace();
	    	return null;
	    }	
	}

	public static String sha( String source )
	{
		try
	    {
			MessageDigest md = MessageDigest.getInstance( "SHA" );
			byte[] bytes = md.digest( source.getBytes() );
			return getString( bytes );
	    }
	    catch( Exception e )
	    {
	    	e.printStackTrace();
	    	return null;
	    }
	}

	public static void main( String[] args )
	{
	    if( args.length < 1 )
	    {
	    	System.out.println( "Usage: com.informit.hash.HashUtils word" );
	    	System.exit( 0 );
	    }
	    String word = args[ 0 ];
	    System.out.println( "Word: " + word );
	    System.out.println( " MD5: " + PasswordEncryptor.md5( word ) );
	    System.out.println( " SHA: " + PasswordEncryptor.sha( word ) );
	 }
}
