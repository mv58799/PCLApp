package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author m.nakamura
 * 
 * Agregador dos campos necess�rios para realizar a consulta em lista de mem�ria
 * de risco.
 */
public class MrDocPrvtMovListFncVO extends BaseMrDocPrvtListFncVO
{
  // Usu�rio da �ltima atualiza��o
  private String m_lastUpdUserId = "";

  // Data da �ltima atualiza��o
  private Date m_lastUpdDate = null;

  //N�mero do documento MR
  private BigInteger m_mrDocCode = null;
  
  //Constante do n�mero do documento MR
  public static final String C_MR_DOC_CODE = "C�digo do Documento MR";
  
  //Constante do campo usu�rio da �ltima atualiza��o
  public static final String C_LAST_UPD_USER_ID = "Usu�rio da �ltima Atualiza��o";

  /**
   * Seta o usu�rio da �ltima atualiza��o
   * 
   * @param lastUpdUserId_ - O usu�rio da �ltima atualiza��o
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * Recupera o usu�rio da �ltima atualiza��o
   * 
   * @return String - Retorna o usu�rio da �ltima atualiza��o
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * Recupera a data da �ltima atualiza��o
   * 
   * @return Date - Retorna a data da �ltima atualiza��o
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * Seta a data da �ltima atualiza��o
   * 
   * @param lastUpdDate_ - A data da �ltima atualiza��o
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Retorna o n�mero do documento MR.
   */
  public BigInteger getMrDocCode()
  {
    return m_mrDocCode;
  }

  /**
   * @param mrDocCode_.Seta o n�mero do documento MR.
   */
  public void setMrDocCode( BigInteger mrDocCode_ )
  {
    m_mrDocCode = mrDocCode_;
  }
}