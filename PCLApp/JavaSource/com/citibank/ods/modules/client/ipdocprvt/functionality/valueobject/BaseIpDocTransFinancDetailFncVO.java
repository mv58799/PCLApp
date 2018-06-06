/*
 * Created on 14/11/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;

import java.util.ArrayList;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplCurAcctPrmntInstrEntity;
import com.citibank.ods.entity.pl.BaseTplCustomerPrvtEntity;
import com.citibank.ods.entity.pl.BaseTplDocTransferEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocTransFinancEntity;
//import com.ibm.jvm.findroots.Base;


/**
 * @author lfabiano
 * @since 14/11/2008
 */
public class BaseIpDocTransFinancDetailFncVO extends BaseODSFncVO
{
	
	private String tpOperacao;
	
	private DataSet m_ownDestAcctIndDomain;
	
	private DataSet m_ipIpInvstCurAcctIndDomain;
	
	private DataSet m_txnTypeCodeDomain = null;
	
	private boolean m_innerActionInd;
	
	private String m_custFullNameText;
	
	private String m_fullNameText = "";
	
	private boolean m_fromSearch = false;
	
	protected BaseTplIpDocTransFinancEntity m_baseTplIpDocTransFinancEntity;

	protected BaseTplDocTransferEntity m_baseTplDocTransferEntity;

	protected BaseTplIpDocCallbackEntity m_baseTplIpDocCallbackEntity;
	
	protected BaseTplCurAcctPrmntInstrEntity m_baseTplCurAcctPrmntInstrEntity;
	
	protected BaseTplIpDocPrvtEntity m_baseTplIpDocPrvtEntity;
	
	protected BaseTplCustomerPrvtEntity m_baseTplCustomerPrvtEntity;
	
	protected BaseTo3ProductAccountEntity m_to3ProductAccountEntity;
  
    //Lista de itens selecionados no Callback
	ArrayList m_selectedCallBackInGrid = new ArrayList();
	
	//Lista de itens deletados no Callback
	ArrayList m_deletedCallBackInGrid = new ArrayList();
	
    //Lista de itens inseridos no Callback
	private ArrayList insertedIpDocCallback = new ArrayList();
	
    //Lista de itens inseridos na Tranferencia
	private ArrayList insertedDocTransfer = new ArrayList();
	
   //Lista de itens deletados na Transferencia
	ArrayList m_deletedDocTransferInGrid = new ArrayList();
	
   //Lista de itens selecionados na Transferencia
	ArrayList m_selectedDocTransferInGrid = new ArrayList();

	
	
	/**
	 * @return Returns the innerActionInd.
	 */
	public boolean isInnerActionInd()
	{
	  return m_innerActionInd;
	}

	/**
	 * @param innerActionInd_ The innerActionInd to set.
	 */
	public void setInnerActionInd( boolean innerActionInd_ )
	{
	  m_innerActionInd = innerActionInd_;
	}
	/**
	 * @return
	 */
	public BaseTplDocTransferEntity getBaseTplDocTransferEntity() {
		return m_baseTplDocTransferEntity;
	}

	/**
	 * @return
	 */
	public BaseTplIpDocCallbackEntity getBaseTplIpDocCallbackEntity() {
		return m_baseTplIpDocCallbackEntity;
	}

	/**
	 * @return
	 */
	public BaseTplIpDocTransFinancEntity getBaseTplIpDocTransFinancEntity() {
		return m_baseTplIpDocTransFinancEntity;
	}

	/**
	 * @param entity
	 */
	public void setBaseTplDocTransferEntity(BaseTplDocTransferEntity baseTplDocTransferEntity_) {
		m_baseTplDocTransferEntity = baseTplDocTransferEntity_;
	}

	/**
	 * @param entity
	 */
	public void setBaseTplIpDocCallbackEntity(BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity_) {
		m_baseTplIpDocCallbackEntity = baseTplIpDocCallbackEntity_;
	}

	/**
	 * @param entity
	 */
	public void setBaseTplIpDocTransFinancEntity(BaseTplIpDocTransFinancEntity baseTplIpDocTransFinancEntity_) {
		m_baseTplIpDocTransFinancEntity = baseTplIpDocTransFinancEntity_;
	}
	
	/**
	   * @return Returns deletedCallBackInGrid.
	   */
	  public ArrayList getDeletedCallBackInGrid()
	  {
		return m_deletedCallBackInGrid;
	  }
	  /**
	   * @param deletedCallBackInGrid_ Field deletedCallBackInGrid to be setted.
	   */
	  public void setDeletedCallBackInGrid( ArrayList deletedCallBackInGrid_ )
	  {
		m_deletedCallBackInGrid = deletedCallBackInGrid_;
	  }
	/**
	   * @return Returns selectedCallBackInGrid.
	   */
	  public ArrayList getSelectedCallBackInGrid()
	  {
		return m_selectedCallBackInGrid;
	  }
	  /**
	   * @param selectedCallBackInGrid_ Field selectedCallBackInGrid to be setted.
	   */
	  public void setSelectedCallBackInGrid( ArrayList selectedCallBackInGrid_ )
	  {
		m_selectedCallBackInGrid = selectedCallBackInGrid_;
	  }
	/**
	   * @return Returns insertIpDocCallback.
	   */
	  public ArrayList getInsertedIpDocCallback()
	  {
		return insertedIpDocCallback;
	  }

