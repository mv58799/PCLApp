package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author m.nakamura
 * 
 * Representação da tabela de histórico de contrato.
 */
public class To3ProductAccountHistEntityVO extends
    BaseTo3ProductAccountEntityVO
{
  // Data de referência do registro no histórico
  private Date m_prodAcctRefDate = null;

  /**
   * Seta a data de referência do registro no histórico
   * 
   * @param prodAcctRefDate_ - A data de referência do registro no histórico
   */
  public void setProdAcctRefDate( Date prodAcctRefDate_ )
  {
    m_prodAcctRefDate = prodAcctRefDate_;
  }

  /**
   * Recupera a data de referência do registro no histórico
   * 
   * @return Date - Retorna a data de referência do registro no histórico
   */
  public Date getProdAcctRefDate()
  {
    return m_prodAcctRefDate;
  }
}