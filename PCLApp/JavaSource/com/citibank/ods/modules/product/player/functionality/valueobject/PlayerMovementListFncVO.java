/*
 * Created on Mar 29, 2007
 *
 */
package com.citibank.ods.modules.product.player.functionality.valueobject;

/**
 * @author atilio.l.araujo
 *  
 */
public class PlayerMovementListFncVO extends BasePlayerListFncVO
{

  /**
   * Constante de descrição do campo do último usuario a atualizar
   */
  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Nome do Usuário";

  /**
   * Codigo do Usuario que efetuou a ultima atualizacao no registro.
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