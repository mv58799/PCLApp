package com.citibank.ods.modules.client.relationeg.form;

import com.citibank.ods.modules.client.relationeg.form.BaseRelationEgDetailForm;

/**
*@author leonardo.nakada
*
*/

public class RelationEgHistoryDetailForm extends BaseRelationEgDetailForm
{

  // Data e Hora que o usuario aprovou o registro cadastrado
  private String m_lastAuthDate = "";

  // Codigo do usuario (SOE ID) que aprovou o cadastro do registro
  private String m_lastAuthUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou aguardando aprovacao
  private String m_recStatCode = "";

  // TODO: Coloque seu comentário aqui.
  private String m_reltnEgRefDate = "";

  /**
  * @return Returns m_lastAuthDate.
  */
  public String getLastAuthDate()
  {
    return m_lastAuthDate;
  }

  /**
  * @param lastAuthDate_ Field m_lastAuthDate to be setted.
  */
  public void setLastAuthDate( String lastAuthDate_ )
  {
    m_lastAuthDate = lastAuthDate_;
  }

  /**
  * @return Returns m_lastAuthUserId.
  */
  public String getLastAuthUserId()
  {
    return m_lastAuthUserId;
  }

  /**
  * @param lastAuthUserId_ Field m_lastAuthUserId to be setted.
  */
  public void setLastAuthUserId( String lastAuthUserId_ )
  {
    m_lastAuthUserId = lastAuthUserId_;
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

  /**
  * @return Returns m_reltnEgRefDate.
  */
  public String getReltnEgRefDate()
  {
    return m_reltnEgRefDate;
  }

  /**
  * @param reltnEgRefDate_ Field m_reltnEgRefDate to be setted.
  */
  public void setReltnEgRefDate( String reltnEgRefDate_ )
  {
    m_reltnEgRefDate = reltnEgRefDate_;
  }
}
