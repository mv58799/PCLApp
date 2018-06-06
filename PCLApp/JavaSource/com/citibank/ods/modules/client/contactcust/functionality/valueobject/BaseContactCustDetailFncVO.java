package com.citibank.ods.modules.client.contactcust.functionality.valueobject;

import com.citibank.ods.common.functionality.valueobject.BaseODSFncVO;
import com.citibank.ods.entity.pl.BaseTplContactCustEntity;

/**
 * @author hamilton.matos
 */

public class BaseContactCustDetailFncVO extends BaseODSFncVO
{

  /**
   * Entity
   */
  protected BaseTplContactCustEntity m_tplContactCustEntity;

  // Nome completo do cliente
  private String m_custFullNameText;

  /**
   * Constante do numero do contato
   */
  public static final String C_CTC_NBR_DESCRIPTION = "Numero do Contato";

  /**
   * Constante do nome do contato
   */
  public static final String C_FULL_NAME_TEXT_DESCRIPTION = "Nome do Contato";

  /**
   * Constante do codigo ddd
   */
  public static final String C_PHONE_DDD_CODE_DESCRIPTION = "Código DDD";

  /**
   * Constante do numero do telefone
   */
  public static final String C_PHONE_NBR_DESCRIPTION = "Numero do Telefone";

  /**
   * Constante do ramal
   */
  public static final String C_PHONE_EXTN_NBR_DESCRIPTION = "Ramal ";

  /**
   * @return Returns tplContactCustEntity.
   */
  public BaseTplContactCustEntity getTplContactCustEntity()
  {
    return m_tplContactCustEntity;
  }

  /**
   * @param tplContactCustEntity_ Field tplContactCustEntity to be setted.
   */
  public void setTplContactCustEntity(
                                      BaseTplContactCustEntity tplContactCustEntity_ )
  {
    m_tplContactCustEntity = tplContactCustEntity_;
  }

  /**
   * @return Returns custFullNameText.
   */
  public String getCustFullNameText()
  {
    return m_custFullNameText;
  }

  /**
   * @param custFullNameText_ Field custFullNameText to be setted.
   */
  public void setCustFullNameText( String custFullNameText_ )
  {
    m_custFullNameText = custFullNameText_;
  }
}