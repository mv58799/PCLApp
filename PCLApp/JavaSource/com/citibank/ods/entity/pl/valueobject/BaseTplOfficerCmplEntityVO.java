package com.citibank.ods.entity.pl.valueobject;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author bruno.zanetti
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseTplOfficerCmplEntityVO extends BaseEntityVO
{

  /**
   */
  private Date m_lastUpdDate;
  private String m_lastUpdUserId;
  private BigInteger m_offcrIntlNbr;
  private BigInteger m_offcrNbr;
  private String m_offcrTypeCode;


  /**
   * @return Returns the lAST_UPD_DATE.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @return Returns the lAST_UPD_USER_ID.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @return Returns the oFFCR_INTL_NBR.
   */
  public BigInteger getOffcrIntlNbr()
  {
    return m_offcrIntlNbr;
  }

  /**
   * @return Returns the oFFCR_NBR.
   */
  public BigInteger getOffcrNbr()
  {
    return m_offcrNbr;
  }

  /**
   * @return Returns the oFFCR_TYPE_CODE.
   */
  public String getOffcrTypeCode()
  {
    return m_offcrTypeCode;
  }

  /**
   * @param last_upd_date The lAST_UPD_DATE to set.
   */
  public void setLastUpdDate( Date last_upd_date )
  {
    m_lastUpdDate = last_upd_date;
  }

  /**
   * @param last_upd_user_id The lAST_UPD_USER_ID to set.
   */
  public void setLastUpdUserId( String last_upd_user_id )
  {
    m_lastUpdUserId = last_upd_user_id;
  }

  /**
   * @param offcr_intl_nbr The oFFCR_INTL_NBR to set.
   */
  public void setOffcrIntlNbr( BigInteger offcr_intl_nbr )
  {
    m_offcrIntlNbr = offcr_intl_nbr;
  }

  /**
   * @param offcr_nbr The oFFCR_NBR to set.
   */
  public void setOffcrNbr( BigInteger offcr_nbr )
  {
    m_offcrNbr = offcr_nbr;
  }

  /**
   * @param offcr_type_code The oFFCR_TYPE_CODE to set.
   */
  public void setOffcrTypeCode( String offcr_type_code )
  {
    m_offcrTypeCode = offcr_type_code;
  }
}