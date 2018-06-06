package com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject;

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
 *      com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject;
 * @version 1.0
 * @author l.braga,28/03/2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BasePortfolioPrvtListFncVO extends BaseFncVO
{
  //Código da Carteira
  private String m_portfCode = "";

  // Descricao da Carteira
  private String m_portfNameText = "";

  // Officer da carteira
  private BigInteger m_portfOffcrNbr = null;

  // nome do officer
  private String m_offcrNameText = "";

  private String m_clickedSearch;

  private boolean isFromSearch;

  // DataSet como os resultados do banco.
  private DataSet m_results;

  public String getPortfCode()
  {
    return m_portfCode;
  }

  public void setPortfCode( String portfCode_ )
  {
    m_portfCode = portfCode_;
  }

  /**
   * @return Returns offcrNameText.
   */
  public String getOffcrNameText()
  {
    return m_offcrNameText;
  }

  /**
   * @param offcrNameText Field offcrNameText to be setted.
   */
  public void setOffcrNameText( String offcrNameText_ )
  {
    m_offcrNameText = offcrNameText_;
  }

  /**
   * @return Returns portfNameText.
   */
  public String getPortfNameText()
  {
    return m_portfNameText;
  }

  /**
   * @param portfNameText Field portfNameText to be setted.
   */
  public void setPortfNameText( String portfNameText )
  {
    m_portfNameText = portfNameText;
  }

  /**
   * @return Returns portfOffcrNbr.
   */
  public BigInteger getPortfOffcrNbr()
  {
    return m_portfOffcrNbr;
  }

  /**
   * @param portfOffcrNbr Field portfOffcrNbr to be setted.
   */
  public void setPortfOffcrNbr( BigInteger portfOffcrNbr )
  {
    m_portfOffcrNbr = portfOffcrNbr;
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

  public String getClickedSearch()
  {
    return m_clickedSearch;
  }

  public void setClickedSearch( String clickedSearch_ )
  {
    m_clickedSearch = clickedSearch_;
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