	  /**
	   * @param insertIpDocCallback_ Field insertIpDocCallback to be setted.
	   */
	  public void setInsertedIpDocCallback( ArrayList insertIpDocCallback_ )
	  {
		insertedIpDocCallback = insertIpDocCallback_;
	  }
	/**
	   * @return Returns insertedDocTransfer.
	   */
	  public ArrayList getInsertedDocTransfer()
	  {
		return insertedDocTransfer;
	  }

	  /**
	   * @param insertedDocTransfer_ Field insertedDocTransfer to be setted.
	   */
	  public void setInsertedDocTransfer( ArrayList insertedDocTransfer_ )
	  {
		insertedDocTransfer = insertedDocTransfer_;
	  }
	/**
	   * @return Returns deletedDocTransferInGrid.
	   */
	  public ArrayList getDeletedDocTransferInGrid()
	  {
		return m_deletedDocTransferInGrid;
	  }
	  /**
	   * @param deletedDocTransferInGrid_ Field deletedDocTransferInGrid to be setted.
	   */
	  public void setDeletedDocTransferInGrid( ArrayList deletedDocTransferInGrid_ )
	  {
		m_deletedDocTransferInGrid = deletedDocTransferInGrid_;
	  }
	/**
	   * @return Returns selectedDocTransferInGrid.
	   */
	  public ArrayList getSelectedDocTransferInGrid()
	  {
		return m_selectedDocTransferInGrid;
	  }
	  /**
	   * @param selectedDocTransferInGrid_ Field selectedDocTransferInGrid to be setted.
	   */
	  public void setSelectedDocTransferInGrid( ArrayList selectedDocTransferInGrid_ )
	  {
		m_selectedDocTransferInGrid = selectedDocTransferInGrid_;
	  }

	/**
	 * @return Returns ipIpInvstCurAcctIndDomain.
	 */
	public DataSet getIpIpInvstCurAcctIndDomain()
	{
	  return m_ipIpInvstCurAcctIndDomain;
	}

	/**
	 * @param ipIpInvstCurAcctIndDomain_ Field ipIpInvstCurAcctIndDomain to be
	 *          setted.
	 */
	public void setIpIpInvstCurAcctIndDomain( DataSet ipIpInvstCurAcctIndDomain_ )
	{
	  m_ipIpInvstCurAcctIndDomain = ipIpInvstCurAcctIndDomain_;
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
	public DataSet getTxnTypeCodeDomain() {
		return m_txnTypeCodeDomain;
	}

	/**
	 * @param set
	 */
	public void setOwnDestAcctIndDomain(DataSet ownDestAcctIndDomain_) {
		m_ownDestAcctIndDomain = ownDestAcctIndDomain_;
	}

	/**
	 * @param set	
	 */
	public void setTxnTypeCodeDomain(DataSet txnTypeCodeDomain_) {
		m_txnTypeCodeDomain = txnTypeCodeDomain_;
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
	 * @return
	 */
	public String getCustFullNameText() {
		return m_custFullNameText;
	}

	/**
	 * @param string
	 */
	public void setCustFullNameText(String custFullNameText_) {
		m_custFullNameText = custFullNameText_;
	}
	
	
	
	/**
     * @return Returns fromSearch.
     */
    public boolean isFromSearch()
    {
    return m_fromSearch;
   }

	/**
	 * @param b
	 */
	public void setFromSearch(boolean fromSearch_) {
		m_fromSearch = fromSearch_;
	}

	/**
	 * @return
	 */
	public BaseTplIpDocPrvtEntity getBaseTplIpDocPrvtEntity() {
		return m_baseTplIpDocPrvtEntity;
	}

	/**
	 * @param entity
	 */
	public void setBaseTplIpDocPrvtEntity(BaseTplIpDocPrvtEntity m_baseTplIpDocPrvtEntity_) {
		m_baseTplIpDocPrvtEntity = m_baseTplIpDocPrvtEntity_;
	}

	/**
	 * @return
	 */
	public BaseTplCustomerPrvtEntity getBaseTplCustomerPrvtEntity() {
		return m_baseTplCustomerPrvtEntity;
	}

	/**
	 * @param entity
	 */
	public void setBaseTplCustomerPrvtEntity(BaseTplCustomerPrvtEntity m_baseTplCustomerPrvtEntity_) {
		m_baseTplCustomerPrvtEntity = m_baseTplCustomerPrvtEntity_;
	}

	/**
	 * @return
	 */
	public BaseTo3ProductAccountEntity getTo3ProductAccountEntity() {
		return m_to3ProductAccountEntity;
	}

	/**
	 * @param entity
	 */
	public void setTo3ProductAccountEntity(BaseTo3ProductAccountEntity m_to3ProductAccountEntity_) {
		m_to3ProductAccountEntity = m_to3ProductAccountEntity_;
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
