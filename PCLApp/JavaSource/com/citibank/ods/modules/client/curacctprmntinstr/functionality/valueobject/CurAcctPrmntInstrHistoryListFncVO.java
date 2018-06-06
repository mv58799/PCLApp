package com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject;

import java.math.BigInteger;
import java.util.Date;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.curacctprmntinstr.functionality.valueobject;
 * @version 1.0
 * @author angelica.almeida,18/06/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class CurAcctPrmntInstrHistoryListFncVO extends
    BaseCurAcctPrmntInstrListFncVO
{
  /**
   * Constante da da Data de Referência
   */
  public static final String C_CUR_ACCT_PRMNT_INSTR_REF_DATE = "Data de Referência";

  /**
   * Data de Referencia do registro no historico
   */
  private Date m_curAcctPrmntInstrRefDate;

  //Número do cliente
  private BigInteger m_custNbrHistSrc = null;

  //Código da instrução permanente
  private BigInteger m_prmntInstrCodeHistSrc = null;

  public Date getCurAcctPrmntInstrRefDate()
  {
    return m_curAcctPrmntInstrRefDate;
  }

  public void setCurAcctPrmntInstrRefDate( Date curAcctPrmntInstrRefDate_ )
  {
    m_curAcctPrmntInstrRefDate = curAcctPrmntInstrRefDate_;
  }

  public BigInteger getCustNbrHistSrc()
  {
    return m_custNbrHistSrc;
  }

  public void setCustNbrHistSrc( BigInteger custNbrHistSrc_ )
  {
    m_custNbrHistSrc = custNbrHistSrc_;
  }

  public BigInteger getPrmntInstrCodeHistSrc()
  {
    return m_prmntInstrCodeHistSrc;
  }

  public void setPrmntInstrCodeHistSrc( BigInteger prmntInstrCodeHistSrc_ )
  {
    m_prmntInstrCodeHistSrc = prmntInstrCodeHistSrc_;
  }
}