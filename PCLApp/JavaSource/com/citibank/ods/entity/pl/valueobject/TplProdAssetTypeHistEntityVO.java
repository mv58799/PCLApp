/*
 * Created on 13/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author lfabiano
 * @since 13/10/2008
 */
public class TplProdAssetTypeHistEntityVO extends BaseTplProdAssetTypeEntityVO
{
	/**
	 * Comment for <code>m_lastAuthUserId</code> 
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private String m_lastAuthUserId;

	/**
	 * Comment for <code>m_lastAuthDate</code>
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private Date m_lastAuthDate;

	/**
	 * Comment for <code>m_recStatCode</code>
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private String m_recStatCode;
    
	/**
	 * Comment for <code>m_recStatCode</code>
	 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	 */
	private String m_opernCode;
	
	/**
	 * Data de Referencia do registro no historico.
	 */
	private Date m_assetTypeRefDate;
	
	
	public void setOpernCode(String opernCode_)
	{
		m_opernCode = opernCode_;
	}
	
	public String getOpernCode()
	{
		return m_opernCode;
	}

	/**
	 * @return Returns the m_lastAuthDate.
	 */
	public Date getLastAuthDate() {
		return m_lastAuthDate;
	}
	/**
	 * @param authDate The m_lastAuthDate to set.
	 */
	public void setLastAuthDate(Date authDate_) {
		m_lastAuthDate = authDate_;
	}
	/**
	 * @return Returns the m_lastAuthUserId.
	 */
	public String getLastAuthUserId() {
		return m_lastAuthUserId;
	}
	/**
	 * @param authUserId The m_lastAuthUserId to set.
	 */
	public void setLastAuthUserId(String authUserId_) {
		m_lastAuthUserId = authUserId_;
	}
	/**
	 * @return Returns the m_recStatCode.
	 */
	public String getRecStatCode() {
		return m_recStatCode;
	}
	/**
	 * @param statCode The m_recStatCode to set.
	 */
	public void setRecStatCode(String statCode_) {
		m_recStatCode = statCode_;
	}

	/**
	 * @return
	 */
	public Date getAssetTypeRefDate() {
		return m_assetTypeRefDate;
	}

	/**
	 * @param date
	 */
	public void setAssetTypeRefDate(Date m_assetTypeRefDate_) {
		m_assetTypeRefDate = m_assetTypeRefDate_;
	}

}
