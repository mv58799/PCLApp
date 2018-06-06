package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de Memória de Risco.
 */
public class BaseTplMrDocPrvtEntityVO extends BaseEntityVO
{
  // Data e Hora da Ultima Atualizacao Efetuada pelo Usuario.
  private Date m_lastUpdDate = null;

  // Codigo do Usuario que Efetuou a Ultima Atualizacao no Registro.
  private String m_lastUpdUserId = "";

  // Descricao do Instrucao Permanente
  private String m_mrDocText = "";

  // Indicador de Utilizacao de Conta CCI: 'S' (Sim), 'N' (Nao)
  private String m_mrInvstCurAcctInd = "";

  // Codigo da Conta Produto
  private BigInteger m_prodAcctCode = null;

  // Codigo da Sub-conta Produto
  private BigInteger m_prodUnderAcctCode = null;

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
   * Recupera a Descricao do Instrucao Permanente
   * 
   * @return Retorna a Descricao do Instrucao Permanente
   */
  public String getMrDocText()
  {
    return m_mrDocText;
  }

  /**
   * Seta a Descricao do Instrucao Permanente
   * 
   * @param mrDocText_ - A Descricao do Instrucao Permanente
   */
  public void setMrDocText( String mrDocText_ )
  {
    m_mrDocText = mrDocText_;
  }

  /**
   * Recupera o Indicador de Utilizacao de Conta CCI
   * 
   * @return Retorna o Indicador de Utilizacao de Conta CCI
   */
  public String getMrInvstCurAcctInd()
  {
    return m_mrInvstCurAcctInd;
  }

  /**
   * Seta o Indicador de Utilizacao de Conta CCI
   * 
   * @param mrInvstCurAcctInd_ - O Indicador de Utilizacao de Conta CCI
   */
  public void setMrInvstCurAcctInd( String mrInvstCurAcctInd_ )
  {
    m_mrInvstCurAcctInd = mrInvstCurAcctInd_;
  }

  /**
   * Retorna o Codigo da Conta Produto
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
   * @param prodAcctCode_ - o Codigo da Conta Produto
   */
  public void setProdAcctCode( BigInteger prodAcctCode_ )
  {
    m_prodAcctCode = prodAcctCode_;
  }

  /**
   * Recupera o Codigo da Sub-conta Produto
   * 
   * @return Retorna o Codigo da Sub-conta Produto
   */
  public BigInteger getProdUnderAcctCode()
  {
    return m_prodUnderAcctCode;
  }

  /**
   * Seta o Codigo da Sub-conta Produto
   * 
   * @param prodUnderAcctCode_ - O Codigo da Sub-conta Produto
   */
  public void setProdUnderAcctCode( BigInteger prodUnderAcctCode_ )
  {
    m_prodUnderAcctCode = prodUnderAcctCode_;
  }
}