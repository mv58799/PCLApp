package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * Classe que instancia os valores correspondente a um registro da tabela
 * Tpl_Bkr_Portf_Mgmt
 * @author Hamilton Matos
 */

public class BaseTplBkrPortfMgmtEntityVO extends BaseEntityVO
{

  // Nome Completo da corretora
  private String m_bkrNameText;

  // CNPJ da Corretora
  private String m_bkrCnpjNbr;

  // Conta corrente BMF
  private String m_bmfCurAcctNbr;

  // Conta investimento BMF
  private String m_bmfInvstAcctNbr;

  // Conta corrente Bovespa
  private String m_bovespaCurAcctNbr;

  // Conta investimento Bovespa
  private String m_bovespaInvstAcctNbr;

  // Data e Hora que o usuário aprovou o registro cadastrado
  private Date m_lastUpdDate;

  // Codigo do usuario (SOE ID) que efetuou a última atualização no registro
  private String m_lastUpdUserId;

  // Código da Conta Produto
  private BigInteger m_prodAcctCode;

  // Código da sub conta produto
  private BigInteger m_prodUnderAcctCode;

  // Código do produto.
  private String m_prodCode = "";

  // Endereco da corretora
  private String m_bkrAddrText;

  // Código do cliente
  private BigInteger m_bkrCustNbr;

  // Nome completo do cliente
  private String m_custFullNameText;

  // Número do cliente
  private BigInteger m_custNbr;

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
   * @return Returns bmfCurAcctNbr.
   */
  public String getBmfCurAcctNbr()
  {
    return m_bmfCurAcctNbr;
  }

  /**
   * @param bmfCurAcctNbr_ Field bmfCurAcctNbr to be setted.
   */
  public void setBmfCurAcctNbr( String bmfCurAcctNbr_ )
  {
    m_bmfCurAcctNbr = bmfCurAcctNbr_;
  }

  /**
   * @return Returns bmfInvstAcctNbr.
   */
  public String getBmfInvstAcctNbr()
  {
    return m_bmfInvstAcctNbr;
  }

  /**
   * @param bmfInvstAcctNbr_ Field bmfInvstAcctNbr to be setted.
   */
  public void setBmfInvstAcctNbr( String bmfInvstAcctNbr_ )
  {
    m_bmfInvstAcctNbr = bmfInvstAcctNbr_;
  }

  /**
   * @return Returns bovespaCurAcctNbr.
   */
  public String getBovespaCurAcctNbr()
  {
    return m_bovespaCurAcctNbr;
  }

  /**
   * @param bovespaCurAcctNbr_ Field bovespaCurAcctNbr to be setted.
   */
  public void setBovespaCurAcctNbr( String bovespaCurAcctNbr_ )
  {
    m_bovespaCurAcctNbr = bovespaCurAcctNbr_;
  }

  /**
   * @return Returns bovespaInvstAcctNbr.
   */
  public String getBovespaInvstAcctNbr()
  {
    return m_bovespaInvstAcctNbr;
  }

  /**
   * @param bovespaInvstAcctNbr_ Field bovespaInvstAcctNbr to be setted.
   */
  public void setBovespaInvstAcctNbr( String bovespaInvstAcctNbr_ )
  {
    m_bovespaInvstAcctNbr = bovespaInvstAcctNbr_;
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
   * @return Returns custNbr.
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * @param custNbr_ Field custNbr to be setted.
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
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
   * @return Returns bkrCustNbr.
   */
  public BigInteger getBkrCustNbr()
  {
    return m_bkrCustNbr;
  }

  /**
   * @param bkrCustNbr_ Field bkrCustNbr to be setted.
   */
  public void setBkrCustNbr( BigInteger bkrCustNbr_ )
  {
    m_bkrCustNbr = bkrCustNbr_;
  }

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
   * @return Returns prodCode.
   */
  public String getProdCode()
  {
    return m_prodCode;
  }

  /**
   * @param prodCode_ Field prodCode to be setted.
   */
  public void setProdCode( String prodCode_ )
  {
    m_prodCode = prodCode_;
  }
}