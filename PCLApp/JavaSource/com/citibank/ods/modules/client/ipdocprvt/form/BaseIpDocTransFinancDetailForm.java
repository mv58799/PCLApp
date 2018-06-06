/*
 * Created on 14/11/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.client.ipdocprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.modules.client.contactcust.form.ContactCustSearchable;
import com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable;

/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class BaseIpDocTransFinancDetailForm extends BaseForm {

	private String tpOperacao;
	
	private String m_ipDocCode;
	
	private String m_trfAcctType;

	private String m_docTransferCode = "";

	private String m_ipInvstCurAcctInd;

	private DataSet m_ipInvstCurAcctIndDomain;
	
	private String m_beneMainDestAcctInd;
	
	private String m_beneAcctTypeCode;

	//Nome Completo do Cliente/Empresa.

	private String m_custFullNameText = "";

	//Numero do Cliente no CMS (Customer Number)

	private String m_custCurAcctNbr = "";

	//Número do Documento IP

	private String m_invstCurAcctNbr = "";

	//Codigo do Banco

	private String m_bankCode = "";

	//Codigo da Agencia Bancaria

	private String m_brchCode = "";

	//Número da Conta Corrente do Beneficiario.

	private String m_beneCurAcctNbr = "";

	//Número do Cpf/Cnpj do Beneficiario.

	private String m_beneCpfCnpjNbr = "";

	//Nome do Beneficiario.

	private String m_beneNameText = "";

	//Valor da Transferencia.

	private String m_trfAmtNbr = "";

	//Canal de Atendimento.

	private String m_chnnlAttdText = "";

	//Data da Transferencia.

	private String m_trfDate = "";

	//Hora da Transferencia.

	private String m_trfHour = "";

	//Codigo de Area do Telefone.

	private String m_phoneDddCode = "";

	//Numero do Telefone.

	private String m_phoneNbr = "";

	//Numero do Ramal Telefonico.

	private String m_phoneExtNbr = "";

	//Nome Completo do Cliente/ Empresa.

	private String m_fullNameText = "";

	//Nome Completo do Cliente/ empresa segundo contato.

	private String m_fullName1Text = "";

	//Nome Completo do Cliente/ empresa terceiro contato.

	private String m_fullName2Text = "";

	private DataSet m_ownDestAcctIndDomain;

	private DataSet m_docTransferResults = null;

	private String[] m_domainsFullNameText;

	private String[] m_domainsPhone;

	private String[] m_domainsDDDPhone;

	private String[] m_domainsRamalPhone;

	private String[] m_domainsCtcNbr;

	private String[] m_domainsFullNameText_2;

	private String[] m_domainsFullNameText_3;

	private String[] m_domainsDocTransferCode;

	private String[] m_domainsAgnBankCode;

	private String[] m_domainsBrchCode;

	private String[] m_domainsAgnBankName;

	private String[] m_domainsBrchName;

	private String[] m_domainsCurAcctCode;

	private String[] m_domainsTxnTypeCode;

	private String[] m_domainsOwnDestAcctInd;

	private String[] m_domainDocTransferNbr;

	private String[] m_domainsBeneCpfCnpjNbr;

	private String[] m_domainsBeneNameText;

	private String[] m_domainsBeneMainDestAcctInd;

	private String[] m_domainsBeneAcctTypeCode;

	// Lista com os ítens a inseridos na base
	private String[] m_selectedCallBackInGrid = null;

	// Lista com os ítens a inseridos na base
	private String[] m_selectedDocTransferInGrid = null;

	// Lista com os ítens excluídos
	private String[] m_deletedCallBack = null;

	// Lista com os ítens excluídos
	private String[] m_deletedDocTransfer = null;

	/**
	 * @return
	 */
	public String getBankCode() {
		return m_bankCode;
	}

	/**
	 * @return
	 */
	public String getBeneCpfCnpjNbr() {
		return m_beneCpfCnpjNbr;
	}

	/**
	 * @return
	 */
	public String getBeneCurAcctNbr() {
		return m_beneCurAcctNbr;
	}

	/**
	 * @return
	 */
	public String getBeneNameText() {
		return m_beneNameText;
	}

	/**
	 * @return
	 */
	public String getBrchCode() {
		return m_brchCode;
	}

	/**
	 * @return
	 */
	public String getChnnlAttdText() {
		return m_chnnlAttdText;
	}

	/**
	 * @return
	 */
	public String getCustCurAcctNbr() {
		return m_custCurAcctNbr;
	}

	/**
	 * @return
	 */
	public String getCustFullNameText() {
		return m_custFullNameText;
	}

	/**
	 * @return
	 */
	public String[] getDeletedCallBack() {
		return m_deletedCallBack;
	}

	/**
	 * @return
	 */
	public String[] getDeletedDocTransfer() {
		return m_deletedDocTransfer;
	}

	/**
	 * @return
	 */
	public String getDocTransferCode() {
		return m_docTransferCode;
	}

	/**
	 * @return
	 */
	public DataSet getDocTransferResults() {
		return m_docTransferResults;
	}

	/**
	 * @return
	 */
	public String[] getDomainDocTransferNbr() {
		return m_domainDocTransferNbr;
	}

	/**
	 * @return
	 */
	public String[] getDomainsAgnBankCode() {
		return m_domainsAgnBankCode;
	}

	/**
	 * @return
	 */
	public String[] getDomainsAgnBankName() {
		return m_domainsAgnBankName;
	}

	/**
	 * @return
	 */
	public String[] getDomainsBeneAcctTypeCode() {
		return m_domainsBeneAcctTypeCode;
	}

	/**
	 * @return
	 */
	public String[] getDomainsBeneCpfCnpjNbr() {
		return m_domainsBeneCpfCnpjNbr;
	}

	/**
	 * @return
	 */
	public String[] getDomainsBeneMainDestAcctInd() {
		return m_domainsBeneMainDestAcctInd;
	}

	/**
	 * @return
	 */
	public String[] getDomainsBeneNameText() {
		return m_domainsBeneNameText;
	}

	/**
	 * @return
	 */
	public String[] getDomainsBrchCode() {
		return m_domainsBrchCode;
	}

	/**
	 * @return
	 */
	public String[] getDomainsBrchName() {
		return m_domainsBrchName;
	}

	/**
	 * @return
	 */
	public String[] getDomainsCtcNbr() {
		return m_domainsCtcNbr;
	}

	/**
	 * @return
	 */
	public String[] getDomainsCurAcctCode() {
		return m_domainsCurAcctCode;
	}

	/**
	 * @return
	 */
	public String[] getDomainsDDDPhone() {
		return m_domainsDDDPhone;
	}

	/**
	 * @return
	 */
	public String[] getDomainsDocTransferCode() {
		return m_domainsDocTransferCode;
	}

	/**
	 * @return
	 */
	public String[] getDomainsFullNameText() {
		return m_domainsFullNameText;
	}

	/**
	 * @return
	 */
	public String[] getDomainsFullNameText_2() {
		return m_domainsFullNameText_2;
	}

	/**
	 * @return
	 */
	public String[] getDomainsFullNameText_3() {
		return m_domainsFullNameText_3;
	}

	/**
	 * @return
	 */
	public String[] getDomainsOwnDestAcctInd() {
		return m_domainsOwnDestAcctInd;
	}

	/**
	 * @return
	 */
	public String[] getDomainsPhone() {
		return m_domainsPhone;
	}

	/**
	 * @return
	 */
	public String[] getDomainsRamalPhone() {
		return m_domainsRamalPhone;
	}

	/**
	 * @return
	 */
	public String[] getDomainsTxnTypeCode() {
		return m_domainsTxnTypeCode;
	}

	/**
	 * @return
	 */
	public String getFullName1Text() {
		return m_fullName1Text;
	}

	/**
	 * @return
	 */
	public String getFullName2Text() {
		return m_fullName2Text;
	}

	/**
	 * @return
	 */
	public String getFullNameText() {
		return m_fullNameText;
	}

	/**
	 * @return
	 */
	public String getInvstCurAcctNbr() {
		return m_invstCurAcctNbr;
	}

	/**
	 * @return
	 */
	public String getIpDocCode() {
		return m_ipDocCode;
	}

	/**
	 * @return
	 */
	public String getIpInvstCurAcctInd() {
		return m_ipInvstCurAcctInd;
	}

	/**
	 * @return
	 */
	public DataSet getIpInvstCurAcctIndDomain() {
		return m_ipInvstCurAcctIndDomain;
	}

	/**
	 * @return
	 */
	public DataSet getOwnDestAcctIndDomain() {
		return m_ownDestAcctIndDomain;
	}

	/**
	 * @return
	 */
	public String getPhoneDddCode() {
		return m_phoneDddCode;
	}

	/**
	 * @return
	 */
	public String getPhoneExtNbr() {
		return m_phoneExtNbr;
	}

	/**
	 * @return
	 */
	public String getPhoneNbr() {
		return m_phoneNbr;
	}

	/**
	 * @return
	 */
	public String[] getSelectedCallBackInGrid() {
		return m_selectedCallBackInGrid;
	}

	/**
	 * @return
	 */
	public String[] getSelectedDocTransferInGrid() {
		return m_selectedDocTransferInGrid;
	}

	/**
	 * @return
	 */
	public String getTrfAmtNbr() {
		return m_trfAmtNbr;
	}

	/**
	 * @return
	 */
	public String getTrfDate() {
		return m_trfDate;
	}

	/**
	 * @return
	 */
	public String getTrfHour() {
		return m_trfHour;
	}

	/**
	 * @param string
	 */
	public void setBankCode(String m_bankCode_) {
		m_bankCode = m_bankCode_;
	}

	/**
	 * @param string
	 */
	public void setBeneCpfCnpjNbr(String m_beneCpfCnpjNbr_) {
		m_beneCpfCnpjNbr = m_beneCpfCnpjNbr_;
	}

	/**
	 * @param string
	 */
	public void setBeneCurAcctNbr(String m_beneCurAcctNbr_) {
		m_beneCurAcctNbr = m_beneCurAcctNbr_;
	}

	/**
	 * @param string
	 */
	public void setBeneNameText(String m_beneNameText_) {
		m_beneNameText = m_beneNameText_;
	}

	/**
	 * @param string
	 */
	public void setBrchCode(String m_brchCode_) {
		m_brchCode = m_brchCode_;
	}

	/**
	 * @param string
	 */
	public void setChnnlAttdText(String m_chnnlAttdText_) {
		m_chnnlAttdText = m_chnnlAttdText_;
	}

	/**
	 * @param string
	 */
	public void setCustCurAcctNbr(String m_custCurAcctNbr_) {
		m_custCurAcctNbr = m_custCurAcctNbr_;
	}

	/**
	 * @param string
	 */
	public void setCustFullNameText(String string) {
		m_custFullNameText = string;
	}

	/**
	 * @param strings
	 */
	public void setDeletedCallBack(String[] m_deletedCallBack_) {
		m_deletedCallBack = m_deletedCallBack_;
	}

	/**
	 * @param strings
	 */
	public void setDeletedDocTransfer(String[] m_deletedDocTransfer_) {
		m_deletedDocTransfer = m_deletedDocTransfer_;
	}

	/**
	 * @param string
	 */
	public void setDocTransferCode(String m_docTransferCode_) {
		m_docTransferCode = m_docTransferCode_;
	}

	/**
	 * @param set
	 */
	public void setDocTransferResults(DataSet m_docTransferResults_) {
		m_docTransferResults = m_docTransferResults_;
	}

	/**
	 * @param strings
	 */
	public void setDomainDocTransferNbr(String[] m_domainDocTransferNbr_) {
		m_domainDocTransferNbr = m_domainDocTransferNbr_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsAgnBankCode(String[] m_domainsAgnBankCode_) {
		m_domainsAgnBankCode = m_domainsAgnBankCode_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsAgnBankName(String[] m_domainsAgnBankName_) {
		m_domainsAgnBankName = m_domainsAgnBankName_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsBeneAcctTypeCode(String[] m_domainsBeneAcctTypeCode_) {
		m_domainsBeneAcctTypeCode = m_domainsBeneAcctTypeCode_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsBeneCpfCnpjNbr(String[] m_domainsBeneCpfCnpjNbr_) {
		m_domainsBeneCpfCnpjNbr = m_domainsBeneCpfCnpjNbr_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsBeneMainDestAcctInd(String[] m_domainsBeneMainDestAcctInd_) {
		m_domainsBeneMainDestAcctInd = m_domainsBeneMainDestAcctInd_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsBeneNameText(String[] m_domainsBeneNameText_) {
		m_domainsBeneNameText = m_domainsBeneNameText_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsBrchCode(String[] m_domainsBrchCode_) {
		m_domainsBrchCode = m_domainsBrchCode_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsBrchName(String[] m_domainsBrchName_) {
		m_domainsBrchName = m_domainsBrchName_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsCtcNbr(String[] m_domainsCtcNbr_) {
		m_domainsCtcNbr = m_domainsCtcNbr_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsCurAcctCode(String[] m_domainsCurAcctCode_) {
		m_domainsCurAcctCode = m_domainsCurAcctCode_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsDDDPhone(String[] m_domainsDDDPhone_) {
		m_domainsDDDPhone = m_domainsDDDPhone_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsDocTransferCode(String[] m_domainsDocTransferCode_) {
		m_domainsDocTransferCode = m_domainsDocTransferCode_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsFullNameText(String[] m_domainsFullNameText_) {
		m_domainsFullNameText = m_domainsFullNameText_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsFullNameText_2(String[] domainsFullNameText_2_) {
		m_domainsFullNameText_2 = domainsFullNameText_2_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsFullNameText_3(String[] domainsFullNameText_3_) {
		m_domainsFullNameText_3 = domainsFullNameText_3_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsOwnDestAcctInd(String[] domainsOwnDestAcctInd_) {
		m_domainsOwnDestAcctInd = domainsOwnDestAcctInd_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsPhone(String[] domainsPhone_) {
		m_domainsPhone = domainsPhone_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsRamalPhone(String[] domainsRamalPhone_) {
		m_domainsRamalPhone = domainsRamalPhone_;
	}

	/**
	 * @param strings
	 */
	public void setDomainsTxnTypeCode(String[] domainsTxnTypeCode_) {
		m_domainsTxnTypeCode = domainsTxnTypeCode_;
	}

	/**
	 * @param string
	 */
	public void setFullName1Text(String fullName1Text_) {
		m_fullName1Text = fullName1Text_;
	}

	/**
	 * @param string
	 */
	public void setFullName2Text(String fullName2Text_) {
		m_fullName2Text = fullName2Text_;
	}

	/**
	 * @param string
	 */
	public void setFullNameText(String fullNameText_) {
		m_fullNameText = fullNameText_;
	}

	/**
	 * @param string
	 */
	public void setInvstCurAcctNbr(String invstCurAcctNbr_) {
		m_invstCurAcctNbr = invstCurAcctNbr_;
	}

	/**
	 * @param string
	 */
	public void setIpDocCode(String ipDocCode_) {
		m_ipDocCode = ipDocCode_;
	}

	/**
	 * @param string
	 */
	public void setIpInvstCurAcctInd(String ipInvstCurAcctInd_) {
		m_ipInvstCurAcctInd = ipInvstCurAcctInd_;
	}

	/**
	 * @param set
	 */
	public void setIpInvstCurAcctIndDomain(DataSet ipInvstCurAcctIndDomain_) {
		m_ipInvstCurAcctIndDomain = ipInvstCurAcctIndDomain_;
	}

	/**
	 * @param set
	 */
	public void setOwnDestAcctIndDomain(DataSet ownDestAcctIndDomain_) {
		m_ownDestAcctIndDomain = ownDestAcctIndDomain_;
	}

	/**
	 * @param string
	 */
	public void setPhoneDddCode(String phoneDddCode_) {
		m_phoneDddCode = phoneDddCode_;
	}

	/**
	 * @param string
	 */
	public void setPhoneExtNbr(String phoneExtNbr_) {
		m_phoneExtNbr = phoneExtNbr_;
	}

	/**
	 * @param string
	 */
	public void setPhoneNbr(String phoneNbr_) {
		m_phoneNbr = phoneNbr_;
	}

	/**
	 * @param strings
	 */
	public void setSelectedCallBackInGrid(String[] selectedCallBackInGrid_) {
		m_selectedCallBackInGrid = selectedCallBackInGrid_;
	}

	/**
	 * @param strings
	 */
	public void setSelectedDocTransferInGrid(String[] selectedDocTransferInGrid_) {
		m_selectedDocTransferInGrid = selectedDocTransferInGrid_;
	}

	/**
	 * @param string
	 */
	public void setTrfAmtNbr(String trfAmtNbr_) {
		m_trfAmtNbr = trfAmtNbr_;
	}

	/**
	 * @param string
	 */
	public void setTrfDate(String trfDate_) {
		m_trfDate = trfDate_;
	}

	/**
	 * @param string
	 */
	public void setTrfHour(String trfHour_) {
		m_trfHour = trfHour_;
	}
	
	//Realiza as validações do form
	public ActionErrors validate( ActionMapping actionMapping_,
								   HttpServletRequest request_ )
	{
		ActionErrors errors = new ActionErrors();
		
		ODSValidator.validateDateHour("Data/Hora", m_trfDate,errors);
		ODSValidator.validateBigDecimal("Valor",m_trfAmtNbr.replace(",", "." ),8,3,errors);
		
		return errors;		
	}	


	/**
	 * @return
	 */
	public String getTrfAcctType() {
		return m_trfAcctType;
	}

	/**
	 * @param string
	 */
	public void setTrfAcctType(String m_trfAcctType_) {
		m_trfAcctType = m_trfAcctType_;
	}

	/**
	 * @return
	 */
	public String getBeneMainDestAcctInd() {
		return m_beneMainDestAcctInd;
	}

	/**
	 * @param string
	 */
	public void setBeneMainDestAcctInd(String m_beneMainDestAcctInd_) {
		m_beneMainDestAcctInd = m_beneMainDestAcctInd_;
	}

	/**
	 * @return
	 */
	public String getBeneAcctTypeCode() {
		return m_beneAcctTypeCode;
	}

	/**
	 * @param string
	 */
	public void setBeneAcctTypeCode(String m_beneAcctTypeCode_) {
		m_beneAcctTypeCode = m_beneAcctTypeCode_;
	}

	/**
	 * @return
	 */
	public String getTpOperacao() {
		return tpOperacao;
	}

	/**
	 * @param string
	 */
	public void setTpOperacao(String tpOperacao_) {
		tpOperacao = tpOperacao_;
	}

}
