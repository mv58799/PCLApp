/*
 * Created on Apr 15, 2007
 *
 * 
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.citibank.ods.modules.client.relationeg.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author leonardo.nakada
 * 
 * 
 * Preferences - Java - Code Style - Code Templates
 */
public class BaseRelationEgListFncVO extends BaseODSFncVO
{
  public static final String C_RELTN_NBR_DESCRIPTION = "Número de Relacionamento";

  public static final String C_EG_NBR_DESCRIPTION = "EG";

  private String egNbr;

  private BigInteger reltnNbr;

  private DataSet m_results;

  private String clickedSearch;

  //Número do ER
  private String m_erNbrSrc = "";

  //Combo com os ER da base
  private DataSet m_erNbrDomain;

  /**
   * @return Returns the egNbr.
   */
  public String getEgNbr()
  {
    return egNbr;
  }

  /**
   * @param egNbr_ The egNbr to set.
   */
  public void setEgNbr( String egNbr_ )
  {
    egNbr = egNbr_;
  }

  /**
   * @return Returns the reltnNbr.
   */
  public BigInteger getReltnNbr()
  {
    return reltnNbr;
  }

  /**
   * @param reltnNbr_ The reltnNbr to set.
   */
  public void setReltnNbr( BigInteger reltnNbr_ )
  {
    reltnNbr = reltnNbr_;
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

  public DataSet getErNbrDomain()
  {
    return m_erNbrDomain;
  }

  public void setErNbrDomain( DataSet erNbrDomain_ )
  {
    m_erNbrDomain = erNbrDomain_;
  }

  public String getErNbrSrc()
  {
    return m_erNbrSrc;
  }

  public void setErNbrSrc( String erNbrSrc_ )
  {
    m_erNbrSrc = erNbrSrc_;
  }
}