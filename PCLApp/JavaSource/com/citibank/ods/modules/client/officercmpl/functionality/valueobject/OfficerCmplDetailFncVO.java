package com.citibank.ods.modules.client.officercmpl.functionality.valueobject;

import com.citibank.ods.entity.pl.TplOfficerCmplEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.officercmpl.functionality.valueobject; 
 *@version 1.0
 *@author fabio.luppi.gil,Mar 5, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class OfficerCmplDetailFncVO extends BaseOfficerCmplDetailFncVO
{
  //	Indicador de navegação de busca
  private boolean m_isFromSearch = false;

  public OfficerCmplDetailFncVO()
  {
    m_tplOfficerCmplEntity = new TplOfficerCmplEntity();
  }

  
  /**
   * @return Returns the isFromSearch.
   */
  public boolean isFromSearch()
  {
    return m_isFromSearch;
  }
  /**
   * @param isFromSearch_ The isFromSearch to set.
   */
  public void setFromSearch( boolean isFromSearch_ )
  {
    m_isFromSearch = isFromSearch_;
  }
}