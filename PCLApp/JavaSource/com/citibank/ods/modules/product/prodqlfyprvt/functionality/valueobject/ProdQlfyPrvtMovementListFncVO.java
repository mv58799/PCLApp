/*
 * Created on Mar 17, 2007
 *
 */
package com.citibank.ods.modules.product.prodqlfyprvt.functionality.valueobject;

/**
 * @author fernando.salgado
 *  
 */
public class ProdQlfyPrvtMovementListFncVO extends BaseProdQlfyPrvtListFncVO
{
  /**
   * Constante de descrição do campo do último usuario a atualizar
   */
  public static final String C_LAST_UPD_USER_ID_DESCRIPTION = "Nome do Usuário";

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