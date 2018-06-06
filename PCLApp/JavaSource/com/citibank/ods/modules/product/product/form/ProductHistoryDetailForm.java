package com.citibank.ods.modules.product.product.form;

import com.citibank.ods.modules.product.product.form.BaseProductDetailForm;

/**
*@author leonardo.nakada
*
*/

public class ProductHistoryDetailForm extends BaseProductDetailForm
{

  // Data e Hora que o Usuario aprovou o Registro Cadastrado
  private String m_lastAuthDate = "";

  // Codigo do Usuario que Aprovou o Cadastro do Registro.
  private String m_lastAuthUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou aguardando aprovacao
  private String m_recStatCode = "";

  // TODO: Coloque seu comentário aqui.
  private String m_prodRefDate = "";

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
  * @return Returns m_prodRefDate.
  */
  public String getProdRefDate()
  {
    return m_prodRefDate;
  }

  /**
  * @param prodRefDate_ Field m_prodRefDate to be setted.
  */
  public void setProdRefDate( String prodRefDate_ )
  {
    m_prodRefDate = prodRefDate_;
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
}
