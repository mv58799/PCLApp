package com.citibank.ods.modules.client.relationeg.form;

import com.citibank.ods.modules.client.relationeg.form.BaseRelationEgDetailForm;

/**
*@author leonardo.nakada
*
*/

public class RelationEgMovementDetailForm extends BaseRelationEgDetailForm
{

  // TODO: Coloque seu comentário aqui.
  private String m_opernCode = "";

  /**
  * @return Returns m_opernCode.
  */
  public String getOpernCode()
  {
    return m_opernCode;
  }

  /**
  * @param opernCode_ Field m_opernCode to be setted.
  */
  public void setOpernCode( String opernCode_ )
  {
    m_opernCode = opernCode_;
  }
}
