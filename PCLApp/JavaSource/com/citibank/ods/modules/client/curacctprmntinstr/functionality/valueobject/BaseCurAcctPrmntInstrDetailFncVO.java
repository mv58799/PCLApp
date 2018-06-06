package com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package
 *      com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;
 * @version 1.0
 * @author michele.monteiro,04/06/2007
 *  
 */

public class BaseCurAcctPrmntInstrDetailFncVO extends BaseODSFncVO
{
  //Tela destino do botão buscar
  private String m_clickedSearch = "";

  //Data/hora da última atualização
  private Date m_lastUpdDate = null;

  //Usuário da última atualização
  private String m_lastUpdUserId = "";

  // Codigo do Documento IP
  private BigInteger m_prmntInstrCode = null;

  // Codigo da Conta Produto. Informacao gerada pela ODS Private para
  // identificar uma operacao ou um contrato
  private BigInteger m_prodAcctCode = null;

  // Codigo da sub conta produto. Codigo gerado pela ODS para identificar
  // produtos que tenham subcontas ou subcontratos
  private BigInteger m_prodUnderAcctCode = null;

  //Nome do Cliente
  private String m_custFullName = "";

  //Número da conta corrente
  private BigInteger m_curAcctNbr = null;

  // Numero do Cliente no CMS
  private BigInteger m_custNbr = null;

  ArrayList m_baseTplIpDocPrvtEntityList = null;

  boolean isFromSearch = false;

  // Domains ipDocDomains
  private String[][] ipDocDomains = null;

  // Dataset com indicador de conta CCI - Combo
  private DataSet m_prmntInstrInvstCurAcctIndDomain = null;

  // Indicador de conta CCI
  private String m_prmntInstrInvstCurAcctInd = "";

  //Descrição da instrução permanente
  private String m_prmntInstrText = "";

  //Número da conta CCI
  private String m_invstAcctCurNbr = "";

  //Variável de controle de deleção de ip
  boolean isRelation = false;
  
  //Lista de itens selecionados no grid
  ArrayList m_selectedItemsInGrid = new ArrayList();
  
  //Lista de itens selecionados no grid para exclusão
  ArrayList m_deletedItems = new ArrayList();



  /**
   * Constante do nome da instrução permanente.
   */
  public static final String C_PRMNT_INSTR_CODE = "Número da Instrução Permanente";

  /**
   * @return clickedSearch. Retorna a URL destino do botão buscar.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  /**
   * @param clickedSearch_. Seta a URL destino do botão buscar.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /**
   * @return m_curAcctNbr. Retorna o número da conta corrente.
   */
  public BigInteger getCurAcctNbr()
  {
    return m_curAcctNbr;
  }

  /**
   * @param curAcctNbr_. Seta o número da conta corrente.
   */
  public void setCurAcctNbr( BigInteger curAcctNbr_ )
  {
    m_curAcctNbr = curAcctNbr_;
  }

  /**
   * @return m_custFullName. Retorna o nome do cliente.
   */
  public String getCustFullName()
  {
    return m_custFullName;
  }

  /**
   * @param custFullName_.Seta o número do cliente.
   */
  public void setCustFullName( String custFullName_ )
  {
    m_custFullName = custFullName_;
  }

  /**
   * @return m_custNbr.Retorna o número do cliente.
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_.Seta o número do cliente.
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  /**
   * @return m_lastUpdDate. Retorna a data/hora da última atualização.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_.Seta a data/hora da última atualização.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return m_lastUpdUserId. Retorna o usuário da última atualização.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_. Seta o usuário da última atualiação.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return m_prmntInstrCode. Retorna o número da instrução permanente.
   */
  public BigInteger getPrmntInstrCode()
  {
    return m_prmntInstrCode;
  }

  /**
   * @param prmntInstrCode_. Seta o código da instrução permanente.
   */
  public void setPrmntInstrCode( BigInteger prmntInstrCode_ )
  {
    m_prmntInstrCode = prmntInstrCode_;
  }

  /**
   * @return m_prodAcctCode. Retorna o número da conta produto.
   */
  public BigInteger getProdAcctCode()
  {
    return m_prodAcctCode;
  }

