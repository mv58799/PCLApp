package com.citibank.ods.modules.product.prodriskcatprvt.form;

import com.citibank.ods.modules.product.prodriskcatprvt.form.BaseProdRiskCatPrvtDetailForm;

/**
*@author angelica.almeida
*
*/

public class ProdRiskCatPrvtHistoryDetailForm extends BaseProdRiskCatPrvtDetailForm
{

  // Data e Hora que o usuario aprovou o registro cadastrado
  private String m_lastAuthDate = "";

  // Codigo do usuario (SOE ID) que aprovou o cadastro do registro
  private String m_lastAuthUserId = "";

  // Status Registro - Identifica se o registro esta ativo, inativo ou aguardando aprovacao
  private String m_recStatCode = "";
  
  // 
  private String m_prodRiskCatRefDate = "";

  
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
   * @return Returns m_prodRiskCatRefDate.
   */
  public String getProdRiskCatRefDate()
  {
    return m_prodRiskCatRefDate;
  }
  
  /**
   * @param prodRiskCatRefDate_ Field m_prodRiskCatRefDate to be setted.
   */
  public void setProdRiskCatRefDate( String prodRiskCatRefDate_ )
  {
    m_prodRiskCatRefDate = prodRiskCatRefDate_;
  }

}
