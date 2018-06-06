/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.general.centrapproval.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author Ronaldo Machado (G&P Java Team) 
 */
public class BaseCentrApprovalListFncVO extends BaseODSFncVO {	
	
	/**
	 * Resultado da Consulta 
	 */
	private DataSet m_results;	
	
	/**
	*  C�digo do m�dulo da aprova��o 
	*/
	private BigInteger m_moduleCodeSrc;
	
	/**
	* Codigo do usuario que efetuou a ultima atualizacao no registro.
	*/
	private String m_lastUpdUserIdSrc;
	
	/**
	 *  Descri��o do m�dulo do movimento
	 */
	private String m_moduleProcessText;	
	
	/**
	 *  Lista de dom�nio dos m�dulos em movimento
	 */
	private DataSet m_moduleProcessDomain;

	/**
	 * @return
	 */
	public String getLastUpdUserIdSrc() {
		return m_lastUpdUserIdSrc;
	}

	/**
	 * @return
	 */
	public BigInteger getModuleCodeSrc() {
		return m_moduleCodeSrc;
	}

	/**
	 * @return
	 */
	public DataSet getResults() {
		return m_results;
	}

	/**
	 * @param string
	 */
	public void setLastUpdUserIdSrc(String m_lastUpdUserIdSrc_) {
		m_lastUpdUserIdSrc = m_lastUpdUserIdSrc_;
	}

	/**
	 * @param integer
	 */
	public void setModuleCodeSrc(BigInteger m_moduleCodeSrc_) {
		m_moduleCodeSrc = m_moduleCodeSrc_;
	}

	/**
	 * @param set
	 */
	public void setResults(DataSet m_results_) {
		m_results = m_results_;
	}

	/**
	 * @return
	 */
	public DataSet getModuleProcessDomain() {
		return m_moduleProcessDomain;
	}

	/**
	 * @return
	 */
	public String getMduleProcessText() {
		return m_moduleProcessText;
	}

	/**
	 * @param set
	 */
	public void setModuleProcessDomain(DataSet m_moduleProcessDomain_) {
		m_moduleProcessDomain = m_moduleProcessDomain_;
	}

	/**
	 * @param string
	 */
	public void setModuleProcessText(String m_moduleProcessText_) {
		m_moduleProcessText = m_moduleProcessText_;
	}

}
