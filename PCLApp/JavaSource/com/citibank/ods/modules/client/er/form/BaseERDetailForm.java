/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.modules.client.erem.form.EREMDetailable;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class BaseERDetailForm extends BaseForm implements EREMDetailable
{
	/** Identificador do grupo de relacionamento sob o qual varios 
	* EMs estao agrupados. (Entitlement Relationship)
	*/ 
	private String m_erNbr = "";
	
	/**
	 * Idenfica se houve transferencia do relacionamento do consumer para o 
	 * Private. S = relacionamento foi transferido do consumer N = nao houve transferencia
	 */
	private String erReltnTrfInd;
	
	/**
	*  Domínio - Idenfica se houve transferencia do relacionamento do consumer para o 
	* Private. S = relacionamento foi transferido do consumer N = nao houve transferencia
	*/
	private DataSet erReltnTrfIndDomain;
	
	/**Codigo do motivo de encerramento de relacionamento.
	 * 
	 */
	private String reltnEndReasCode;
	
	/**Domínio - Codigo do motivo de encerramento de relacionamento.
	* 
	*/
	private DataSet reltnEndReasCodeDomain;
	
	/**Descricao do motivo de encerramento de relacionamento, somente preenchido quando o codigo acima for do tipo "Outros".
	*/
	private String reltnEndReasText;
	
	/**Codigo da classificacao da estimativa de patrimonio do relacionamento.
	 * 
	 */
	private String equityClassCode;
	
	/**Dmonínio - Codigo da classificacao da estimativa de patrimonio do relacionamento.
	 * 
	 */
	private DataSet equityClassCodeDomain;
	
	/**Status Registro - Identifica se o registro esta ativo, inativo ou aguardando aprovacao
	 * 
	 */
    private String recStatCode = "";
    
    /**Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
    * 
    */
    private String lastUpdDate = "";

   /**Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
    * 
    */
    private String lastUpdUserId = "";	

	/**Data e hora que o usuario aprovou o registro cadastrado.
	* 
	*/
	private String lastAuthDate = "";

   /**Codigo do usuario (soeid) que aprovou o cadastro do registro.
	* 
	*/
	private String lastAuthUserId = "";	
	
	/**
	 * @return
	 */
	public String getEquityClassCode() {
		return equityClassCode;
	}

	/**
	 * @return
	 */
	public DataSet getEquityClassCodeDomain() {
		return equityClassCodeDomain;
	}

	/**
	 * @return
	 */
	public String getErNbr() {
		return m_erNbr;
	}

	/**
	 * @return
	 */
	public String getErReltnTrfInd() {
		return erReltnTrfInd;
	}

	/**
	 * @return
	 */
	public DataSet getErReltnTrfIndDomain() {
		return erReltnTrfIndDomain;
	}

	/**
	 * @return
	 */
	public String getLastUpdDate() {
		return lastUpdDate;
	}

/**
 * @return
 */
    public String getLastUpdUserId() {
		return lastUpdUserId;
	}

	/**
	 * @return
	 */
	public String getRecStatCode() {
		return recStatCode;
	}

	/**
	 * @return
	 */
	public String getReltnEndReasCode() {
		return reltnEndReasCode;
	}

	/**
	 * @return
	 */
	public DataSet getReltnEndReasCodeDomain() {
		return reltnEndReasCodeDomain;
	}

	/**
	 * @return
	 */
	public String getReltnEndReasText() {
		return reltnEndReasText;
	}

	/**
	 * @param string
	 */
	public void setEquityClassCode(String equityClassCode) {
		this.equityClassCode = equityClassCode;
	}

	/**
	 * @param string
	 */
	public void setEquityClassCodeDomain(DataSet equityClassCodeDomain) {
		this.equityClassCodeDomain = equityClassCodeDomain;
	}

	/**
	 * @param string
	 */
	public void setErNbr(String erNbr) {
		this.m_erNbr = erNbr;
	}

	/**
	 * @param string
	 */
	public void setErReltnTrfInd(String erReltnTrfInd) {
		this.erReltnTrfInd = erReltnTrfInd;
	}

	/**
	 * @param set
	 */
	public void setErReltnTrfIndDomain(DataSet erReltnTrfIndDomain) {
		this.erReltnTrfIndDomain = erReltnTrfIndDomain;
	}

	/**
	 * @param string
	 */
	public void setLastUpdDate(String lastUpdDate) {
		this.lastUpdDate = lastUpdDate;
	}

	/**
 	* @param string
 	*/
	public void setLastUpdUserId(String lastUpdUserId) {
		this.lastUpdUserId = lastUpdUserId;
	}

	/**
	 * @param string
	 */
	public void setRecStatCode(String string) {
		recStatCode = string;
	}

	/**
	 * @param string
	 */
	public void setReltnEndReasCode(String string) {
		reltnEndReasCode = string;
	}

	/**
	 * @param string
	 */
	public void setReltnEndReasCodeDomain(DataSet string) {
		reltnEndReasCodeDomain = string;
	}

	/**
	 * @param string
	 */
	public void setReltnEndReasText(String string) {
		reltnEndReasText = string;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.client.erem.form.EREMDetailable#getSelectedERNbr()
	 */
	public String getSelectedERNbr() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.client.erem.form.EREMDetailable#setSelectedERNbr(java.lang.String)
	 */
	public void setSelectedERNbr( String ERNbr_ )
    {
		this.setErNbr( ERNbr_ );
    }


	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.client.erem.form.EREMDetailable#getSelectedEMNbr()
	 */
	public String getSelectedEMNbr() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.citibank.ods.modules.client.erem.form.EREMDetailable#setSelectedEMNbr(java.lang.String)
	 */
	public void setSelectedEMNbr(String emNbr_) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @return
	 */
	public String getLastAuthDate() {
		return lastAuthDate;
	}
	
	/**
	 * @return
	 */
	public String getLastAuthUserId() {
		return lastAuthUserId;
	}
	
		/**
		 * @param string
		 */
		public void setLastAuthDate(String lastAuthDate_) {
			lastAuthDate = lastAuthDate_;
		}
	
	/**
	 * @param string
	 */
	public void setLastAuthUserId(String lastAuthUserId_) {
		lastAuthUserId = lastAuthUserId_;
	}
	
    //Realiza as validações do form
	public ActionErrors validate( ActionMapping actionMapping_,
								  HttpServletRequest request_ )
	{
	  ActionErrors errors = new ActionErrors();
		
	  ODSValidator.validateMaxLength("Motivo Outros",reltnEndReasText,100,errors );		
	  return errors;		
	}

}
