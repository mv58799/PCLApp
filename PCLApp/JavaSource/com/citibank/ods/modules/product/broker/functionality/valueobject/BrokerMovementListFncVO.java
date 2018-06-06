package com.citibank.ods.modules.product.broker.functionality.valueobject;

/**
 * @author hamilton.matos
 *  
 */
public class BrokerMovementListFncVO extends BaseBrokerListFncVO
{

  /**
   * Descrição do campo do último usuário a atualizar corretora
   */
  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Nome do Usuário";

  /**
   * Código do usuário que efetuou a última atualização no registro.
   */
  private String m_lastUpdUserIdSrc;

  /**
   * @return Returns lastUpdUserIdSrc.
   */
  public String getLastUpdUserIdSrc()
  {
    return m_lastUpdUserIdSrc;
  }

  /**
   * @param lastUpdUserIdSrc_ Field lastUpdUserIdSrc to be setted.
   */
  public void setLastUpdUserIdSrc( String lastUpdUserIdSrc_ )
  {
    m_lastUpdUserIdSrc = lastUpdUserIdSrc_;
  }
}