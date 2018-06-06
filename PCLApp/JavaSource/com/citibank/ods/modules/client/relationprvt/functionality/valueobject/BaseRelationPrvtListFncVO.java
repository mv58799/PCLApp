package com.citibank.ods.modules.client.relationprvt.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.relationprvt.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 29, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseRelationPrvtListFncVO extends BaseFncVO
{

  private String m_ownerCustNbrInd = "";

  private BigInteger m_custNbrSrc = null;

  private String m_custFullNameTextSrc = "";

  private BigInteger m_acctNbrSrc = null;

  private BigInteger m_reltnNbrSrc = null;

  private BigInteger m_custCpfCnpjNbrSrc = null;

  private String m_selectedReltnNbr = null;

  private String m_clickedSearch = "";

  //Variável de controle - Buscar
  private boolean m_isFromSearch = false;

  public static final String C_CUST_NBR_SRC_DESCRIPTION = "Número do Cliente";

  public static final String C_ACCT_NBR_SRC_DESCRIPTION = "Número da Conta Corrente";

  public static final String C_RELTN_NBR_SRC_DESCRIPTION = "Número do Relacionamento";

  public static final String C_CUST_CPF_CNPJ_NBR_SRC_DESCRIPTION = "Número do CPF/CNPJ";

  public static final String C_OWNER_SELECTED_SRC_DESCRIPTION = "Indicador de Owner";

  public static final String C_CUST_FULL_NAME_TEXT_SRC_DESCRIPTION = "Nome do Cliente";

  /**
   * @return Returns the acctNbrSrc.
   */
  public BigInteger getAcctNbrSrc()
  {
    return m_acctNbrSrc;
  }

  /**
   * @param acctNbrSrc_ The acctNbrSrc to set.
   */
  public void setAcctNbrSrc( BigInteger acctNbrSrc_ )
  {
    m_acctNbrSrc = acctNbrSrc_;
  }

  /**
   * @return Returns the custCpfCnpjNbrSrc.
   */
  public BigInteger getCustCpfCnpjNbrSrc()
  {
    return m_custCpfCnpjNbrSrc;
  }

  /**
   * @param custCpfCnpjNbrSrc_ The custCpfCnpjNbrSrc to set.
   */
  public void setCustCpfCnpjNbrSrc( BigInteger custCpfCnpjNbrSrc_ )
  {
    m_custCpfCnpjNbrSrc = custCpfCnpjNbrSrc_;
  }

  /**
   * @return Returns the custNbrSrc.
   */
  public BigInteger getCustNbrSrc()
  {
    return m_custNbrSrc;
  }

  /**
   * @param custNbrSrc_ The custNbrSrc to set.
   */
  public void setCustNbrSrc( BigInteger custNbrSrc_ )
  {
    m_custNbrSrc = custNbrSrc_;
  }

  /**
   * @return Returns the ownerCustNbrInd.
   */
  public String getOwnerCustNbrInd()
  {
    return m_ownerCustNbrInd;
  }

  /**
   * @param ownerCustNbrInd_ The ownerCustNbrInd to set.
   */
  public void setOwnerCustNbrInd( String ownerCustNbrInd_ )
  {
    m_ownerCustNbrInd = ownerCustNbrInd_;
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
   * @return Returns the results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param results_ The results to set.
   */
  public void setResults( DataSet results_ )
  {
    m_results = results_;
  }

  /**
   * @return Returns the selectedReltnNbr.
   */
  public String getSelectedReltnNbr()
  {
    return m_selectedReltnNbr;
  }

  /**
   * @param selectedReltnNbr_ The selectedReltnNbr to set.
   */
  public void setSelectedReltnNbr( String selectedReltnNbr_ )
  {
    m_selectedReltnNbr = selectedReltnNbr_;
  }

  // DataSet como os resultados do banco.
  private DataSet m_results;

  /**
   * @return Returns the custFullNameTextSrc.
   */
  public String getCustFullNameTextSrc()
  {
    return m_custFullNameTextSrc;
  }

  /**
   * @param custFullNameTextSrc_ The custFullNameTextSrc to set.
   */
  public void setCustFullNameTextSrc( String custFullNameTextSrc_ )
  {
    m_custFullNameTextSrc = custFullNameTextSrc_;
  }

  /**
   * @return Returns clickedSearch.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  /**
   * @param clickedSearch_ Field clickedSearch to be setted.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /**
   * @return Returns isFromSearch.
   */
  public boolean isFromSearch()
  {
    return m_isFromSearch;
  }

  /**
   * @param isFromSearch_ Field isFromSearch to be setted.
   */
  public void setFromSearch( boolean isFromSearch_ )
  {
    m_isFromSearch = isFromSearch_;
  }
}