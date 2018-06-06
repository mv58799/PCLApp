package com.citibank.ods.modules.client.erem.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplErEmHistEntity;

//
//©2002-2007 Accenture. All rights reserved. 
//
/**
 * [Class description]
 * 
 *@see package com.citibank.ods.modules.client.erem.functionality.valueobject; 
 *@version 1.0
 *@author gerson.a.rodrigues,Apr 9, 2007
 *
 *<PRE>
 *<U>Updated by:</U>
 *<U>Description:</U>
 *</PRE>
 */

public class EREMHistoryDetailFncVO extends BaseEREMDetailFncVO
{  
  private DataSet historyResults;
  
  /**
   * @return Returns historyResults.
   */
  public DataSet getHistoryResults()
  {
    return historyResults;
  }
  /**
   * @param historyResults_ Field historyResults to be setted.
   */
  public void setHistoryResults( DataSet historyResults_ )
  {
    historyResults = historyResults_;
  }
  
  public EREMHistoryDetailFncVO()
  {
    m_baseTplErEmEntity = new TplErEmHistEntity();
  }
  
}
