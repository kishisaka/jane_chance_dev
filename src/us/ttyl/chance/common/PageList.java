package us.ttyl.chance.common;

import java.util.*;

public class PageList 
{
	private boolean m_hasNext = false;
	private boolean m_hasPrevious = false;
	private List m_data = null;
	private int m_maxSize = 0;

	public PageList(List data, boolean next, boolean previous, int maxSize)	
	{
		m_hasNext = next;
		m_hasPrevious = previous;
		m_data = data;
		m_maxSize = maxSize;
	}
	
	public List getM_data() 
	{
		return m_data;
	}
	
	public boolean isM_hasNext() 
	{
		return m_hasNext;
	}
	
	public boolean isM_hasPrevious() 
	{
		return m_hasPrevious;
	}

	public int getM_maxSize() 
	{
		return m_maxSize;
	}
	
}
