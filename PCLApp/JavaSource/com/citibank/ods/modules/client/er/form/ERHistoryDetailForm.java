/*
 * Created on 09/12/2008
 *
 */
package com.citibank.ods.modules.client.er.form;

import com.citibank.ods.common.dataset.DataSet;

/**
 * @author lfabiano
 * @since 09/12/2008
 */
public class ERHistoryDetailForm extends BaseERDetailForm
{

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCode = "";

  private String m_ErRefDate = "";
  
  private DataSet m_historyResults = null;  

  
  /**
   * @return Returns historyResults.
   */
  public DataSet getHistoryResults()
  {
	return m_historyResults;
  }
  /**
   * @param historyResults_ Field historyResults to be setted.
   */
  public void setHistoryResults( DataSet historyResults_ )
  {
	m_historyResults = historyResults_;
  }
  
  /**
   * @return Returns m_erRefDate.
   */
  public String getErRefDate()
  {
	return m_ErRefDate;
  }

  /**
   * @param erRefDate_ Field m_erRefDate to be setted.
   */
  public void setErRefDate( String ErRefDate_ )
  {
	m_ErRefDate = ErRefDate_;
  }

  /**
   * @return Returns m_recStatCode.
   */
  public String getRecStatCode()
  {
	return m_recStatCode;
  }

  /**
   * @param recStatCode_ Field m_recStatCode to be setted.
   */
  public void setRecStatCode( String recStatCode_ )
  {
	m_recStatCode = recStatCode_;
  }

  public String getSelectedEREMRefDate()
  {
	return null;
  }

  /**
   * @param erRefDate_ Field m_erRefDate to be setted.
   */
  public void setSelectedEREMRefDate( String ErRefDate_ )
  {
	setErRefDate( ErRefDate_ );
  }
}