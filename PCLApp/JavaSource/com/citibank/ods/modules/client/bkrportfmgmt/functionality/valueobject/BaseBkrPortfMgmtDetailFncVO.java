package com.citibank.ods.modules.client.bkrportfmgmt.functionality.valueobject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplBkrPortfMgmtEntity;

/**
 * @author Hamilton Matos
 */

public class BaseBkrPortfMgmtDetailFncVO extends BaseODSFncVO
{

  // CNPJ da Corretora
  private BigInteger m_bkrCnpjNbr;

  // Nome Completo da corretora
  private String m_bkrNameText;

  // Resultado da consulta de carteiras administradas
  private DataSet m_portfolioResults;

  // Variável de controle utilizada no carregamento da lista de carteiras
  private boolean isPortfolioLoaded = false;

  // Código da Conta Produto
  private BigInteger m_prodAcctCode;

  // Código da sub conta produto
  private BigInteger m_prodUnderAcctCode;

  // Lista de chaves do mapa de entidades de associação corretoras x carteiras
  private ArrayList m_bkrPortfMgmtMapKey;

  // Variável de controle do botão de confirmação de associações
  private boolean m_confirmAssociationEnabled = false;

  // Variável de controle do botão de confirmação de associações
  private boolean m_associationConfirmed = false;

  // Variável de controle para lista de corretora x carteira
  private boolean m_isPmaItemClicked = false;

  // Variável de controle de alteração
  private boolean m_isUpdate = false;

  // Variável de controle de aprovação
  private boolean m_isApprove = false;

  // Entidade de associação de corretora associada a carteira administrada
  protected BaseTplBkrPortfMgmtEntity m_baseTplBkrPortfMgmtEntity;

  // Lista de ítens selecionados no grid de corretoras
  protected ArrayList m_selectedItemsInBrokerGrid = new ArrayList();

  // Lista de ítens selecionados no grid de corretoras associadas a carteira
  // administrada
  protected ArrayList m_selectedItemsInBkrPortfMgmtGrid = new ArrayList();

  // Mapa de entidades de associações corretoras x carteiras
  protected Map m_bkrPortfMgmtMap = new HashMap();

  // Mapa de estados de checkboxes do grid de associações corretoras x carteiras
  protected Map m_checkBoxMap = new HashMap();

  // Elementos do grid de corretoras
  protected ArrayList m_baseTplBrokerEntityArray = new ArrayList();

  // Elementos do grid de associações carteiras x corretoras
  protected ArrayList m_baseTplBkrPortfMgmtEntityArray = new ArrayList();

  /**
   * @return Returns baseTplBkrPortfMgmtEntity.
   */
  public BaseTplBkrPortfMgmtEntity getBaseTplBkrPortfMgmtEntity()
  {
    return m_baseTplBkrPortfMgmtEntity;
  }

  /**
   * @param baseTplBkrPortfMgmtEntity_ Field baseTplBkrPortfMgmtEntity to be
   *          setted.
   */
  public void setBaseTplBkrPortfMgmtEntity(
                                           BaseTplBkrPortfMgmtEntity baseTplBkrPortfMgmtEntity_ )
  {
    m_baseTplBkrPortfMgmtEntity = baseTplBkrPortfMgmtEntity_;
  }

  /**
   * @return Returns baseTplBrokerEntityArray.
   */
  public ArrayList getBaseTplBrokerEntityArray()
  {
    return m_baseTplBrokerEntityArray;
  }

  /**
   * @param baseTplBrokerEntityArray_ Field baseTplBrokerEntityArray to be
   *          setted.
   */
  public void setBaseTplBrokerEntityArray( ArrayList baseTplBrokerEntityArray_ )
  {
    m_baseTplBrokerEntityArray = baseTplBrokerEntityArray_;
  }

  /**
   * @return Returns bkrCnpjNbr.
   */
  public BigInteger getBkrCnpjNbr()
  {
    return m_bkrCnpjNbr;
  }

  /**
   * @param bkrCnpjNbr_ Field bkrCnpjNbr to be setted.
   */
  public void setBkrCnpjNbr( BigInteger bkrCnpjNbr_ )
  {
    m_bkrCnpjNbr = bkrCnpjNbr_;
  }

  /**
   * @return Returns bkrNameText.
   */
  public String getBkrNameText()
  {
    return m_bkrNameText;
  }

  /**
   * @param bkrNameText_ Field bkrNameText to be setted.
   */
  public void setBkrNameText( String bkrNameText_ )
  {
    m_bkrNameText = bkrNameText_;
  }

  /**
   * @return Returns selectedItemsInBrokerGrid.
   */
  public ArrayList getSelectedItemsInBrokerGrid()
  {
    return m_selectedItemsInBrokerGrid;
  }

  /**
   * @param selectedItemsInBrokerGrid_ Field selectedItemsInBrokerGrid to be
   *          setted.
   */
  public void setSelectedItemsInBrokerGrid( ArrayList selectedItemsInBrokerGrid_ )
  {
    m_selectedItemsInBrokerGrid = selectedItemsInBrokerGrid_;
  }

  /**
   * @return Returns selectedItemsInBkrPortfMgmtGrid.
   */
  public ArrayList getSelectedItemsInBkrPortfMgmtGrid()
  {
    return m_selectedItemsInBkrPortfMgmtGrid;
  }

