package com.citibank.ods.modules.product.prodplayerrole.functionality.valueobject;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.product.playerproduct.functionality.valueobject; 
 *@version 1.0
 *@author atilio.l.araujo,Apr 4, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class ProdPlayerRoleMovementListFncVO extends BaseProdPlayerRoleListFncVO
{
  /*
   * Último usuário alteração
   */
  private String m_lastUpdUserIdSrc;
    
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
