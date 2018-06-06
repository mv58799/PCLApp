package com.citibank.ods.modules.product.broker.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

/**
 * @author Hamilton Matos
 */

public class BaseBrokerListFncVO extends BaseODSFncVO
{
  public static final String C_BKR_CNPJ_NBR_DESCRIPTION = "CNPJ";

  public static final String C_BKR_NAME_TEXT_DESCRIPTION = "Razão Social";

  private String m_bkrCnpjNbrSrc;

  private String m_bkrNameTextSrc;

  private DataSet m_results;

  private String m_selectedBkrCnpjNbr;

  /**
   * @return Returns bkrCnpjNbrSrc.
   */
  public String getBkrCnpjNbrSrc()
  {
    return m_bkrCnpjNbrSrc;
  }

  /**
   * @param bkrCnpjNbrSrc_ Field bkrCnpjNbrSrc to be setted.
   */
  public void setBkrCnpjNbrSrc( String bkrCnpjNbrSrc_ )
  {
    m_bkrCnpjNbrSrc = bkrCnpjNbrSrc_;
  }

  /**
   * @return Returns bkrNameTextSrc.
   */
  public String getBkrNameTextSrc()
  {
    return m_bkrNameTextSrc;
  }

  /**
   * @param bkrNameTextSrc_ Field bkrNameTextSrc to be setted.
   */
  public void setBkrNameTextSrc( String bkrNameTextSrc_ )
  {
    m_bkrNameTextSrc = bkrNameTextSrc_;
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
   * @return Returns selectedBkrCnpjNbr.
   */
  public String getSelectedBkrCnpjNbr()
  {
    return m_selectedBkrCnpjNbr;
  }

  /**
   * @param selectedBkrCnpjNbr_ Field selectedBkrCnpjNbr to be setted.
   */
  public void setSelectedBkrCnpjNbr( String selectedBkrCnpjNbr_ )
  {
    m_selectedBkrCnpjNbr = selectedBkrCnpjNbr_;
  }
}