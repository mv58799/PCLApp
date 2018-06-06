package com.citibank.ods.entity.bg.valueobject;

import com.citibank.ods.common.entity.valueobject.BaseEntityVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * 
 * @see package com.citibank.ods.entity.bg.valueobject;
 * @version 1.0
 * @author michele.monteiro,18/06/2007
 */

public class BaseTbgPointAcctInvstEntityVO extends BaseEntityVO
{

  //Número da filial
  private String m_acctBrchNbr = "";

  //Número da entidade
  private String m_acctBusNbr = "";

  //Número da conta corrente
  private String m_curAcctNbr = "";

  //Número da filial
  private String m_invstAcctBrchNbr = "";

  //Número da entidade
  private String m_invstAcctBusNbr = "";

  //Conta Investimento
  private String m_invstCurAcctNbr = "";

  //Código de origem do movimento
  private String m_custMovOrigCode = "";

  /**
   * @return Returns curAcctNbr.
   */
  public String getCurAcctNbr()
  {
    return m_curAcctNbr;
  }

  /**
   * @param curAcctNbr_ Field curAcctNbr to be setted.
   */
  public void setCurAcctNbr( String curAcctNbr_ )
  {
    m_curAcctNbr = curAcctNbr_;
  }

  /**
   * @return Returns acctBrchNbr.
   */
  public String getAcctBrchNbr()
  {
    return m_acctBrchNbr;
  }

  /**
   * @param acctBrchNbr_ Field acctBrchNbr to be setted.
   */
  public void setAcctBrchNbr( String acctBrchNbr_ )
  {
    m_acctBrchNbr = acctBrchNbr_;
  }

  /**
   * @return Returns acctBusNbr.
   */
  public String getAcctBusNbr()
  {
    return m_acctBusNbr;
  }

  /**
   * @param acctBusNbr_ Field acctBusNbr to be setted.
   */
  public void setAcctBusNbr( String acctBusNbr_ )
  {
    m_acctBusNbr = acctBusNbr_;
  }

  /**
   * @return Returns custOrigCode.
   */
  public String getMovCustOrigCode()
  {
    return m_custMovOrigCode;
  }

  /**
   * @param custOrigCode_ Field custOrigCode to be setted.
   */
  public void setCustMovOrigCode( String custOrigCode_ )
  {
    m_custMovOrigCode = custOrigCode_;
  }

  /**
   * @return Returns invstAcctBrchNbr.
   */
  public String getInvstAcctBrchNbr()
  {
    return m_invstAcctBrchNbr;
  }

  /**
   * @param invstAcctBrchNbr_ Field invstAcctBrchNbr to be setted.
   */
  public void setInvstAcctBrchNbr( String invstAcctBrchNbr_ )
  {
    m_invstAcctBrchNbr = invstAcctBrchNbr_;
  }

  /**
   * @return Returns invstAcctBusNbr.
   */
  public String getInvstAcctBusNbr()
  {
    return m_invstAcctBusNbr;
  }

  /**
   * @param invstAcctBusNbr_ Field invstAcctBusNbr to be setted.
   */
  public void setInvstAcctBusNbr( String invstAcctBusNbr_ )
  {
    m_invstAcctBusNbr = invstAcctBusNbr_;
  }

  /**
   * @return Returns invstCurAcctNbr.
   */
  public String getInvstCurAcctNbr()
  {
    return m_invstCurAcctNbr;
  }

  /**
   * @param invstCurAcctNbr_ Field invstCurAcctNbr to be setted.
   */
  public void setInvstCurAcctNbr( String invstCurAcctNbr_ )
  {
    m_invstCurAcctNbr = invstCurAcctNbr_;
  }
}