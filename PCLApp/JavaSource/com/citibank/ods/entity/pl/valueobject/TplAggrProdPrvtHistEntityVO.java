/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.entity.pl.valueobject;

import java.util.Date;

/**
 * @author fernando.salgado
 * 
 * Tabela Historica de Agrupador de Produtos Private
 */
public class TplAggrProdPrvtHistEntityVO extends BaseTplAggrProdPrvtEntityVO
{
  /**
   * Data de Referencia do registro no historico
   */
  private Date m_prvtProdAggrRefDate;

  
  /**
   * @return Returns the prvtProdAggrRefDate.
   */
  public Date getPrvtProdAggrRefDate()
  {
    return m_prvtProdAggrRefDate;
  }
  /**
   * @param prvtProdAggrRefDate_ The prvtProdAggrRefDate to set.
   */
  public void setPrvtProdAggrRefDate( Date prvtProdAggrRefDate_ )
  {
    m_prvtProdAggrRefDate = prvtProdAggrRefDate_;
  }
}