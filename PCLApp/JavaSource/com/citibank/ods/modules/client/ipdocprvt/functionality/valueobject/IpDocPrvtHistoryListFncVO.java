package com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;

import java.util.Date;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.ipdocprvt.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 12, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class IpDocPrvtHistoryListFncVO extends BaseIpDocPrvtListFncVO
{

  public static final String C_IP_DOC_REF_DATE_DESCRIPTION = "Data de Referencia do Historico";

  private Date m_ipDocRefDate;

  /**
   * @return Returns ipDocRefDate.
   */
  public Date getIpDocRefDate()
  {
    return m_ipDocRefDate;
  }

  /**
   * @param ipDocRefDate_ Field ipDocRefDate to be setted.
   */
  public void setIpDocRefDate( Date ipDocRefDate_ )
  {
    m_ipDocRefDate = ipDocRefDate_;
  }
}