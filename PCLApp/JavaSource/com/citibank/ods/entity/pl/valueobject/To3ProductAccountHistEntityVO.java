package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author m.nakamura
 * 
 * Representa��o da tabela de hist�rico de contrato.
 */
public class To3ProductAccountHistEntityVO extends
    BaseTo3ProductAccountEntityVO
{
  // Data de refer�ncia do registro no hist�rico
  private Date m_prodAcctRefDate = null;

  /**
   * Seta a data de refer�ncia do registro no hist�rico
   * 
   * @param prodAcctRefDate_ - A data de refer�ncia do registro no hist�rico
   */
  public void setProdAcctRefDate( Date prodAcctRefDate_ )
  {
    m_prodAcctRefDate = prodAcctRefDate_;
  }

  /**
   * Recupera a data de refer�ncia do registro no hist�rico
   * 
   * @return Date - Retorna a data de refer�ncia do registro no hist�rico
   */
  public Date getProdAcctRefDate()
  {
    return m_prodAcctRefDate;
  }
}