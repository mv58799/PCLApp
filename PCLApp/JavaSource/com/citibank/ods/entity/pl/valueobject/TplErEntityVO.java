/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class TplErEntityVO extends BaseTplErEntityVO
{
	/**
	 * Comment for <code>m_lastAuthDate</code>
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private Date m_lastAuthDate;

	/**
	 * Comment for <code>m_lastAuthUserId</code>
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private String m_lastAuthUserId;
	
	protected BaseTplErEntityVO m_data;
    
    
	/**
	 * @return Returns the lastAuthDate.
	 */
	public Date getLastAuthDate()
	{
	  return m_lastAuthDate;
	}
	/**
	 * @param lastAuthDate_ The lastAuthDate to set.
	 */
	public void setLastAuthDate( Date lastAuthDate_ )
	{
	  m_lastAuthDate = lastAuthDate_;
	}
	/**
	 * @return Returns the lastAuthUserId.
	 */
	public String getLastAuthUserId()
	{
	  return m_lastAuthUserId;
	}
	/**
	 * @param lastAuthUserId_ The lastAuthUserId to set.
	 */
	public void setLastAuthUserId( String lastAuthUserId_ )
	{
	  m_lastAuthUserId = lastAuthUserId_;
	}
	
	public BaseTplErEntityVO getData()
	{
	  return m_data;
	}
	

}