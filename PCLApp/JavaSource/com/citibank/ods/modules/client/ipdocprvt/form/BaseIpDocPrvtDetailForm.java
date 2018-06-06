package com.citibank.ods.modules.client.ipdocprvt.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.form.BaseForm;
import com.citibank.ods.common.util.ODSValidator;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.BaseTplDocTransferEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.modules.client.contactcust.form.ContactCustSearchable;
import com.citibank.ods.modules.client.contactcust.functionality.valueobject.BaseContactCustDetailFncVO;
import com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable;
import com.citibank.ods.modules.client.doctransfer.functionality.valueobject.BaseDocTransferDetailFncVO;
import com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject.BaseIpDocPrvtDetailFncVO;

/**
 * @author l.braga
 *  
 */

public class BaseIpDocPrvtDetailForm extends BaseForm implements
	IpDocPrvtDetailable, CustomerSearchable, ContactCustSearchable	
{

  private String C_INSERT_IP_DOC_PRVT = "insertIpDocPrvt";

  private String C_INSERT_IP_DOC_CALLBACK = "insertCallback";

  private String C_INSERT_DOC_TRANSFER = "insertDocTransfer";
  
  private String m_custCurAcctNbrSrc;
  
  private String m_invstCurAcctNbrSrc;

  private String m_actionPerformed;

  private String m_docTransferCode = "";

  private DataSet m_txnTypeCodeDomain = null;

  private String m_ctcNameText;

  private String m_ipInvstCurAcctInd;

  private DataSet m_ipInvstCurAcctIndDomain;
  
  private String m_beneMainDestAcctInd;
  
  private DataSet m_beneMainDestAcctIndDomain;
  
  private String m_beneAcctTypeCode;
  
  private DataSet m_beneAcctTypeCodeIndDomain;

  //Numero do Cliente no CMS (Customer Number)
  private String m_custNbrSrc = "";

  //Nome do Cliente no CMS (Customer Number)
  private String m_custFullNameText = "";

  // Codigo do Documento IP
  private String m_ipDocCode = "";

  // Descricao do Instrucao Permanente
  private String m_ipDocText = "";

  private String m_agnBankCode = "";

  private String m_brchCode = "";

  private String m_curAcctNbr = "";

  private String m_txnTypeCode = "";

  private String m_ownDestAcctInd = "";

  private DataSet m_ownDestAcctIndDomain;

  private DataSet m_docTransferResults = null;

  private String m_fullNameText = "";
  
  private String m_fullNameText_2 = "";
  
  private String m_fullNameText_3 = "";
  
  private String phoneDddCode = "";
  
  private String phoneNbr = "";
  
  private String phoneExtnNbr = "";

  private String m_ctcNbr = "";

  private DataSet m_callBackResults = null;

  private DataSet m_agnBankCodeDomain = null;

  private String m_lastUpdDate = "";

  private String m_lastUpdUserId = "";

  private String m_recStatCode = "";
  
  private String m_beneCpfCnpjNbr = "";
  
  private String m_beneNameText = "";

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

  private String m_clickedSearch = "";

  // Lista com os ítens a inseridos na base
  private String[] m_selectedCallBackInGrid = null;

  // Lista com os ítens a inseridos na base
  private String[] m_selectedDocTransferInGrid = null;

  // Lista com os ítens excluídos
  private String[] m_deletedCallBack = null;

  // Lista com os ítens excluídos
  private String[] m_deletedDocTransfer = null;

  /**
   * @return Retorna o número do cliente.
   *  
   */
  public String getCustNbrSrc()
  {
	return m_custNbrSrc;
  }

  /**
   * 
   * @param custNbrSrc_.Seta o número do cliente.
   */
  public void setCustNbrSrc( String custNbrSrc_ )
  {
	m_custNbrSrc = custNbrSrc_;

  }

  /**
   * @return Returns agnBankCode.
   */
  public String getAgnBankCode()
  {
	return m_agnBankCode;
  }

  /**
   * @param agnBankCode_ Field agnBankCode to be setted.
   */
  public void setAgnBankCode( String agnBankCode_ )
  {
	m_agnBankCode = agnBankCode_;
  }

  /**
   * @return Returns agnBankCodeDomain.
   */
  public DataSet getAgnBankCodeDomain()
  {
	return m_agnBankCodeDomain;
  }

  /**
   * @param agnBankCodeDomain_ Field agnBankCodeDomain to be setted.
   */
  public void setAgnBankCodeDomain( DataSet agnBankCodeDomain_ )
  {
	m_agnBankCodeDomain = agnBankCodeDomain_;
  }

  /**
   * @return Returns brchCode.
   */
  public String getBrchCode()
  {
	return m_brchCode;
  }

  /**
   * @param brchCode_ Field brchCode to be setted.
   */
  public void setBrchCode( String brchCode_ )
  {
	m_brchCode = brchCode_;
  }

  /**
   * @return Returns callBackResults.
   */
  public DataSet getCallBackResults()
  {
	return m_callBackResults;
  }

  /**
   * @param callBackResults_ Field callBackResults to be setted.
   */
  public void setCallBackResults( DataSet callBackResults_ )
  {
	m_callBackResults = callBackResults_;
  }

  /**
   * @return Returns ctcNbr.
   */
  public String getCtcNbr()
  {
	return m_ctcNbr;
  }

  /**
   * @param ctcNbr_ Field ctcNbr to be setted.
   */
  public void setCtcNbr( String ctcNbr_ )
  {
	m_ctcNbr = ctcNbr_;
  }

  /**
   * @return Returns curAcctNbr.
   */
  public String getCurAcctNbr()
  {
	return m_curAcctNbr;
  }

  /**
   * @param curAcctNbr_ Field curAcctNbr to be setted.
   */
  public void setCurAcctNbr( String curAcctNbr_ )
  {
	m_curAcctNbr = curAcctNbr_;
  }

  /**
   * @return Returns custFullNameText.
   */
  public String getCustFullNameText()
  {
	return m_custFullNameText;
  }

  /**
   * @param custFullNameText_ Field custFullNameText to be setted.
   */
  public void setCustFullNameText( String custFullNameText_ )
  {
	m_custFullNameText = custFullNameText_;
  }

  /**
   * @return Returns docTransferCode.
   */
  public String getDocTransferCode()
  {
	return m_docTransferCode;
  }

  /**
   * @param docTransferCode_ Field docTransferCode to be setted.
   */
  public void setDocTransferCode( String docTransferCode_ )
  {
	m_docTransferCode = docTransferCode_;
  }

  /**
   * @return Returns docTransferResults.
   */
  public DataSet getDocTransferResults()
  {
	return m_docTransferResults;
  }

  /**
   * @param docTransferResults_ Field docTransferResults to be setted.
   */
  public void setDocTransferResults( DataSet docTransferResults_ )
  {
	m_docTransferResults = docTransferResults_;
  }

  /**
   * @return Returns domainDocTransferNbr.
   */
  public String[] getDomainDocTransferNbr()
  {
	return m_domainDocTransferNbr;
  }

  /**
   * @param domainDocTransferNbr_ Field domainDocTransferNbr to be setted.
   */
  public void setDomainDocTransferNbr( String[] domainDocTransferNbr_ )
  {
	m_domainDocTransferNbr = domainDocTransferNbr_;
  }

  /**
   * @return Returns domainsAgnBankCode.
   */
  public String[] getDomainsAgnBankCode()
  {
	return m_domainsAgnBankCode;
  }

  /**
   * @param domainsAgnBankCode_ Field domainsAgnBankCode to be setted.
   */
  public void setDomainsAgnBankCode( String[] domainsAgnBankCode_ )
  {
	m_domainsAgnBankCode = domainsAgnBankCode_;
  }

  /**
   * @return Returns domainsBrchCode.
   */
  public String[] getDomainsBrchCode()
  {
	return m_domainsBrchCode;
  }

  /**
   * @param domainsBrchCode_ Field domainsBrchCode to be setted.
   */
  public void setDomainsBrchCode( String[] domainsBrchCode_ )
  {
	m_domainsBrchCode = domainsBrchCode_;
  }

  /**
   * @return Returns domainsCtcNbr.
   */
  public String[] getDomainsCtcNbr()
  {
	return m_domainsCtcNbr;
  }

  /**
   * @param domainsCtcNbr_ Field domainsCtcNbr to be setted.
   */
  public void setDomainsCtcNbr( String[] domainsCtcNbr_ )
  {
	m_domainsCtcNbr = domainsCtcNbr_;
  }

  /**
   * @return Returns domainsCurAcctCode.
   */
  public String[] getDomainsCurAcctCode()
  {
	return m_domainsCurAcctCode;
  }

  /**
   * @param domainsCurAcctCode_ Field domainsCurAcctCode to be setted.
   */
  public void setDomainsCurAcctCode( String[] domainsCurAcctCode_ )
  {
	m_domainsCurAcctCode = domainsCurAcctCode_;
  }

  /**
   * @return Returns domainsDocTransferCode.
   */
  public String[] getDomainsDocTransferCode()
  {
	return m_domainsDocTransferCode;
  }

  /**
   * @param domainsDocTransferCode_ Field domainsDocTransferCode to be setted.
   */
  public void setDomainsDocTransferCode( String[] domainsDocTransferCode_ )
  {
	m_domainsDocTransferCode = domainsDocTransferCode_;
  }

  /**
   * @return Returns domainsOwnDestAcctInd.
   */
  public String[] getDomainsOwnDestAcctInd()
  {
	return m_domainsOwnDestAcctInd;
  }

  /**
   * @param domainsOwnDestAcctInd_ Field domainsOwnDestAcctInd to be setted.
   */
  public void setDomainsOwnDestAcctInd( String[] domainsOwnDestAcctInd_ )
  {
	m_domainsOwnDestAcctInd = domainsOwnDestAcctInd_;
  }

  /**
   * @return Returns domainsTxnTypeCode.
   */
  public String[] getDomainsTxnTypeCode()
  {
	return m_domainsTxnTypeCode;
  }

  /**
   * @param domainsTxnTypeCode_ Field domainsTxnTypeCode to be setted.
   */
  public void setDomainsTxnTypeCode( String[] domainsTxnTypeCode_ )
  {
	m_domainsTxnTypeCode = domainsTxnTypeCode_;
  }

  /**
   * @return Returns ipDocCode.
   */
  public String getIpDocCode()
  {
	return m_ipDocCode;
  }

  /**
   * @param ipDocCode_ Field ipDocCode to be setted.
   */
  public void setIpDocCode( String ipDocCode_ )
  {
	m_ipDocCode = ipDocCode_;
  }

  /**
   * @return Returns ipDocText.
   */
  public String getIpDocText()
  {
	return m_ipDocText;
  }

  /**
   * @param ipDocText_ Field ipDocText to be setted.
   */
  public void setIpDocText( String ipDocText_ )
  {
	m_ipDocText = ipDocText_;
  }

  /**
   * @return Returns ipInvstCurAcctInd.
   */
  public String getIpInvstCurAcctInd()
  {
	return m_ipInvstCurAcctInd;
  }

  /**
   * @param ipInvstCurAcctInd_ Field ipInvstCurAcctInd to be setted.
   */
  public void setIpInvstCurAcctInd( String ipInvstCurAcctInd_ )
  {
	m_ipInvstCurAcctInd = ipInvstCurAcctInd_;
  }

  /**
   * @return Returns ownDestAcctInd.
   */
  public String getOwnDestAcctInd()
  {
	return m_ownDestAcctInd;
  }

  /**
   * @param ownDestAcctInd_ Field ownDestAcctInd to be setted.
   */
  public void setOwnDestAcctInd( String ownDestAcctInd_ )
  {
	m_ownDestAcctInd = ownDestAcctInd_;
  }

  /**
   * @return Returns recStatCode.
   */
  public String getRecStatCode()
  {
	return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
	m_recStatCode = recStatCode_;
  }

  /**
   * @return Returns txnTypeCode.
   */
  public String getTxnTypeCode()
  {
	return m_txnTypeCode;
  }

  /**
   * @param txnTypeCode_ Field txnTypeCode to be setted.
   */
  public void setTxnTypeCode( String txnTypeCode_ )
  {
	m_txnTypeCode = txnTypeCode_;
  }

  /**
   * @return Returns selectedCustNbr.
   */
  public String getSelectedCustNbr()
  {
	return null;
  }

  /**
   * @param selectedCustNbr_ Field selectedCustNbr to be setted.
   */
  public void setSelectedCustNbr( String selectedCustNbr_ )
  {
	setCustNbrSrc( selectedCustNbr_ );
  }

  /**
   * @return Returns selectedIpDocCode.
   */
  public String getSelectedIpDocCode()
  {
	return null;
  }

  /**
   * @param selectedIpDocCode_ Field selectedIpDocCode to be setted.
   */
  public void setSelectedIpDocCode( String selectedIpDocCode_ )
  {
	setIpDocCode( selectedIpDocCode_ );
  }

  /**
   * @return Returns ctcNameText.
   */
  public String getCtcNameText()
  {
	return m_ctcNameText;
  }

  /**
   * @param ctcNameText_ Field ctcNameText to be setted.
   */
  public void setCtcNameText( String ctcNameText_ )
  {
	m_ctcNameText = ctcNameText_;
  }

  /**
   * @return Returns domainsFullNameText.
   */
  public String[] getDomainsFullNameText()
  {
	return m_domainsFullNameText;
  }
  
  /**
	 * @return Returns domainsPhone
	 */
	public String[] getDomainsPhone()
	{
	  return this.m_domainsPhone;
	}
	
	/**
		 * @return Returns domainsPhone
		 */
    public String[] getDomainsDDDPhone()
	{
	  return this.m_domainsDDDPhone;
	}
	
	
	/**
			 * @return Returns domainsPhone
			 */
	public String[] getDomainsRamalPhone()
	{
	  return this.m_domainsRamalPhone;
	}
	


  /**
   * @param domainsFullNameText_ Field domainsFullNameText to be setted.
   */
  public void setDomainsFullNameText( String[] domainsFullNameText_ )
  {
	m_domainsFullNameText = domainsFullNameText_;
  }
  
  /**
	 * @param 
	 */
  public void setDomainsPhone( String[] domainsPhone )
  {
    this.m_domainsPhone = domainsPhone;
  }
  
  /**
	   * @param 
	   */
	public void setDomainsDDDPhone( String[] domainsDDDPhone )
	{
	  this.m_domainsDDDPhone = domainsDDDPhone;
	}
	
	
	/**
		   * @param 
		   */
  public void setDomainsRamalPhone( String[] domainsRamalPhone )
  {
    this.m_domainsRamalPhone = domainsRamalPhone;
  }

  /**
   * @return Returns fullNameText.
   */
  public String getFullNameText()
  {
	return m_fullNameText;
  }

  /**
   * @param fullNameText_ Field fullNameText to be setted.
   */
  public void setFullNameText( String fullNameText_ )
  {
	m_fullNameText = fullNameText_;
  }

  /**
   * @return Returns actionPerformed.
   */
  public String getActionPerformed()
  {
	return m_actionPerformed;
  }

  /**
   * @param actionPerformed_ Field actionPerformed to be setted.
   */
  public void setActionPerformed( String actionPerformed_ )
  {
	m_actionPerformed = actionPerformed_;
  }

  /**
   * @return Returns ipInvstCurAcctIndDomain.
   */
  public DataSet getIpInvstCurAcctIndDomain()
  {
	return m_ipInvstCurAcctIndDomain;
  }

  /**
   * @param ipInvstCurAcctIndDomain_ Field ipInvstCurAcctIndDomain to be setted.
   */
  public void setIpInvstCurAcctIndDomain( DataSet ipInvstCurAcctIndDomain_ )
  {
	m_ipInvstCurAcctIndDomain = ipInvstCurAcctIndDomain_;
  }

  /**
   * @return Returns ownDestAcctIndDomain.
   */
  public DataSet getOwnDestAcctIndDomain()
  {
	return m_ownDestAcctIndDomain;
  }

  /**
   * @param ownDestAcctIndDomain_ Field ownDestAcctIndDomain to be setted.
   */
  public void setOwnDestAcctIndDomain( DataSet ownDestAcctIndDomain_ )
  {
	m_ownDestAcctIndDomain = ownDestAcctIndDomain_;
  }

  /**
   * @return Returns clickedSearch.
   */
  public String getClickedSearch()
  {
	return m_clickedSearch;
  }

  /**
   * @param clickedSearch_ Field clickedSearch to be setted.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
	m_clickedSearch = clickedSearch_;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getSelectedCustNbrList()
   */
  public String getSelectedCustNbrList()
  {
	return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setSelectedCustNbrList(java.lang.String)
   */
  public void setSelectedCustNbrList( String selectedCustNbr_ )
  {
	setCustNbrSrc( selectedCustNbr_ );

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.contactcust.form.ContactCustSearchable#getSelectedCtcNbrSrc()
   */
  public String getSelectedCtcNbrSrc()
  {
	return m_ctcNbr;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.contactcust.form.ContactCustSearchable#setSelectedCtcNbrSrc(java.lang.String)
   */
  public void setSelectedCtcNbrSrc( String ctcNbrSrc_ )
  {
	setCtcNbr( ctcNbrSrc_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.contactcust.form.ContactCustSearchable#setCtcNbrSrc(java.lang.String)
   */
  public void setCtcNbrSrc( String ctcNbrSrc_ )
  {
	setCtcNbr( ctcNbrSrc_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.client.contactcust.form.ContactCustSearchable#getCtcNbrSrc()
   */
  public String getCtcNbrSrc()
  {
	return m_ctcNbr;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedCurAcctNbrSrc()
   */
  public String getSelectedCurAcctNbrSrc()
  {
	return m_curAcctNbr;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedCurAcctNbrSrc(java.lang.String)
   */
  public void setSelectedCurAcctNbrSrc( String curAcctNbr_ )
  {
	setCurAcctNbr( curAcctNbr_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getCurAcctNbrSrc()
   */
  public String getCurAcctNbrSrc()
  {
	return m_curAcctNbr;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setCurAcctNbrSrc(java.lang.String)
   */
  public void setCurAcctNbrSrc( String curAcctNbrSrc_ )
  {
	setCurAcctNbr( curAcctNbrSrc_ );
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedProdAcctCodeSrc()
   */
  public String getSelectedProdAcctCodeSrc()
  {
	return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedProdAcctCodeSrc(java.lang.String)
   */
  public void setSelectedProdAcctCodeSrc( String prodAcctCode_ )
  {

  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#getSelectedProdUnderAcctCodeSrc()
   */
  public String getSelectedProdUnderAcctCodeSrc()
  {
	return null;
  }

  /*
   * (non-Javadoc)
   * @see com.citibank.ods.modules.control.contract.form.CurAccountSearchable#setSelectedProdUnderAcctCodeSrc(java.lang.String)
   */
  public void setSelectedProdUnderAcctCodeSrc( String prodUnderAcctCode_ )
  {

  }

  /**
   * @return m_lastUpdDate. Retorna data/hora da última atualização.
   */
  public String getLastUpdDate()
  {
	return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_.Seta data/hora da última atualização.
   */
  public void setLastUpdDate( String lastUpdDate_ )
  {
	m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return m_lastUpdUserId. Retorna usuário da última atualização.
   */
  public String getLastUpdUserId()
  {
	return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_.Seta usuário da última atualização.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
	m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns txnTypeCodeDomain.
   */
  public DataSet getTxnTypeCodeDomain()
  {
	return m_txnTypeCodeDomain;
  }

  /**
   * @return Returns selectedCallBackInGrid.
   */
  public String[] getSelectedCallBackInGrid()
  {
	return m_selectedCallBackInGrid;
  }

  /**
   * @param selectedCallBackInGrid_ Field selectedCallBackInGrid to be setted.
   */
  public void setSelectedCallBackInGrid( String[] selectedCallBackInGrid_ )
  {
	m_selectedCallBackInGrid = selectedCallBackInGrid_;
  }

  /**
   * @return Returns selectedDocTransferInGrid.
   */
  public String[] getSelectedDocTransferInGrid()
  {
	return m_selectedDocTransferInGrid;
  }

  /**
   * @param selectedDocTransferInGrid_ Field selectedDocTransferInGrid to be
   *          setted.
   */
  public void setSelectedDocTransferInGrid( String[] selectedDocTransferInGrid_ )
  {
	m_selectedDocTransferInGrid = selectedDocTransferInGrid_;
  }

  /**
   * @param txnTypeCodeDomain_ Field txnTypeCodeDomain to be setted.
   */
  public void setTxnTypeCodeDomain( DataSet txnTypeCodeDomain_ )
  {
	m_txnTypeCodeDomain = txnTypeCodeDomain_;
  }

  /**
   * @return Returns domainsAgnBankName.
   */
  public String[] getDomainsAgnBankName()
  {
	return m_domainsAgnBankName;
  }

  /**
   * @param domainsAgnBankName_ Field domainsAgnBankName to be setted.
   */
  public void setDomainsAgnBankName( String[] domainsAgnBankName_ )
  {
	m_domainsAgnBankName = domainsAgnBankName_;
  }

  /**
   * @return Returns domainsBrchName.
   */
  public String[] getDomainsBrchName()
  {
	return m_domainsBrchName;
  }

  /**
   * @param domainsBrchName_ Field domainsBrchName to be setted.
   */
  public void setDomainsBrchName( String[] domainsBrchName_ )
  {
	m_domainsBrchName = domainsBrchName_;
  }

  /**
   * @return Returns deletedCallBack.
   */
  public String[] getDeletedCallBack()
  {
	return m_deletedCallBack;
  }

  /**
   * @param deletedCallBack_ Field deletedCallBack to be setted.
   */
  public void setDeletedCallBack( String[] deletedCallBack_ )
  {
	m_deletedCallBack = deletedCallBack_;
  }

  /**
   * @return Returns deletedDocTransfer.
   */
  public String[] getDeletedDocTransfer()
  {
	return m_deletedDocTransfer;
  }

  /**
   * @param deletedDocTransfer_ Field deletedDocTransfer to be setted.
   */
  public void setDeletedDocTransfer( String[] deletedDocTransfer_ )
  {
	m_deletedDocTransfer = deletedDocTransfer_;
  }

  public ActionErrors validate( ActionMapping actionMapping_,
							   HttpServletRequest request_ )
  {

	ActionErrors errors = new ActionErrors();

	if ( m_actionPerformed != null && !m_actionPerformed.equals( "" ) )
	{
	  if ( m_actionPerformed.equals( C_INSERT_IP_DOC_CALLBACK ) )
	  {
		ODSValidator.validateBigInteger(
										 BaseContactCustDetailFncVO.C_CTC_NBR_DESCRIPTION,
										 m_ctcNbr,
										 BaseTplContactCustEntity.C_CTC_NBR_SIZE,
										 errors );
	  }
	  else if ( m_actionPerformed.equals( C_INSERT_DOC_TRANSFER ) )
	  {
		ODSValidator.validateBigInteger(
										 BaseDocTransferDetailFncVO.C_DOC_TRANFER_CODE_DESCRIPTION,
										 m_docTransferCode,
										 BaseTplDocTransferEntity.C_DOC_TRANFER_CODE_SIZE,
										 errors );

		ODSValidator.validateMaxLength(
										BaseDocTransferDetailFncVO.C_OWN_DEST_ACCT_IND_DESCRIPTION,
										m_ownDestAcctInd,
										BaseTplDocTransferEntity.C_OWN_DEST_ACCT_IND_SIZE,
										errors );

		ODSValidator.validateBigInteger(
										 BaseDocTransferDetailFncVO.C_TXN_TYPE_CODE_DESCRIPTION,
										 m_txnTypeCode,
										 BaseTplDocTransferEntity.C_TXN_TYPE_CODE_SIZE,
										 errors );

		ODSValidator.validateBigInteger(
										 BaseDocTransferDetailFncVO.C_AGN_BANK_CODE_DESCRIPTION,
										 m_agnBankCode,
										 BaseTplDocTransferEntity.C_AGN_BANK_CODE_SIZE,
										 errors );

		ODSValidator.validateBigInteger(
										 BaseDocTransferDetailFncVO.C_CUST_NBR_DESCRIPTION,
										 m_custNbrSrc,
										 BaseTplDocTransferEntity.C_CUST_NBR_SIZE,
										 errors );

		ODSValidator.validateBigInteger(
										 BaseDocTransferDetailFncVO.C_BRCH_CODE_DESCRIPTION,
										 m_brchCode,
										 BaseTplDocTransferEntity.C_BRCH_CODE_SIZE,
										 errors );

		ODSValidator.validateBigInteger(
										 BaseDocTransferDetailFncVO.C_IP_DOC_CODE_DESCRIPTION,
										 m_ipDocCode,
										 BaseTplDocTransferEntity.C_IP_DOC_CODE_SIZE,
										 errors );

		ODSValidator.validateBigInteger(
										 BaseDocTransferDetailFncVO.C_CUR_ACCT_NBR_DESCRIPTION,
										 m_curAcctNbr,
										 BaseTplDocTransferEntity.C_CUR_ACCT_NBR_SIZE,
										 errors );

	  }
	  else if ( m_actionPerformed.equals( C_INSERT_IP_DOC_PRVT ) )
	  {
		ODSValidator.validateBigInteger(
										 BaseIpDocPrvtDetailFncVO.C_CUST_NBR_DESCRIPTION,
										 m_custNbrSrc,
										 BaseTplIpDocPrvtEntity.C_CUST_NBR_SIZE,
										 errors );

		ODSValidator.validateBigInteger(
										 BaseIpDocPrvtDetailFncVO.C_IP_DOC_CODE_DESCRIPTION,
										 m_ipDocCode,
										 BaseTplIpDocPrvtEntity.C_IP_DOC_CODE_SIZE,
										 errors );

		ODSValidator.validateMaxLength(
										BaseIpDocPrvtDetailFncVO.C_IP_DOC_TEXT_DESCRIPTION,
										m_ipDocText,
										BaseTplIpDocPrvtEntity.C_IP_DOC_TEXT_SIZE,
										errors );

		ODSValidator.validateMaxLength(
										BaseIpDocPrvtDetailFncVO.C_IP_INVST_CUR_ACCT_IND_DESCRIPTION,
										m_ipInvstCurAcctInd,
										BaseTplIpDocPrvtEntity.C_IP_INVST_CUR_ACCT_IND_SIZE,
										errors );

	  }
	}
	return errors;
  }

  public void reset( ActionMapping arg0_, HttpServletRequest arg1_ )
  {

	if ( m_selectedCallBackInGrid != null )
	{
	  for ( int i = 0; i < m_selectedCallBackInGrid.length; i++ )
	  {
		m_selectedCallBackInGrid[ i ] = "N";
	  }
	}

	if ( m_selectedDocTransferInGrid != null )
	{
	  for ( int i = 0; i < m_selectedDocTransferInGrid.length; i++ )
	  {
		m_selectedDocTransferInGrid[ i ] = "N";
	  }
	}
	if ( m_deletedCallBack != null )
	{
	  for ( int i = 0; i < m_deletedCallBack.length; i++ )
	  {
		m_deletedCallBack[ i ] = "N";
	  }
	}

	if ( m_deletedDocTransfer != null )
	{
	  for ( int i = 0; i < m_deletedDocTransfer.length; i++ )
	  {
		m_deletedDocTransfer[ i ] = "N";
	  }
	}

  }

/* (non-Javadoc)
 * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#getReltnNbr()
 */
public String getReltnNbr() {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see com.citibank.ods.modules.client.customerprvt.form.CustomerSearchable#setReltnNbr(java.lang.String)
 */
public void setReltnNbr(String reltnNbr_) {
	// TODO Auto-generated method stub
	
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
  public String getBeneNameText() {
	return m_beneNameText;
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
  public void setBeneNameText(String m_beneNameText_) {
	m_beneNameText = m_beneNameText_;
  }

/**
 * @return
 */
  public String getBeneMainDestAcctInd() {
	return m_beneMainDestAcctInd;
  }
 
/**
 * @return
 */
  public DataSet getBeneMainDestAcctIndDomain() {
	return m_beneMainDestAcctIndDomain;
  }

/**
 * @param string
 */
  public void setBeneMainDestAcctInd(String m_beneMainDestAcctInd_) {
	m_beneMainDestAcctInd = m_beneMainDestAcctInd_;
  }

/**
 * @param set
 */
  public void setBeneMainDestAcctIndDomain(DataSet m_beneMainDestAcctIndDomain_) {
	m_beneMainDestAcctIndDomain = m_beneMainDestAcctIndDomain_;
  }

/**
 * @return
 */
  public DataSet getBeneAcctTypeCodeIndDomain() {
	return m_beneAcctTypeCodeIndDomain;
  }

/**
 * @param set
 */
  public void setBeneAcctTypeCodeIndDomain(DataSet m_beneAcctTypeCodeIndDomain_) {
	m_beneAcctTypeCodeIndDomain = m_beneAcctTypeCodeIndDomain_;
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
	 * @return
	 */	
	public String getFullNameText_2() {
	  return m_fullNameText_2;
	}

  /**
   * @return
   */
	public String getFullNameText_3() {
	  return m_fullNameText_3;
	}

	/**
   * @return
   */
	public String getPhoneDddCode() {
	  return phoneDddCode;
	}

  /**
   * @return
   */
	public String getPhoneExtnNbr() {
	  return phoneExtnNbr;
	}

  /**
   * @return
   */
	public String getPhoneNbr() {
	  return phoneNbr;
	}

  /**
   * @param string
   */
	public void setFullNameText_2(String m_fullNameText_2_) {
	  m_fullNameText_2 = m_fullNameText_2_;
	}

  /**
   * @param string
   */
	public void setFullNameText_3(String m_fullNameText_3_) {
	  m_fullNameText_3 = m_fullNameText_3_;
	}

  /**
   * @param string
   */
	public void setPhoneDddCode(String phoneDddCode_) {
	  phoneDddCode = phoneDddCode_;
	}

  /**
   * @param string
   */
	public void setPhoneExtnNbr(String phoneExtnNbr_) {
	  phoneExtnNbr = phoneExtnNbr_;
	}

  /**
   * @param string
   */
	public void setPhoneNbr(String phoneNbr_) {
	  phoneNbr = phoneNbr_;
	}



/**
 * @return
 */
  public String getInvstCurAcctNbrSrc() {
	return m_invstCurAcctNbrSrc;
  }


/**
 * @param string
 */
  public void setInvstCurAcctNbrSrc(String m_invstCurAcctNbrSrc_) {
	m_invstCurAcctNbrSrc = m_invstCurAcctNbrSrc_;
  }

/**
 * @return
 */
  public String getCustCurAcctNbrSrc() {
	return m_custCurAcctNbrSrc;
  }

/**
 * @param string
 */
  public void setCustCurAcctNbrSrc(String m_custCurAcctNbrSrc_) {
	m_custCurAcctNbrSrc = m_custCurAcctNbrSrc_;
  }

/**
 * @return
 */
  public String[] getDomainsFullNameText_2() {
	return m_domainsFullNameText_2;
  }

/**
 * @param strings
 */
  public void setDomainsFullNameText_2(String[] m_domainsFullNameText_2_) {
	m_domainsFullNameText_2 = m_domainsFullNameText_2_;
  }

/**
 * @return
 */
  public String[] getDomainsFullNameText_3() {
	return m_domainsFullNameText_3;
  }

/**
 * @param strings
 */
  public void setDomainsFullNameText_3(String[] m_domainsFullNameText_3_) {
	m_domainsFullNameText_3 = m_domainsFullNameText_3_;
  }

}