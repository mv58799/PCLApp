package com.citibank.ods.modules.client.mrdocprvt.functionality.valueobject;

import java.math.BigInteger;
import java.util.Date;

/**
 * @author m.nakamura
 * 
 * Agregador dos campos necessários para realizar a consulta em lista de memória
 * de risco.
 */
public class MrDocPrvtMovListFncVO extends BaseMrDocPrvtListFncVO
{
  // Usuário da última atualização
  private String m_lastUpdUserId = "";

  // Data da última atualização
  private Date m_lastUpdDate = null;

  //Número do documento MR
  private BigInteger m_mrDocCode = null;
  
  //Constante do número do documento MR
  public static final String C_MR_DOC_CODE = "Código do Documento MR";
  
  //Constante do campo usuário da última atualização
  public static final String C_LAST_UPD_USER_ID = "Usuário da Última Atualização";

  /**
   * Seta o usuário da última atualização
   * 
   * @param lastUpdUserId_ - O usuário da última atualização
   */
  public void setLastUpdUserId( String lastUpdUserId_ )
  {
    m_lastUpdUserId = lastUpdUserId_;
  }

  /**
   * Recupera o usuário da última atualização
   * 
   * @return String - Retorna o usuário da última atualização
   */
  public String getLastUpdUserId()
  {
    return m_lastUpdUserId;
  }

  /**
   * Recupera a data da última atualização
   * 
   * @return Date - Retorna a data da última atualização
   */
  public Date getLastUpdDate()
  {
    return m_lastUpdDate;
  }

  /**
   * Seta a data da última atualização
   * 
   * @param lastUpdDate_ - A data da última atualização
   */
  public void setLastUpdDate( Date lastUpdDate_ )
  {
    m_lastUpdDate = lastUpdDate_;
  }

  /**
   * @return Retorna o número do documento MR.
   */
  public BigInteger getMrDocCode()
  {
    return m_mrDocCode;
  }

  /**
   * @param mrDocCode_.Seta o número do documento MR.
   */
  public void setMrDocCode( BigInteger mrDocCode_ )
  {
    m_mrDocCode = mrDocCode_;
  }
}