  /**
   * @param selectedItemsInBkrPortfMgmtGrid_ Field
   *          selectedItemsInBkrPortfMgmtGrid to be setted.
   */
  public void setSelectedItemsInBkrPortfMgmtGrid(
                                                 ArrayList selectedItemsInBkrPortfMgmtGrid_ )
  {
    m_selectedItemsInBkrPortfMgmtGrid = selectedItemsInBkrPortfMgmtGrid_;
  }

  /**
   * @return Returns baseTplBkrPortfMgmtEntityArray.
   */
  public ArrayList getBaseTplBkrPortfMgmtEntityArray()
  {
    return m_baseTplBkrPortfMgmtEntityArray;
  }

  /**
   * @param baseTplBkrPortfMgmtEntityArray_ Field baseTplBkrPortfMgmtEntityArray
   *          to be setted.
   */
  public void setBaseTplBkrPortfMgmtEntityArray(
                                                ArrayList baseTplBkrPortfMgmtEntityArray_ )
  {
    m_baseTplBkrPortfMgmtEntityArray = baseTplBkrPortfMgmtEntityArray_;
  }

  /**
   * @return Returns portfolioResults.
   */
  public DataSet getPortfolioResults()
  {
    return m_portfolioResults;
  }

  /**
   * @param portfolioResults_ Field portfolioResults to be setted.
   */
  public void setPortfolioResults( DataSet portfolioResults_ )
  {
    m_portfolioResults = portfolioResults_;
  }

  /**
   * @return Returns isPortfolioLoaded.
   */
  public boolean isPortfolioLoaded()
  {
    return isPortfolioLoaded;
  }

  /**
   * @param isPortfolioLoaded_ Field isPortfolioLoaded to be setted.
   */
  public void setPortfolioLoaded( boolean isPortfolioLoaded_ )
  {
    isPortfolioLoaded = isPortfolioLoaded_;
  }

  /**
   * @return Returns prodAcctCode.
   */
  public BigInteger getProdAcctCode()
  {
    return m_prodAcctCode;
  }

  /**
   * @param prodAcctCode_ Field prodAcctCode to be setted.
   */
  public void setProdAcctCode( BigInteger prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  /**
   * @return Returns prodUnderAcctCode.
   */
  public BigInteger getProdUnderAcctCode()
  {
    return m_prodUnderAcctCode;
  }

  /**
   * @param prodUnderAcctCode_ Field prodUnderAcctCode to be setted.
   */
  public void setProdUnderAcctCode( BigInteger prodUnderAcctCode_ )
  {
    m_prodUnderAcctCode = prodUnderAcctCode_;
  }

  /**
   * @return Returns bkrPortfMgmtMap.
   */
  public Map getBkrPortfMgmtMap()
  {
    return m_bkrPortfMgmtMap;
  }

  /**
   * @param bkrPortfMgmtMap_ Field bkrPortfMgmtMap to be setted.
   */
  public void setBkrPortfMgmtMap( Map bkrPortfMgmtMap_ )
  {
    m_bkrPortfMgmtMap = bkrPortfMgmtMap_;
  }

  /**
   * @return Returns checkBoxMap.
   */
  public Map getCheckBoxMap()
  {
    return m_checkBoxMap;
  }

  /**
   * @param checkBoxMap_ Field checkBoxMap to be setted.
   */
  public void setCheckBoxMap( Map checkBoxMap_ )
  {
    m_checkBoxMap = checkBoxMap_;
  }

  /**
   * @return Returns bkrPortfMgmtMapKey.
   */
  public ArrayList getBkrPortfMgmtMapKey()
  {
    return m_bkrPortfMgmtMapKey;
  }

  /**
   * @param bkrPortfMgmtMapKey_ Field bkrPortfMgmtMapKey to be setted.
   */
  public void setBkrPortfMgmtMapKey( ArrayList bkrPortfMgmtMapKey_ )
  {
    m_bkrPortfMgmtMapKey = bkrPortfMgmtMapKey_;
  }

  /**
   * @return Returns confirmAssociationEnabled.
   */
  public boolean isConfirmAssociationEnabled()
  {
    return m_confirmAssociationEnabled;
  }

  /**
   * @param confirmAssociationEnabled_ Field confirmAssociationEnabled to be
   *          setted.
   */
  public void setConfirmAssociationEnabled( boolean confirmAssociationEnabled_ )
  {
    m_confirmAssociationEnabled = confirmAssociationEnabled_;
  }

  /**
   * @return Returns associationConfirmed.
   */
  public boolean isAssociationConfirmed()
  {
    return m_associationConfirmed;
  }

  /**
   * @param associationConfirmed_ Field associationConfirmed to be setted.
   */
  public void setAssociationConfirmed( boolean associationConfirmed_ )
  {
    m_associationConfirmed = associationConfirmed_;
  }

  /**
   * @return Returns isPmaItemClicked.
   */
  public boolean isPmaItemClicked()
  {
    return m_isPmaItemClicked;
  }

  /**
   * @param isPmaItemClicked_ Field isPmaItemClicked to be setted.
   */
  public void setPmaItemClicked( boolean isPmaItemClicked_ )
  {
    m_isPmaItemClicked = isPmaItemClicked_;
  }

  public boolean isApprove()
  {
    return m_isApprove;
  }

  public void setApprove( boolean isApprove_ )
  {
    m_isApprove = isApprove_;
  }

  public boolean isUpdate()
  {
    return m_isUpdate;
  }

  public void setUpdate( boolean isUpdate_ )
  {
    m_isUpdate = isUpdate_;
  }

}