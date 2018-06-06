/*
 * Created on 06/10/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.general.centrapproval.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;

/**
 * @author citi
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class BaseCentrApprovalListForm extends BaseForm {

	/**
		 * Resultado da Consulta 
		 */
	private DataSet m_results;

	/**
	*  Código do módulo da aprovação 
	*/
	private String m_moduleCodeSrc;

	/**
	* Codigo do usuario que efetuou a ultima atualizacao no registro.
	*/
	private String m_lastUpdUserIdSrc;

	/**
		 *  Descrição do módulo do movimento
		 */
	private String m_moduleProcessText;

	/**
	 *  Lista de domínio dos módulos em movimento
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
	public String getModuleCodeSrc() {
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
	public void setModuleCodeSrc(String m_moduleCodeSrc_) {
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
	public String getModuleProcessText() {
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
