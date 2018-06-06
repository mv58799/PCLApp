package com.citibank.ods.modules.product.broker.functionality.valueobject;

/**
 * @author hamilton.matos
 *  
 */
public class BrokerMovementListFncVO extends BaseBrokerListFncVO
{

  /**
   * Descri��o do campo do �ltimo usu�rio a atualizar corretora
   */
  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Nome do Usu�rio";

  /**
   * C�digo do usu�rio que efetuou a �ltima atualiza��o no registro.
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