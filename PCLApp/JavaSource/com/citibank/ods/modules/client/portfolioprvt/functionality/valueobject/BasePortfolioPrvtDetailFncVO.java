package com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseFncVO;
import com.citibank.ods.entity.pl.BaseTplPortfolioPrvtEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.portfolioprvt.functionality.valueobject; 
 *@version 1.0
 *@author l.braga,28/03/2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class BasePortfolioPrvtDetailFncVO extends BaseFncVO
{
  public BaseTplPortfolioPrvtEntity m_portfolioPrvtEntity ;
  
  private String m_offNameText;

  public static final String C_PORTF_NAME_TEXT_DESCRIPTION = "Descrição Carteira";
  public static final String C_PORTF_OFFCR_NBR_DESCRIPTION = "Número do Officer";
  public static final String C_OFFCR_NAME_TEXT_DESCRIPTION = "Nome do Officer";




  /**
   * @return Returns offNameText.
   */
  public String getOffNameText()
  {
    return m_offNameText;
  }
  /**
   * @param offNameText_ Field offNameText to be setted.
   */
  public void setOffNameText( String offNameText_ )
  {
    m_offNameText = offNameText_;
  }
  /**
   * @return Returns tplCustomerPrvtEntity.
   */
  public BaseTplPortfolioPrvtEntity getTplPortfolioPrvtEntity()
  {
    return m_portfolioPrvtEntity;
  }
  /**
   * @param tplCustomerPrvtEntity_ Field tplCustomerPrvtEntity to be setted.
   */
  public void setTplPortfolioPrvtEntity(
                                        BaseTplPortfolioPrvtEntity portfolioPrvtEntity_ )
  {
    m_portfolioPrvtEntity = portfolioPrvtEntity_;
  }
}
