/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author fernando.salgado
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * @generated "UML to Java (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
 */
public class TplProdQlfyPrvtEntityVO extends BaseTplProdQlfyPrvtEntityVO
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
}