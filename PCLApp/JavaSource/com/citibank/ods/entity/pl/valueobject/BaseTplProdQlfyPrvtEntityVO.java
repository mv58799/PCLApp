/*
 * Created on Mar 17, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author fernando.salgado
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseTplProdQlfyPrvtEntityVO
{
	/**
	   * Codigo da Qualificação do Produto
	   * 
	   * @generated "UML to Java
	   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	   */
	private BigInteger m_prodQlfyCode;
	
	/**
	   * Descrição da Qualificação do Produto
	   * 
	   * @generated "UML to Java
	   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	   */
	private String m_prodQlfyText;
	
	/**
	   * Codigo do Usuário da última alteração
	   * 
	   * @generated "UML to Java
	   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	   */
	private String m_lastUpdUserId;
	
	/**
	   * Data da Última Alteração da Qualificação do Produto
	   * 
	   * @generated "UML to Java
	   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	   */
	private Date m_lastUpdDate;
	
	/**
	   * Codigo do Usuário da Autorização Última Atualização da Qualificação do Produto
	   * 
	   * @generated "UML to Java
	   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	   */
	private String m_lastAuthUserId;
	
	/**
	   * Date da Autorização da Última Atualização da Qualificação do Produto
	   * 
	   * @generated "UML to Java
	   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
	   */
	private Date m_lastAuthDate;
	
	/**
	   * Codigo do Status da Qualificação do Produto
	   * 
	   * @generated "UML to Java
	   *            (com.ibm.xtools.transform.uml2.java.internal.UML2JavaTransform)"
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
	 * @return Returns the m_lastUpdDate.
	 */
	public Date getLastUpdDate() {
		return m_lastUpdDate;
	}
	/**
	 * @param updDate The m_lastUpdDate to set.
	 */
	public void setLastUpdDate(Date updDate_) {
		m_lastUpdDate = updDate_;
	}
	/**
	 * @return Returns the m_lastUpdUserId.
	 */
	public String getLastUpdUserId() {
		return m_lastUpdUserId;
	}
	/**
	 * @param updUserId The m_lastUpdUserId to set.
	 */
	public void setLastUpdUserId(String updUserId_) {
		m_lastUpdUserId = updUserId_;
	}
	/**
	 * @return Returns the m_prodQlfyCode.
	 */
	public BigInteger getProdQlfyCode() {
		return m_prodQlfyCode;
	}
	/**
	 * @param qlfyCode The m_prodQlfyCode to set.
	 */
	public void setProdQlfyCode(BigInteger qlfyCode_) {
		m_prodQlfyCode = qlfyCode_;
	}
	/**
	 * @return Returns the m_prodQlfyText.
	 */
	public String getProdQlfyText() {
		return m_prodQlfyText;
	}
	/**
	 * @param qlfyText The m_prodQlfyText to set.
	 */
	public void setProdQlfyText(String qlfyText_) {
		m_prodQlfyText = qlfyText_;
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
