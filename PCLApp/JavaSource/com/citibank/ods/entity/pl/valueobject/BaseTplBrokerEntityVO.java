package com.citibank.ods.entity.pl.valueobject;

import java.math.BigDecimal;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author Hamilton Matos
 */
public class BaseTplBrokerEntityVO extends BaseEntityVO
{
  //CNPJ da Corretora.
  private String m_bkrCnpjNbr;

  //Nome Completo da corretora
  private String m_bkrNameText;

  //Endereco da corretora.
  private String m_bkrAddrText;

  //Codigo de Mercado na BMF em que a corretora atua.
  private String m_bkrBmfMktCode;

  //Codigo de Mercado na Bovespa em que a corretora atua.
  private String m_bkrBovespaMktCode;

  //Percentual de rebate (repasse) da BMF
  private BigDecimal m_bkrRbtBmfRate;

  //Percentual de rebate (repasse) da Bovespa.
  private BigDecimal m_bkrRbtBovespaRate;

  //Data de Solicitacao da aprovacao.
  private Date m_bkrReqDate;

  //Data de renovacao
  private Date m_bkrRnwDate;

  //Codigo do status da aprovacao da corretora.
  private String m_bkrApprvStatCode;

  //Data da aprovacao
  private Date m_bkrApprvDate;

  //Descricao do Andamento do Processo de aprovacao.
  private String m_bkrAuthProcSitText;

  //Valor do Limite de Credito em reais solicitado.
  private BigDecimal m_bkrReqCrLimLcyAmt;

  //Valordo Limite de Credito em Real aprovado.
  private BigDecimal m_bkrApprvCrLimLcyAmt;

  //Valor do Limite de Credito em Dolar solicitado.
  private BigDecimal m_bkrReqCrLimDlrAmt;

  //Valor do Limite de Credito em Dolar aprovado.
  private BigDecimal m_bkrApprvCrLimDlrAmt;

  //Descricao dos Servicos Prestados
  private String m_bkrSuplServText;

  //Campo de Observacao
  private String m_bkrCommentText;

  //Data e hora da ultima atualizacao efetuada pelo usuario
  private Date m_lastUpdDate;

  //Codigo do usuario (SOE ID) que efetuou a ultima atualizacao no registro
  private String m_lastUpdUserId;

  /**
   * @return Returns bkrAddrText.
   */
  public String getBkrAddrText()
  {
    return m_bkrAddrText;
  }

  /**
   * @param bkrAddrText_ Field bkrAddrText to be setted.
   */
  public void setBkrAddrText( String bkrAddrText_ )
  {
    m_bkrAddrText = bkrAddrText_;
  }

  /**
   * @return Returns bkrApprvCrLimDlrAmt.
   */
  public BigDecimal getBkrApprvCrLimDlrAmt()
  {
    return m_bkrApprvCrLimDlrAmt;
  }

  /**
   * @param bkrApprvCrLimDlrAmt_ Field bkrApprvCrLimDlrAmt to be setted.
   */
  public void setBkrApprvCrLimDlrAmt( BigDecimal bkrApprvCrLimDlrAmt_ )
  {
    m_bkrApprvCrLimDlrAmt = bkrApprvCrLimDlrAmt_;
  }

  /**
   * @return Returns bkrApprvCrLimLcyAmt.
   */
  public BigDecimal getBkrApprvCrLimLcyAmt()
  {
    return m_bkrApprvCrLimLcyAmt;
  }

  /**
   * @param bkrApprvCrLimLcyAmt_ Field bkrApprvCrLimLcyAmt to be setted.
   */
  public void setBkrApprvCrLimLcyAmt( BigDecimal bkrApprvCrLimLcyAmt_ )
  {
    m_bkrApprvCrLimLcyAmt = bkrApprvCrLimLcyAmt_;
  }

  /**
   * @return Returns bkrApprvDate.
   */
  public Date getBkrApprvDate()
  {
    return m_bkrApprvDate;
  }

  /**
   * @param bkrApprvDate_ Field bkrApprvDate to be setted.
   */
  public void setBkrApprvDate( Date bkrApprvDate_ )
  {
    m_bkrApprvDate = bkrApprvDate_;
  }

  /**
   * @return Returns bkrApprvStatCode.
   */
  public String getBkrApprvStatCode()
  {
    return m_bkrApprvStatCode;
  }

  /**
   * @param bkrApprvStatCode_ Field bkrApprvStatCode to be setted.
   */
  public void setBkrApprvStatCode( String bkrApprvStatCode_ )
  {
    m_bkrApprvStatCode = bkrApprvStatCode_;
  }

  /**
   * @return Returns bkrAuthProcSitText.
   */
  public String getBkrAuthProcSitText()
  {
    return m_bkrAuthProcSitText;
  }

  /**
   * @param bkrAuthProcSitText_ Field bkrAuthProcSitText to be setted.
   */
  public void setBkrAuthProcSitText( String bkrAuthProcSitText_ )
  {
    m_bkrAuthProcSitText = bkrAuthProcSitText_;
  }

