package us.ttyl.chance.common;

import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import us.ttyl.chance.dao.*;

public class UserAttributes 
{
	private static final Log m_logger = LogFactory.getLog(UserAttributes.class);
	
	private Hashtable m_bodyType = null;
	private Hashtable m_choice = null;
	private Hashtable m_color = null;
	private Hashtable m_ethnicity = null;
	private Hashtable m_hasChildren = null;
	private Hashtable m_lookingFor = null;
	private Hashtable m_maritalStatus = null;
	private Hashtable m_sex = null;
	private Hashtable m_wantsChildren = null;
	
	public void reinit()
	{
		m_logger.info("reinit called");
		m_bodyType = new BodytypeDAO().getAll();
		m_choice = new ChoiceDAO().getAll();
		m_color = new ColorDAO().getAll();
		m_ethnicity = new EthnicityDAO().getAll();
		m_hasChildren = new HasChildrenDAO().getAll();
		m_lookingFor = new LookingForDAO().getAll();
		m_maritalStatus = new MaritalStatusDAO().getAll();
		m_sex = new SexDAO().getAll();
		m_wantsChildren = new WantsChildrenDAO().getAll();
	}

	public Hashtable getM_bodyType() {
		return m_bodyType;
	}

	public Hashtable getM_choice() {
		return m_choice;
	}

	public Hashtable getM_color() {
		return m_color;
	}

	public Hashtable getM_ethnicity() {
		return m_ethnicity;
	}

	public Hashtable getM_hasChildren() {
		return m_hasChildren;
	}

	public Hashtable getM_lookingFor() {
		return m_lookingFor;
	}

	public Hashtable getM_maritalStatus() {
		return m_maritalStatus;
	}

	public Hashtable getM_sex() {
		return m_sex;
	}

	public Hashtable getM_wantsChildren() {
		return m_wantsChildren;
	}
}
