/*
 * Created on Mar 2, 2007
 *
 */
package com.citibank.ods.modules.client.officercmpl.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
/**
 * @author bruno.zanetti
 *  
 */
public abstract class BaseOfficerCmplListFncVO extends BaseODSFncVO
{
  /*
   * Descrição dos campos
   */
  public static final String C_OFFCR_NBR_DESCRIPTION = "Número do Banker";

  public static final String C_OFFCR_TYPE_CODE_DESCRIPTION = "Tipo de Banker";

  public static final String C_OFFCR_INTL_DESCRIPTION = "Número Internacional do Banker";

  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Usuário última atualização";

  public static final String C_OFFCR_REF_DATE = "Data de referência";

  private BigInteger m_offcrIntnlNbrSrc = null;

  private BigInteger m_offcrNbrSrc = null;

  private String m_offcrTypeCodeSrc = null;

  private DataSet m_offcrTypeCodeDomain;

  private DataSet m_results;

  private BigInteger m_selectedOffcrNbr = null;

  private String m_offcrTextSrc;

  private String m_clickedSearch;

  private boolean isFromSearch;

  /**
   * @return Returns the clickedSearch.
   */
  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  /**
   * @param clickedSearch_ The clickedSearch to set.
   */
  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
  }

  /**
   * @return Returns the m_offcrIntnlNbrSrc.
   */
  public BigInteger getOffcrIntnlNbrSrc()
  {
    return m_offcrIntnlNbrSrc;
  }

  /**
   * @param intnlNbr The m_offcrIntnlNbrSrc to set.
   */
  public void setOffcrIntnlNbrSrc( BigInteger intnlNbr )
  {
    m_offcrIntnlNbrSrc = intnlNbr;
  }

  /**
   * @return Returns the m_offcrNbrSrc.
   */
  public BigInteger getOffcrNbrSrc()
  {
    return m_offcrNbrSrc;
  }

  /**
   * @param nbr The m_offcrNbrSrc to set.
   */
  public void setOffcrNbrSrc( BigInteger nbr )
  {
    m_offcrNbrSrc = nbr;
  }

  /**
   * @return Returns the m_offcrTypeCodeSrc.
   */
  public String getOffcrTypeCodeSrc()
  {
    return m_offcrTypeCodeSrc;
  }

  /**
   * @param typeCode The m_offcrTypeCodeSrc to set.
   */
  public void setOffcrTypeCodeSrc( String typeCode )
  {
    m_offcrTypeCodeSrc = typeCode;
  }

  /**
   * @return Returns the m_offcrTypeCodeDomain.
   */
  public DataSet getOffcrTypeCodeDomain()
  {
    return m_offcrTypeCodeDomain;
  }

  /**
   * @param typeCodeDomain The m_offcrTypeCodeDomain to set.
   */
  public void setOffcrTypeCodeDomain( DataSet typeCodeDomain )
  {
    m_offcrTypeCodeDomain = typeCodeDomain;
  }

  /**
   * @return Returns the m_results.
   */
  public DataSet getResults()
  {
    return m_results;
  }

  /**
   * @param m_results The m_results to set.
   */
  public void setResults( DataSet m_results )
  {
    this.m_results = m_results;
  }

  /**
   * @return Returns the m_selectedOffcrNbr.
   */
  public BigInteger getSelectedOffcrNbr()
  {
    return m_selectedOffcrNbr;
  }

  /**
   * @param offcrNbr The m_selectedOffcrNbr to set.
   */
  public void setSelectedOffcrNbr( BigInteger offcrNbr )
  {
    m_selectedOffcrNbr = offcrNbr;
  }

  public String getOffcrTextSrc()
  {
    return m_offcrTextSrc;
  }

  public void setOffcrTextSrc( String offcrTextSrc_ )
  {
    m_offcrTextSrc = offcrTextSrc_;
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