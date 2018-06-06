package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * 
 *@author michele.monteiro,02/05/2007
 */

public class BaseTbgSystemEntityVO extends BaseEntityVO
{
  // Codigo da segmentacao do sistema
  private BigInteger m_sysSegCode = null;
  
  // Codigo da segmentacao do sistema
  private BigInteger m_sysCode = null;

  //Nome do sistema
  private String m_sysName = "";
  
  //Codigo do status do sistema
  private String m_sysStatCode="";
  
  //Codigo do tipo de sistema
  private BigInteger m_sysTypeCode = null;
  
  /**
   * @return Retorna o código do sistema.
   */
  public BigInteger getSysCode()
  {
    return m_sysCode;
  }
  /**
   * @param sysCode_.Seta o código do sistema.
   */
  public void setSysCode( BigInteger sysCode_ )
  {
    m_sysCode = sysCode_;
  }
  /**
   * @return Retorna o nome do sistema;
   */
  public String getSysName()
  {
    return m_sysName;
  }
  /**
   * @param sysName_.Seta o nome do sistema;
   */
  public void setSysName( String sysName_ )
  {
    m_sysName = sysName_;
  }
  /**
   * @return Retorna o código da segmentação do sistema.
   */
  public BigInteger getSysSegCode()
  {
    return m_sysSegCode;
  }
  /**
   * @param sysSegCode_.Seta o código da segmentação do sistema;
   */
  public void setSysSegCode( BigInteger sysSegCode_ )
  {
    m_sysSegCode = sysSegCode_;
  }
  /**
   * @return Retorna o status do sistema;
   */
  public String getSysStatCode()
  {
    return m_sysStatCode;
  }
  /**
   * @param sysStatCode_.Seta o status do sistema.
   */
  public void setSysStatCode( String sysStatCode_ )
  {
    m_sysStatCode = sysStatCode_;
  }
  /**
   * @return Retorna o tipo de sistema.
   */
  public BigInteger getSysTypeCode()
  {
    return m_sysTypeCode;
  }
  /**
   * @param sysTypeCode_.Seta o tipo de sistema.
   */
  public void setSysTypeCode( BigInteger sysTypeCode_ )
  {
    m_sysTypeCode = sysTypeCode_;
  }
}
