/*
 * Created on 09/12/2008
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.citibank.ods.modules.client.er.functionality.valueobject;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.entity.pl.TplErHistEntity;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class ERHistoryDetailFncVO extends BaseERDetailFncVO
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
  
  public ERHistoryDetailFncVO()
  {
	baseTplErEntity = new TplErHistEntity();
  }
  
}
