package com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;

import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTo3ProductAccountEntity;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;
import com.citibank.ods.entity.pl.BaseTplDocTransferEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocCallbackEntity;
import com.citibank.ods.entity.pl.BaseTplIpDocPrvtEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseIpDocPrvtDetailFncVO extends BaseODSFncVO
{

  protected BaseTplIpDocPrvtEntity m_baseTplIpDocPrvtEntity;

  protected BaseTplDocTransferEntity m_baseTplDocTransferEntity;

  protected BaseTplIpDocCallbackEntity m_baseTplIpDocCallbackEntity;
  
  //Entidade que agrega a tabela de Contatos
  protected BaseTplContactCustEntity m_tplContactCustEntity = null;
  
  //Entidade que agrega a tabela de Conta de Produtos
  protected BaseTo3ProductAccountEntity m_to3ProductAccountEntity = null;

  private DataSet m_txnTypeCodeDomain = null;

  private DataSet m_ipIpInvstCurAcctIndDomain;

  private DataSet m_ownDestAcctIndDomain;
  
  private DataSet m_beneMainDestAcctIndDomain;
  
  private DataSet m_beneAcctTypeCodeIndDomain;
  
  private String m_custFullNameText;

  private boolean m_innerActionInd;

  private ArrayList insertedDocTransfer = new ArrayList();

  
  private ArrayList insertedIpDocCallback = new ArrayList();

  

  public static final String C_CUST_NBR_DESCRIPTION = "Número do Cliente";

  public static final String C_IP_DOC_CODE_DESCRIPTION = "Código do Documento IP";

  public static final String C_IP_DOC_TEXT_DESCRIPTION = "Descrição do Documento IP";

  public static final String C_IP_INVST_CUR_ACCT_IND_DESCRIPTION = "Indicador de Utilização de CCI";

  private String m_clickedSearch = "";

  private String m_fullNameText = "";

  private boolean m_fromSearch = false;
  
  /**
   * Controla a busca da sequencia considernado a chave composta
   */
  private boolean m_nextVall = false;

  private String m_lastUpdUserId = "";

  private Date m_lastUpdDate = null;

  private boolean m_loaded = false;
  
  //Lista de itens selecionados no grid
  ArrayList m_selectedCallBackInGrid = new ArrayList();
  
  //Lista de itens selecionados no grid
  ArrayList m_selectedDocTransferInGrid = new ArrayList();
  
  //Lista de itens selecionados no grid
  ArrayList m_deletedCallBackInGrid = new ArrayList();
  
  //Lista de itens selecionados no grid
  ArrayList m_deletedDocTransferInGrid = new ArrayList();


  /**
   * @return Returns baseTplDocTransferEntity.
   */
  public BaseTplDocTransferEntity getBaseTplDocTransferEntity()
  {
    return m_baseTplDocTransferEntity;
  }

  /**
   * @param baseTplDocTransferEntity_ Field baseTplDocTransferEntity to be
   *          setted.
   */
  public void setBaseTplDocTransferEntity(
                                          BaseTplDocTransferEntity baseTplDocTransferEntity_ )
  {
    m_baseTplDocTransferEntity = baseTplDocTransferEntity_;
  }

  /**
   * @return Returns baseTplIpDocCallbackEntity.
   */
  public BaseTplIpDocCallbackEntity getBaseTplIpDocCallbackEntity()
  {
    return m_baseTplIpDocCallbackEntity;
  }

  /**
   * @param baseTplIpDocCallbackEntity_ Field baseTplIpDocCallbackEntity to be
   *          setted.
   */
  public void setBaseTplIpDocCallbackEntity(
                                            BaseTplIpDocCallbackEntity baseTplIpDocCallbackEntity_ )
  {
    m_baseTplIpDocCallbackEntity = baseTplIpDocCallbackEntity_;
  }

  /**
   * @return Returns the baseTplIpDocPrvtEntity.
   */
  public BaseTplIpDocPrvtEntity getBaseTplIpDocPrvtEntity()
  {
    return m_baseTplIpDocPrvtEntity;
  }

  /**
   * @param baseTplIpDocPrvtEntity_ The baseTplIpDocPrvtEntity to set.
   */
  public void setBaseTplIpDocPrvtEntity(
                                        BaseTplIpDocPrvtEntity baseTplIpDocPrvtEntity_ )
  {
    m_baseTplIpDocPrvtEntity = baseTplIpDocPrvtEntity_;
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
   * @return Returns fromSearch.
   */
  public boolean isFromSearch()
  {
    return m_fromSearch;
  }
  
  /**
  * @return Returns m_nextVall.
  */
  public boolean isNextVall()
  {
	  return m_nextVall;
  }

  /**
   * @param fromSearch_ Field fromSearch to be setted.
   */
  public void setFromSearch( boolean fromSearch_ )
  {
    m_fromSearch = fromSearch_;
  }
  
  /**
   * @param m_nextVall 
  */
  public void setNextVall( boolean m_nextVall )
  {
    this.m_nextVall = m_nextVall;
  }

  /**
   * @return Returns lastUpdDate.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field lastUpdDate to be setted.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field lastUpdUserId to be setted.
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
   * @param txnTypeCodeDomain_ Field txnTypeCodeDomain to be setted.
   */
  public void setTxnTypeCodeDomain( DataSet txnTypeCodeDomain_ )
  {
    m_txnTypeCodeDomain = txnTypeCodeDomain_;
  }

  public boolean isLoaded()
  {
    return m_loaded;
  }

  public void setLoaded( boolean loaded_ )
  {
    m_loaded = loaded_;
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
 * @return
 */
  public DataSet getBeneMainDestAcctIndDomain() {
	return m_beneMainDestAcctIndDomain;
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
  public BaseTplContactCustEntity getTplContactCustEntity() {
	return m_tplContactCustEntity;
  }

/**
 * @param entity
 */
  public void setTplContactCustEntity(BaseTplContactCustEntity m_tplContactCustEntity_) {
	m_tplContactCustEntity = m_tplContactCustEntity_;
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

}