  /**
   * @param prodAcctCode_.Seta o número da conta produto.
   */
  public void setProdAcctCode( BigInteger prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  /**
   * @return m_prodUnderAcctCode. Retorna o número da sub conta produto.
   */
  public BigInteger getProdUnderAcctCode()
  {
    return m_prodUnderAcctCode;
  }

  /**
   * @param prodUnderAcctCode_. Seta o número da sub conta produto.
   */
  public void setProdUnderAcctCode( BigInteger prodUnderAcctCode_ )
  {
    m_prodUnderAcctCode = prodUnderAcctCode_;
  }

  /**
   * @return Returns ipDocDomains.
   */
  public String[][] getIpDocDomains()
  {
    return ipDocDomains;
  }

  /**
   * @param ipDocDomains_ Field ipDocDomains to be setted.
   */
  public void setIpDocDomains( String[][] ipDocDomains_ )
  {
    ipDocDomains = ipDocDomains_;
  }

  /**
   * @return Returns isFromSearch.
   */
  public boolean isFromSearch()
  {
    return isFromSearch;
  }

  /**
   * @param isFromSearch_ Field isFromSearch to be setted.
   */
  public void setFromSearch( boolean isFromSearch_ )
  {
    isFromSearch = isFromSearch_;
  }

  /**
   * @return Returns baseTplIpDocPrvtEntityList.
   */
  public ArrayList getBaseTplIpDocPrvtEntityList()
  {
    return m_baseTplIpDocPrvtEntityList;
  }

  /**
   * @param baseTplIpDocPrvtEntityList_ Field baseTplIpDocPrvtEntityList to be
   *          setted.
   */
  public void setBaseTplIpDocPrvtEntityList(
                                            ArrayList baseTplIpDocPrvtEntityList_ )
  {
    m_baseTplIpDocPrvtEntityList = baseTplIpDocPrvtEntityList_;
  }

  /**
   * @return Returns prmntInstrInvstCurAcctIndDomain.
   */
  public DataSet getPrmntInstrInvstCurAcctIndDomain()
  {
    return m_prmntInstrInvstCurAcctIndDomain;
  }

  /**
   * @param prmntInstrInvstCurAcctIndDomain_ Field
   *          prmntInstrInvstCurAcctIndDomain to be setted.
   */
  public void setPrmntInstrInvstCurAcctIndDomain(
                                                 DataSet prmntInstrInvstCurAcctIndDomain_ )
  {
    m_prmntInstrInvstCurAcctIndDomain = prmntInstrInvstCurAcctIndDomain_;
  }

  /**
   * @return Returns prmntInstrText.
   */
  public String getPrmntInstrText()
  {
    return m_prmntInstrText;
  }

  /**
   * @param prmntInstrText_ Field prmntInstrText to be setted.
   */
  public void setPrmntInstrText( String prmntInstrText_ )
  {
    m_prmntInstrText = prmntInstrText_;
  }

  /**
   * @return Returns prmntInstrInvstCurAcctInd.
   */
  public String getPrmntInstrInvstCurAcctInd()
  {
    return m_prmntInstrInvstCurAcctInd;
  }

  /**
   * @param prmntInstrInvstCurAcctInd_ Field prmntInstrInvstCurAcctInd to be
   *          setted.
   */
  public void setPrmntInstrInvstCurAcctInd( String prmntInstrInvstCurAcctInd_ )
  {
    m_prmntInstrInvstCurAcctInd = prmntInstrInvstCurAcctInd_;
  }

  /**
   * @return Returns invstAcctCurNbr.
   */
  public String getInvstAcctCurNbr()
  {
    return m_invstAcctCurNbr;
  }

  /**
   * @param invstAcctCurNbr_ Field invstAcctCurNbr to be setted.
   */
  public void setInvstAcctCurNbr( String invstAcctCurNbr_ )
  {
    m_invstAcctCurNbr = invstAcctCurNbr_;
  }


  /**
   * @return Returns isRelation.
   */
  public boolean isRelation()
  {
    return isRelation;
  }
  /**
   * @param isRelation_ Field isRelation to be setted.
   */
  public void setRelation( boolean isRelation_ )
  {
    isRelation = isRelation_;
  }
  
  
  /**
   * @return Returns selectedItemsInGrid.
   */
  public ArrayList getSelectedItemsInGrid()
  {
    return m_selectedItemsInGrid;
  }
  /**
   * @param selectedItemsInGrid_ Field selectedItemsInGrid to be setted.
   */
  public void setSelectedItemsInGrid( ArrayList selectedItemsInGrid_ )
  {
    m_selectedItemsInGrid = selectedItemsInGrid_;
  }
  
  
  /**
   * @return Returns deletedItems.
   */
  public ArrayList getDeletedItems()
  {
    return m_deletedItems;
  }
  /**
   * @param deletedItems_ Field deletedItems to be setted.
   */
  public void setDeletedItems( ArrayList deletedItems_ )
  {
    m_deletedItems = deletedItems_;
  }
}