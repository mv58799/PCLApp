package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @author michele.monteiro,02/05/2007
 *  
 */

public class BaseTbgSystemSegmentEntityVO extends BaseEntityVO
{
  // Codigo da segmentacao do sistema
  private BigInteger m_sysSegCode = null;

  // Nome da segmentacao do sistema
  private String m_sysSegName = "";

  /**
   * @return Retorna o código do segmento do sistema.
   */
  public BigInteger getSysSegCode()
  {
    return m_sysSegCode;
  }

  /**
   * @param sysSegCode_.Seta o código do segmento do sistema.
   */
  public void setSysSegCode( BigInteger sysSegCode_ )
  {
    m_sysSegCode = sysSegCode_;
  }

  /**
   * @return Retorna o nome da segmentação do sistema.
   */
  public String getSysSegName()
  {
    return m_sysSegName;
  }

  /**
   * @param sysSegName_.Seta o nome da segmentação do sistema.
   */
  public void setSysSegName( String sysSegName_ )
  {
    m_sysSegName = sysSegName_;
  }
}