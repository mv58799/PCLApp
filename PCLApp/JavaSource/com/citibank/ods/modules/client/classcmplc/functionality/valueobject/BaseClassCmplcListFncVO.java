package com.citibank.ods.modules.client.classcmplc.functionality.valueobject;

import java.math.BigInteger;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.classcmplc.functionality.valueobject;
 * @version 1.0
 * @author gerson.a.rodrigues,Mar 13, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public class BaseClassCmplcListFncVO extends BaseODSFncVO
{

  public static final String C_CLASS_CMPLC_CODE_SRC_DESCRIPTION = "Código de Classificação Compliance";

  public static final String C_CLASS_CMPLC_TEXT_SRC_DESCRIPTION = "Descrição de Classificação Compliance";

  private BigInteger m_classCmplcCodeSrc;

  private String m_classCmplcTextSrc;
  
  private String m_sensIndSrc;
  
  private String m_selectedClassCmplcCode;

  private DataSet m_sensIndDomain;
  
  private DataSet m_results;

  /**
   * @return Returns classCmplcCodeSrc.
   */
  public BigInteger getClassCmplcCodeSrc()
  {
    return m_classCmplcCodeSrc;
  }

  /**
   * @param classCmplcCodeSrc_ Field classCmplcCodeSrc to be setted.
   */
  public void setClassCmplcCodeSrc( BigInteger classCmplcCodeSrc_ )
  {
    m_classCmplcCodeSrc = classCmplcCodeSrc_;
  }

  /**
   * @return Returns classCmplcTextSrc.
   */
  public String getClassCmplcTextSrc()
  {
    return m_classCmplcTextSrc;
  }

  /**
   * @param classCmplcTextSrc_ Field classCmplcTextSrc to be setted.
   */
  public void setClassCmplcTextSrc( String classCmplcTextSrc_ )
  {
    m_classCmplcTextSrc = classCmplcTextSrc_;
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
   * @return Returns selectedClassCmplcCode.
   */
  public String getSelectedClassCmplcCode()
  {
    return m_selectedClassCmplcCode;
  }
  /**
   * @param selectedClassCmplcCode_ Field selectedClassCmplcCode to be setted.
   */
  public void setSelectedClassCmplcCode( String selectedClassCmplcCode_ )
  {
    m_selectedClassCmplcCode = selectedClassCmplcCode_;
  }
  /**
   * @return Returns sensIndDomain.
   */
  public DataSet getSensIndDomain()
  {
    return m_sensIndDomain;
  }
  /**
   * @param sensIndDomain_ Field sensIndDomain to be setted.
   */
  public void setSensIndDomain( DataSet sensIndDomain_ )
  {
    m_sensIndDomain = sensIndDomain_;
  }
  /**
   * @return Returns sensIndSrc.
   */
  public String getSensIndSrc()
  {
    return m_sensIndSrc;
  }
  /**
   * @param sensIndSrc_ Field sensIndSrc to be setted.
   */
  public void setSensIndSrc( String sensIndSrc_ )
  {
    m_sensIndSrc = sensIndSrc_;
  }
}