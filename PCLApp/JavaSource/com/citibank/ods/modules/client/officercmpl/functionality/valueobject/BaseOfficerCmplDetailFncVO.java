package com.citibank.ods.modules.client.officercmpl.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplOfficerCmplEntity;
//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 * @see package
 *      com.citibank.ods.modules.client.officercmpl.functionality.valueobject;
 * @version 1.0
 * @author fabio.luppi.gil,Mar 5, 2007
 * 
 * <PRE>
 * 
 * <U>Updated by: </U> <U>Description: </U>
 * 
 * </PRE>
 */

public abstract class BaseOfficerCmplDetailFncVO extends BaseODSFncVO
{
  /*
   * Descrição dos campos
   */
  public static final String C_OFFCR_NBR_DESCRIPTION = "Número do Banker";

  public static final String C_OFFCR_TYPE_CODE_DESCRIPTION = "Tipo de Banker";

  public static final String C_OFFCR_INTL_DESCRIPTION = "Número Internacional do Banker";
  
  public static final String C_OFFCR_NAME_TEXT = "Nome do Officer";
  
  protected BaseTplOfficerCmplEntity m_tplOfficerCmplEntity;
  
  private String m_OffcrNameText;

  private String m_clickedSearch;

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
   * @return Returns tplOfficerCmplEntity.
   */
  public BaseTplOfficerCmplEntity getTplOfficerCmplEntity()
  {
    return m_tplOfficerCmplEntity;
  }

  /**
   * @param tplOfficerCmplEntity_ Field tplOfficerCmplEntity to be setted.
   */
  public void setTplOfficerCmplEntity(
                                      BaseTplOfficerCmplEntity tplOfficerCmplEntity_ )
  {
    m_tplOfficerCmplEntity = tplOfficerCmplEntity_;
  }

  private DataSet m_offcrTypeCodeDomain;

  /**
   * @return Returns offcrTypeCodeDomain.
   */
  public DataSet getOffcrTypeCodeDomain()
  {
    return m_offcrTypeCodeDomain;
  }

  /**
   * @param offcrTypeCodeDomain_ Field offcrTypeCodeDomain to be setted.
   */
  public void setOffcrTypeCodeDomain( DataSet offcrTypeCodeDomain_ )
  {
    m_offcrTypeCodeDomain = offcrTypeCodeDomain_;
  }
  /**
   * @return Returns offcrNameText.
   */
  public String getOffcrNameText()
  {
    return m_OffcrNameText;
  }
  /**
   * @param offcrNameText_ Field offcrNameText to be setted.
   */
  public void setOffcrNameText( String offcrNameText_ )
  {
    m_OffcrNameText = offcrNameText_;
  }
}