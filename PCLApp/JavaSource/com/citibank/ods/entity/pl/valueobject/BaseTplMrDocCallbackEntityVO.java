package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de relacionamento Contato X Memória de Risco.
 */
public class BaseTplMrDocCallbackEntityVO extends BaseEntityVO
{
  // Numero do Contato
  private BigInteger m_ctcNbr = null;

  // Numero do Cliente no CMS (Customer Number)
  private BigInteger m_custNbr = null;

  // Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
  private Date m_lastUpdDate = null;

  // Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
  private String m_lastUpdUserId = "";

  // Codigo do Callback
  private BigInteger m_mrCallbackCode = null;

  // Codigo da Conta Produto
  private BigInteger m_prodAcctCode = null;

  // Codigo da Sub-conta Produto
  private BigInteger m_prodUnderAcctCode = null;

  /**
   * Recupera o Numero do Contato.
   * 
   * @return Retorna o Numero do Contato.
   */
  public BigInteger getCtcNbr()
  {
    return m_ctcNbr;
  }

  /**
   * Seta o Numero do Contato.
   * 
   * @param ctcNbr_ - O Numero do Contato.
   */
  public void setCtcNbr( BigInteger ctcNbr_ )
  {
    m_ctcNbr = ctcNbr_;
  }

  /**
   * Recupera o Numero do Cliente no CMS
   * 
   * @return Retorna o Numero do Cliente no CMS
   */
  public BigInteger getCustNbr()
  {
    return m_custNbr;
  }

  /**
   * Seta o Numero do Cliente no CMS
   * 
   * @param custNbr_ - O Numero do Cliente no CMS
   */
  public void setCustNbr( BigInteger custNbr_ )
  {
    m_custNbr = custNbr_;
  }

  /**
   * Recupera a Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
   * 
   * @return Retorna a Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * Seta a Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
   * 
   * @param lastUpdDate_ - A Data e Hora da Ultima Atualizacao Efetuada pelo
   *          Usuario.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * Recupera o Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
   * 
   * @return Retorna o Codigo do Usuario que Efetuou a Ultima Atualizacao no
   *         Registro.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * Seta o Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
   * 
   * @param lastUpdUserId_ - O Codigo do Usuario que Efetuou a Ultima
   *          Atualizacao no Registro.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * Recupera o Codigo do Callback
   * 
   * @return Retorna o Codigo do Callback
   */
  public BigInteger getMrCallbackCode()
  {
    return m_mrCallbackCode;
  }

  /**
   * Seta o Codigo do Callback
   * 
   * @param mrCallbackCode_ - O Codigo do Callback
   */
  public void setMrCallbackCode( BigInteger mrCallbackCode_ )
  {
    m_mrCallbackCode = mrCallbackCode_;
  }

  /**
   * Recupera o Codigo da Conta Produto
   * 
   * @return Retorna o Codigo da Conta Produto
   */
  public BigInteger getProdAcctCode()
  {
    return m_prodAcctCode;
  }

  /**
   * Seta o Codigo da Conta Produto
   * 
   * @param prodAcctCode_ - O Codigo da Conta Produto
   */
  public void setProdAcctCode( BigInteger prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  /**
   * Recupera o Codigo da Sub-Conta Produto
   * 
   * @return Retorna o Codigo da Sub-Conta Produto
   */
  public BigInteger getProdUnderAcctCode()
  {
    return m_prodUnderAcctCode;
  }

  /**
   * Seta o Codigo da Sub-Conta Produto
   * 
   * @param prodUnderAcctCode_ - O Codigo da Sub-Conta Produto
   */
  public void setProdUnderAcctCode( BigInteger prodUnderAcctCode_ )
  {
    m_prodUnderAcctCode = prodUnderAcctCode_;
  }

}