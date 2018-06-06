package com.citibank.ods.modules.client.erem.form;

import com.citibank.ods.modules.client.erem.form.BaseEREMDetailForm;

/**
*@author gerson.a.rodrigues
*
*/

public class EREMDetailForm extends BaseEREMDetailForm
{

  // Status Registro - Identifica se o registro esta ativo, inativo ou aguardando aprovacao
  private String m_recStatCode = "";

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
}