  /**
   * @return Returns bkrBmfMktCode.
   */
  public String getBkrBmfMktCode()
  {
    return m_bkrBmfMktCode;
  }

  /**
   * @param bkrBmfMktCode_ Field bkrBmfMktCode to be setted.
   */
  public void setBkrBmfMktCode( String bkrBmfMktCode_ )
  {
    m_bkrBmfMktCode = bkrBmfMktCode_;
  }

  /**
   * @return Returns bkrBovespaMktCode.
   */
  public String getBkrBovespaMktCode()
  {
    return m_bkrBovespaMktCode;
  }

  /**
   * @param bkrBovespaMktCode_ Field bkrBovespaMktCode to be setted.
   */
  public void setBkrBovespaMktCode( String bkrBovespaMktCode_ )
  {
    m_bkrBovespaMktCode = bkrBovespaMktCode_;
  }

  /**
   * @return Returns bkrCnpjNbr.
   */
  public String getBkrCnpjNbr()
  {
    return m_bkrCnpjNbr;
  }

  /**
   * @param bkrCnpjNbr_ Field bkrCnpjNbr to be setted.
   */
  public void setBkrCnpjNbr( String bkrCnpjNbr_ )
  {
    m_bkrCnpjNbr = bkrCnpjNbr_;
  }

  /**
   * @return Returns bkrCommentText.
   */
  public String getBkrCommentText()
  {
    return m_bkrCommentText;
  }

  /**
   * @param bkrCommentText_ Field bkrCommentText to be setted.
   */
  public void setBkrCommentText( String bkrCommentText_ )
  {
    m_bkrCommentText = bkrCommentText_;
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
   * @return Returns bkrRbtBmfRate.
   */
  public BigDecimal getBkrRbtBmfRate()
  {
    return m_bkrRbtBmfRate;
  }

  /**
   * @param bkrRbtBmfRate_ Field bkrRbtBmfRate to be setted.
   */
  public void setBkrRbtBmfRate( BigDecimal bkrRbtBmfRate_ )
  {
    m_bkrRbtBmfRate = bkrRbtBmfRate_;
  }

  /**
   * @return Returns bkrRbtBovespaRate.
   */
  public BigDecimal getBkrRbtBovespaRate()
  {
    return m_bkrRbtBovespaRate;
  }

  /**
   * @param bkrRbtBovespaRate_ Field bkrRbtBovespaRate to be setted.
   */
  public void setBkrRbtBovespaRate( BigDecimal bkrRbtBovespaRate_ )
  {
    m_bkrRbtBovespaRate = bkrRbtBovespaRate_;
  }

  /**
   * @return Returns bkrReqCrLimDlrAmt.
   */
  public BigDecimal getBkrReqCrLimDlrAmt()
  {
    return m_bkrReqCrLimDlrAmt;
  }

  /**
   * @param bkrReqCrLimDlrAmt_ Field bkrReqCrLimDlrAmt to be setted.
   */
  public void setBkrReqCrLimDlrAmt( BigDecimal bkrReqCrLimDlrAmt_ )
  {
    m_bkrReqCrLimDlrAmt = bkrReqCrLimDlrAmt_;
  }

  /**
   * @return Returns bkrReqCrLimLcyAmt.
   */
  public BigDecimal getBkrReqCrLimLcyAmt()
  {
    return m_bkrReqCrLimLcyAmt;
  }

  /**
   * @param bkrReqCrLimLcyAmt_ Field bkrReqCrLimLcyAmt to be setted.
   */
  public void setBkrReqCrLimLcyAmt( BigDecimal bkrReqCrLimLcyAmt_ )
  {
    m_bkrReqCrLimLcyAmt = bkrReqCrLimLcyAmt_;
  }

  /**
   * @return Returns bkrReqDate.
   */
  public Date getBkrReqDate()
  {
    return m_bkrReqDate;
  }

  /**
   * @param bkrReqDate_ Field bkrReqDate to be setted.
   */
  public void setBkrReqDate( Date bkrReqDate_ )
  {
    m_bkrReqDate = bkrReqDate_;
  }

  /**
   * @return Returns bkrRnwDate.
   */
  public Date getBkrRnwDate()
  {
    return m_bkrRnwDate;
  }

  /**
   * @param bkrRnwDate_ Field bkrRnwDate to be setted.
   */
  public void setBkrRnwDate( Date bkrRnwDate_ )
  {
    m_bkrRnwDate = bkrRnwDate_;
  }

  /**
   * @return Returns bkrSuplServText.
   */
  public String getBkrSuplServText()
  {
    return m_bkrSuplServText;
  }

  /**
   * @param bkrSuplServText_ Field bkrSuplServText to be setted.
   */
  public void setBkrSuplServText( String bkrSuplServText_ )
  {
    m_bkrSuplServText = bkrSuplServText_;
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
}