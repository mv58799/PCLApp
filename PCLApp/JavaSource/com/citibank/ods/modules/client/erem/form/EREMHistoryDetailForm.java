package com.citibank.ods.modules.client.erem.form;

import com.citibank.ods.common.dataset.DataSet;
import com.citibank.ods.modules.client.erem.form.BaseEREMDetailForm;

/**
 * @author gerson.a.rodrigues
 *  
 */

public class EREMHistoryDetailForm extends BaseEREMDetailForm
{

  // Status Registro - Identifica se o registro esta ativo, inativo ou
  // aguardando aprovacao
  private String m_recStatCode = "";

  private String m_EREMRefDate = "";
  
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
   * @return Returns m_erEmRefDate.
   */
  public String getEREMRefDate()
  {
    return m_EREMRefDate;
  }

  /**
   * @param erEmRefDate_ Field m_erEmRefDate to be setted.
   */
  public void setEREMRefDate( String EREMRefDate_ )
  {
    m_EREMRefDate = EREMRefDate_;
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
   * @param erEmRefDate_ Field m_erEmRefDate to be setted.
   */
  public void setSelectedEREMRefDate( String EREMRefDate_ )
  {
    setEREMRefDate( EREMRefDate_ );
  }
}