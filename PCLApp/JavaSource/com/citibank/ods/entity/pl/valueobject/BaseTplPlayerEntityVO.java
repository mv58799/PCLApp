/*
 * Created on 02/04/2007
 *
 */
package com.citibank.ods.entity.pl.valueobject;

import java.math.BigInteger;
import java.util.Date;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

/**
 * @author angelica.almeida
 *  
 */
public class BaseTplPlayerEntityVO extends BaseEntityVO
{

  private String m_plyrCnpjNbr;

  private String m_plyrName;

  private String m_plyrAddrText;

  private Date m_plyrDueDlgDate;

  private String m_plyrDueDlgExecInd;

  private Date m_plyrDueDlgEndDate;

  private Date m_plyrDueDlgRnwDate;

  private Date m_plyrInvstCmtteApprvDate;

  private String m_plyrApprvRstrnText;

  private String m_plyrSuplServText;

  private String m_plyrCmntText;

  private BigInteger m_plyrDvCode;

  private String m_lastUpdUserId;

  private Date m_lastUpdDate;

  /**
   * @return Returns the lastUpdDate.
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * @param lastUpdDate_ The lastUpdDate to set.
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Returns the lastUpdUserId.
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * @param lastUpdUserId_ The lastUpdUserId to set.
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * @return Returns the plyrAddrText.
   */
  public String getPlyrAddrText()
  {
    return m_plyrAddrText;
  }

  /**
   * @param plyrAddrText_ The plyrAddrText to set.
   */
  public void setPlyrAddrText( String plyrAddrText_ )
  {
    m_plyrAddrText = plyrAddrText_;
  }

  /**
   * @return Returns the plyrApprvRstrnText.
   */
  public String getPlyrApprvRstrnText()
  {
    return m_plyrApprvRstrnText;
  }

  /**
   * @param plyrApprvRstrnText_ The plyrApprvRstrnText to set.
   */
  public void setPlyrApprvRstrnText( String plyrApprvRstrnText_ )
  {
    m_plyrApprvRstrnText = plyrApprvRstrnText_;
  }

  /**
   * @return Returns the plyrCmntText.
   */
  public String getPlyrCmntText()
  {
    return m_plyrCmntText;
  }

  /**
   * @param plyrCmntText_ The plyrCmntText to set.
   */
  public void setPlyrCmntText( String plyrCmntText_ )
  {
    m_plyrCmntText = plyrCmntText_;
  }

  /**
   * @return Returns the plyrCnpjNbr.
   */
  public String getPlyrCnpjNbr()
  {
    return m_plyrCnpjNbr;
  }

  /**
   * @param plyrCnpjNbr_ The plyrCnpjNbr to set.
   */
  public void setPlyrCnpjNbr( String plyrCnpjNbr_ )
  {
    m_plyrCnpjNbr = plyrCnpjNbr_;
  }

  /**
   * @return Returns the plyrDueDlgDate.
   */
  public Date getPlyrDueDlgDate()
  {
    return m_plyrDueDlgDate;
  }

  /**
   * @param plyrDueDlgDate_ The plyrDueDlgDate to set.
   */
  public void setPlyrDueDlgDate( Date plyrDueDlgDate_ )
  {
    m_plyrDueDlgDate = plyrDueDlgDate_;
  }

  /**
   * @return Returns the plyrDueDlgEndDate.
   */
  public Date getPlyrDueDlgEndDate()
  {
    return m_plyrDueDlgEndDate;
  }

  /**
   * @param plyrDueDlgEndDate_ The plyrDueDlgEndDate to set.
   */
  public void setPlyrDueDlgEndDate( Date plyrDueDlgEndDate_ )
  {
    m_plyrDueDlgEndDate = plyrDueDlgEndDate_;
  }

  /**
   * @return Returns the plyrDueDlgExecInd.
   */
  public String getPlyrDueDlgExecInd()
  {
    return m_plyrDueDlgExecInd;
  }

  /**
   * @param plyrDueDlgExecInd_ The plyrDueDlgExecInd to set.
   */
  public void setPlyrDueDlgExecInd( String plyrDueDlgExecInd_ )
  {
    m_plyrDueDlgExecInd = plyrDueDlgExecInd_;
  }

  /**
   * @return Returns the plyrDueDlgRnwDate.
   */
  public Date getPlyrDueDlgRnwDate()
  {
    return m_plyrDueDlgRnwDate;
  }

  /**
   * @param plyrDueDlgRnwDate_ The plyrDueDlgRnwDate to set.
   */
  public void setPlyrDueDlgRnwDate( Date plyrDueDlgRnwDate_ )
  {
    m_plyrDueDlgRnwDate = plyrDueDlgRnwDate_;
  }

  /**
   * @return Returns the plyrInvstCmtteApprvDate.
   */
  public Date getPlyrInvstCmtteApprvDate()
  {
    return m_plyrInvstCmtteApprvDate;
  }

  /**
   * @param plyrInvstCmtteApprvDate_ The plyrInvstCmtteApprvDate to set.
   */
  public void setPlyrInvstCmtteApprvDate( Date plyrInvstCmtteApprvDate_ )
  {
    m_plyrInvstCmtteApprvDate = plyrInvstCmtteApprvDate_;
  }

  /**
   * @return Returns the plyrName.
   */
  public String getPlyrName()
  {
    return m_plyrName;
  }

  /**
   * @param plyrName_ The plyrName to set.
   */
  public void setPlyrName( String plyrName_ )
  {
    m_plyrName = plyrName_;
  }

  /**
   * @return Returns the plyrSuplServText.
   */
  public String getPlyrSuplServText()
  {
    return m_plyrSuplServText;
  }

  /**
   * @param plyrSuplServText_ The plyrSuplServText to set.
   */
  public void setPlyrSuplServText( String plyrSuplServText_ )
  {
    m_plyrSuplServText = plyrSuplServText_;
  }

  public BigInteger getPlyrDvCode()
  {
    return m_plyrDvCode;
  }

  public void setPlyrDvCode( BigInteger plyrDvCode_ )
  {
    m_plyrDvCode = plyrDvCode_;
  }
}