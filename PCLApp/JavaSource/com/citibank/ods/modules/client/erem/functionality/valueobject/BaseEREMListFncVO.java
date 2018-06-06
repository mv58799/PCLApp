package com.citibank.ods.modules.client.erem.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package com.citibank.ods.modules.client.erem.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Apr 9, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseEREMListFncVO extends BaseFncVO
{

  private String m_erNbrSrc = null;

  private String m_emNbrSrc = null;

  private BigInteger m_custNbrSrc = null;

  private String m_custFullNameTextSrc = null;

  private BigInteger m_reltnNbrSrc = null;

  private BigInteger m_acctNbr = null;

  private DataSet m_results = null;

  private String m_selectedEmNbr = null;

  private String m_selectedErNbr = null;

  public static final String C_ER_EM_REF_DATE_DESCRIPTION = "Data de Referência";

  public static final String C_ER_NBR_SRC_DESCRIPTION = "Número ER";

  public static final String C_EM_NBR_SRC_DESCRIPTION = "Número EM";

  public static final String C_CUST_NBR_SRC_DESCRIPTION = "Número do Cliente";

  public static final String C_CUST_FULL_NAME_TEXT_DESCRIPTION = "Nome do Cliente";

  public static final String C_CUR_ACCT_NBR_DESCRIPTION = "Número da Conta Corrente";

  public static final String C_RELTN_NBR_DESCRIPTION = "Número do Relacionamento";
  
  //Dados Comp´lementares de ER
  
  

  private String clickedSearch;

  private boolean isFromSearch = false;

  /**
   * @return Returns custNbrSrc.
   */
  public BigInteger getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbrSrc_ Field custNbrSrc to be setted.
   */
  public void setCustNbrSrc( BigInteger custNbrSrc_ )
  {
    m_custNbrSrc = custNbrSrc_;
  }

  /**
   * @return Returns erNbrSrc.
   */
  public String getErNbrSrc()
  {
    return m_erNbrSrc;
  }

  /**
   * @param erNbrSrc_ Field erNbrSrc to be setted.
   */
  public void setErNbrSrc( String erNbrSrc_ )
  {
    m_erNbrSrc = erNbrSrc_;
  }

  /**
   * @return Returns results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ Field results to be setted.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * @return Returns selectedEmNbr.
   */
  public String getSelectedEmNbr()
  {
    return m_selectedEmNbr;
  }

  /**
   * @param selectedEmNbr_ Field selectedEmNbr to be setted.
   */
  public void setSelectedEmNbr( String selectedEmNbr_ )
  {
    m_selectedEmNbr = selectedEmNbr_;
  }

  /**
   * @return Returns selectedErNbr.
   */
  public String getSelectedErNbr()
  {
    return m_selectedErNbr;
  }

  /**
   * @param selectedErNbr_ Field selectedErNbr to be setted.
   */
  public void setSelectedErNbr( String selectedErNbr_ )
  {
    m_selectedErNbr = selectedErNbr_;
  }

  /**
   * @return Returns emNbrSrc.
   */
  public String getEmNbrSrc()
  {
    return m_emNbrSrc;
  }

  /**
   * @param emNbrSrc_ Field emNbrSrc to be setted.
   */
  public void setEmNbrSrc( String emNbrSrc_ )
  {
    m_emNbrSrc = emNbrSrc_;
  }

  /**
   * @return Returns custFullNameTextSrc.
   */
  public String getCustFullNameTextSrc()
  {
    return m_custFullNameTextSrc;
  }

  /**
   * @param custFullNameTextSrc_ Field custFullNameTextSrc to be setted.
   */
  public void setCustFullNameTextSrc( String custFullNameTextSrc_ )
  {
    m_custFullNameTextSrc = custFullNameTextSrc_;
  }

  /**
   * @return Returns the reltnNbrSrc.
   */
  public BigInteger getReltnNbrSrc()
  {
    return m_reltnNbrSrc;
  }

  /**
   * @param reltnNbrSrc_ The reltnNbrSrc to set.
   */
  public void setReltnNbrSrc( BigInteger reltnNbrSrc_ )
  {
    m_reltnNbrSrc = reltnNbrSrc_;
  }

  /**
   * @return Returns the acctNbr.
   */
  public BigInteger getAcctNbr()
  {
    return m_acctNbr;
  }

  /**
   * @param acctNbr_ The acctNbr to set.
   */
  public void setAcctNbr( BigInteger acctNbr_ )
  {
    m_acctNbr = acctNbr_;
  }

  /**
   * @return Returns the clickedSearch.
   */
  public String getClickedSearch()
  {
    return clickedSearch;
  }

  /**
   * @param clickedSearch_ The clickedSearch to set.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    clickedSearch = clickedSearch_;
  }

  public boolean isFromSearch()
  {
    return isFromSearch;
  }

  public void setFromSearch( boolean isFromSearch_ )
  {
    isFromSearch = isFromSearch_;
  }
}