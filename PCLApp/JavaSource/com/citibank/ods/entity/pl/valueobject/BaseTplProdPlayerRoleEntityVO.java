package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.entity.pl.valueobject;
 * @version 1.0
 * @author acacio.domingos,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseTplProdPlayerRoleEntityVO extends BaseEntityVO
{
  private String m_prodCode;

  private String m_sysCode;

  private BigInteger m_sysSegCode;

  private String m_plyrCnpjNbr;

  private String m_plyrRoleTypeCode;

  private String m_lastUpdUserId;

  private Date m_lastUpdDate;

  /**
   * @return Returns lastUpdDate.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ Field lastUpdDate to be setted.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ Field lastUpdUserId to be setted.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns plyrCnpjNbr.
   */
  public String getPlyrCnpjNbr()
  {
    return m_plyrCnpjNbr;
  }

  /**
   * @param plyrCnpjNbr_ Field plyrCnpjNbr to be setted.
   */
  public void setPlyrCnpjNbr( String plyrCnpjNbr_ )
  {
    m_plyrCnpjNbr = plyrCnpjNbr_;
  }

  /**
   * @return Returns plyrRoleTypeCode.
   */
  public String getPlyrRoleTypeCode()
  {
    return m_plyrRoleTypeCode;
  }

  /**
   * @param plyrRoleTypeCode_ Field plyrRoleTypeCode to be setted.
   */
  public void setPlyrRoleTypeCode( String plyrRoleTypeCode_ )
  {
    m_plyrRoleTypeCode = plyrRoleTypeCode_;
  }

  /**
   * @return Returns prodCode.
   */
  public String getProdCode()
  {
    return m_prodCode;
  }

  /**
   * @param prodCode_ Field prodCode to be setted.
   */
  public void setProdCode( String prodCode_ )
  {
    m_prodCode = prodCode_;
  }

  /**
   * @return Returns sysCode.
   */
  public String getSysCode()
  {
    return m_sysCode;
  }

  /**
   * @param sysCode_ Field sysCode to be setted.
   */
  public void setSysCode( String sysCode_ )
  {
    m_sysCode = sysCode_;
  }

  /**
   * @return Returns sysSegCode.
   */
  public BigInteger getSysSegCode()
  {
    return m_sysSegCode;
  }

  /**
   * @param sysSegCode_ Field sysSegCode to be setted.
   */
  public void setSysSegCode( BigInteger sysSegCode_ )
  {
    m_sysSegCode = sysSegCode_;
  }
}