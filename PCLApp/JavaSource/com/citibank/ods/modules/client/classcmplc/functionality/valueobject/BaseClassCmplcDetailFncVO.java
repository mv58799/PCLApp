package com.citibank.ods.modules.client.classcmplc.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplClassCmplcEntity;

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

public class BaseClassCmplcDetailFncVO extends BaseODSFncVO
{

  protected BaseTplClassCmplcEntity m_tplClassCmplcEntity;

  public static final String C_CLASS_CMPLC_CODE_DESCRIPTION = "Código da Classificação Compliance";

  public static final String C_CLASS_CMPLC_TEXT_DESCRIPTION = "Descrição da Classificação Compliance";

  public static final String C_SENS_IND_DESCRIPTION = "Indicador de Sensitividade";
  
  /**
   * @return Returns sensIndDomain.
   */
  public DataSet getSensIndDomain()
  {
    return sensIndDomain;
  }
  /**
   * @param sensIndDomain_ Field sensIndDomain to be setted.
   */
  public void setSensIndDomain( DataSet sensIndDomain_ )
  {
    sensIndDomain = sensIndDomain_;
  }
  public DataSet sensIndDomain = null;
  /**
   * @return Returns tplOfficerCmplEntity.
   */
  public BaseTplClassCmplcEntity getBaseTplClassCmplcEntity()
  {
    return m_tplClassCmplcEntity;
  }

  /**
   * @param tplOfficerCmplEntity_ Field tplOfficerCmplEntity to be setted.
   */
  public void setBaseTplClassCmplcEntity(
                                         BaseTplClassCmplcEntity m_tplClassCmplcEntity_ )
  {
    m_tplClassCmplcEntity = m_tplClassCmplcEntity_;
  }
}