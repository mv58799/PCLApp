/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.customerprvtcmpl.functionality.valueobject;

/**
 * @author bruno.zanetti
 *
 */
public class CustomerPrvtCmplMovementListFncVO extends BaseCustomerPrvtCmplListFncVO
{
  private String m_lastUpdUserIdSrc;
    
  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Usuário";

  /**
   * @return Returns lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }
  /**
   * @param lastUpdUserIdSrc_ Field lastUpdUserIdSrc to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }

}